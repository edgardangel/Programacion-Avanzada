package Modelo2;

import Libreria.Archivotxt;
import java.util.ArrayList;

public class ListaObras {
    private ArrayList<Obra> obras;
    private Archivotxt archivo;

    public ListaObras() {
        obras = new ArrayList<>();
        archivo = new Archivotxt("Obras.txt");
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<String[]> data = archivo.cargar();
        if (data != null) {
            for (String[] d : data) {
                obras.add(new Obra(d[0], d[1], d[2]));
            }
        }
    }

    public void agregarObra(Obra obra) {
        obra.setId(generarId());
        obras.add(obra);
        guardarDatos();
    }

    public void eliminarObra(int index) {
        obras.remove(index);
        guardarDatos();
    }

    public void actualizarObra(int index, Obra nuevaObra) {
        obras.set(index, nuevaObra);
        guardarDatos();
    }

    private String generarId() {
        return String.format("%03d", obras.size() + 1);
    }

    private void guardarDatos() {
        StringBuilder sb = new StringBuilder();
        for (Obra o : obras) {
            sb.append(o.toLinea()).append("\n");
        }
        archivo.guardar(sb.toString());
    }

    public ArrayList<Obra> getObras() { return obras; }
}