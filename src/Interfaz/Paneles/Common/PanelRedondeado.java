package Interfaz.Paneles.Common;

import java.awt.*;
import javax.swing.*;

/**
 * Panel redondeado a añadir en inicio de sesión y registro
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelRedondeado extends JPanel {
    private int cornerRadius;

    /**
     * Constructor del PanelRedondeado
     * 
     * @param radius radio de redondeo
     */
    public PanelRedondeado(int radius) {
        super();
        this.cornerRadius = radius;
        this.setOpaque(false);
    }

    /**
     * Método sobreescrito para cambiar la apariencia del panel
     * 
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}
