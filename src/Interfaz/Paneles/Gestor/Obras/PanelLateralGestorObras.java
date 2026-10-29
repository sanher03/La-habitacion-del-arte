package Interfaz.Paneles.Gestor.Obras;

import javax.swing.*;

import Interfaz.Paneles.Common.SuperButton;

import java.awt.event.*;

/**
 * Panel lateral de obras del gestor con las opciones de modificacion de obras
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 * 
 * 
 */
public class PanelLateralGestorObras extends JPanel {
    JButton boton_nuevo, boton_detalles, boton_exponer, boton_almacenar;
    JButton boton_retirar, boton_prestar, boton_restaurar;

    /**
     * Constructor del panel lateral
     * 
     */
    public PanelLateralGestorObras() {
        super();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        boton_nuevo = new SuperButton("Nuevo", 150);
        boton_detalles = new SuperButton("Detalles", 150);
        boton_exponer = new SuperButton("Exponer", 150);
        boton_almacenar = new SuperButton("Almacenar", 150);
        boton_retirar = new SuperButton("Retirar", 150);
        boton_prestar = new SuperButton("Prestar", 150);
        boton_restaurar = new SuperButton("Restaurar", 150);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_nuevo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_nuevo, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_detalles, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_detalles, 10, SpringLayout.SOUTH, boton_nuevo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_exponer, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_exponer, 10, SpringLayout.SOUTH, boton_detalles);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_almacenar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_almacenar, 10, SpringLayout.SOUTH, boton_exponer);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_retirar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_retirar, 10, SpringLayout.SOUTH, boton_almacenar);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_prestar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_prestar, 10, SpringLayout.SOUTH, boton_retirar);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_restaurar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_restaurar, 10, SpringLayout.SOUTH, boton_prestar);

        this.add(boton_nuevo);
        this.add(boton_detalles);
        this.add(boton_exponer);
        this.add(boton_almacenar);
        this.add(boton_retirar);
        this.add(boton_prestar);
        this.add(boton_restaurar);

    }

    /**
     * Setter del controlador de los detalles de una obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorDetallesObra(ActionListener l) {
        boton_detalles.addActionListener(l);
    }

    /**
     * Setter del controlador de crear una nueva obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorNuevaObra(ActionListener l) {
        boton_nuevo.addActionListener(l);
    }

    /**
     * Setter del controlador de exponer una obra
     * 
     * @param l ActionListener a asignar
     * 
     */    
    public void setControladorExponerObra(ActionListener l) {
        boton_exponer.addActionListener(l);
    }

    /**
     * Setter del controlador de almacenar una obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorAlmacenarObra(ActionListener l) {
        boton_almacenar.addActionListener(l);
    }

    /**
     * Setter del controlador de retirar una obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorRetirarObra(ActionListener l) {
        boton_retirar.addActionListener(l);
    }

    /**
     * Setter del controlador de prestar una obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorPrestarObra(ActionListener l) {
        boton_prestar.addActionListener(l);
    }

    /**
     * Setter del controlador de restaurar una obra
     * 
     * @param l ActionListener a asignar
     * 
     */
    public void setControladorRestaurarObra(ActionListener l) {
        boton_restaurar.addActionListener(l);
    }

}
