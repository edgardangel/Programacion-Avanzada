package Partefinal;

import Modelo2.Obra;
import Modelo2.ListaObras;
import Libreria.Librerias;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObraFrame extends JInternalFrame implements ActionListener {

    private ListaObras listaObras;
    private DefaultTableModel model;
    private JTable tabla;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JMenuBar menuPadre;

    public ObraFrame(JMenuBar menuPadre) {
        super("Gestión de Obras", true, true, true, true);
        this.menuPadre = menuPadre;
        this.listaObras = new ListaObras();
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
        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción"}, 0);
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
        setSize(700, 400);
    }

    private void cargarDatos() {
        model.setRowCount(0);
        for (Obra obra : listaObras.getObras()) {
            model.addRow(new Object[]{obra.getId(), obra.getNombre(), obra.getDescripcion()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            dialogs.ObraDialog dialog = new dialogs.ObraDialog(this, true, null);
            if (dialog.exito()) {
                listaObras.agregarObra(dialog.getObra());
                cargarDatos();
            }
        } else if (e.getSource() == btnEliminar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int confirmar = JOptionPane.showConfirmDialog(
                    this, 
                    "¿Eliminar obra?", 
                    "Confirmar", 
                    JOptionPane.YES_NO_OPTION
                );
                if (confirmar == JOptionPane.YES_OPTION) {
                    listaObras.eliminarObra(fila);
                    cargarDatos();
                }
            }
        }
    }
}
