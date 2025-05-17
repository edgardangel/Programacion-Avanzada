package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaService {
    private static final String FILE_PATH = "src/proyecto.json";
    private Gson gson = new Gson();

    public void guardarEntidades(List<Entidad> entidades) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(entidades, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Entidad> cargarEntidades() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Entidad>>() {}.getType();
            List<Entidad> resultado = gson.fromJson(reader, listType);

            // Asegurar que cada entidad tenga su lista de atributos inicializada
            for (Entidad entidad : resultado) {
                if (entidad.getAtributos() == null) {
                    entidad.setAtributos(new ArrayList<>());
                }
            }

            return resultado;
        } catch (Exception e) {
            System.err.println("Error al leer proyecto.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}