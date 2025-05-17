package patrones.factory;

import modelo.Entidad;

public class EntidadFactory {
    public static Entidad crearEntidad(String nombre) {
        return new Entidad(nombre);
    }
}