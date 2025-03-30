package Parte2;

import java.util.ArrayList;

public class ListaCategorias_d {
    private ArrayList<Categoria> categorias;

    public ListaCategorias_d() {
        this.categorias = new ArrayList<>();
    }

    public boolean agregarCategoria(Categoria categoria) {
        if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
            categorias.add(categoria);
            return true;
        }
        return false;
    }

    public boolean eliminarCategoriaPorId(String id) {
        Categoria cat = buscarCategoriaPorId(id);
        if (cat != null) {
            categorias.remove(cat);
            return true;
        }
        return false;
    }

    public Categoria buscarCategoriaPorId(String id) {
        for (Categoria cat : categorias) {
            if (cat.getIdcategoria().equals(id)) {
                return cat;
            }
        }
        return null;
    }

    public Object[] CategoriasArreglo() {
        return categorias.toArray();
    }

    public void cargarCategorias(ArrayList<String[]> lista) {
        for (String[] datos : lista) {
            if (datos.length >= 2) {
                String id = datos[0];
                String nombre = datos[1];
                Categoria cat = new Categoria(id, nombre);
                this.agregarCategoria(cat);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Categoria cat : categorias) {
            sb.append(cat.toString()).append("\n");
        }
        return sb.toString();
    }
}