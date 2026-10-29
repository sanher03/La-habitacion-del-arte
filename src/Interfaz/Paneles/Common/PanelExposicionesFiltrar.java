package Interfaz.Paneles.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.jdatepicker.impl.JDatePickerImpl;

/**
 * Panel para filtrar exposiciones
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelExposicionesFiltrar extends JPanel{
    JLabel label1, label2, label3;
    JDatePickerImpl fecha1, fecha2;
    JRadioButton opcion1, opcion2, opcion3;
    JCheckBox casilla1, casilla2, casilla3, casilla4;
    JButton boton1;

    /**
     * Constructor de PanelExposicionesFiltrar
     */
    public PanelExposicionesFiltrar(){
        super();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        label1 = new JLabel("Intervalo de fechas:");

        label2 = new JLabel("Tipo de exposición:");

        label3 = new JLabel("Tipo de obras:");

        fecha1 = DatePickerPanel.create();

        fecha2 = DatePickerPanel.create();

        ButtonGroup buttonGroup = new ButtonGroup();
        opcion1 = new JRadioButton("Temporal");
        opcion2 = new JRadioButton("Permanente");
        opcion3 = new JRadioButton("Ambos Tipos");
        buttonGroup.add(opcion1);
        buttonGroup.add(opcion2);
        buttonGroup.add(opcion3);

        opcion3.setSelected(true);

        casilla1 = new JCheckBox("Pinturas");
        casilla2 = new JCheckBox("Esculturas");
        casilla3 = new JCheckBox("Fotografías");
        casilla4 = new JCheckBox("Audiovisuales");
        // Crear un grupo para las opciones, el cual garantizará
        // que sólo una de las opciones está seleccionada

        boton1 = new JButton("Filtrar");
        boton1.setBorderPainted(true);
        boton1.setFocusPainted(false);
        boton1.setContentAreaFilled(true);
        boton1.setOpaque(true);
        boton1.setPreferredSize(new Dimension(150, 50));// Establecer tamaño preferido
        boton1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        layout.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, fecha1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, fecha2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, opcion1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, opcion2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, opcion3, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, casilla1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, casilla2, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, casilla3, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, casilla4, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton1, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, fecha1, 30, SpringLayout.NORTH, label1);
        layout.putConstraint(SpringLayout.NORTH, fecha2, 10, SpringLayout.SOUTH, fecha1);
        layout.putConstraint(SpringLayout.NORTH, label2, 40, SpringLayout.NORTH, fecha2);
        layout.putConstraint(SpringLayout.NORTH, opcion1, 30, SpringLayout.NORTH, label2);
        layout.putConstraint(SpringLayout.NORTH, opcion2, 10, SpringLayout.SOUTH, opcion1);
        layout.putConstraint(SpringLayout.NORTH, opcion3, 10, SpringLayout.SOUTH, opcion2);
        layout.putConstraint(SpringLayout.NORTH, label3, 25, SpringLayout.SOUTH, opcion3);
        layout.putConstraint(SpringLayout.NORTH, casilla1, 20, SpringLayout.SOUTH, label3);
        layout.putConstraint(SpringLayout.NORTH, casilla2, 10, SpringLayout.SOUTH, casilla1);
        layout.putConstraint(SpringLayout.NORTH, casilla3, 10, SpringLayout.SOUTH, casilla2);
        layout.putConstraint(SpringLayout.NORTH, casilla4, 10, SpringLayout.SOUTH, casilla3);
        layout.putConstraint(SpringLayout.NORTH, boton1, 25, SpringLayout.SOUTH, casilla4);

        this.add(label1);
        this.add(fecha1);
        this.add(fecha2);
        this.add(label2);
        this.add(opcion1);
        this.add(opcion2);
        this.add(opcion3);
        this.add(label3);
        this.add(casilla1);
        this.add(casilla2);
        this.add(casilla3);
        this.add(casilla4);
        this.add(boton1);

    }
    
    /**
     * Getter de la primera fecha
     * 
     * @return fecha1 primera fecha
     */
    public JDatePickerImpl getFecha1() {
        return fecha1;
    }

    /**
     * Getter de la segunda fecha
     * 
     * @return fecha2 segunda fecha
     */
    public JDatePickerImpl getFecha2() {
        return fecha2;
    }

    /**
     * Getter del radioButton de la primera opción
     * 
     * @return radioButton de la primera opción
     */
    public JRadioButton getOpcion1() {
        return opcion1;
    }

    /**
     * Getter del radioButton de la segunda opción
     * 
     * @return radioButton de la segunda opción
     */
    public JRadioButton getOpcion2() {
        return opcion2;
    }

    /**
     * Getter del radioButton de la tercera opción
     * 
     * @return radioButton de la tercera opción
     */
    public JRadioButton getOpcion3() {
        return opcion3;
    }

    /**
     * Getter de la checkBox de la primera casilla
     * 
     * @return checkBox de la primera casilla
     */
    public JCheckBox getCasilla1() {
        return casilla1;
    }

    /**
     * Getter de la checkBox de la segunda casilla
     * 
     * @return checkBox de la segunda casilla
     */
    public JCheckBox getCasilla2() {
        return casilla2;
    }

    /**
     * Getter de la checkBox de la tercera casilla
     * 
     * @return checkBox de la tercera casilla
     */
    public JCheckBox getCasilla3() {
        return casilla3;
    }

    /**
     * Getter de la checkBox de la cuarta casilla
     * 
     * @return checkBox de la cuarta casilla
     */
    public JCheckBox getCasilla4() {
        return casilla4;
    }

    /**
     * Setter del controlador de filtros
     * 
     * @param c actionListener a añadir al botón
     */
    public void setControladorFiltros(ActionListener c){
        boton1.addActionListener(c);
    }
}
