package Modelo;

import java.util.ArrayList;

import Libreria.Archivotxt;

public class ListaObras {
    private ArrayList<Obra> obras;
    private Archivotxt archivo;

    public ListaObras() {
        this.obras = new ArrayList<>();
        this.archivo = new Archivotxt("Obras.txt");
        cargarObras();
    }

    private void cargarObras() {
        ArrayList<String[]> datos = archivo.cargar();
        if (datos != null) {
            for (String[] obraData : datos) {
                obras.add(new Obra(obraData[0], obraData[1], obraData[2]));
            }
        }
    }

    public void agregarObra(Obra obra) {
        obras.add(obra);
        archivo.guardar(toArchivo());
    }

    private String toArchivo() {
        StringBuilder sb = new StringBuilder();
        for (Obra o : obras) {
            sb.append(o.toLinea()).append("\n");
        }
        return sb.toString();
    }

    public String generarNuevoId() {
        return String.format("%03d", obras.size() + 1);
    }
}
