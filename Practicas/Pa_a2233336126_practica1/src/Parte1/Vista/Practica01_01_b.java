package Parte1.Vista;

import Parte1.Controlador.ControladorVentanas;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Practica01_01_b extends JFrame {

    private JTabbedPane contentPane;

    public static void main(String[] args) {
        ControladorVentanas.mostrarPractica01_01_b();
    }

    public Practica01_01_b() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        setTitle("Frame Practica01_01_b");

        contentPane = new JTabbedPane();

        JPanel panel = new JPanel();
        contentPane.add("Pestaña 1", panel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        contentPane.add("Pestaña 2", scrollPane);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane deskTopPane = new JDesktopPane();
        contentPane.addTab("Pestaña 3", null, deskTopPane, null);
        
        JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
        contentPane.addTab("Pestaña 4", null, internalFrame, null);
        internalFrame.setVisible(true);
    }
}
