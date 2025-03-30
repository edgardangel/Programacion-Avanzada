package tarea08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AplicacionClima extends JFrame {
	
    // Componentes para los datos del clima
    private JLabel etiquetaCondicion = new JLabel("Desconocido");
    private JLabel etiquetaTemperatura = new JLabel("0°C");
    private JLabel etiquetaHumedad = new JLabel("0%");
    private JLabel etiquetaPrecipitacion = new JLabel("0%");
    private JLabel etiquetaViento = new JLabel("0 km/h");

    // Campo de texto para el código postal
    private JTextField campoCodigoPostal;

    public AplicacionClima() {
        // Configurar la ventana
        setTitle("Aplicación del Clima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 380);
        setLocationRelativeTo(null);

        // Panel principal con BoxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título principal
        JLabel etiquetaTitulo = new JLabel("¿Cómo está el clima?");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto de bienvenida
        JLabel etiquetaBienvenida = new JLabel("¡Bienvenido a la aplicación del clima! Presiona el botón 'Actualizar' para obtener el pronóstico más reciente.");
        etiquetaBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para el código postal
        JPanel panelCodigoPostal = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel etiquetaCodigoPostal = new JLabel("Código Postal:");
        campoCodigoPostal = new JTextField(10);
        campoCodigoPostal.setText("12345");
        panelCodigoPostal.add(etiquetaCodigoPostal);
        panelCodigoPostal.add(campoCodigoPostal);

        // Botón de actualizar
        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para los datos del clima
        JPanel panelClima = new JPanel(new GridLayout(5, 1, 10, 5));
        panelClima.setBorder(BorderFactory.createTitledBorder("Condiciones Actuales"));

        // Configurar estilo de los datos
        agregarDatoClima(panelClima, "Condición:", etiquetaCondicion);
        agregarDatoClima(panelClima, "Temperatura:", etiquetaTemperatura);
        agregarDatoClima(panelClima, "Humedad:", etiquetaHumedad);
        agregarDatoClima(panelClima, "Precipitación:", etiquetaPrecipitacion);
        agregarDatoClima(panelClima, "Viento:", etiquetaViento);

        // Ensamblar componentes
        panelPrincipal.add(etiquetaTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(etiquetaBienvenida);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelCodigoPostal);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(botonActualizar);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelClima);

        // Configurar acción del botón
        botonActualizar.addActionListener((ActionEvent e) -> {
            // Obtener el código postal ingresado
            String codigoPostal = campoCodigoPostal.getText();

            // Simular la obtención de datos del clima
            Clima clima = ServicioClima.obtenerClima(codigoPostal);

            // Actualizar la interfaz con los datos del clima
            actualizarInterfaz(clima);
        });

        add(panelPrincipal);
        setVisible(true);
    }

    private void agregarDatoClima(JPanel panel, String etiqueta, JLabel valor) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.add(new JLabel(etiqueta), BorderLayout.WEST);
        fila.add(valor, BorderLayout.EAST);
        panel.add(fila);
    }

    private void actualizarInterfaz(Clima clima) {
        etiquetaCondicion.setText(clima.getCondicion());
        etiquetaTemperatura.setText(String.format("%.2f°C", clima.getTemperatura())); // Formato de 2 decimales
        etiquetaHumedad.setText(String.format("%.2f%%", clima.getHumedad())); // Formato de 2 decimales
        etiquetaPrecipitacion.setText(String.format("%.2f%%", clima.getPrecipitacion())); // Formato de 2 decimales
        etiquetaViento.setText(String.format("%.2f km/h", clima.getViento())); // Formato de 2 decimales
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicacionClima());
    }
}

// Clase que representa los datos del clima
class Clima {
    private String condicion;
    private double temperatura;
    private double humedad;
    private double precipitacion;
    private double viento;

    public Clima(String condicion, double temperatura, double humedad, double precipitacion, double viento) {
        this.condicion = condicion;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.precipitacion = precipitacion;
        this.viento = viento;
    }

    public String getCondicion() {
        return condicion;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public double getViento() {
        return viento;
    }
}

// Servicio que simula la obtención de datos del clima
class ServicioClima {
    public static Clima obtenerClima(String codigoPostal) {
        // Simular la obtención de datos del clima (puedes cambiarlo para que sea aleatorio o predefinido)
        String condicion = "Soleado";
        double temperatura = Math.random() * 30 + 10; // Entre 10°C y 40°C
        double humedad = Math.random() * 100; // Entre 0% y 100%
        double precipitacion = Math.random() * 100; // Entre 0% y 100%
        double viento = Math.random() * 50; // Entre 0 km/h y 50 km/h

        return new Clima(condicion, temperatura, humedad, precipitacion, viento);
    }
}