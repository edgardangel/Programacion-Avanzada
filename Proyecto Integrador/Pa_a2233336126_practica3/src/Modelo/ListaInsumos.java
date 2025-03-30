/*package Modelo;

import Libreria.Archivotxt;
import java.util.ArrayList;

public class ListaInsumos {
    private ArrayList<Insumo> insumos;
    private Archivotxt archivo;
    private ListaCategorias listaCategorias;

    public ListaInsumos() {
    	listaCategorias = new ListaCategorias(); // Cargar categorías primero
        insumos = new ArrayList<>();
        archivo = new Archivotxt("Insumos.txt");
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<String[]> data = archivo.cargar();
        if (data != null) {
            for (String[] d : data) {
                insumos.add(new Insumo(d[0], d[1], d[2]));
            }
        }
    }

    public void agregarInsumo(Insumo insumo) {
        insumo = new Insumo(generarId(), insumo.getProducto(), insumo.getIdCategoria());
        insumos.add(insumo);
        archivo.guardar(toTexto());
    }

    public void eliminarInsumoPorId(String id) {
        insumos.removeIf(i -> i.getIdProducto().equals(id));
        archivo.guardar(toTexto());
    }

    private String generarId() {
        return String.format("%03d", insumos.size() + 1);
    }

    private String toTexto() {
        StringBuilder sb = new StringBuilder();
        for (Insumo i : insumos) {
            sb.append(i.getIdProducto()).append(",")
              .append(i.getProducto()).append(",")
              .append(i.getIdCategoria()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Insumo> getInsumos() { return insumos; }
}
*/

package Modelo;

import Libreria.ArchivoJSON;
import Libreria.ArchivoXML;
import Libreria.Archivotxt;
import java.util.ArrayList;
import java.util.List;

public class ListaInsumos {
    private ArrayList<Insumo> insumos;
    private Archivotxt archivo;
    private ListaCategorias listaCategorias;

    public ListaInsumos() {
        listaCategorias = new ListaCategorias();
        insumos = new ArrayList<>();
        archivo = new Archivotxt("Insumos.txt");
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<String[]> data = archivo.cargar();
        if (data != null) {
            for (String[] d : data) {
                insumos.add(new Insumo(d[0], d[1], d[2]));
            }
        }
    }

    public void agregarInsumo(Insumo insumo) {
        insumo = new Insumo(generarId(), insumo.getProducto(), insumo.getIdCategoria());
        insumos.add(insumo);
        archivo.guardar(toTexto());
    }

    public void eliminarInsumoPorId(String id) {
        insumos.removeIf(i -> i.getIdProducto().equals(id));
        archivo.guardar(toTexto());
    }

    private String generarId() {
        return String.format("%03d", insumos.size() + 1);
    }

    private String toTexto() {
        StringBuilder sb = new StringBuilder();
        for (Insumo i : insumos) {
            sb.append(i.getIdProducto()).append(",")
              .append(i.getProducto()).append(",")
              .append(i.getIdCategoria()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Insumo> getInsumos() {
        return insumos;
    }
    
 // Guardar en XML
    public void guardarXML(String ruta) {
        new ArchivoXML<>(ListaInsumos.class).guardar(this, ruta);
    }

    // Guardar en JSON
    public void guardarJSON(String ruta) {
        new ArchivoJSON<>().guardar(this, ruta);
    }

    // Datos para Excel
    public List<Object[]> getDatosParaExcel() {
        List<Object[]> datos = new ArrayList<>();
        for (Insumo insumo : insumos) {
            datos.add(new Object[]{
                insumo.getIdProducto(),
                insumo.getProducto(),
                insumo.getIdCategoria()
            });
        }
        return datos;
    }
}
