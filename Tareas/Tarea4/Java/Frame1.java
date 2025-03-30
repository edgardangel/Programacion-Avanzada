package a2233336126_cursoUdemy_unidad01;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame1 extends JFrame {

    JPanel pane = new JPanel();

    Frame1() {
        super("My Simple Frame");
        setBounds(100, 100, 300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container con = this.getContentPane();
        con.add(pane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame1();
    }
}