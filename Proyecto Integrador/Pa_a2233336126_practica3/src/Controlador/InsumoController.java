package Controlador;

import Modelo.Categoria;
import Modelo.Insumo;
import Modelo.ListaCategorias;
import Modelo.ListaInsumos;
import Vista.InsumoDialog;
import Vista.InsumoFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class InsumoController {
    private InsumoFrame vista;
    private ListaInsumos modelo;
    private ListaCategorias listaCategorias;

    public InsumoController(InsumoFrame vista, ListaInsumos modelo, ListaCategorias listaCategorias) {
        this.vista = vista;
        this.modelo = modelo;
        this.listaCategorias = listaCategorias;
        configurarListeners();
        actualizarTabla();
    }

    private void configurarListeners() {
        vista.getBtnAgregar().addActionListener(this::manejarAgregar);
    }

    private void manejarAgregar(ActionEvent e) {
        InsumoDialog dialog = new InsumoDialog(
            (JFrame) SwingUtilities.getWindowAncestor(vista),
            listaCategorias.getCategorias()
        );
        dialog.setVisible(true);
        if (dialog.exito()) {
            Categoria categoria = dialog.getCategoriaSeleccionada();
            Insumo nuevoInsumo = new Insumo(
                "",
                dialog.getNombreInsumo(),
                categoria.getIdcategoria()
            );
            modelo.agregarInsumo(nuevoInsumo);
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        Object[][] datos = modelo.getInsumos().stream()
            .map(insumo -> {
                String idCategoria = insumo.getIdCategoria();
                Categoria categoria = listaCategorias.buscarCategoriaPorId(idCategoria);
                String nombreCategoria = (categoria != null) 
                    ? categoria.getCategoria() 
                    : "Categoría Desconocida"; // <- Manejo de null
                return new Object[]{
                    insumo.getIdProducto(),
                    insumo.getProducto(),
                    nombreCategoria
                };
            })
            .toArray(Object[][]::new);
        vista.actualizarTabla(datos);
    }
}