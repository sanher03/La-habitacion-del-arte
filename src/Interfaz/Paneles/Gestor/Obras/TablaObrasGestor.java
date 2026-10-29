package Interfaz.Paneles.Gestor.Obras;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;


import Obra.*;

/**
 * Clase con la tabla de obras de gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class TablaObrasGestor {

    /**
     * Funcion estatica de creacion de tabla de obras
     * 
     */
    public static JTable create() {

        String[] titulos = { "nombre", "autor", "descripción", "tipo", "estado" };

        DefaultTableModel model = new DefaultTableModel();

        for (String s : titulos) {
            model.addColumn(s);
        }

        JTable tabla = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };

       

        tabla.setRowHeight(40);
        // Por defecto se pueden seleccionar varias filas en la tabla. Si se
        // quiere que sólo pueda seleccionarse una, usar setSelectionMode
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto
        return tabla;
    }

    /**
     * Modificacion de la tabla de obras segun la lista de obras del Sistema
     * 
     * @param model Modelo de la tabla
     * @param obras Lista de obras de Sistema
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Obra> obras) {
        int filas = obras.size();
        int columnas = 5;
        // Nuevos datos de ejemplo

        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
            matriz[i][0] = obras.get(i).getNombre();
            matriz[i][1] = obras.get(i).getAutor();
            matriz[i][2] = obras.get(i).getDescr();
            matriz[i][3] = obras.get(i).getTipoObra().toString();
            matriz[i][4] = obras.get(i).getEstado().toString();
        }

        // Eliminar todas las filas existentes
        model.setRowCount(0);

        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }

}
