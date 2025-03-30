package a2233336126_cursoUdemy_unidad01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HandleEvent extends JFrame {
    JButton jbtOK = new JButton("OK");
    JButton jbtCancel = new JButton("Cancel");

    public HandleEvent() {
        JPanel panel = new JPanel();
        panel.add(jbtOK);
        panel.add(jbtCancel);
        add(panel);

        OKListenerClass listener1 = new OKListenerClass();
        CancelListenerClass listener2 = new CancelListenerClass();
        jbtOK.addActionListener(listener1);
        jbtCancel.addActionListener(listener2);

        setTitle("Handle Event");
        setSize(200, 150);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame frame = new HandleEvent();
        frame.setVisible(true);
    }

    class OKListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("OK button clicked");
        }
    }

    class CancelListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel button clicked");
        }
    }
}