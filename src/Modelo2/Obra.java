package Modelo2;

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
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Método para persistencia
    public String toLinea() {
        return id + "," + nombre + "," + descripcion;
    }

	public void setId(String generarId) {
		this.id = generarId;
	}
}