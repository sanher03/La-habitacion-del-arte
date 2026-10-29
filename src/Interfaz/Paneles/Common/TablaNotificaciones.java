package Interfaz.Paneles.Common;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;

import Interfaz.Controladores.*;

import Usuario.Notificacion;
import Usuario.Usuario;

/**
 * Tabla de notificaciones
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class TablaNotificaciones{
    /**
     * Método para crear la tabla
     * 
     * @param botonRenderer boton
     */
    public static JTable create(TableCellRenderer botonRenderer) {
        String[] titulos = { "Emisor", "Visualización" };

        DefaultTableModel model = new DefaultTableModel();

        for (String s : titulos) {
            model.addColumn(s);
        }

        // Crear la tabla, pasando las filas y los títulos como parámetro
        JTable tabla = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };

        tabla.setRowHeight(40);

        tabla.getColumnModel().getColumn(1).setCellRenderer(botonRenderer);

        tabla.addMouseListener(new MouseListenerTablaNotificaciones(tabla, 1));
        // Por defecto se pueden seleccionar varias filas en la tabla. Si se
        // quiere que sólo pueda seleccionarse una, usar setSelectionMode
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tabla;
    }

    /**
     * Método para modificar el contenido de las filas
     * @param model modelo de tabla 
     * @param notificaciones notificaciones
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Notificacion> notificaciones) {
        int filas = notificaciones.size();
        int columnas = 2;

        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Crear un nuevo objeto para cada posición de la matriz
                // Asignar el objeto a la posición (i, j) de la matriz
                Usuario emisor = notificaciones.get(i).getEmisor();
                if (emisor != null) {
                    matriz[i][j] = emisor.getIdentificacion();
                } else {
                    matriz[i][j] = "Sistema";
                }
                 
            }
        }
        // Eliminar todas las filas existentes
        model.setRowCount(0);

        
        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }
    
}
