package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controlador.ControladorEntidades;
import modelo.Entidad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaEntidades extends JFrame {
    private JTable tablaEntidades;
    private JButton btnAgregar, btnEliminar, btnModificar, btnSalir;
    private ControladorEntidades controlador;

    public VistaEntidades() {
        setTitle("Gestión de Entidades");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título superior
        JLabel lblTitulo = new JLabel("GESTIÓN DE ENTIDADES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla de entidades
        String[] columnas = {"Nombre"};
        tablaEntidades = new JTable();
        tablaEntidades.setModel(new DefaultTableModel(new Object[][]{}, columnas));
        tablaEntidades.setRowHeight(25);
        tablaEntidades.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaEntidades.setBackground(Color.WHITE);
        tablaEntidades.setForeground(Color.BLACK);

        // Estilo al encabezado de la tabla
        JTableHeader header = tablaEntidades.getTableHeader();
        header.setBackground(new Color(0, 102, 204)); // Azul medio
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tablaEntidades);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Entidades Registradas"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con mensaje y botones
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Mensaje informativo
        JLabel lblMensaje = new JLabel("Seleccione una entidad para modificar sus atributos", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblMensaje.setForeground(new Color(100, 100, 100)); // Gris suave
        panelInferior.add(lblMensaje, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.WHITE);

        // Crear botones con estilo
        btnAgregar = createStyledButton("Agregar", new Color(46, 204, 113)); // Verde
        btnEliminar = createStyledButton("Eliminar", new Color(231, 76, 60)); // Rojo
        btnModificar = createStyledButton("Modificar", new Color(52, 152, 219)); // Azul
        btnSalir = createStyledButton("Salir", new Color(142, 68, 173)); // Morado

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnSalir);

        panelInferior.add(panelBotones, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.SOUTH);

        // Configurar eventos
        configurarEventos();

        // Centrar ventana
        setLocationRelativeTo(null);
    }

    // Método auxiliar para crear botones con estilo
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la entidad:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                controlador.agregarEntidad(nombre);
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = tablaEntidades.getSelectedRow();
            if (selectedRow != -1) {
                String nombreSeleccionado = (String) tablaEntidades.getValueAt(selectedRow, 0);
                Entidad entidadAEliminar = encontrarEntidadPorNombre(nombreSeleccionado);
                if (entidadAEliminar != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro de eliminar '" + nombreSeleccionado + "'?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        controlador.eliminarEntidad(entidadAEliminar);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una entidad.");
            }
        });

        btnModificar.addActionListener(e -> {
            int selectedRow = tablaEntidades.getSelectedRow();
            if (selectedRow != -1) {
                String nombreSeleccionado = (String) tablaEntidades.getValueAt(selectedRow, 0);
                Entidad entidadAModificar = encontrarEntidadPorNombre(nombreSeleccionado);
                if (entidadAModificar != null) {
                    controlador.modificarEntidad(entidadAModificar);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una entidad.");
            }
        });

        btnSalir.addActionListener(e -> controlador.salir());
    }

    public void setControlador(ControladorEntidades controlador) {
        this.controlador = controlador;
    }

    private Entidad encontrarEntidadPorNombre(String nombre) {
        for (int i = 0; i < tablaEntidades.getRowCount(); i++) {
            String nombreFila = (String) tablaEntidades.getValueAt(i, 0);
            if (nombre.equals(nombreFila)) {
                return controlador.buscarEntidadPorNombre(nombre);
            }
        }
        return null;
    }

    public void mostrarEntidades(List<Entidad> entidades) {
        DefaultTableModel model = (DefaultTableModel) tablaEntidades.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (Entidad entidad : entidades) {
            model.addRow(new Object[]{entidad.getNombre()});
        }
    }

    public void agregarFilaTabla(Entidad entidad) {
        ((DefaultTableModel) tablaEntidades.getModel()).addRow(new Object[]{entidad.getNombre()});
    }

    public void abrirVistaAtributos(Entidad entidad) {
        SwingUtilities.invokeLater(() -> {
            VistaAtributos vistaAtributos = new VistaAtributos(entidad);
            vistaAtributos.setLocationRelativeTo(this);
            vistaAtributos.setVisible(true);
        });
    }
}