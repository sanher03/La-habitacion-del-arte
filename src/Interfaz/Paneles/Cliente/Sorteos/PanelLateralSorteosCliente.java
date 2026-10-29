package Interfaz.Paneles.Cliente.Sorteos;

import javax.swing.*;

import Interfaz.Paneles.Common.SuperButton;

import java.awt.event.*;

/**
 * Panel lateral de los sorteos del cliente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelLateralSorteosCliente extends JPanel{
    JButton boton_detalles;

    /**
     * Constructor del panel lateral de los sorteos del cliente
     */
    public PanelLateralSorteosCliente() {
        super();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        boton_detalles = new SuperButton("Detalles", 150);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_detalles, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, boton_detalles, 30, SpringLayout.NORTH, this);

        this.add(boton_detalles);

    }

    /**
     * Setter del controlador de detalles del sorteo
     * 
     * @param l actionlistener
     */
    public void setControladorDetallesSorteo(ActionListener l) {
        boton_detalles.addActionListener(l);
    }
}
