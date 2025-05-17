package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controlador.ControladorDefinicionAtributos;
import modelo.DefinicionAtributo;
import modelo.Entidad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaAtributos extends JFrame {
    private JTable tablaAtributos;
    private JButton btnAgregar, btnEliminar, btnModificar, btnSalir;
    private ControladorDefinicionAtributos controlador;
    private Entidad entidad;

    public VistaAtributos(Entidad entidad) {
        this.entidad = entidad;
        setTitle("Atributos de " + entidad.getNombre());
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título superior
        JLabel lblTitulo = new JLabel("Administrar Atributos - " + entidad.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla de atributos
        String[] columnas = {"Nombre", "Tipo de Dato", "Valor"};
        tablaAtributos = new JTable();
        tablaAtributos.setModel(new DefaultTableModel(new Object[][]{}, columnas));
        tablaAtributos.setRowHeight(25);
        tablaAtributos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaAtributos.setBackground(Color.WHITE);
        tablaAtributos.setForeground(Color.BLACK);

        // Estilo al encabezado de la tabla
        JTableHeader header = tablaAtributos.getTableHeader();
        header.setBackground(new Color(52, 152, 219)); // Azul claro
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tablaAtributos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Atributos"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con mensaje y botones
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Mensaje informativo (opcional)
        JLabel lblMensaje = new JLabel("Seleccione un atributo para modificar o eliminar.", SwingConstants.CENTER);
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

        // Inicializar el controlador y cargar los atributos
        controlador = new ControladorDefinicionAtributos(this, entidad);
        mostrarAtributos(entidad.getAtributos());

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
            VistaDefinicionAtributos dialogo = new VistaDefinicionAtributos(VistaAtributos.this);
            if (dialogo.mostrarDialogo()) {
                String nombre = dialogo.getNombre();
                String tipoDato = dialogo.getTipoDato();
                String valorStr = dialogo.getValor();
                Object valor = parseValor(tipoDato, valorStr);

                if (valor != null) {
                    controlador.agregarAtributo(nombre, tipoDato, valor);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = tablaAtributos.getSelectedRow();
            if (selectedRow != -1) {
                String nombreSeleccionado = (String) tablaAtributos.getValueAt(selectedRow, 0);
                for (DefinicionAtributo attr : entidad.getAtributos()) {
                    if (attr.getNombre().equals(nombreSeleccionado)) {
                        controlador.eliminarAtributo(attr);
                        return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, seleccione un atributo para eliminar.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            int selectedRow = tablaAtributos.getSelectedRow();
            if (selectedRow != -1) {
                String nombreSeleccionado = (String) tablaAtributos.getValueAt(selectedRow, 0);
                DefinicionAtributo atributo = null;

                for (DefinicionAtributo attr : entidad.getAtributos()) {
                    if (attr.getNombre().equals(nombreSeleccionado)) {
                        atributo = attr;
                        break;
                    }
                }

                if (atributo != null) {
                    String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", atributo.getNombre());
                    String nuevoTipoDato = JOptionPane.showInputDialog(this, "Nuevo tipo de dato:", atributo.getTipoDato());
                    String nuevoValorStr = JOptionPane.showInputDialog(this, "Nuevo valor:", atributo.getValor().toString());

                    Object nuevoValor = parseValor(nuevoTipoDato, nuevoValorStr);

                    if (nuevoNombre != null && nuevoTipoDato != null && nuevoValor != null) {
                        controlador.modificarAtributo(atributo, nuevoNombre, nuevoTipoDato, nuevoValor);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, seleccione un atributo para modificar.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnSalir.addActionListener(e -> controlador.salir());
    }

    public void setControlador(ControladorDefinicionAtributos controlador) {
        this.controlador = controlador;
    }

    public void mostrarAtributos(List<DefinicionAtributo> atributos) {
        DefaultTableModel model = (DefaultTableModel) tablaAtributos.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (DefinicionAtributo atributo : atributos) {
            model.addRow(new Object[]{
                    atributo.getNombre(),
                    atributo.getTipoDato(),
                    atributo.getValor()
            });
        }
    }

    public void agregarFilaTabla(DefinicionAtributo atributo) {
        ((DefaultTableModel) tablaAtributos.getModel()).addRow(new Object[]{
                atributo.getNombre(),
                atributo.getTipoDato(),
                atributo.getValor()
        });
    }

    public void actualizarFilaTabla(DefinicionAtributo atributo) {
        int selectedRow = tablaAtributos.getSelectedRow();
        if (selectedRow != -1) {
            ((DefaultTableModel) tablaAtributos.getModel()).setValueAt(atributo.getNombre(), selectedRow, 0);
            ((DefaultTableModel) tablaAtributos.getModel()).setValueAt(atributo.getTipoDato(), selectedRow, 1);
            ((DefaultTableModel) tablaAtributos.getModel()).setValueAt(atributo.getValor(), selectedRow, 2);
        }
    }

    public void actualizarTabla() {
        mostrarAtributos(entidad.getAtributos()); // Recarga toda la tabla con los datos actualizados
    }

    private Object parseValor(String tipoDato, String valorStr) {
        switch (tipoDato.toLowerCase()) {
            case "int":
                try {
                    return Integer.parseInt(valorStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido para tipo 'int'.");
                    return null;
                }
            case "double":
                try {
                    return Double.parseDouble(valorStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido para tipo 'double'.");
                    return null;
                }
            case "boolean":
                if ("true".equalsIgnoreCase(valorStr) || "false".equalsIgnoreCase(valorStr)) {
                    return Boolean.parseBoolean(valorStr);
                } else {
                    JOptionPane.showMessageDialog(this, "Valor inválido para tipo 'boolean'.");
                    return null;
                }
            default:
                return valorStr; // Para String u otros tipos no validados
        }
    }
}