package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entidad {
    private String nombre;
    private List<DefinicionAtributo> atributos;

    public Entidad() {
        this.atributos = new ArrayList<>();
    }

    public Entidad(String nombre) {
        this.nombre = nombre;
        this.atributos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DefinicionAtributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<DefinicionAtributo> atributos) {
        this.atributos = atributos != null ? new ArrayList<>(atributos) : new ArrayList<>();
    }

    public void addAtributo(DefinicionAtributo atributo) {
        atributos.add(atributo);
    }

    public void removeAtributo(DefinicionAtributo atributo) {
        atributos.remove(atributo);
    }

    @Override
    public String toString() {
        return nombre;
    }
}