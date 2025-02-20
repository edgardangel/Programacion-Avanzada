package tarea08;
import javax.swing.*;
import java.awt.*;

public class BillingPoint extends JFrame {

    public BillingPoint() {
        setTitle("Billing Point");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para la lista de productos
        JPanel productsPanel = new JPanel(new GridLayout(0, 5));
        productsPanel.add(new JLabel("PRODID"));
        productsPanel.add(new JLabel("PRODNAME"));
        productsPanel.add(new JLabel("PRODQTY"));
        productsPanel.add(new JLabel("PRODPRI"));
        productsPanel.add(new JLabel("PRODCAT"));
        // ... agregar componentes para la lista de productos

        add(productsPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BillingPoint();
    }
    
}