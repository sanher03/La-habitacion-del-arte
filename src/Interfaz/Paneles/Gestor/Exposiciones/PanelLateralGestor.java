package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

import Interfaz.Paneles.Common.SuperButton;

import java.awt.event.*;

/**
 * Panel lateral del gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelLateralGestor extends JPanel {
    JButton boton_nuevo, boton_editar, boton_borrar;
    JButton boton_descuentos, boton_sorteo, boton_estadisticas;

    /**
     * Construcot del panel lateral del gestor
     */
    public PanelLateralGestor() {
        super();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        boton_nuevo = new SuperButton("Nuevo", 150);
        boton_editar = new SuperButton("Editar", 150);
        boton_borrar = new SuperButton("Borrar", 150);
        boton_descuentos = new SuperButton("Descuentos", 150);

        boton_sorteo = new SuperButton("Sorteo", 150);
        boton_estadisticas = new SuperButton("Estadisticas", 150);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_nuevo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_nuevo, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_editar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_editar, 10, SpringLayout.SOUTH, boton_nuevo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_borrar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_borrar, 10, SpringLayout.SOUTH, boton_editar);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_descuentos, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_descuentos, 10, SpringLayout.SOUTH, boton_borrar);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_sorteo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_sorteo, 10, SpringLayout.SOUTH, boton_descuentos);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_estadisticas, 0, SpringLayout.HORIZONTAL_CENTER,
                this);
        layout.putConstraint(SpringLayout.NORTH, boton_estadisticas, 10, SpringLayout.SOUTH, boton_sorteo);

        this.add(boton_nuevo);
        this.add(boton_editar);
        this.add(boton_borrar);
        this.add(boton_descuentos);
        this.add(boton_sorteo);
        this.add(boton_estadisticas);

    }

    /**
     * Setter del controlador programarExpo
     * 
     * @param l actionListener
     */
    public void setControladorProgramarExpo(ActionListener l) {
        boton_nuevo.addActionListener(l);
    }

    /**
     * Setter del controlador editarExpo
     * 
     * @param l actionListener
     */
    public void setControladorEditarExpo(ActionListener l) {
        boton_editar.addActionListener(l);
    }

    /**
     * Setter del controlador borrarExpo
     * 
     * @param l actionListener
     */
    public void setControladorBorrarExpo(ActionListener l) {
        boton_borrar.addActionListener(l);
    }

    /**
     * Setter del controlador descuentosExpo
     * 
     * @param l actionListener
     */
    public void setControladorDescuentosExpo(ActionListener l) {
        boton_descuentos.addActionListener(l);
    }

    /**
     * Setter del controlador estadisticasExpo
     * 
     * @param l actionListener
     */
    public void setControladorEstadisticasExpo(ActionListener l) {
        boton_estadisticas.addActionListener(l);
    }

    /**
     * Setter del controlador sorteoExpo
     * 
     * @param l actionListener
     */
    public void setControladorSorteoExpo(ActionListener l) {
        boton_sorteo.addActionListener(l);
    }

}
