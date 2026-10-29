package Interfaz.Paneles.Empleado;

import Exposicion.*;
import Interfaz.Paneles.Invitado.PanelVerExposicion;
import Interfaz.Controladores.ControladorVenderEntradas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Panel de vender entradas
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelVenderEntradas extends PanelVerExposicion {
    JLabel precio, tarjeta_label;
    JComboBox<String> comboBox, horas;
    JTextField numTarjeta;
    JButton vender;

    /**
     * Función para actualizar el panel
     * 
     * @param expo exposición
     */
    public void update(Exposicion expo) {
        this.removeAll();

        super.update(expo);

        String comboBoxItems[] = { "Número de entradas", "1", "2", "3", "4", "5", "6", "7" };

        comboBox = new JComboBox<>(comboBoxItems);

        ControladorVenderEntradas controlador = new ControladorVenderEntradas(this, expo);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.controladorNumEntradas();
            }
        });

        horas = PanelVenderEntradas.horasDisponibles();

        tarjeta_label = new JLabel("Número de tarjeta");

        numTarjeta = new JTextField("");
        numTarjeta.setPreferredSize(new Dimension(200, 30));

        vender = new JButton("Vender entrada(s)");

        vender.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.controladorVender();
            }

        });

        SpringLayout layoutDerAbajo = (SpringLayout) panelDerechoAbajo.getLayout();

        layoutDerAbajo.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, comboBox, 380, SpringLayout.NORTH, panelDerechoAbajo);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, horas, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, horas, 30, SpringLayout.SOUTH, comboBox);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, tarjeta_label, 30, SpringLayout.WEST,
                panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, tarjeta_label, 30, SpringLayout.SOUTH, horas);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, numTarjeta, 30, SpringLayout.EAST, tarjeta_label);
        layoutDerAbajo.putConstraint(SpringLayout.VERTICAL_CENTER, numTarjeta, 0, SpringLayout.VERTICAL_CENTER,
                tarjeta_label);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, vender, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, vender, 30, SpringLayout.SOUTH, numTarjeta);

        panelDerechoAbajo.add(comboBox);
        panelDerechoAbajo.add(horas);
        panelDerechoAbajo.add(tarjeta_label);
        panelDerechoAbajo.add(numTarjeta);
        panelDerechoAbajo.add(vender);

        this.revalidate();
        this.repaint();

    }

    /**
     * Función para actualizar el panel
     * 
     * @param precio       precio
     * @param panelDerecho panel derecho
     */
    public void update(Double precio, JPanel panelDerecho) {

        if (this.precio != null) {
            panelDerecho.remove(this.precio);
        }
        this.precio = new JLabel("Precio a pagar: " + precio + " €");

        SpringLayout layout = (SpringLayout) panelDerecho.getLayout();

        layout.putConstraint(SpringLayout.WEST, this.precio, 230, SpringLayout.WEST, panelDerecho);
        layout.putConstraint(SpringLayout.NORTH, this.precio, 385, SpringLayout.NORTH, panelDerecho);

        panelDerecho.add(this.precio);
    }

    /**
     * Getter del combo box
     * 
     * @return combo box
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * Getter de las horas
     * 
     * @return horas horas
     */
    public JComboBox<String> getHoras() {
        return horas;
    }

    /**
     * Getter del número de tarjeta
     * 
     * @return numTarjeta numTarjeta
     */
    public JTextField getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * Getter del botón
     * 
     * @return botón vender
     */
    public JButton getVender() {
        return vender;
    }

}
