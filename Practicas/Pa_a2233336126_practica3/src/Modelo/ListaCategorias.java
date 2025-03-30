/*package Modelo;


import java.util.ArrayList;

import Libreria.Archivotxt;

public class ListaCategorias {
    private ArrayList<Categoria> categorias;
    private Archivotxt archivo;

    public ListaCategorias() {
        categorias = new ArrayList<>();
        archivo = new Archivotxt("Categorias.txt");
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<String[]> data = archivo.cargar();
        if (data != null) {
            for (String[] d : data) {
                categorias.add(new Categoria(d[0], d[1]));
            }
        }
    }

    public void agregarCategoria(Categoria cat) {
        cat.setCategoria(generarId());
        categorias.add(cat);
        guardarDatos();
    }

    public void eliminarCategoria(int index) {
        categorias.remove(index);
        guardarDatos();
    }

    public void actualizarCategoria(int index, Categoria nuevaCat) {
        categorias.set(index, nuevaCat);
        guardarDatos();
    }

    private String generarId() {
        return String.format("%03d", categorias.size() + 1);
    }

    private void guardarDatos() {
        StringBuilder sb = new StringBuilder();
        for (Categoria cat : categorias) {
            sb.append(cat.toLinea()).append("\n");
        }
        archivo.guardar(sb.toString());
    }

    public ArrayList<Categoria> getCategorias() { return categorias; }
    
    public Categoria buscarCategoriaPorId(String id) {
        for (Categoria cat : categorias) {
            if (cat.getIdcategoria().equals(id)) return cat;
        }
        return null;
    }
} */

package Modelo;

import Libreria.ArchivoJSON;
import Libreria.ArchivoXML;
import Libreria.Archivotxt;
import java.util.ArrayList;
import java.util.List;

public class ListaCategorias {
    private ArrayList<Categoria> categorias;
    private Archivotxt archivo;

    public ListaCategorias() {
        categorias = new ArrayList<>();
        archivo = new Archivotxt("Categorias.txt");
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<String[]> data = archivo.cargar();
        if (data != null) {
            for (String[] d : data) {
                categorias.add(new Categoria(d[0], d[1]));
            }
        }
    }

    public void agregarCategoria(Categoria cat) {
        cat.setCategoria(generarId());
        categorias.add(cat);
        guardarDatos();
    }

    public void eliminarCategoria(int index) {
        categorias.remove(index);
        guardarDatos();
    }

    public void actualizarCategoria(int index, Categoria nuevaCat) {
        categorias.set(index, nuevaCat);
        guardarDatos();
    }

    private String generarId() {
        return String.format("%03d", categorias.size() + 1);
    }

    private void guardarDatos() {
        StringBuilder sb = new StringBuilder();
        for (Categoria cat : categorias) {
            sb.append(cat.toLinea()).append("\n");
        }
        archivo.guardar(sb.toString());
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public Categoria buscarCategoriaPorId(String id) {
        for (Categoria cat : categorias) {
            if (cat.getIdcategoria().equals(id)) {
                return cat;
            }
        }
        return null;
    }
    

    // Cargar desde XML
    public static ListaCategorias cargarXML(String ruta) {
        return new ArchivoXML<>(ListaCategorias.class).cargar(ruta);
    }

    

    // Cargar desde JSON
    public static ListaCategorias cargarJSON(String ruta) {
        return (ListaCategorias) new ArchivoJSON<>().cargar(ruta, ListaCategorias.class);
    }

    // Datos para Excel
    public List<Object[]> getDatosParaExcel() {
        List<Object[]> datos = new ArrayList<>();
        for (Categoria cat : categorias) {
            datos.add(new Object[]{cat.getIdcategoria(), cat.getCategoria()});
        }
        return datos;
    }
    
    public void guardarXML(String ruta) {
        new ArchivoXML<>(ListaCategorias.class).guardar(this, ruta);
    }

    public void guardarJSON(String ruta) {
        new ArchivoJSON<>().guardar(this, ruta);
    }
    
}
