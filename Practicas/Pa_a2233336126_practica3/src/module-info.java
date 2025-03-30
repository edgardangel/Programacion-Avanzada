/**
 * 
 */
/**
 * 
 */
module Pa_a2233336126_practica3 {
	// Módulos de JavaFX o Swing (si los usas)
    requires java.desktop;
    
    // Jakarta XML (JAXB)
    requires jakarta.xml.bind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires org.codehaus.stax2; // Módulo faltante
    requires com.ctc.wstx; // Woodstox
    
    // Jackson (JSON)
    requires com.fasterxml.jackson.annotation; // Módulo faltante
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    
    // Apache POI (Excel)
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    
    // Si usas Java 11+ con Jakarta, añade también:
    requires jakarta.activation;
    
    // Paquetes que expones
    exports Modelo;
    exports Vista;
    exports Controlador;
    exports Libreria;
    
    // Permite que Jackson acceda a clases por reflexión
    opens Modelo to com.fasterxml.jackson.databind;
}