package Interfaz.InterfazUtils;

import javax.swing.*;

/**
 * Clase para utils de labels
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class LabelUtils {
    /**
     * Función para añadir un label sin retorno
     * 
     * @param label label
     * @param panel panel
     */
    private static void __addLabel(JLabel label, JPanel panel) {
        panel.add(label);
    }

    /**
     * Función para añadir un label con retorno
     * 
     * @param label label
     * @param panel panel
     * @return label
     */
    public static JLabel addLabel(JLabel label, JPanel panel) {
        LabelUtils.__addLabel(label, panel);
        return label;
    }
}
