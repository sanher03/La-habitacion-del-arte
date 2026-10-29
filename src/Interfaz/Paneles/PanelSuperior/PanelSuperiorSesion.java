package Interfaz.Paneles.PanelSuperior;

import javax.swing.*;

import Interfaz.InterfazUtils.InterfazUtils;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel superior sesión
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSuperiorSesion extends JPanel {
    private JButton botonLogout;
    private JLabel titulo;

    /**
     * Constructor del panel
     */
    public PanelSuperiorSesion(){
        super();

        this.setBackground(new Color(171,219,227));
        this.setPreferredSize(new Dimension(0, InterfazUtils.DIM_Y/20));
        
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        botonLogout = new JButton("Cerrar sesión");
        titulo = new JLabel("La habitacion del arte");

        this.add(botonLogout);
        this.add(titulo);

        layout.putConstraint(SpringLayout.EAST, botonLogout, -15, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, botonLogout, 15, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, titulo, 15, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
    }

    /**
     * Setter del controlador de logout
     * @param c actionListener
     */
    public void setControladorLogout(ActionListener c){
        botonLogout.addActionListener(c);
    }
    
}
