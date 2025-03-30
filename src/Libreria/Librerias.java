package Libreria;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

public class Librerias {

    public Icon EtiquetaImagen(String archivoimagen, int x, int y) {
        String ruta = archivoimagen;
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));
        return icono;
    }
    
    public JMenuBar menuspadre(JMenuBar barra, boolean accion) {
        if (barra == null) return null; // Validar nulos
        int cantmenus = barra.getMenuCount();
        for (int pos = 0; pos < cantmenus; pos++) {
            barra.getMenu(pos).setEnabled(accion);
        }
        return barra;
    }
}