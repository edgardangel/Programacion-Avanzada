package Parte1.Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

import Parte1.Controlador.ControladorVentanas;

public class Practica01_02 extends JWindow {
	private JPanel contentPane;

	public static void main(String[] args) {
		ControladorVentanas.mostrarPractica01_02();
	}

	public Practica01_02() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
}