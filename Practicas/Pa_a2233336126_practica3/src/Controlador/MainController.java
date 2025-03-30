package Controlador;

import Vista.*;
import Modelo.*;
import Libreria.ArchivoExcel; 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class MainController {
    private MainFrame vista;
    private ListaCategorias listaCategorias;
    private ListaInsumos listaInsumos;
    private ListaObras listaObras;

    public MainController(MainFrame vista) {
        this.vista = vista;
        this.listaCategorias = new ListaCategorias();
        this.listaInsumos = new ListaInsumos();
        this.listaObras = new ListaObras();
        configurarListeners();
    }

    private void configurarListeners() {
        vista.setCategoriasListener(e -> abrirCategorias());
        vista.setInsumosListener(e -> abrirInsumos());
        vista.setObrasListener(e -> abrirObras());
        vista.setExportarXMLListener(e -> exportarXML());
        vista.setExportarJSONListener(e -> exportarJSON());
        vista.setExportarExcelListener(e -> exportarExcel());
    }

    private void abrirCategorias() {
        CategoriaFrame frame = new CategoriaFrame();
        new CategoriaController(frame, listaCategorias);
        vista.abrirVentana(frame);
    }

    private void abrirInsumos() {
        InsumoFrame frame = new InsumoFrame();
        new InsumoController(frame, listaInsumos, listaCategorias);
        vista.abrirVentana(frame);
    }

    private void abrirObras() {
        ObraFrame frame = new ObraFrame();
        new ObraController(frame, listaObras);
        vista.abrirVentana(frame);
    }
    

    private void exportarJSON() {
        listaCategorias.guardarJSON("categorias.json");
        listaInsumos.guardarJSON("insumos.json");
        listaObras.guardarJSON("obras.json");
        JOptionPane.showMessageDialog(vista, "Datos exportados a JSON", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportarExcel() {
        // Exportar categorías
        ArchivoExcel.guardar(
            listaCategorias.getDatosParaExcel(),
            new String[]{"ID", "Nombre"},
            "categorias.xlsx"
        );

        // Exportar insumos
        ArchivoExcel.guardar(
            listaInsumos.getDatosParaExcel(),
            new String[]{"ID", "Producto", "ID Categoría"},
            "insumos.xlsx"
        );

        // Exportar obras
        ArchivoExcel.guardar(
            listaObras.getDatosParaExcel(),
            new String[]{"ID", "Nombre", "Descripción"},
            "obras.xlsx"
        );

        JOptionPane.showMessageDialog(vista, "Datos exportados a Excel", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportarXML() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como XML");
        if (fileChooser.showSaveDialog(vista) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            listaCategorias.guardarXML(ruta + "_categorias.xml");
            listaInsumos.guardarXML(ruta + "_insumos.xml");
            listaObras.guardarXML(ruta + "_obras.xml");
            JOptionPane.showMessageDialog(vista, "Datos exportados a XML", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}