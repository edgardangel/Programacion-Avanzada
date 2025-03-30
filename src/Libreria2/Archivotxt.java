package Libreria2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivotxt {
    private String nombreArchivo;

    public Archivotxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // Guardar texto en archivo (sobrescribe)
    public void guardar(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(texto);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    // Cargar datos del archivo
    public ArrayList<String[]> cargar() {
        ArrayList<String[]> lineas = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    lineas.add(linea.split(","));
                }
            } catch (IOException e) {
                System.err.println("Error al cargar archivo: " + e.getMessage());
            }
        }
        return lineas;
    }

    // Verificar existencia del archivo
    public boolean existe() {
        return new File(nombreArchivo).exists();
    }
}