package Modelo2;

public class Insumo {
    private String idProducto;
    private String producto;
    private String idCategoria;

    public Insumo(String idProducto, String producto, String idCategoria) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.idCategoria = idCategoria;
    }

    // Getters
    public String getIdProducto() { return idProducto; }
    public String getProducto() { return producto; }
    public String getIdCategoria() { return idCategoria; }
}