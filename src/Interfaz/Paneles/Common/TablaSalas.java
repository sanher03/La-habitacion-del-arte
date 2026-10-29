package Interfaz.Paneles.Common;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;

import Sala.*;

/**
 * Tabla de las salas
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class TablaSalas {
    /**
     * Método para crear la tabla
     * 
     * @return tabla
     */
    public static JTable create() {
        

        String[] titulos = { "nombre", "temp minima", "temp maxima", "humedad minima", "humedad maxima"};

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

        //modificarContenidoFilas(model, salas);

        tabla.setRowHeight(40);
        // Por defecto se pueden seleccionar varias filas en la tabla. Si se
        // quiere que sólo pueda seleccionarse una, usar setSelectionMode
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto
        return tabla;
    }

    /**
     * Método para modificar el contenido de las filas
     * @param model modelo de la tabla
     * @param salas salas
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Sala> salas) {
        int filas = salas.size();
        int columnas = 5;
        // Nuevos datos de ejemplo
        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
                matriz[i][0] = salas.get(i).getNombre();
                matriz[i][1] = salas.get(i).getTemperatura().getMin();
                matriz[i][2] = salas.get(i).getTemperatura().getMax();
                matriz[i][3] = salas.get(i).getHumedad().getMin();
                matriz[i][4] = salas.get(i).getHumedad().getMax();
                
            
        }

        // Eliminar todas las filas existentes
        model.setRowCount(0);

        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }
    
}
