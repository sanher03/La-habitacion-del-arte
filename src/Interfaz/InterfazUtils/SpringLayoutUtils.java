package Interfaz.InterfazUtils;

import java.awt.*;
import javax.swing.SpringLayout;

/**
 * Clase de utils de SpringLayout
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class SpringLayoutUtils {

    /**
     * Método que coloca el componente c1 a coordinadas coo de c2
     * 
     * @param c1  primer componente
     * @param c2  segundo componente
     * @param coo coordinates
     * @param l   layout
     */
    public static void setPosition(
            Component c1,
            Component c2,
            Coordinates coo,
            SpringLayout l) {
        if (coo.getNorth() != 0)
            l.putConstraint(
                    SpringLayout.NORTH,
                    c1,
                    coo.getNorth(),
                    SpringLayout.NORTH,
                    c2);
        if (coo.getSouth() != 0)
            l.putConstraint(
                    SpringLayout.SOUTH,
                    c1,
                    coo.getSouth(),
                    SpringLayout.SOUTH,
                    c2);
        if (coo.getEast() != 0)
            l.putConstraint(
                    SpringLayout.EAST,
                    c1,
                    coo.getEast(),
                    SpringLayout.EAST,
                    c2);
        if (coo.getWest() != 0)
            l.putConstraint(
                    SpringLayout.WEST,
                    c1,
                    coo.getWest(),
                    SpringLayout.WEST,
                    c2);
    }

    /**
     * Método que coloca el componente c1 a coordinadas coo de c2
     * 
     * @param c1  primer componente
     * @param c2  segundo componente
     * @param coo coordinates
     * @param l   layout
     */
    public static void setPositionRev(
            Component c1,
            Component c2,
            Coordinates coo,
            SpringLayout l) {
        if (coo.getNorth() != 0)
            l.putConstraint(
                    SpringLayout.NORTH,
                    c1,
                    coo.getNorth(),
                    SpringLayout.SOUTH,
                    c2);
        if (coo.getSouth() != 0)
            l.putConstraint(
                    SpringLayout.SOUTH,
                    c1,
                    coo.getSouth(),
                    SpringLayout.NORTH,
                    c2);
        if (coo.getEast() != 0)
            l.putConstraint(
                    SpringLayout.EAST,
                    c1,
                    coo.getEast(),
                    SpringLayout.WEST,
                    c2);
        if (coo.getWest() != 0)
            l.putConstraint(
                    SpringLayout.WEST,
                    c1,
                    coo.getWest(),
                    SpringLayout.EAST,
                    c2);
    }
}
