package dialogs;

import Modelo2.Obra;
import Partefinal.ObraFrame;

import javax.swing.*;
import java.awt.*;

public class ObraDialog extends JDialog {

    private JTextField txtNombre, txtDescripcion;
    private JButton btnGuardar;
    private boolean exito = false;
    private Obra obra;

    public ObraDialog(ObraFrame obraFrame, boolean modal, Obra obra) {
        this.obra = obra;
        initComponents();
        setLocationRelativeTo(obraFrame);
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));

        // Campo Nombre
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        // Campo Descripción
        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (!txtNombre.getText().isEmpty() && !txtDescripcion.getText().isEmpty()) {
                exito = true;
                obra = new Obra("", txtNombre.getText(), txtDescripcion.getText());
                dispose();
            }
        });
        add(btnGuardar);

        pack();
    }

    public boolean exito() { return exito; }
    public Obra getObra() { return obra; }
}