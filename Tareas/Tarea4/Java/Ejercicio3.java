package Tarea04;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ejercicio3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejercicio3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Bienvenido a Ejercicio3");
            JButton button = new JButton("Cambiar texto");
            button.addActionListener(e -> {
                if(label.getText().equals("Bienvenido a Ejercicio3"))
                    label.setText("Has cambiado el texto");
                else
                    label.setText("Bienvenido a Ejercicio3");
            });
            panel.add(label);
            panel.add(button);
            frame.add(panel);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}