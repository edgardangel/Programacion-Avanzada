package Controlador;

import Modelo.ListaObras;
import Modelo.Obra;
import Vista.ObraDialog;
import Vista.ObraFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class ObraController {
    private ObraFrame vista;
    private ListaObras modelo;

    public ObraController(ObraFrame vista, ListaObras modelo) {
        this.vista = vista;
        this.modelo = modelo;
        configurarListeners();
        actualizarTabla();
    }

    private void configurarListeners() {
        vista.getBtnAgregar().addActionListener(this::manejarAgregar);
    }

    private void manejarAgregar(ActionEvent e) {
        ObraDialog dialog = new ObraDialog((JFrame) SwingUtilities.getWindowAncestor(vista));
        dialog.setVisible(true);
        if (dialog.exito()) {
            Obra nuevaObra = new Obra(
                "",
                dialog.getNombreObra(),
                dialog.getDescripcion()
            );
            modelo.agregarObra(nuevaObra);
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        Object[][] datos = modelo.getObras().stream()
            .map(obra -> new Object[]{
                obra.getId(),
                obra.getNombre(),
                obra.getDescripcion()
            })
            .toArray(Object[][]::new);
        vista.actualizarTabla(datos);
    }
}