package Parte2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practica03_b extends JFrame implements ActionListener {

    private ListaCategorias_b listaCategorias;

    private JTextField Tid, Tcategoria;
    private JTextArea Tareacategoria;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;

    public Practica03_b() {
        super("Administración de Categorías");

        listaCategorias = new ListaCategorias_b();

        setBounds(100, 100, 400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 9, 71, 20);
        panelFormulario.add(labelId);

        Tid = new JTextField(10);
        Tid.setBounds(91, 9, 147, 20);
        Tid.setEditable(false);
        panelFormulario.add(Tid);

        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(10, 34, 71, 20);
        panelFormulario.add(labelCategoria);

        Tcategoria = new JTextField(20);
        Tcategoria.setBounds(91, 34, 147, 20);
        Tcategoria.setEditable(false);
        panelFormulario.add(Tcategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(20, 80, 100, 20);
        Bagregar.addActionListener(this);
        panelFormulario.add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(130, 80, 100, 20);
        Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(240, 80, 80, 20);
        Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 120, 360, 150);
        panelFormulario.add(scrollPane);

        Tareacategoria = new JTextArea(10, 40);
        Tareacategoria.setEditable(false);
        scrollPane.setViewportView(Tareacategoria);

        setVisible(true);
    }

    public void Volveralinicio() {
        Bagregar.setText("Agregar");
        Bsalir.setText("Salir");
        Bsalir.setEnabled(true);

        Tid.setEditable(false);
        Tcategoria.setEditable(false);
        Tid.setText("");
        Tcategoria.setText("");
    }

    private boolean esdatoscompletos() {
        if (Tid.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID está vacío.");
            return false;
        }
        if (Tcategoria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo Categoría está vacío.");
            return false;
        }
        return true;
    }

    public void Altas() {
        if (Bagregar.getText().equals("Agregar")) {
            Bagregar.setText("Salvar");
            Bsalir.setText("Cancelar");
            Bsalir.setEnabled(false);

            Tid.setEditable(true);
            Tcategoria.setEditable(true);
        } else {
            if (esdatoscompletos()) {
                String id = Tid.getText().trim();
                String cat = Tcategoria.getText().trim();

                if (!listaCategorias.agregarCategoria(new Categoria(id, cat))) {
                    JOptionPane.showMessageDialog(this, "Lo siento, el ID " + id + " ya existe.");
                } else {
                    Tareacategoria.setText(listaCategorias.toString());
                }
            }
            Volveralinicio();
        }
    }

    public void Eliminar() {
        Object[] opciones = listaCategorias.CategoriasArreglo();
        if (opciones.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay categorías para eliminar.");
            return;
        }

        Categoria seleccion = (Categoria) JOptionPane.showInputDialog(
            this,
            "Seleccione la categoría a eliminar",
            "Eliminación de Categorías",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion != null) {
            String idEliminar = seleccion.getIdcategoria();
            if (!listaCategorias.eliminarCategoriaPorId(idEliminar)) {
                JOptionPane.showMessageDialog(this, "No existe la categoría con ID " + idEliminar);
            } else {
                Tareacategoria.setText(listaCategorias.toString());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bagregar) {
            Altas();
        } else if (e.getSource() == Beliminar) {
            Eliminar();
        } else if (e.getSource() == Bsalir) {
            if (Bsalir.getText().equals("Cancelar")) {
                Volveralinicio();
            } else {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Practica03_b();
    }
}