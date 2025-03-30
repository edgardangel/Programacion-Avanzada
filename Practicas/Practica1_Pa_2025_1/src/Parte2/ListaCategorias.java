package Parte2;

import java.util.ArrayList;
import javax.swing.JComboBox;

public class ListaCategorias {
    private ArrayList<Categoria> categorias;

    public ListaCategorias() {
        this.categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        if (buscarCategoriaPorId(categoria.getIdcategoria()) == null) {
            this.categorias.add(categoria);
        }
    }

    public void eliminarCategoriaPorId(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            this.categorias.remove(categoria);
        }
    }

    public String toString() {
        String resultado = "";
        for (Categoria categoria : categorias) {
            resultado += categoria.toString() + "\n";
        }
        return resultado;
    }

    Categoria buscarCategoriaPorId(String id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdcategoria().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    public JComboBox cargarListaCategoriaAComboBox(JComboBox comboBox) {
    	JComboBox aux = comboBox;
        aux.removeAllItems();
        for (Categoria categoria : categorias) {
            aux.addItem(categoria);
        }
        return aux;
    }

    public Object[] CategoriasArreglo() {
        return this.categorias.toArray();
    }
}