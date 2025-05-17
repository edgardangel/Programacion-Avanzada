package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaDefinicionAtributos extends JDialog {
    private JTextField txtNombre;
    private JTextField txtTipoDato;
    private JTextField txtValor;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private boolean aceptado = false;

    public VistaDefinicionAtributos(JFrame owner) {
        super(owner, "Definir Atributo", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nombre del Atributo:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Tipo de Dato (ej: String, int):"));
        txtTipoDato = new JTextField();
        add(txtTipoDato);

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNombre.getText().isEmpty() && !txtTipoDato.getText().isEmpty()) {
                    aceptado = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VistaDefinicionAtributos.this, "Todos los campos son obligatorios.");
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        add(btnAceptar);
        add(btnCancelar);

        setLocationRelativeTo(owner);
    }

    public boolean mostrarDialogo() {
        setVisible(true);
        return aceptado;
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getTipoDato() {
        return txtTipoDato.getText();
    }

    public String getValor() {
        return txtValor.getText();
    }
}