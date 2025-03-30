package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu mConfiguracion;
    private JMenuItem miCategorias, miInsumos, miObras;
    private JMenuItem miExportarXML, miExportarJSON, miExportarExcel;
    
    public MainFrame() {
        super("Sistema de Gestión de Construcción");
        initComponents();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        desktopPane = new JDesktopPane();
        menuBar = new JMenuBar();

        // Menú Configuración (existente)
        JMenu mConfiguracion = new JMenu("Configuración");
        miCategorias = new JMenuItem("Categorías");
        miInsumos = new JMenuItem("Insumos");
        miObras = new JMenuItem("Obras");
        mConfiguracion.add(miCategorias);
        mConfiguracion.add(miInsumos);
        mConfiguracion.add(miObras);

        // Menú Exportar (nuevo)
        JMenu mExportar = new JMenu("Exportar");
        miExportarXML = new JMenuItem("Guardar como XML");
        miExportarJSON = new JMenuItem("Guardar como JSON");
        miExportarExcel = new JMenuItem("Exportar a Excel");
        mExportar.add(miExportarXML);
        mExportar.add(miExportarJSON);
        mExportar.add(miExportarExcel);

        menuBar.add(mConfiguracion);
        menuBar.add(mExportar); // Añadir menú Exportar
        setJMenuBar(menuBar);
        add(desktopPane, BorderLayout.CENTER);
    }

    // Métodos para asignar listeners desde el controlador
    public void setCategoriasListener(ActionListener listener) {
        miCategorias.addActionListener(listener);
    }

    public void setInsumosListener(ActionListener listener) {
        miInsumos.addActionListener(listener);
    }

    public void setObrasListener(ActionListener listener) {
        miObras.addActionListener(listener);
    }

    public void abrirVentana(JInternalFrame ventana) {
        desktopPane.add(ventana);
        ventana.setVisible(true);
        centrarVentana(ventana);
    }

    private void centrarVentana(JInternalFrame ventana) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension ventanaSize = ventana.getSize();
        ventana.setLocation(
            (desktopSize.width - ventanaSize.width) / 2,
            (desktopSize.height - ventanaSize.height) / 2
        );
    }
    
    public void setExportarXMLListener(java.awt.event.ActionListener listener) {
        miExportarXML.addActionListener(listener);
    }

    public void setExportarJSONListener(java.awt.event.ActionListener listener) {
        miExportarJSON.addActionListener(listener);
    }

    public void setExportarExcelListener(java.awt.event.ActionListener listener) {
        miExportarExcel.addActionListener(listener);
    }
    
    
}