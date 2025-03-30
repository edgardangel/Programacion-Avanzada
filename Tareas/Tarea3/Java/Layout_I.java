package tarea03_Layouts;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout_I {

	public static void main(String[] args) {
		
		Marco_Layout marco = new Marco_Layout();
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);

	}
}
/*
class Marco_Layout extends JFrame {
	public Marco_Layout() {
		setTitle("Prueba de acciones");
		setBounds(600,350,600,300);
		Panel_Layout lamina = new Panel_Layout();
		//FlowLayout disposicion = new FlowLayout (FlowLayout.RIGHT);
		//lamina.setLayout(disposicion);
		lamina.setLayout(new FlowLayout (FlowLayout.LEFT));
		add(lamina);
	}
}

class Panel_Layout extends JPanel {
	public Panel_Layout () {
		add (new JButton ("Amarillo"));
		add (new JButton ("Rojo"));
		add (new JButton ("Azul"));
	}
}*/