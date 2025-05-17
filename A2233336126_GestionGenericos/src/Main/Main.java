package Main;

import javax.swing.SwingUtilities;

import controlador.ControladorEntidades;
import vista.VistaEntidades;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaEntidades vistaEntidades = new VistaEntidades();
            new ControladorEntidades(vistaEntidades);
            vistaEntidades.setVisible(true);
        });
    }
}