package patrones.singleton;

import modelo.PersistenciaService;

public class PersistenciaServiceSingleton {
    private static PersistenciaService instance;

    private PersistenciaServiceSingleton() {}

    public static PersistenciaService getInstance() {
        if (instance == null) {
            instance = new PersistenciaService();
        }
        return instance;
    }
}