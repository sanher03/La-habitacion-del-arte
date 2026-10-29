package Interfaz.Paneles.Common;

import javax.swing.*;

import java.awt.event.*;

/**
 * Panel para el título
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelTitulo extends JPanel{
    JLabel titulo;
    JButton boton;

    /**
     * Constructor del PanelTitulo
     * 
     * @param nombre de título
     */
    public PanelTitulo(String nombre){
        super();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        titulo = new JLabel(nombre);
        titulo.setFont(titulo.getFont().deriveFont(24f));

        layout.putConstraint(SpringLayout.WEST, titulo, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, titulo, 10, SpringLayout.NORTH, this);
        this.add(titulo);
    }

    /**
     * Constructor de PanelTitulo
     * 
     * @param nombre de título
     * @param boton_nombre boton
     */
    public PanelTitulo(String nombre, String boton_nombre){
        super();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        titulo = new JLabel(nombre);
        titulo.setFont(titulo.getFont().deriveFont(24f));

        boton = new JButton(boton_nombre);


        layout.putConstraint(SpringLayout.WEST, titulo, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, titulo, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, boton, -20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, boton, 10, SpringLayout.NORTH, this);
        this.add(titulo);
        this.add(boton);
    }

    /**
     * Setter del controlador del botón
     * 
     * @param c actionListener a settear
     */
    public void setControladorBoton(ActionListener c){
        boton.addActionListener(c);
    }

    /**
     * Getter del label título
     * 
     * @return titulo label
     */
    public JLabel getTitulo() {
        return titulo;
    }
}
