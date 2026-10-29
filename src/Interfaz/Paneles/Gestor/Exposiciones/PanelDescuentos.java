package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

/**
 * Panel de descuentos
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelDescuentos extends JTabbedPane{

    PanelDescuentoExistente existente;
    PanelAñadirDescuento aniadir;

    /** 
     * Constructor del panel de descuentos
     */
    public PanelDescuentos() {
        super();

        existente = new PanelDescuentoExistente();
        aniadir = new PanelAñadirDescuento(this);

        this.add("Existente", existente);
        this.add("Añadir", aniadir);
    }

    /**
     * Getter del panel de descuentos existente
     * @return panel de descuentos existente
     */
    public PanelDescuentoExistente getExistente() {
        return existente;
    }

    /**
     * Getter del panel añadir descuento
     * @return panel añadir descuento
     */
    public PanelAñadirDescuento getAniadir() {
        return aniadir;
    }
}
    
