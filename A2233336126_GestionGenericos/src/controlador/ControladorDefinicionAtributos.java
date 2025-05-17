package controlador;

import modelo.DefinicionAtributo;
import modelo.Entidad;
import vista.VistaAtributos;

import java.util.List;

public class ControladorDefinicionAtributos {
    private VistaAtributos vistaAtributos;
    private Entidad entidad;

    public ControladorDefinicionAtributos(VistaAtributos vistaAtributos, Entidad entidad) {
        this.vistaAtributos = vistaAtributos;
        this.entidad = entidad;
        this.vistaAtributos.setControlador(this);
        this.vistaAtributos.mostrarAtributos(entidad.getAtributos());
    }

    public void agregarAtributo(String nombre, String tipoDato, Object valor) {
        DefinicionAtributo atributo = new DefinicionAtributo(nombre, tipoDato, valor);
        entidad.addAtributo(atributo);
        vistaAtributos.agregarFilaTabla(atributo);
    }

    public void eliminarAtributo(DefinicionAtributo atributo) {
        entidad.removeAtributo(atributo);
        vistaAtributos.actualizarTabla();
    }

    public void modificarAtributo(DefinicionAtributo atributo, String nuevoNombre, String nuevoTipoDato, Object nuevoValor) {
        atributo.setNombre(nuevoNombre);
        atributo.setTipoDato(nuevoTipoDato);
        atributo.setValor(nuevoValor != null ? nuevoValor.toString() : ""); // ✅ Convertimos a String
        vistaAtributos.actualizarFilaTabla(atributo);
    }

    public void salir() {
        vistaAtributos.dispose();
    }
}