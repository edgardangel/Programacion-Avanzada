package Libreria;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class ArchivoXML<T> {
    private Class<T> tipo;

    public ArchivoXML(Class<T> tipo) {
        this.tipo = tipo;
    }

    public void guardar(T objeto, String rutaArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(tipo);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(objeto, new File(rutaArchivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T cargar(String rutaArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(tipo);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return tipo.cast(unmarshaller.unmarshal(new File(rutaArchivo)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}