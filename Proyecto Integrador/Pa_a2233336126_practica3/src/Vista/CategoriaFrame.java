/*package Vista;

import Libreria.Librerias;
import Modelo.Categoria;
import Modelo.ListaCategorias;

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
            Vista.CategoriaDialog dialog = new Vista.CategoriaDialog(this, true, null);
            if (dialog.exito()) {
                lista.agregarCategoria(dialog.getCategoria());
                cargarDatos();
            }
        } else if (e.getSource() == btnEditar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                Categoria cat = lista.getCategorias().get(fila);
                Vista.CategoriaDialog dialog = new Vista.CategoriaDialog(this, true, cat);
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
} */

package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoriaFrame extends JInternalFrame {
    private DefaultTableModel model;
    private JTable tabla;
    private JButton btnAgregar, btnEditar, btnEliminar;

    public CategoriaFrame() {
        super("Gestión de Categorías", true, true, true, true);
        initComponents();
        setSize(500, 400);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Configurar tabla
        model = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        tabla = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarTabla(Object[][] datos) {
        model.setRowCount(0);
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }
}