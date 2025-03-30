package Parte2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Practica03_d extends JFrame implements ActionListener {

    private ListaCategorias_d listaCategorias;
    private ListaInsumos_b listaInsumos;

    private Archivotxt archivoCategorias;
    private Archivotxt archivoInsumos;

    private JComboBox ComboCategoria;
    private JTextField Tid, Tinsumo;
    private JButton Bagregar, Beliminar, Bsalir;
    private JTextArea areaProductos;
    private JPanel panelFormulario;

    private final String archivoCat = "categorias.txt";
    private final String archivoIns = "insumos.txt";

    public Practica03_d() {
        super("Administración de Insumos - Versión con Archivo (Practica03_d)");

        this.listaCategorias = new ListaCategorias_d();
        this.listaInsumos = new ListaInsumos_b();

        this.archivoCategorias = new Archivotxt(archivoCat);
        this.archivoInsumos = new Archivotxt(archivoIns);

        cargarCategoriasDesdeArchivo();
        cargarInsumosDesdeArchivo();

        setBounds(100, 100, 400, 350);
        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10, 66, 71, 20);
        panelFormulario.add(labelCategoria);

        ComboCategoria = new JComboBox(this.listaCategorias.CategoriasArreglo());
        ComboCategoria.setEnabled(true); 
        ComboCategoria.setBounds(91, 66, 160, 20);
        panelFormulario.add(ComboCategoria);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        panelFormulario.add(labelId);

        this.Tid = new JTextField(10);
        this.Tid.setEditable(false);
        this.Tid.setBounds(91, 9, 147, 20);
        panelFormulario.add(Tid);

        JLabel labelInsumo = new JLabel("Insumo:");
        labelInsumo.setBounds(10, 34, 71, 20);
        panelFormulario.add(labelInsumo);

        this.Tinsumo = new JTextField(20);
        this.Tinsumo.setEditable(false);
        this.Tinsumo.setBounds(91, 35, 147, 20);
        panelFormulario.add(Tinsumo);

        this.Bagregar = new JButton("Agregar");
        this.Bagregar.setBounds(20, 104, 111, 20);
        this.Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        this.Beliminar = new JButton("Eliminar");
        this.Beliminar.setBounds(153, 104, 111, 20);
        this.Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        this.Bsalir = new JButton("Salir");
        this.Bsalir.setBounds(274, 104, 79, 20);
        this.Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 132, 357, 179);
        panelFormulario.add(scrollPane);

        this.areaProductos = new JTextArea(10, 40);
        this.areaProductos.setEditable(false);
        scrollPane.setViewportView(areaProductos);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        actualizarTextArea();
    }

    private void cargarCategoriasDesdeArchivo() {
        ArrayList<String> lineas = archivoCategorias.cargar();
        ArrayList<String[]> temp = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i += 2) {
            if (i + 1 < lineas.size()) {
                String[] par = { lineas.get(i), lineas.get(i + 1) };
                temp.add(par);
            }
        }
        listaCategorias.cargarCategorias(temp);
    }

    private void cargarInsumosDesdeArchivo() {
        ArrayList<String> lineas = archivoInsumos.cargar();
        ArrayList<String[]> temp = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i += 3) {
            if (i + 2 < lineas.size()) {
                String[] trio = { lineas.get(i), lineas.get(i + 1), lineas.get(i + 2) };
                temp.add(trio);
            }
        }
        listaInsumos.cargarInsumo(temp);
    }

    private void guardarCategoriasEnArchivo() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : listaCategorias.CategoriasArreglo()) {
            Categoria c = (Categoria) obj;
            sb.append(c.getIdcategoria()).append(", ")
              .append(c.getCategoria()).append("\n");
        }
        archivoCategorias.guardar(sb.toString());
    }

    private void guardarInsumosEnArchivo() {
        StringBuilder sb = new StringBuilder();
        for (Insumo ins : listaInsumos.getInsumos()) {
            sb.append(ins.getIdProducto()).append(", ")
              .append(ins.getProducto()).append(", ")
              .append(ins.getIdCategoria()).append("\n");
        }
        archivoInsumos.guardar(sb.toString());
    }

    private void actualizarTextArea() {
        this.areaProductos.setText(this.listaInsumos.toString());
    }

    public void Volveralinicio() {
        this.Bagregar.setText("Agregar");
        this.Bsalir.setText("Salir");
        this.Bsalir.setEnabled(true);
        this.Tid.setEditable(false);
        this.Tinsumo.setEditable(false);
        this.ComboCategoria.setSelectedIndex(0);
        this.Tid.setText("");
        this.Tinsumo.setText("");
    }

    private boolean esdatoscompletos() {
        if (this.Tid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID está vacío.");
            return false;
        }
        if (this.Tinsumo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Insumo está vacío.");
            return false;
        }
        if (this.ComboCategoria.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna categoría.");
            return false;
        }
        return true;
    }

    public void Altas() {
        if (this.Bagregar.getText().compareTo("Agregar") == 0) {
            this.Bagregar.setText("Salvar");
            this.Bsalir.setText("Cancelar");
            this.Bsalir.setEnabled(false);
            this.Tid.setEditable(true);
            this.Tinsumo.setEditable(true);
        } else {
            if (esdatoscompletos()) {
                String id = this.Tid.getText().trim();
                String insumo = this.Tinsumo.getText().trim();
                String idc = ((Categoria) this.ComboCategoria.getSelectedItem()).getIdcategoria().trim();
                Insumo nodo = new Insumo(id, insumo, idc);

                if (!this.listaInsumos.agregarInsumo(nodo)) {
                    JOptionPane.showMessageDialog(this, "El ID " + id + " ya existe. No se puede agregar.");
                } else {
                    actualizarTextArea();
                    guardarInsumosEnArchivo();
                }
            }
            this.Volveralinicio();
        }
    }

    public void Eliminar() {
        Object[] opciones = this.listaInsumos.idinsumos();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay insumos para eliminar.");
            return;
        }
        String id = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un ID",
                "Eliminacion de Insumos",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        if (id != null && !id.isEmpty()) {
            if (!this.listaInsumos.eliminarInsumoPorId(id)) {
                JOptionPane.showMessageDialog(this, "No existe el insumo con ID " + id);
            } else {
                actualizarTextArea();
                guardarInsumosEnArchivo();
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
            if (this.Bsalir.getText().compareTo("Cancelar") == 0) {
                this.Volveralinicio();
            } else {
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Practica03_d();
    }
}