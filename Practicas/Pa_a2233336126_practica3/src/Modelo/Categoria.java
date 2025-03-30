/*package Modelo;

public class Categoria {
    private String idcategoria;
    private String categoria;

    public Categoria(String idcategoria, String categoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getIdcategoria() { return idcategoria; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // Método para persistencia
    public String toLinea() {
        return idcategoria + "," + categoria;
    }
}*/

package Modelo;

public class Categoria {
    private String idcategoria;
    private String categoria;

    public Categoria(String idcategoria, String categoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getIdcategoria() {
        return idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método para persistencia en archivo
    public String toLinea() {
        return idcategoria + "," + categoria;
    }
}