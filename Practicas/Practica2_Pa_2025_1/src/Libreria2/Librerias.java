package Libreria2;

import javax.swing.JMenuBar;

public class Librerias {
    
    // Habilitar/deshabilitar todos los menús
    public JMenuBar menuspadre(JMenuBar barra, boolean accion) {
        if (barra == null) return null;
        
        int totalMenus = barra.getMenuCount();
        for (int i = 0; i < totalMenus; i++) {
            barra.getMenu(i).setEnabled(accion);
        }
        return barra;
    }
}