package Parte1.Controlador;

import Parte1.Vista.Practica01_01;
import Parte1.Vista.Practica01_01_b;
import Parte1.Vista.Practica01_02;
import Parte1.Vista.Practica01_03;

public class ControladorVentanas {

    public static void mostrarPractica01_01() {
        Practica01_01 frame = new Practica01_01();
        frame.setVisible(true);
    }
    
    public static void mostrarPractica01_01_b() {
        Practica01_01_b frame = new Practica01_01_b();
        frame.setVisible(true);
    }
    
    public static void mostrarPractica01_02() {
        Practica01_02 window = new Practica01_02();
        window.setVisible(true);
    }
    
    public static void mostrarPractica01_03() {
        Practica01_03 dialog = new Practica01_03();
        dialog.setDefaultCloseOperation(Practica01_03.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
