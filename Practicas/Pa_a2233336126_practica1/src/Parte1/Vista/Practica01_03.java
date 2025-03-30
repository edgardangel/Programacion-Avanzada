package Parte1.Vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Parte1.Controlador.ControladorVentanas;

public class Practica01_03 extends JDialog {
	private JPanel contentPane;

	public static void main(String[] args) {
		ControladorVentanas.mostrarPractica01_03();
	}

	public Practica01_03() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
}
