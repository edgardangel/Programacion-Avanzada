/*package Vista;

import Partefinal.ObraFrame;

import javax.swing.*;

import Modelo.Obra;

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
}*/

package Vista;

import javax.swing.*;
import java.awt.*;

public class ObraDialog extends JDialog {
    private JTextField txtNombre, txtDescripcion;
    private JButton btnGuardar;
    private boolean exito = false;

    public ObraDialog(JFrame parent) {
        super(parent, "Gestión de Obra", true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField(20);
        add(txtDescripcion);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> validarCampos());
        add(btnGuardar);

        pack();
    }

    private void validarCampos() {
        if (txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        exito = true;
        dispose();
    }

    public boolean exito() {
        return exito;
    }

    public String getNombreObra() {
        return txtNombre.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }
}