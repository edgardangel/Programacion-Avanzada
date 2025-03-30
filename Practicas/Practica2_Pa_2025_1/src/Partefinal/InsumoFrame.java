package Partefinal;

import Modelo2.Categoria;
import Modelo2.Insumo;
import Modelo2.ListaCategorias;
import Modelo2.ListaInsumos;
import Libreria.Librerias;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsumoFrame extends JInternalFrame implements ActionListener {

    private ListaInsumos listaInsumos;
    private ListaCategorias listaCategorias;
    private DefaultTableModel model;
    private JTable tabla;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JMenuBar menuPadre;

    public InsumoFrame(JMenuBar menuPadre) {
        super("Gestión de Insumos", true, true, true, true);
        this.menuPadre = menuPadre;
        this.listaInsumos = new ListaInsumos();
        this.listaCategorias = new ListaCategorias();
        initComponents();
        new Librerias().menuspadre(menuPadre, false);
        
        // Corrección aquí:
        this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                new Librerias().menuspadre(menuPadre, true);
            }
        });
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Configurar tabla
        model = new DefaultTableModel(new Object[]{"ID", "Insumo", "Categoría"}, 0);
        tabla = new JTable(model);
        cargarDatos();

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEditar.setEnabled(false); // Deshabilitar hasta implementar
        btnEliminar.addActionListener(this);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setSize(600, 400);
    }

    private void cargarDatos() {
        model.setRowCount(0);
        for (Insumo insumo : listaInsumos.getInsumos()) {
            String idCategoria = insumo.getIdCategoria();
            Categoria categoria = listaCategorias.buscarCategoriaPorId(idCategoria);
            String nombreCategoria = (categoria != null) ? categoria.getCategoria() : "Categoría Desconocida"; // <- Cambio aquí
            model.addRow(new Object[]{
                insumo.getIdProducto(), 
                insumo.getProducto(), 
                nombreCategoria // Usar variable segura
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            dialogs.InsumoDialog dialog = new dialogs.InsumoDialog(this, true, null, listaCategorias);
            if (dialog.exito()) {
                listaInsumos.agregarInsumo(dialog.getInsumo());
                cargarDatos();
            }
        } else if (e.getSource() == btnEliminar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int confirmar = JOptionPane.showConfirmDialog(
                    this, 
                    "¿Eliminar insumo?", 
                    "Confirmar", 
                    JOptionPane.YES_NO_OPTION
                );
                if (confirmar == JOptionPane.YES_OPTION) {
                    listaInsumos.eliminarInsumoPorId(tabla.getValueAt(fila, 0).toString());
                    cargarDatos();
                }
            }
        }
    }
}
