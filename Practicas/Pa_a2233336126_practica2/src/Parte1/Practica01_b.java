package Parte1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Libreria.Archivotxt;
import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Practica01_b extends JFrame implements ActionListener {
    ListaInsumos listainsumo;
    ListaCategorias listacategorias;

    Archivotxt archivocategorias;
    Archivotxt archivoinsumos;
    
    private JList ListaCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario;
    private DefaultListModel<Categoria> modelocategoria;

    public void inicializarcategorias() {
        
        modelocategoria = new DefaultListModel<>();
        
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
        
        Categoria nodo1 = new Categoria("01", "Materiales");
        Categoria nodo2 = new Categoria("02", "Mano de Obra");
        Categoria nodo3 = new Categoria("03", "Maquinaria y Equipo");
        Categoria nodo4 = new Categoria("04", "Servicios");

        this.listacategorias.agregarCategoria(nodo1);
        this.listacategorias.agregarCategoria(nodo2);
        this.listacategorias.agregarCategoria(nodo3);
        this.listacategorias.agregarCategoria(nodo4);
        
    }
    
    public Practica01_b() {
        super("Administración de Productos");
        this.inicializarcategorias();
        this.listainsumo = new ListaInsumos();

        setBounds(0, 0, 390, 370);
        panelFormulario = new JPanel();
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 66, 71, 20);
        panelFormulario.add(labelCategoria);

        JScrollPane scrollPane_jlist = new JScrollPane();
        scrollPane_jlist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_jlist.setBounds(91, 61, 157, 42);
        panelFormulario.add(scrollPane_jlist);

        ListaCategoria = new JList();
        scrollPane_jlist.setViewportView(ListaCategoria);
        ListaCategoria.setModel(this.modelocategoria);
        ListaCategoria.setEnabled(true);
        
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        this.Tid = new JTextField(10);
        this.Tid.setEditable(false);
        this.Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);

        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        this.Tinsumo = new JTextField(20);
        this.Tinsumo.setEditable(false);
        this.Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);

        this.Bagregar = new JButton("Agregar");
        this.Bagregar.setBounds(20, 104, 111, 20);
        this.Bagregar.addActionListener(this);
        panelFormulario.setLayout(null);
        panelFormulario.add(Bagregar);

        this.Beliminar = new JButton("Eliminar");
        this.Beliminar.setBounds(153, 104, 111, 20);
        this.Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);
        panelFormulario.setLayout(null);

        this.Bsalir = new JButton("Salir");
        this.Bsalir.setBounds(274, 104, 79, 20);
        this.Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);
        this.areaProductos = new JTextArea(10, 40);
        scrollPane.setViewportView(areaProductos);
        this.areaProductos.setEditable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        if (this.listainsumo.toString()!= null) {
        	this.areaProductos.setText(this.listainsumo.toString());
        }
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
    
    public void Altas()
    {
        if (this.Bagregar.getText().compareTo("Agregar")==0)
        {
            this.ListaCategoria.setSelectedIndex(0);
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Beliminar.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
            this.ListaCategoria.setEnabled(true);
            this.ListaCategoria.setFocusable(true);
        }
        else
        {
            if (esdatoscompletos())
            {
                System.out.println("aqui");
                String id,insumo,idcategoria;
                id=this.Tid.getText().trim();
                insumo=this.Tinsumo.getText().trim();
                idcategoria=this.modelocategoria.get(this.ListaCategoria.getSelectedIndex()).getIdcategoria();
                Insumo nodo=new Insumo(id,insumo,idcategoria);
                if (!this.listainsumo.agregarInsumo(nodo))
                {
                    String mensaje="lo siente el id "+id+" ya existe lo tiene asignado "+this.listainsumo.buscarInsumo(id);
                    JOptionPane.showMessageDialog(this,mensaje);
                }
                else
                {
                    this.archivoinsumos.guardar(this.listainsumo.toArchivo());
                    this.areaProductos.setText(this.listainsumo.toString());
                }
            }
            this.Volveralinicio();
        }
    }
    
    public void Eliminar()
    {
        Object[] opciones = this.listainsumo.idinsumos();
        String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Elimi");
        if ((id != null) || (!id.isEmpty()))
        {
            if (!this.listainsumo.eliminarInsumoPorId(id))
                JOptionPane.showMessageDialog(this, "No existe este id");
            else
            {
                // sobreescribimos el archivo cuando se elimina un nuevo elemento
                this.archivoinsumos.guardar(this.listainsumo.toArchivo());
                this.areaProductos.setText(this.listainsumo.toString());
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
		new Practica01_b();
	}
}
