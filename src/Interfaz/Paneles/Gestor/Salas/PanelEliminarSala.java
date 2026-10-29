package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;

import Interfaz.Paneles.Common.SuperButton;

import java.awt.event.ActionListener;

/**
 * Panel eliminar sala
 */
public class PanelEliminarSala extends JPanel{
    SpringLayout layout;
    JLabel text1, text2;
    JButton boton;

    /**
     * Constructor del panel
     */
    public PanelEliminarSala(){
        super();

        layout = new SpringLayout();
        this.setLayout(layout);
        
        text1 = new JLabel("¿Seguro que quieres");
        text2 = new JLabel("eliminar esta sala?");
        boton = new SuperButton("Eliminar Sala", 200);


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
     * 
     * @param c actionListener
     */
    public void setControladorEliminar(ActionListener c){
        boton.addActionListener(c);
    }
}
