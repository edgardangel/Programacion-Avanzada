package Parte2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Practica04 extends JFrame implements ActionListener {

    private JMenuBar BarraMenu;
    private JMenu Moperacion, Mconfiguracion, Msalir;
    private JMenuItem SMsalida, SMcategorias, SMinsumos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Practica04 window = new Practica04();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Practica04() {
        // Configuración del JFrame
        setTitle("Practica04");
        setBounds(100, 100, 622, 395);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barra de Menú
        BarraMenu = new JMenuBar();
        setJMenuBar(BarraMenu);

        // Menú Operaciones
        Moperacion = new JMenu("Operacion");
        BarraMenu.add(Moperacion);

        // Menú Configuración
        Mconfiguracion = new JMenu("Configuracion");
        BarraMenu.add(Mconfiguracion);

        // Menú Salir
        Msalir = new JMenu("Salir");
        BarraMenu.add(Msalir);

        // Submenús
        SMcategorias = new JMenuItem("Categorias");
        Mconfiguracion.add(SMcategorias);

        SMinsumos = new JMenuItem("Insumos");
        Mconfiguracion.add(SMinsumos);

        SMsalida = new JMenuItem("Salida");
        Msalir.add(SMsalida);

        // Agregar ActionListener
        SMcategorias.addActionListener(this);
        SMinsumos.addActionListener(this);
        SMsalida.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SMsalida) {
            dispose();
        } else if (e.getSource() == SMcategorias) {
            JOptionPane.showMessageDialog(this, "Llamando a Conceptos");
        } else if (e.getSource() == SMinsumos) {
            Practica03_a hijo = new Practica03_a();
            hijo.setVisible(true);
        }
    }
}

