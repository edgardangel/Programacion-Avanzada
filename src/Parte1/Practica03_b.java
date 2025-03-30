package Parte1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Libreria.Archivotxt;
import Libreria.Librerias;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;

public class Practica03_b extends JFrame implements ActionListener {

    ListaInsumos listainsumo;
    ListaCategorias listacategorias;
    Archivotxt archivocategorias;
    Archivotxt archivoinsumos;
    Librerias libreria;
    
    private JList<Categoria> ListaCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;
    private JTable TareaProductos;
    private JLabel Limagen;
    
    private DefaultListModel<Categoria> modelocategoria;
    private DefaultTableModel modeloinsumos;

    public void inicializarcategorias() {
        this.archivocategorias = new Archivotxt("Categoria.txt");
        this.archivoinsumos = new Archivotxt("Insumos.txt");
        this.listacategorias = new ListaCategorias();
        this.listainsumo = new ListaInsumos();

        if (this.archivocategorias.existe())
            this.listacategorias.cargarCategorias(this.archivocategorias.cargar());
        if (this.archivoinsumos.existe())
            this.listainsumo.cargarInsumo(this.archivoinsumos.cargar());

        modelocategoria = this.listacategorias.generarModeloCategorias();
        modeloinsumos = this.listainsumo.getmodelo(this.listacategorias);
    }
    
    public Practica03_b() {
        super("Administración de Productos");
        this.libreria = new Librerias();
        
        // 1. Inicializar componentes PRIMERO
        initComponents();
        
        // 2. Luego cargar datos
        this.inicializarcategorias();
        
        // 3. Configurar modelos después de inicializar
        ListaCategoria.setModel(modelocategoria);
        actualizartabla();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        // Componentes de la interfaz
        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        panelFormulario.add(labelCategoria);

        JScrollPane scrollPane_jlist = new JScrollPane();
        scrollPane_jlist.setBounds(91, 61, 157, 42);
        panelFormulario.add(scrollPane_jlist);

        ListaCategoria = new JList<>();
        scrollPane_jlist.setViewportView(ListaCategoria);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        Tid = new JTextField();
        Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        Tinsumo = new JTextField();
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
        
        TareaProductos = new JTable(); // Inicializar la tabla ANTES de usarla
        scrollPane.setViewportView(TareaProductos);

        Limagen = new JLabel();
        Limagen.setBounds(274, 9, 86, 80);
        panelFormulario.add(Limagen);

        // Configurar listeners
        TareaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                actualizarimagen();
            }
        });
    }

    public void actualizarimagen() {
        int fila = TareaProductos.getSelectedRow();
        String Nombrearchivo = "000.png";
        
        if (fila >= 0) {
            Nombrearchivo = TareaProductos.getValueAt(fila, 0).toString() + ".png";
        }
        
        File fichero = new File(System.getProperty("user.dir") + File.separator + 
                              "Imagenes" + File.separator + Nombrearchivo);
        
        if (!fichero.exists()) {
            fichero = new File(System.getProperty("user.dir") + File.separator + 
                             "Imagenes" + File.separator + "000.png");
        }
        
        Limagen.setIcon(libreria.EtiquetaImagen(fichero.getPath(), Limagen.getWidth(), Limagen.getHeight()));
    }

    public void actualizartabla() {
        // Regenerar el modelo con los datos actualizados
        this.modeloinsumos = this.listainsumo.getmodelo(this.listacategorias);
        this.TareaProductos.setModel(this.modeloinsumos);
        
        // Ajustar el ancho de las columnas (opcional)
        this.TareaProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.TareaProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.TareaProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    public void Volveralinicio() {
        this.Bagregar.setText("Agregar");
        this.Bsalir.setText("Salir");
        this.Bsalir.setEnabled(true);
        this.Tid.setEditable(false);
        this.Tinsumo.setEditable(false);
        this.ListaCategoria.setEnabled(false);
        this.Tid.setText("");
        this.Tinsumo.setText("");
        this.ListaCategoria.setSelectedIndex(0);
    }
    
    public Boolean esdatoscompletos() {
        boolean enc=false;
        String id,insumo,idcategoria;
        id="";
        insumo="";
        idcategoria="";
        id=this.Tid.getText().trim();
        insumo=this.Tinsumo.getText().trim();
        if (this.ListaCategoria.getSelectedIndex()>=0)
            idcategoria=this.modelocategoria.get(this.ListaCategoria.getSelectedIndex()).getIdcategoria();
        if ((!id.isEmpty())&&(!insumo.isEmpty())&&(!idcategoria.isEmpty()))
            enc=true;
        System.out.println(id+" "+insumo+" "+idcategoria);
        return enc;
    }
    
    public void Altas() {
        if (this.Bagregar.getText().compareTo("Agregar")==0) {
            this.ListaCategoria.setSelectedIndex(0);
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Beliminar.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
            this.ListaCategoria.setEnabled(true);
            this.ListaCategoria.setFocusable(true);
        } else {
            if (esdatoscompletos()) {
                System.out.println("aqui");
                String id,insumo,idcategoria;
                id=this.Tid.getText().trim();
                insumo=this.Tinsumo.getText().trim();
                idcategoria=this.modelocategoria.get(this.ListaCategoria.getSelectedIndex()).getIdcategoria();
                Insumo nodo=new Insumo(id,insumo,idcategoria);
                if (!this.listainsumo.agregarInsumo(nodo)) {
                    String mensaje="lo siente el id "+id+" ya existe lo tiene asignado "+this.listainsumo.buscarInsumo(id);
                    JOptionPane.showMessageDialog(this,mensaje);
                } else {
                    // sobreescribimos el archivo cuando se agrega un nuevo elemento
                    this.archivoinsumos.guardar(this.listainsumo.toArchivo());
                    this.actualizartabla();
                }
            }
            this.Volveralinicio();
        }
    }
    
    public void Eliminar() {
    	String[] opciones = this.listainsumo.idinsumos();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay insumos para eliminar");
            return;
        }
        String id = (String) JOptionPane.showInputDialog(
            null, 
            "Seleccione un insumo:", 
            "Eliminación de Insumos", 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );
        if ((id != null) || (!id.isEmpty())) {
            if (!this.listainsumo.eliminarInsumoPorId(id)) {
                JOptionPane.showMessageDialog(this, "No existe este id");
            } else {
                // sobreescribimos el archivo cuando se elimina un nuevo elemento
                this.archivoinsumos.guardar(this.listainsumo.toArchivo());
                this.actualizartabla();
            }
        }
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.Bagregar) {
			this.Altas();
		} else if (e.getSource() == this.Beliminar) {
			this.Eliminar();
		} else if (e.getSource() == Bsalir) {
			if (this.Bsalir.getText().compareTo("Cancelar")==0) {
				  this.Volveralinicio();
			} else {
				this.dispose();
			}
		}
	}
    
    public static void main(String[] args) {
        new Practica03_b();
    }

}