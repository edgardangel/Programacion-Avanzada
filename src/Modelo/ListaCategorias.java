package Modelo;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class ListaCategorias {
    private ArrayList<Categoria> categorias;

    public ListaCategorias() {
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
    
    public void cargarCategorias(ArrayList<String[]> categoriasString) {
        for (String[] categoriaString : categoriasString) {
            String idCategoria = categoriaString[0];
            String nombreCategoria = categoriaString[1];
            Categoria categoria = new Categoria(idCategoria, nombreCategoria);
            this.agregarCategoria(categoria);
        }
    }

    public DefaultListModel<Categoria> generarModeloCategorias() {
        DefaultListModel<Categoria> modelo = new DefaultListModel<>();
        for (Categoria categoria : categorias) {
            modelo.addElement(categoria);
        }
        return modelo;
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
