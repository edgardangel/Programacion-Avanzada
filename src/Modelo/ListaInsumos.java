package Modelo;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ListaInsumos {
    private ArrayList<Insumo> insumos;

    public ListaInsumos () {
        this.insumos = new ArrayList<>();
    }

    public boolean agregarInsumo(Insumo insumo) {
        if (buscarInsumoPorId(insumo.getIdProducto()) == null) {
            insumos.add(insumo);
            return true;
        }
        return false;
    }

    public boolean eliminarInsumoPorId(String id) {
        Insumo ins = buscarInsumoPorId(id);
        if (ins != null) {
            insumos.remove(ins);
            return true;
        }
        return false;
    }

    public Insumo buscarInsumoPorId(String id) {
        for (Insumo ins : insumos) {
            if (ins.getIdProducto().equals(id)) {
                return ins;
            }
        }
        return null;
    }

    public String buscarInsumo(String id) {
        for (Insumo ins : insumos) {
            if (ins.getIdProducto().equals(id)) {
                return ins.getProducto();
            }
        }
        return null;
    }

    public String[] idinsumos() {
        String[] datos = new String[insumos.size()];
        for (int i = 0; i < insumos.size(); i++) {
            datos[i] = insumos.get(i).getIdProducto();
        }
        return datos;
    }

    public String toArchivo() {
        String resultado = "";
        for (Insumo insumo : insumos) {
            resultado += insumo.toLinea() + "\n";
        }
        return resultado;
    }

    public void cargarInsumo(ArrayList<String[]> insumosString) {
        for (String[] categoriaString : insumosString) {
            String id = categoriaString[0];
            String insumo = categoriaString[1];
            String idCategoria = categoriaString[2];
            Insumo nodo = new Insumo(id, insumo, idCategoria);
            this.agregarInsumo(nodo);
        }
    }
    
    public DefaultTableModel getmodelo(ListaCategorias listac) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Id");
        modelo.addColumn("Insumo");
        modelo.addColumn("Categoria");

        for (Insumo nodo : this.insumos) {
            String[] info = new String[3];
            info[0] = nodo.getIdProducto().trim();
            info[1] = nodo.getProducto().trim();
            
            // Verifica que buscarCategoriaPorId devuelva un objeto válido
            Categoria categoria = listac.buscarCategoriaPorId(nodo.getIdCategoria().trim());
            info[2] = (categoria != null) ? categoria.getCategoria() : "Desconocido";

            modelo.addRow(info);
        }
        return modelo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Insumo ins : insumos) {
            sb.append(ins.toString()).append("\n");
        }
        return sb.toString();
    }
    public ArrayList<Insumo> getInsumos() {
        return this.insumos;
    }
}
