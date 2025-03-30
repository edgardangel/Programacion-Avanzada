package Parte2;

import java.util.ArrayList;

public class ListaCategorias_b {
    private ArrayList<Categoria> categorias;

    public ListaCategorias_b() {
        this.categorias = new ArrayList<>();
    }


    public boolean agregarCategoria(Categoria categoria) {
        if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
            this.categorias.add(categoria);
            return true;
        } else {
            return false;
        }
    }


    public boolean eliminarCategoriaPorId(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            this.categorias.remove(categoria);
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
        return this.categorias.toArray();
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