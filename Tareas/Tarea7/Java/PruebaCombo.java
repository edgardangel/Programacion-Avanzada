package tarea07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PruebaCombo {
    public static void main(String[] args) {
        MarcoCombo miMarco = new MarcoCombo();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoCombo extends JFrame {
    public MarcoCombo() {
        setVisible(true);
        setBounds(550, 300, 550, 400);
        LaminaCombo miLamina = new LaminaCombo();
        add(miLamina);
    }
}

class LaminaCombo extends JPanel {
	
    public LaminaCombo() {
        setLayout(new BorderLayout());

        texto = new JLabel("En un lugar de la mancha de cuyo nombre...");
        texto.setFont(new Font("Serif", Font.PLAIN, 18));
        add(texto, BorderLayout.CENTER);

        JPanel lamina_norte = new JPanel(); 
        micombo = new JComboBox();
        micombo.setEditable(true);
        micombo.addItem("Serif");
        micombo.addItem("SansSerif");
        micombo.addItem("Monospaced");
        micombo.addItem("Dialog");

        Evento_combo mievento = new Evento_combo();
        micombo.addActionListener(mievento);
        lamina_norte.add(micombo);
        add(lamina_norte, BorderLayout.NORTH);
    }

    private class Evento_combo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fuenteSeleccionada = (String) micombo.getSelectedItem();
            texto.setFont(new Font((String)micombo.getSelectedItem(), Font.PLAIN, 18));
        }
    }
    
    private JComboBox micombo;
    private JLabel texto;
}