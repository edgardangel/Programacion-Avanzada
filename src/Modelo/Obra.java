package Modelo;

public class Obra {
    private String id;
    private String nombre;
    private String descripcion;

    public Obra(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    
    public String toLinea() {
        return id + "," + nombre + "," + descripcion;
    }
}
