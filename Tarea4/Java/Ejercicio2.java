package Tarea04;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Ejercicio2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> crearYMostrarGUI());
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("Ejercicio2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Bienvenido a Ejercicio2", JLabel.CENTER);
        frame.getContentPane().add(label);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}