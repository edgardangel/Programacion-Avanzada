package Parte2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Practica05 extends JFrame implements ActionListener {

    private JDesktopPane escritorio; // Contenedor para ventanas internas
    private JMenuBar BarraMenu;
    private JMenu Moperacion, Mconfiguracion, Msalir;
    private JMenuItem SMsalida, SMcategorias, SMinsumos, SMobras;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Practica05().setVisible(true);
        });
    }

    public Practica05() {
        crearVentana();
        crearMenus();
    }

    private void crearVentana() {
        setTitle("Sistema de Gestión - Practica05");
        setSize(622, 395);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar JDesktopPane
        escritorio = new JDesktopPane();
        getContentPane().add(escritorio, BorderLayout.CENTER);
    }

    private void crearMenus() {
        BarraMenu = new JMenuBar();
        setJMenuBar(BarraMenu);

        // Menú Operación
        Moperacion = new JMenu("Operación");
        BarraMenu.add(Moperacion);

        // Menú Configuración
        Mconfiguracion = new JMenu("Configuración");
        BarraMenu.add(Mconfiguracion);

        // Items de Configuración
        SMcategorias = new JMenuItem("Categorías");
        SMinsumos = new JMenuItem("Insumos");
        Mconfiguracion.add(SMcategorias);
        Mconfiguracion.add(SMinsumos);

        // Menú Salir
        Msalir = new JMenu("Salir");
        BarraMenu.add(Msalir);

        // Item Salida
        SMsalida = new JMenuItem("Salir");
        Msalir.add(SMsalida);
        
        SMobras = new JMenuItem("Obras");
        Mconfiguracion.add(SMobras);
        SMobras.addActionListener(this);

        // Listeners
        SMcategorias.addActionListener(this);
        SMinsumos.addActionListener(this);
        SMsalida.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SMsalida) {
            System.exit(0);
        } else if (e.getSource() == SMcategorias) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de Categorías no implementada");
        } else if (e.getSource() == SMinsumos) {
            abrirVentanaInsumos();
        } else if (e.getSource() == SMobras) {
            abrirVentanaObras();
        }
    }

    private void abrirVentanaObras() {
        Practica03_Obras ventanaObras = new Practica03_Obras();
        ventanaObras.setVisible(true);
        escritorio.add(ventanaObras);
        ventanaObras.setLocation(
            (escritorio.getWidth() - ventanaObras.getWidth()) / 2,
            (escritorio.getHeight() - ventanaObras.getHeight()) / 2
        );
    }
    
    private void abrirVentanaInsumos() {
        Practica03_c ventanaInsumos = new Practica03_c(BarraMenu);
        ventanaInsumos.setVisible(true);
        
        // Centrar ventana en el escritorio
        int x = (escritorio.getWidth() - ventanaInsumos.getWidth()) / 2;
        int y = (escritorio.getHeight() - ventanaInsumos.getHeight()) / 2;
        ventanaInsumos.setLocation(x, y);
        
        escritorio.add(ventanaInsumos);
        ventanaInsumos.toFront(); // Traer al frente
    }
}