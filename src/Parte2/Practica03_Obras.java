package Parte2;

import Modelo.ListaObras;
import Modelo.Obra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica03_Obras extends JInternalFrame implements ActionListener {

    private ListaObras listaObras;
    private JTextField txtNombre, txtDescripcion;
    private JButton btnGuardar;

    public Practica03_Obras() {
        super("Gestión de Obras", true, true, true, true);
        listaObras = new ListaObras();
        initComponents();
        setSize(400, 200);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos de texto
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        // Botón
        btnGuardar = new JButton("Guardar Obra");
        btnGuardar.addActionListener(this);
        panel.add(new JLabel());
        panel.add(btnGuardar);

        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarObra();
        }
    }

    private void guardarObra() {
        String id = listaObras.generarNuevoId();
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (!nombre.isEmpty() && !descripcion.isEmpty()) {
            listaObras.agregarObra(new Obra(id, nombre, descripcion));
            JOptionPane.showMessageDialog(this, "Obra guardada!\nID Generado: " + id);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
    }
    
}
