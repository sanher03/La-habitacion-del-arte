package Interfaz.Paneles.Common;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

/**
 * Botón renderizado para ver exposición
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class BotonRendererVer implements TableCellRenderer{

    /**
     * Método sobreescrito para establecer una celda como botón
     * 
     * @param arg0 tabla
     * @param arg1 objeto
     * @param arg2 boolean
     * @param arg3 boolean
     * @param arg4 int 
     * @param arg5 int
     * 
     * @return boton (Componente)
     */
    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
            int arg5) {
        
        JButton boton = new JButton("Ver exposición");
        boton.setBackground(UIManager.getColor("Button.background"));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }
    
}
