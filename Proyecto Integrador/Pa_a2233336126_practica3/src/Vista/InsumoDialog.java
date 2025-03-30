/*package Vista;
import Partefinal.InsumoFrame;

import javax.swing.*;

import Modelo.Insumo;
import Modelo.ListaCategorias;

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
        for (Modelo.Categoria cat : listaCategorias.getCategorias()) {
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
} */

package Vista;

import Modelo.Categoria;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InsumoDialog extends JDialog {
    private JTextField txtNombre;
    private JComboBox<Categoria> cmbCategorias;
    private JButton btnGuardar;
    private boolean exito = false;

    public InsumoDialog(JFrame parent, List<Categoria> categorias) {
        super(parent, "Gestión de Insumo", true);
        initComponents(categorias);
        setLocationRelativeTo(parent);
    }

    private void initComponents(List<Categoria> categorias) {
        setLayout(new GridLayout(3, 2, 10, 10));
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        add(new JLabel("Categoría:"));
        cmbCategorias = new JComboBox<>();
        for (Categoria cat : categorias) {
            cmbCategorias.addItem(cat);
        }
        add(cmbCategorias);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> validarCampos());
        add(btnGuardar);

        pack();
    }

    private void validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        exito = true;
        dispose();
    }

    public boolean exito() {
        return exito;
    }

    public String getNombreInsumo() {
        return txtNombre.getText().trim();
    }

    public Categoria getCategoriaSeleccionada() {
        return (Categoria) cmbCategorias.getSelectedItem();
    }
}