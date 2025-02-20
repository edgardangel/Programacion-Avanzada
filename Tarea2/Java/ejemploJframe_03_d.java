package a2233336126_tareas_unidad01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ejemploJframe_03_d extends JFrame {

    static JLabel La, Lb;
    static JButton Bboton;

    public ejemploJframe_03_d() {
        this.setLayout(null);
        this.setBounds(10, 10, 300, 300);

        La = new JLabel("el triangulo de base 5 y de altura 2");
        La.setBounds(10, 10, 200, 30);

        Lb = new JLabel();
        Lb.setBounds(10, 100, 200, 30);

        Bboton = new JButton();
        Bboton.setText("Calcular");
        Bboton.setBounds(10, 50, 100, 30);

        Bboton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lb.setText("el area del triangulo es 5");
            }
        });

        this.add(La);
        this.add(Lb);
        this.add(Bboton);
    }

    public static void main(String parametro[]) {
        ejemploJframe_03_d ventana = new ejemploJframe_03_d();
        ventana.setVisible(true);
    }
}