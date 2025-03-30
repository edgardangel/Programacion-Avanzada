package a2233336126_tareas_unidad01;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ejemploJframe_03_a extends JFrame {
    static JLabel La, Lb;
    static JButton Bboton;

    public ejemploJframe_03_a() {
        this.setLayout(null);
        this.setBounds(10, 10, 300, 300);

        La = new JLabel("el triangulo de base 5 y de altura 2");
        La.setBounds(10, 10, 200, 30);

        Lb = new JLabel();
        Lb.setBounds(10, 100, 200, 30);

        Bboton = new JButton("Calcular");
        Bboton.setBounds(10, 50, 100, 30);

        this.add(La);
        this.add(Lb);
        this.add(Bboton);
    }

    public static void main(String parametro[]) {
        ejemploJframe_03_a ventana = new ejemploJframe_03_a();
        ventana.setVisible(true);
        Lb.setText("el area del triangulo es 5");
    }
}