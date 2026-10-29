package Interfaz.Paneles.Gestor.Salas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Sala.Sala;

/**
 * Tabla salas gestor
 */
public class TablaSalasGestor {
    /**
     * Método para crear la tabla
     */
    public static JTable create() {
        

        String[] titulos = { "nombre", "aforo", "tomas de corriente", "ancho", "tipo", "exposición"};

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
     * 
     * @param model modelo de tabla
     * @param salas salas
     */
    public static void modificarContenidoFilas(DefaultTableModel model, List<Sala> salas) {
        int filas = salas.size();
        int columnas = 6;
        // Nuevos datos de ejemplo

        Object[][] matriz = new Object[filas][columnas];

        // Rellenar la matriz con objetos mediante un bucle
        for (int i = 0; i < filas; i++) {
                matriz[i][0] = salas.get(i).getNombre();
                matriz[i][1] = salas.get(i).getAforo();
                matriz[i][2] = salas.get(i).getTomasCorriente();
                matriz[i][3] = salas.get(i).getDimensiones().getAncho();
                if(salas.get(i).isClimatizada()){
                    matriz[i][4] = "CLIMATIZADA";
                }
                else {
                    matriz[i][4] = "NO CLIMATIZADA";
                }
                if(salas.get(i).getExpo() != null){
                    matriz[i][5] = salas.get(i).getExpo().getNombre();
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
