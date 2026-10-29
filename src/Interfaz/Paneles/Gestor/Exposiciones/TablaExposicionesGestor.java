package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;

import Exposicion.*;

/**
 * Tabla exposiciones de gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class TablaExposicionesGestor {

    public static JTable create() {
        

        String[] titulos = { "nombre", "autor", "descripción", "tipo", "precio", "estado" };

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
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tabla;
    }

    /**
     * Método para modificar el contenido de las filas
     * @param model modelo
     * @param exposiciones exposiciones
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Exposicion> exposiciones) {
        int filas = exposiciones.size();
        int columnas = 6;

        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
            matriz[i][0] = exposiciones.get(i).getNombre();
            matriz[i][1] = exposiciones.get(i).getAutor();
            matriz[i][2] = exposiciones.get(i).getDescr();
            if (exposiciones.get(i).isPermanente()) {
                matriz[i][3] = "permanente";
            } else {
                matriz[i][3] = "temporal";
            }
            matriz[i][4] = exposiciones.get(i).getPrecio();
            matriz[i][5] = exposiciones.get(i).getEstado();
        }


        // Eliminar todas las filas existentes
        model.setRowCount(0);

        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }
    
}
