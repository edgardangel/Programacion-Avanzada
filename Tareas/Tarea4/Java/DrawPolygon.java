package a2233336126_cursoUdemy_unidad01;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DrawPolygon extends JFrame {
	public DrawPolygon() {
        PolygonsPanel panel = new PolygonsPanel();
        add(panel);
        setTitle("Draw Polygon");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

	public static void main(String[] args) {
        DrawPolygon frame = new DrawPolygon();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 250);
        frame.setVisible(true);
    }

    class PolygonsPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int xCenter = getWidth() / 2;
            int yCenter = getHeight() / 2;
            int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);

            Polygon polygon = new Polygon();
            polygon.addPoint(xCenter + radius, yCenter);
            polygon.addPoint((int)(xCenter + radius * Math.cos(2 * Math.PI/6)), (int)(yCenter - radius * Math.sin(2 * Math.PI/6)));
            polygon.addPoint((int)(xCenter + radius * Math.cos(2 * 2 * Math.PI/6)), (int)(yCenter - radius * Math.sin(2 * 2 * Math.PI/6)));
            polygon.addPoint((int)(xCenter + radius * Math.cos(3 * 2 * Math.PI/6)), (int)(yCenter - radius * Math.sin(3 * 2 * Math.PI/6)));
            polygon.addPoint((int)(xCenter + radius * Math.cos(4 * 2 * Math.PI/6)), (int)(yCenter - radius * Math.sin(4 * 2 * Math.PI/6)));
            polygon.addPoint((int)(xCenter + radius * Math.cos(5 * 2 * Math.PI/6)), (int)(yCenter - radius * Math.sin(5 * 2 * Math.PI/6)));
            g.drawPolygon(polygon);
        }
    }
}