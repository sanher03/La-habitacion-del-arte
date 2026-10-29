package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;
import java.awt.*;

/**
 * Panel del sorteo
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSorteo extends JPanel {
    PanelSorteoCreado sorteoCreado;
    PanelSorteoCrear sorteoCrear;

    /**
     * Constructor del panel del sorteo
     */
    public PanelSorteo() {
        super(new CardLayout());
        CardLayout layout = (CardLayout) this.getLayout();

        sorteoCreado = new PanelSorteoCreado();
        sorteoCrear = new PanelSorteoCrear(this);

        this.add(sorteoCreado, "Sorteo creado");
        this.add(sorteoCrear, "Sorteo crear");

        layout.show(this, "Sorteo creado");

    }

    /**
     * Getter del panel sorteo creado
     * 
     * @return panel sorteo creado
     */
    public PanelSorteoCreado getSorteoCreado() {
        return sorteoCreado;
    }

    /**
     * Getter del panel sorteo crear
     * 
     * @return panel sorteo crear
     */
    public PanelSorteoCrear getSorteoCrear() {
        return sorteoCrear;
    }

}
