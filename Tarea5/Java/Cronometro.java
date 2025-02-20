package tarea05;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Cronometro extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panelPrincipal;
    private JButton botonIniciar, botonDetener, botonSalir;
    private JLabel etiquetaInicio, etiquetaDetener, etiquetaSalida;
    private JTextField campoInicio, campoDetener, campoSalida;
    private long tiempoInicio, tiempoDetener;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Cronometro frame = new Cronometro();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Cronometro() {
        setTitle("Aplicación Cronómetro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 200);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelPrincipal);
        GridBagLayout layout = new GridBagLayout();
        panelPrincipal.setLayout(layout);

        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        GridBagConstraints gbcBotonIniciar = new GridBagConstraints();
        gbcBotonIniciar.insets = new Insets(5, 5, 5, 5);
        gbcBotonIniciar.gridx = 0;
        gbcBotonIniciar.gridy = 0;
        panelPrincipal.add(botonIniciar, gbcBotonIniciar);

        etiquetaInicio = new JLabel("Tiempo de inicio");
        GridBagConstraints gbcEtiquetaInicio = new GridBagConstraints();
        gbcEtiquetaInicio.insets = new Insets(5, 5, 5, 5);
        gbcEtiquetaInicio.gridx = 1;
        gbcEtiquetaInicio.gridy = 0;
        panelPrincipal.add(etiquetaInicio, gbcEtiquetaInicio);

        campoInicio = new JTextField(10);
        campoInicio.setEditable(false);
        GridBagConstraints gbcCampoInicio = new GridBagConstraints();
        gbcCampoInicio.insets = new Insets(5, 5, 5, 5);
        gbcCampoInicio.gridx = 2;
        gbcCampoInicio.gridy = 0;
        panelPrincipal.add(campoInicio, gbcCampoInicio);

        botonDetener = new JButton("Detener");
        botonDetener.addActionListener(this);
        GridBagConstraints gbcBotonDetener = new GridBagConstraints();
        gbcBotonDetener.insets = new Insets(5, 5, 5, 5);
        gbcBotonDetener.gridx = 0;
        gbcBotonDetener.gridy = 1;
        panelPrincipal.add(botonDetener, gbcBotonDetener);

        etiquetaDetener = new JLabel("Tiempo de detención");
        GridBagConstraints gbcEtiquetaDetener = new GridBagConstraints();
        gbcEtiquetaDetener.insets = new Insets(5, 5, 5, 5);
        gbcEtiquetaDetener.gridx = 1;
        gbcEtiquetaDetener.gridy = 1;
        panelPrincipal.add(etiquetaDetener, gbcEtiquetaDetener);

        campoDetener = new JTextField(10);
        campoDetener.setEditable(false);
        GridBagConstraints gbcCampoDetener = new GridBagConstraints();
        gbcCampoDetener.insets = new Insets(5, 5, 5, 5);
        gbcCampoDetener.gridx = 2;
        gbcCampoDetener.gridy = 1;
        panelPrincipal.add(campoDetener, gbcCampoDetener);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        GridBagConstraints gbcBotonSalir = new GridBagConstraints();
        gbcBotonSalir.insets = new Insets(5, 5, 5, 5);
        gbcBotonSalir.gridx = 0;
        gbcBotonSalir.gridy = 2;
        panelPrincipal.add(botonSalir, gbcBotonSalir);

        etiquetaSalida = new JLabel("Tiempo transcurrido (seg)");
        GridBagConstraints gbcEtiquetaSalida = new GridBagConstraints();
        gbcEtiquetaSalida.insets = new Insets(5, 5, 5, 5);
        gbcEtiquetaSalida.gridx = 1;
        gbcEtiquetaSalida.gridy = 2;
        panelPrincipal.add(etiquetaSalida, gbcEtiquetaSalida);

        campoSalida = new JTextField(10);
        campoSalida.setEditable(false);
        GridBagConstraints gbcCampoSalida = new GridBagConstraints();
        gbcCampoSalida.insets = new Insets(5, 5, 5, 5);
        gbcCampoSalida.gridx = 2;
        gbcCampoSalida.gridy = 2;
        panelPrincipal.add(campoSalida, gbcCampoSalida);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            tiempoInicio = System.currentTimeMillis();
            campoInicio.setText(formatearTiempo(tiempoInicio));
            campoDetener.setText("");
            campoSalida.setText("");
        } 
        else if (e.getSource() == botonDetener) {
            if (tiempoInicio == 0) {
                campoSalida.setText("Error: Falta iniciar.");
                return;
            }
            tiempoDetener = System.currentTimeMillis();
            campoDetener.setText(formatearTiempo(tiempoDetener));
            
            double tiempoTranscurrido = (tiempoDetener - tiempoInicio) / 1000.0;
            campoSalida.setText(String.format("%.2f", tiempoTranscurrido));
        } 
        else if (e.getSource() == botonSalir) {
            dispose();
        }
    }
    private String formatearTiempo(long tiempo) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(new Date(tiempo));
    }
}
