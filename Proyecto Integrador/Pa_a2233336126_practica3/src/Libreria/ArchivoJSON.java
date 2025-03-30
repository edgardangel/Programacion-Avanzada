package Libreria;

import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.ListaCategorias;

import java.io.File;

public class ArchivoJSON<T> {
    private ObjectMapper mapper = new ObjectMapper();

    public void guardar(T objeto, String rutaArchivo) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), objeto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T cargar(String rutaArchivo, Class<ListaCategorias> class1) {
        try {
            return (T) mapper.readValue(new File(rutaArchivo), class1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}