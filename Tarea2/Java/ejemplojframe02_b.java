package a2233336126_tareas_unidad01;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ejemplojframe02_b extends JFrame {

    JLabel a, b;

    public ejemplojframe02_b() {
        this.setLayout(null);
        this.setTitle("2020-3");
        this.setBounds(10, 20, 300, 200);

        a = new JLabel("el triangulo de base 2 * 5");
        a.setBounds(10, 10, 200, 30);

        b = new JLabel();
        b.setBounds(10, 50, 300, 30);

        add(a);
        add(b);
    }

    public static void main(String[] args) {
        ejemplojframe02_b ventana = new ejemplojframe02_b();
        ventana.setVisible(true);
    }
}
