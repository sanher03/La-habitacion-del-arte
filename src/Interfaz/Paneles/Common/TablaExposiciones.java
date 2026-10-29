package Interfaz.Paneles.Common;

import javax.swing.*;
import javax.swing.table.*;

import java.util.List;
import java.awt.event.MouseListener;

import Exposicion.*;
import Sistema.Sistema;

/**
 * Tabla de exposiciones
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class TablaExposiciones{
    JTable tabla;

    private List<Exposicion> exposiciones;

    /**
     * Método para crear la tabla 
     * @param botonRenderer boton
     * @return tabla tabla
     */
    public JTable create(TableCellRenderer botonRenderer) {
        this.exposiciones = Sistema.getInstance().exposicionesDisponibles();

        String[] titulos = { "nombre", "autor", "descripción", "tipo", "precio", "compra de entradas" };

        DefaultTableModel model = new DefaultTableModel();

        for (String s : titulos) {
            model.addColumn(s);
        }

        tabla = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };

        modificarContenidoFilas(model, exposiciones);

        tabla.setRowHeight(40);

        tabla.getColumnModel().getColumn(5).setCellRenderer(botonRenderer);

        /*if (usuario.equals("invitado")) {
            tabla.addMouseListener(new MouseListenerTablaInvitado(tabla, 5, vista));
        } else {
            tabla.addMouseListener(new MouseListenerTabla(tabla, 5));
        }*/
        
        // Por defecto se pueden seleccionar varias filas en la tabla. Si se
        // quiere que sólo pueda seleccionarse una, usar setSelectionMode
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto

        return tabla;
        
    }

    /**
     * Método para modificar el contenido de las filas de la tabla
     * @param model modelo de tabla
     * @param exposiciones exposiciones
     */
    public void modificarContenidoFilas(DefaultTableModel model, List<Exposicion> exposiciones) {
        int filas = exposiciones.size();
        int columnas = 6;

        this.exposiciones = exposiciones;
        // Nuevos datos de ejemplo

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
            matriz[i][5] = "";
        }


        // Eliminar todas las filas existentes
        model.setRowCount(0);

        // Agregar las nuevas filas al modelo de tabla
        for (Object[] row : matriz) {
            model.addRow(row);
        }
    }

    /**
     * Setter del controlador de la tabla
     * @param c mouseListener
     */
    public void setControladorTabla(MouseListener c){
        tabla.addMouseListener(c);
    }

    /**
     * Getter de la tabla
     * @return tabla tabla
     */
    public JTable getTabla(){
        return tabla;
    }

    /**
     * Getter de las exposiciones
     * @return exposiciones
     */
    public List<Exposicion> getExposiciones() {
        return exposiciones;
    }
    
}
