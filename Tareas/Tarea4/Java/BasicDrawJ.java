package a2233336126_cursoUdemy_unidad01;

import javax.swing.*;
import java.awt.*;
import javax.swing.JApplet;

public class BasicDrawJ extends JApplet {
    public class GPanel extends JPanel {
        public GPanel() {
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    public BasicDrawJ() {
        GPanel pane = new GPanel();
        add(pane);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RectangleDraw");
        BasicDrawJ applet = new BasicDrawJ();
        applet.init();
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
