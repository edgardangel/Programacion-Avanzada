package dialogs;
import Modelo2.Insumo;
import Modelo2.ListaCategorias;
import Partefinal.InsumoFrame;

import javax.swing.*;
import java.awt.*;

public class InsumoDialog extends JDialog {

    private JTextField txtNombre;
    private JComboBox<String> cmbCategorias;
    private JButton btnGuardar;
    private boolean exito = false;
    private Insumo insumo;
    private ListaCategorias listaCategorias;

    public InsumoDialog(InsumoFrame insumoFrame, boolean modal, Insumo insumo,ListaCategorias listaCategorias) {
        this.insumo = insumo;
        this.listaCategorias = listaCategorias;
        initComponents();
        setLocationRelativeTo(insumoFrame);
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));

        // Campo Nombre
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        // ComboBox Categorías
        add(new JLabel("Categoría:"));
        cmbCategorias = new JComboBox<>();
        for (Modelo2.Categoria cat : listaCategorias.getCategorias()) {
            cmbCategorias.addItem(cat.getCategoria());
        }
        add(cmbCategorias);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (!txtNombre.getText().isEmpty()) {
                exito = true;
                String idCategoria = listaCategorias.getCategorias().get(cmbCategorias.getSelectedIndex()).getIdcategoria();
                insumo = new Insumo("", txtNombre.getText(), idCategoria);
                dispose();
            }
        });
        add(btnGuardar);

        pack();
    }

    public boolean exito() { return exito; }
    public Insumo getInsumo() { return insumo; }
}
