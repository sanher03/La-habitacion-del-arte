package Interfaz.Paneles.PanelSuperior;

import javax.swing.*;

import Interfaz.InterfazUtils.InterfazUtils;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel superior de inicio
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSuperiorInicio extends JPanel {
    private JButton botonCasa;
    private JButton botonLogin;
    private JLabel titulo;

    /**
     * Constructor del panel
     */
    public PanelSuperiorInicio(){
        super();

        this.setBackground(new Color(171,219,227));
        this.setPreferredSize(new Dimension(0, InterfazUtils.DIM_Y/20));
        
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        botonCasa = new JButton("Exposiciones");
        botonLogin = new JButton("Iniciar sesión");
        titulo = new JLabel("La habitacion del arte");

        this.add(botonCasa);
        this.add(botonLogin);
        this.add(titulo);

        layout.putConstraint(SpringLayout.WEST, botonCasa, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, botonCasa, 15, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, botonLogin, -15, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, botonLogin, 15, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, titulo, 15, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
    }

    /**
     * Setter del controlador casa
     * @param c actionListener
     */
    public void setControladorCasa(ActionListener c){
        botonCasa.addActionListener(c);
    }

    /**
     * Setter del controlador de login
     * @param c actionListener
     */
    public void setControladorLogin(ActionListener c){
        botonLogin.addActionListener(c);
    }
    
}
