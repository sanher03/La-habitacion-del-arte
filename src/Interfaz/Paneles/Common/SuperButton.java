package Interfaz.Paneles.Common;

import javax.swing.*;

import java.awt.*;

/**
 * Botón multifuncionalidad con hover
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class SuperButton extends JButton{
    /**
     * Constructor de SuperButton
     * 
     * @param nombre nombre del botón
     * @param width ancho del botón
     */
    public SuperButton(String nombre, int width){
        super(nombre);
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setContentAreaFilled(true);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(width, 50));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
