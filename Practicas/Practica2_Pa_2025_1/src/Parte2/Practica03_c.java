package Parte2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import Libreria.Archivotxt;
import Libreria.Librerias;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;

public class Practica03_c extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ListaInsumos listainsumo;
    private ListaCategorias listacategorias;
    private Archivotxt archivocategorias;
    private Archivotxt archivoinsumos;
    private Librerias libreria;
    private JMenuBar menuBarPadre;
    
    private JList<Categoria> listaCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;
    private JTable TareaProductos;
    private JLabel Limagen;
    
    private DefaultListModel<Categoria> modelocategoria;
    private DefaultTableModel modeloinsumos;

    public Practica03_c(JMenuBar menuBar) {
        super("Gestión de Insumos", true, true, true, true); 
        this.menuBarPadre = menuBar;
        this.libreria = new Librerias();
        this.modelocategoria = new DefaultListModel<>(); 
        this.inicializarcategorias();
        this.initComponents();
        this.libreria.menuspadre(menuBarPadre, false);
        
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                libreria.menuspadre(menuBarPadre, true);
            }
        });
    }

    private void inicializarcategorias() {
        this.archivocategorias = new Archivotxt("Categoria.txt");
        this.archivoinsumos = new Archivotxt("Insumos.txt");
        this.listacategorias = new ListaCategorias();
        this.listainsumo = new ListaInsumos();

        if (this.archivocategorias.existe()) 
            this.listacategorias.cargarCategorias(this.archivocategorias.cargar());
        if (this.archivoinsumos.existe()) 
            this.listainsumo.cargarInsumo(this.archivoinsumos.cargar());

        this.modelocategoria = this.listacategorias.generarModeloCategorias();
        this.modeloinsumos = this.listainsumo.getmodelo(this.listacategorias);
    }

    private void initComponents() {
        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        panelFormulario.add(labelCategoria);

        JScrollPane scrollPane_jlist = new JScrollPane();
        scrollPane_jlist.setBounds(91, 61, 157, 42);
        panelFormulario.add(scrollPane_jlist);

        listaCategoria = new JList<>(modelocategoria); 
        scrollPane_jlist.setViewportView(listaCategoria);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        Tid = new JTextField();
        Tid.setEditable(false);
        Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        Tinsumo = new JTextField();
        Tinsumo.setEditable(false);
        Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 104, 111, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(153, 104, 111, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(274, 104, 79, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);
        
        TareaProductos = new JTable(modeloinsumos);
        scrollPane.setViewportView(TareaProductos);

        Limagen = new JLabel();
        Limagen.setBounds(274, 9, 86, 80);
        panelFormulario.add(Limagen);

        TareaProductos.getSelectionModel().addListSelectionListener(e -> actualizarimagen());
    }

    public void actualizarimagen() {
        int fila = TareaProductos.getSelectedRow();
        String nombreArchivo = (fila >= 0) ? TareaProductos.getValueAt(fila, 0).toString() + ".png" : "000.png";
        String ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + nombreArchivo;
        
        if (!new File(ruta).exists()) ruta = System.getProperty("user.dir") + File.separator + "Imagenes" + File.separator + "000.png";
        
        Limagen.setIcon(libreria.EtiquetaImagen(ruta, Limagen.getWidth(), Limagen.getHeight()));
    }

    public void actualizartabla() {
        modeloinsumos = listainsumo.getmodelo(listacategorias);
        TareaProductos.setModel(modeloinsumos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bagregar) Altas();
        else if (e.getSource() == Beliminar) Eliminar();
        else if (e.getSource() == Bsalir) dispose();
    }

    private void Altas() {
        if (Bagregar.getText().equals("Agregar")) {
            Tid.setEditable(true);
            Tinsumo.setEditable(true);
            listaCategoria.setEnabled(true);
            Bagregar.setText("Guardar");
            Bsalir.setText("Cancelar");
        } else {
            if (esDatosCompletos()) {
                Insumo nuevo = new Insumo(
                    Tid.getText().trim(),
                    Tinsumo.getText().trim(),
                    listaCategoria.getSelectedValue().getIdcategoria()
                );
                if (listainsumo.agregarInsumo(nuevo)) {
                    archivoinsumos.guardar(listainsumo.toArchivo());
                    actualizartabla();
                } else {
                    JOptionPane.showMessageDialog(this, "¡ID ya existe!");
                }
            }
            VolverInicio();
        }
    }

    private void Eliminar() {
        String[] opciones = listainsumo.idinsumos();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay insumos");
            return;
        }
        String id = (String) JOptionPane.showInputDialog(this, "Seleccione:", "Eliminar", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (id != null && listainsumo.eliminarInsumoPorId(id)) {
            archivoinsumos.guardar(listainsumo.toArchivo());
            actualizartabla();
        }
    }

    private boolean esDatosCompletos() {
        return !Tid.getText().isEmpty() && 
               !Tinsumo.getText().isEmpty() && 
               listaCategoria.getSelectedIndex() != -1;
    }

    private void VolverInicio() {
        Tid.setText("");
        Tinsumo.setText("");
        Tid.setEditable(false);
        Tinsumo.setEditable(false);
        listaCategoria.setEnabled(false);
        Bagregar.setText("Agregar");
        Bsalir.setText("Salir");
    }
    
}