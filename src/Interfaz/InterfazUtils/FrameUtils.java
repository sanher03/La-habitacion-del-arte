package Interfaz.InterfazUtils;

import java.awt.*;
import javax.swing.*;

/**
 * Clase para frame
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class FrameUtils {

    /**
     * Método para inicializar una frame de prueba
     * 
     * @param w frame
     */
    public static void setUpMainFrame(JFrame w) {
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setLocationRelativeTo(null);
        w.setExtendedState(JFrame.MAXIMIZED_BOTH);
        w.setResizable(false);
        w.setLayout(new BorderLayout());
        w.setVisible(true);
    }
}
