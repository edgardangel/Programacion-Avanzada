package controlador;

import modelo.Entidad;
import modelo.PersistenciaService;
import patrones.factory.EntidadFactory;
import patrones.singleton.PersistenciaServiceSingleton;
import vista.VistaEntidades;

import java.util.List;

public class ControladorEntidades {
    private VistaEntidades vistaEntidades;
    private List<Entidad> entidades;
    private PersistenciaService persistenciaService;

    public ControladorEntidades(VistaEntidades vistaEntidades) {
        this.vistaEntidades = vistaEntidades;
        this.persistenciaService = PersistenciaServiceSingleton.getInstance();
        this.entidades = persistenciaService.cargarEntidades();
        this.vistaEntidades.setControlador(this);
        this.vistaEntidades.mostrarEntidades(entidades);
    }

    public void agregarEntidad(String nombre) {
        Entidad entidad = EntidadFactory.crearEntidad(nombre);
        entidades.add(entidad);
        persistenciaService.guardarEntidades(entidades);
        vistaEntidades.agregarFilaTabla(entidad);
    }

    public void eliminarEntidad(Entidad entidad) {
        entidades.remove(entidad);
        persistenciaService.guardarEntidades(entidades);
        vistaEntidades.mostrarEntidades(entidades);
    }

    public void modificarEntidad(Entidad entidad) {
        // Lógica para modificar la entidad
        // Esto podría abrir una nueva ventana para editar los atributos
        vistaEntidades.abrirVistaAtributos(entidad);
    }
    
    public Entidad buscarEntidadPorNombre(String nombre) {
        return entidades.stream()
                        .filter(e -> e.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);
    }
    
    public void salir() {
        persistenciaService.guardarEntidades(entidades);
        System.exit(0);
    }
}