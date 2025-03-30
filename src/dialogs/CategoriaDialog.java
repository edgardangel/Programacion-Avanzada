package dialogs;

import Modelo2.Categoria;
import Partefinal.CategoriaFrame;

import javax.swing.*;
import java.awt.*;

public class CategoriaDialog extends JDialog {

    private JTextField txtNombre;
    private JButton btnGuardar;
    private boolean exito = false;
    private Categoria categoria;

    public CategoriaDialog(CategoriaFrame categoriaFrame, boolean modal, Categoria categoria) {
        super();
        this.categoria = categoria;
        initComponents();
        setLocationRelativeTo(categoriaFrame);
    }

	private void initComponents() {
        setLayout(new GridLayout(2, 2, 10, 10));
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        if (categoria != null) txtNombre.setText(categoria.getCategoria());

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (!txtNombre.getText().isEmpty()) {
                exito = true;
                if (categoria == null) {
                    categoria = new Categoria("", txtNombre.getText());
                } else {
                    categoria.setCategoria(txtNombre.getText());
                }
                dispose();
            }
        });
        add(new JLabel());
        add(btnGuardar);
        pack();
    }

    public boolean exito() { return exito; }
    public Categoria getCategoria() { return categoria; }
}