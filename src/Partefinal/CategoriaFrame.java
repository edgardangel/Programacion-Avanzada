package Partefinal;

import Modelo2.Categoria;
import Modelo2.ListaCategorias;
import dialogs.CategoriaDialog;
import Libreria.Librerias;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriaFrame extends JInternalFrame implements ActionListener {

    private ListaCategorias lista;
    private DefaultTableModel model;
    private JTable tabla;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JMenuBar menuPadre;

    public CategoriaFrame(JMenuBar menuPadre) {
        super("Gestión de Categorías", true, true, true, true);
        this.menuPadre = menuPadre;
        this.lista = new ListaCategorias();
        initComponents();
        new Librerias().menuspadre(menuPadre, false);
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                new Librerias().menuspadre(menuPadre, true);
            }
        });
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        tabla = new JTable(model);
        cargarDatos();

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setSize(500, 400);
    }

    private void cargarDatos() {
        model.setRowCount(0);
        for (Categoria cat : lista.getCategorias()) {
            model.addRow(new Object[]{cat.getIdcategoria(), cat.getCategoria()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            dialogs.CategoriaDialog dialog = new dialogs.CategoriaDialog(this, true, null);
            if (dialog.exito()) {
                lista.agregarCategoria(dialog.getCategoria());
                cargarDatos();
            }
        } else if (e.getSource() == btnEditar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                Categoria cat = lista.getCategorias().get(fila);
                dialogs.CategoriaDialog dialog = new dialogs.CategoriaDialog(this, true, cat);
                if (dialog.exito()) {
                    lista.actualizarCategoria(fila, dialog.getCategoria());
                    cargarDatos();
                }
            }
        } else if (e.getSource() == btnEliminar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int confirmar = JOptionPane.showConfirmDialog(this, "¿Eliminar categoría?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    lista.eliminarCategoria(fila);
                    cargarDatos();
                }
            }
        }
    }
}