package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import javax.swing.*;
import java.awt.event.*;

import Interfaz.Paneles.Common.SuperButton;

/**
 * Panel borrar exposición correcto
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelBorrarExpoCorrecto extends JPanel{
    SpringLayout layout;
    JLabel text1, text2;
    JButton boton;

    /**
     * Constructor del PanelBorrarExpoCorrecto
     */
    public PanelBorrarExpoCorrecto(){
        super();

        layout = new SpringLayout();
        this.setLayout(layout);
        
        text1 = new JLabel("¿Seguro que quieres");
        text2 = new JLabel("borrar esta exposicion?");
        boton = new SuperButton("Borrar exposición", 200);


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, text1, 100, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, text2, 10, SpringLayout.SOUTH, text1);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton, 50, SpringLayout.SOUTH, text2);

        this.add(text1);
        this.add(text2);
        this.add(boton);
        
    }

    /**
     * Setter del controlador eliminar
     * @param c actionListener
     */
    public void setControladorEliminar(ActionListener c){
        boton.addActionListener(c);
    }
}
