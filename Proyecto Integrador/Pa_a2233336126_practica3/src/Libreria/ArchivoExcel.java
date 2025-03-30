package Libreria;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.List;

public class ArchivoExcel {
    public static void guardar(List<Object[]> datos, String[] cabeceras, String rutaArchivo) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Datos");
            
            // Cabeceras
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < cabeceras.length; i++) {
                headerRow.createCell(i).setCellValue(cabeceras[i]);
            }
            
            // Datos
            int rowNum = 1;
            for (Object[] fila : datos) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < fila.length; i++) {
                    row.createCell(i).setCellValue(fila[i].toString());
                }
            }
            
            // Guardar
            try (FileOutputStream outputStream = new FileOutputStream(rutaArchivo)) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}