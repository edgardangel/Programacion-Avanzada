package Parte2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Lista<T> {
    private List<T> lista;

    public Lista() {
        this.lista = new ArrayList<>();
    }

    public boolean insertar(T elemento) {
        if (!lista.contains(elemento)) {
            lista.add(elemento);
            return true;
        }
        return false; 
    }

    public boolean eliminar(T elemento) {
        return lista.remove(elemento);
    }

    public boolean modificar(T antiguo, T nuevo) {
        int index = lista.indexOf(antiguo);
        if (index != -1) {
            lista.set(index, nuevo);
            return true;
        }
        return false; 
    }

    public Optional<T> buscar(T elemento) {
        return lista.contains(elemento) ? Optional.of(elemento) : Optional.empty();
    }

    public String[] toStringArreglo(String atributo) {
        String[] datos = new String[this.lista.size()];
        int pos = 0;

        for (T elemento : this.lista) {
            try {
                Field campo = elemento.getClass().getDeclaredField(atributo);
                campo.setAccessible(true);
                Object valor = campo.get(elemento);
                datos[pos] = (valor != null) ? valor.toString() : "null";
            } catch (NoSuchFieldException | IllegalAccessException e) {
                datos[pos] = "Atributo no encontrado";
            }
            pos++;
        }
        return datos;
    }

    public List<T> obtenerLista() {
        return new ArrayList<>(lista);
    }

    public javax.swing.JComboBox<T> agregarAComboBox(javax.swing.JComboBox<T> comboBox) {
        comboBox.removeAllItems();
        for (T item : lista) {
            comboBox.addItem(item);
        }
        return comboBox;
    }
}