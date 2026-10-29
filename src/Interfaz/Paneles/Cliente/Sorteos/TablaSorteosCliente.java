package Interfaz.Paneles.Cliente.Sorteos;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;
import Sorteo.Sorteo;

/**
 * Panel tabla de sorteos del cliente
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class TablaSorteosCliente {

    /**
     * Método para crear el panel
     * @param sorteos a displayear
     * @return tabla tabla
     */
    public static JTable create(List<Sorteo> sorteos) {
        

        String[] titulos = { "Exposición asociada", "Entradas a sortear", "Estado"};

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

        modificarContenidoFilas(model, sorteos);

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
     * @param model modelo de tabla 
     * @param sorteos sorteos
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Sorteo> sorteos) {
        int filas = sorteos.size();
        int columnas = 3;
        // Nuevos datos de ejemplo

        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
            matriz[i][0] = sorteos.get(i).getExposicion().getNombre();
            matriz[i][1] = sorteos.get(i).getnEntradas();
            matriz[i][2] = sorteos.get(i).getEstado().toString();
        }


        // Eliminar todas las filas existentes
        model.setRowCount(0);

        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }
    
}
