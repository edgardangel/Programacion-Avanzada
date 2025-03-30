package Parte3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Libreria.Archivotxt;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.BoxLayout;

public class Practica06_c extends JFrame implements ActionListener {
    ListaInsumos listainsumo;
    ListaCategorias listacategorias;

    Archivotxt archivocategorias;
    Archivotxt archivoinsumos;
    
    private JList ListaCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;
    private JTable TareaProductos;
    
    private DefaultListModel<Categoria> modelocategoria;
    private DefaultTableModel modeloinsumos;

    public void inicializarcategorias() {
        this.archivocategorias = new Archivotxt("Categoria");
        this.archivoinsumos = new Archivotxt("Insumos");
        this.listacategorias = new ListaCategorias();
        this.listainsumo = new ListaInsumos();

        if (this.archivocategorias.existe())
            this.listacategorias.cargarCategorias(this.archivocategorias.cargar());
        if (this.archivoinsumos.existe())
            this.listainsumo.cargarInsumo(this.archivoinsumos.cargar());

        modelocategoria = new DefaultListModel<Categoria>();
        modelocategoria = this.listacategorias.generarModeloCategorias();

        this.modeloinsumos = new DefaultTableModel();
        this.modeloinsumos = this.listainsumo.getmodelo(this.listacategorias);
    }
    
    public Practica06_c() {
        super("Administración de Productos");
        this.inicializarcategorias();
        this.listainsumo = new ListaInsumos();

        setSize(600, 500);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Panel Norte con GridLayout
        JPanel panelNorte = new JPanel(new GridLayout(3, 1, 5, 5)); // 3 filas, 1 columna
        panelNorte.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Fila 1: ID
        JPanel panelId = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel labelId = new JLabel("ID:");
        this.Tid = new JTextField(15);
        this.Tid.setEditable(false);
        panelId.add(labelId);
        panelId.add(Tid);
        panelNorte.add(panelId);

        // Fila 2: Insumo
        JPanel panelInsumo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel labelInsumo = new JLabel("Insumo:");
        this.Tinsumo = new JTextField(25);
        this.Tinsumo.setEditable(false);
        panelInsumo.add(labelInsumo);
        panelInsumo.add(Tinsumo);
        panelNorte.add(panelInsumo);

        // Fila 3: Categoría
        JPanel panelCategoria = new JPanel(new BorderLayout());
        panelCategoria.setBorder(new TitledBorder("Categorías"));
        JScrollPane scrollPane_jlist = new JScrollPane(ListaCategoria);
        scrollPane_jlist.setPreferredSize(new Dimension(500, 120));
        ListaCategoria = new JList();
        ListaCategoria.setModel(this.modelocategoria);
        scrollPane_jlist.setViewportView(ListaCategoria);
        panelCategoria.add(scrollPane_jlist);
        panelNorte.add(panelCategoria);

        add(panelNorte, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        TareaProductos = new JTable(); // Inicializar primero la tabla
        scrollPane = new JScrollPane(TareaProductos); // Pasar la tabla al scroll
        add(scrollPane, BorderLayout.CENTER);

        // Panel Sur (Botones)
        JPanel panelSur = new JPanel(new GridLayout(1, 3, 20, 0)); // 1 fila, 3 columnas
        this.Bagregar = new JButton("Agregar");
        this.Beliminar = new JButton("Eliminar");
        this.Bsalir = new JButton("Salir");
        
        Bagregar.addActionListener(this);
        Beliminar.addActionListener(this);
        Bsalir.addActionListener(this);
        
        panelSur.add(Bagregar);
        panelSur.add(Beliminar);
        panelSur.add(Bsalir);
        add(panelSur, BorderLayout.SOUTH);

        this.actualizartabla();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
    
    public void actualizartabla() {
        this.TareaProductos.setModel(this.modeloinsumos);
        this.TareaProductos.getColumnModel().getColumn(0).setPreferredWidth(5);
        this.TareaProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.TareaProductos.getColumnModel().getColumn(2).setPreferredWidth(35);
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
        Object[] opciones = this.listainsumo.idinsumos();
        String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Eliminacion de Insumos", JOptionPane.PLAIN_MESSAGE);
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
	
	public static void main (String [] args) {
		new Practica06_c();
	}
}