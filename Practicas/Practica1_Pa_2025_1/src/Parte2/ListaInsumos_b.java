package Parte2;

import java.util.ArrayList;

public class ListaInsumos_b {
    private ArrayList<Insumo> insumos;

    public ListaInsumos_b() {
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

    public void cargarInsumo(ArrayList<String[]> insumosString) {
        for (String[] insArr : insumosString) {
            if (insArr.length >= 3) {
                String id = insArr[0];
                String producto = insArr[1];
                String idCat = insArr[2];
                Insumo nodo = new Insumo(id, producto, idCat);
                this.agregarInsumo(nodo);
            }
        }
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