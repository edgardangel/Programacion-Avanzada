package Controlador;

import Modelo.Categoria;
import Modelo.ListaCategorias;
import Vista.CategoriaDialog;
import Vista.CategoriaFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriaController {
    private CategoriaFrame vista;
    private ListaCategorias modelo;

    public CategoriaController(CategoriaFrame vista, ListaCategorias modelo) {
        this.vista = vista;
        this.modelo = modelo;
        configurarListeners();
        actualizarTabla();
    }

    private void configurarListeners() {
        vista.getBtnAgregar().addActionListener(this::manejarAgregar);
        vista.getBtnEditar().addActionListener(this::manejarEditar);
        vista.getBtnEliminar().addActionListener(this::manejarEliminar);
    }

    private void manejarAgregar(ActionEvent e) {
        CategoriaDialog dialog = new CategoriaDialog((JFrame) SwingUtilities.getWindowAncestor(vista));
        dialog.setVisible(true);
        if (dialog.exito()) {
            Categoria nuevaCat = new Categoria("", dialog.getNombre());
            modelo.agregarCategoria(nuevaCat);
            actualizarTabla();
        }
    }

    private void manejarEditar(ActionEvent e) {
        int fila = vista.getFilaSeleccionada();
        if (fila >= 0) {
            Categoria cat = modelo.getCategorias().get(fila);
            CategoriaDialog dialog = new CategoriaDialog((JFrame) SwingUtilities.getWindowAncestor(vista));
            dialog.setNombre(cat.getCategoria());
            dialog.setVisible(true);
            if (dialog.exito()) {
                cat.setCategoria(dialog.getNombre());
                modelo.actualizarCategoria(fila, cat);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void manejarEliminar(ActionEvent e) {
        int fila = vista.getFilaSeleccionada();
        if (fila >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Eliminar categoría?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                modelo.eliminarCategoria(fila);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTabla() {
        Object[][] datos = modelo.getCategorias().stream()
            .map(cat -> new Object[]{cat.getIdcategoria(), cat.getCategoria()})
            .toArray(Object[][]::new);
        vista.actualizarTabla(datos);
    }
}