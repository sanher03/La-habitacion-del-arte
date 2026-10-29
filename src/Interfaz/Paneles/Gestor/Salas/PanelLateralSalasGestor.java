package Interfaz.Paneles.Gestor.Salas;

import java.awt.event.*;
import javax.swing.*;

import Interfaz.Paneles.Common.SuperButton;

/**
 * Panel lateral salas gestor
 */
public class PanelLateralSalasGestor extends JPanel{
    JButton boton_nuevo, boton_editar, boton_eliminar;
    JButton boton_dividir;

    /**
     * Constructor del panel 
     */
    public PanelLateralSalasGestor() {
        super();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        boton_nuevo = new SuperButton("Nuevo", 150);
        boton_editar = new SuperButton("Editar", 150);
        boton_eliminar = new SuperButton("Eliminar", 150);
        boton_dividir = new SuperButton("Dividir", 150);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_nuevo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_nuevo, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_editar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_editar, 10, SpringLayout.SOUTH, boton_nuevo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_eliminar, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_eliminar, 10, SpringLayout.SOUTH, boton_editar);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_dividir, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_dividir, 10, SpringLayout.SOUTH, boton_eliminar);


        this.add(boton_nuevo);
        this.add(boton_editar);
        this.add(boton_eliminar);
        this.add(boton_dividir);

    }


    /**
     * Setter del controlador nuevo 
     * 
     * @param c actionListener
     */
    public void setControladorNuevo(ActionListener c){
        boton_nuevo.addActionListener(c);
    }

    /**
     * Setter del controlador editar
     * 
     * @param c actionListener
     */
    public void setControladorEditar(ActionListener c){
        boton_editar.addActionListener(c);
    }

    /**
     * Setter del controlador eliminar
     * 
     * @param c actionListener
     */
    public void setControladorEliminar(ActionListener c){
        boton_eliminar.addActionListener(c);
    }

    /**
     * Setter del controlador dividir
     * 
     * @param c actionListener
     */
    public void setControladorDividir(ActionListener c){
        boton_dividir.addActionListener(c);
    }
}
