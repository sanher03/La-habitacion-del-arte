package Interfaz.Paneles.Cliente;

import Exposicion.*;
import Interfaz.Controladores.ControladorCompraEntradas;
import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Paneles.Invitado.PanelVerExposicion;
import Interfaz.Paneles.Empleado.PanelVenderEntradas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

/**
 * Panel dedicado a la compra de entradas
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelComprarEntradas extends PanelVerExposicion {

    private JLabel precio;
    private JComboBox<String> comboBox, horas;
    private JDatePickerImpl fechaCompra;
    private JLabel introducirFechaHora, tarjeta_label, codigo_label;
    private JTextField numTarjeta, codigo;
    private JButton vender;

    /**
     * Función para actualizar la vista
     * 
     * @param expo exposición en cuestión
     */
    public void update(Exposicion expo) {
        this.removeAll();

        super.update(expo);

        String comboBoxItems[] = { "Número de entradas", "1", "2", "3", "4", "5", "6", "7" };

        comboBox = new JComboBox<>(comboBoxItems);

        introducirFechaHora = new JLabel("Fecha y hora:");

        fechaCompra = DatePickerPanel.create();

        ControladorCompraEntradas controlador = new ControladorCompraEntradas(this, expo);

        fechaCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.controladorFecha();
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.controladorNumEntradas();
            }
        });

        horas = PanelVenderEntradas.horasDisponibles();

        tarjeta_label = new JLabel("Número de tarjeta:");

        codigo_label = new JLabel("Código de sorteo:");

        numTarjeta = new JTextField("");
        numTarjeta.setPreferredSize(new Dimension(200, 30));

        codigo = new JTextField("");
        codigo.setPreferredSize(new Dimension(100, 30));

        vender = new JButton("Comprar entrada(s)");

        vender.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.controladorComprar();
            }

        });

        SpringLayout layoutDerAbajo = (SpringLayout) panelDerechoAbajo.getLayout();

        layoutDerAbajo.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, comboBox, 380, SpringLayout.NORTH, panelDerechoAbajo);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, introducirFechaHora, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, introducirFechaHora, 30, SpringLayout.SOUTH, comboBox);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, fechaCompra, 200, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, fechaCompra, 30, SpringLayout.SOUTH, comboBox);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, horas, 500, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, horas, 30, SpringLayout.SOUTH, comboBox);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, codigo_label, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, codigo_label, 30, SpringLayout.SOUTH, horas);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, codigo, 30, SpringLayout.EAST, codigo_label);
        layoutDerAbajo.putConstraint(SpringLayout.VERTICAL_CENTER, codigo, 0, SpringLayout.VERTICAL_CENTER,
                codigo_label);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, tarjeta_label, 300, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, tarjeta_label, 30, SpringLayout.SOUTH, horas);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, numTarjeta, 30, SpringLayout.EAST, tarjeta_label);
        layoutDerAbajo.putConstraint(SpringLayout.VERTICAL_CENTER, numTarjeta, 0, SpringLayout.VERTICAL_CENTER,
                tarjeta_label);

        layoutDerAbajo.putConstraint(SpringLayout.WEST, vender, 30, SpringLayout.WEST, panelDerechoAbajo);
        layoutDerAbajo.putConstraint(SpringLayout.NORTH, vender, 30, SpringLayout.SOUTH, numTarjeta);

        panelDerechoAbajo.add(comboBox);
        panelDerechoAbajo.add(introducirFechaHora);
        panelDerechoAbajo.add(fechaCompra);
        panelDerechoAbajo.add(horas);
        panelDerechoAbajo.add(codigo_label);
        panelDerechoAbajo.add(codigo);
        panelDerechoAbajo.add(tarjeta_label);
        panelDerechoAbajo.add(numTarjeta);
        panelDerechoAbajo.add(vender);

        this.revalidate();
        this.repaint();

    }

    /**
     * Función para modificar la vista según precio y panelDerecho
     * 
     * @param precio       precio
     * @param panelDerecho panel derecho
     */
    public void update(Double precio, JPanel panelDerecho) {
        if (this.precio != null) {
            panelDerecho.remove(this.precio);
        }

        this.precio = new JLabel("Precio a pagar (con descuentos aplicados): " + precio + " €");

        SpringLayout layout = (SpringLayout) panelDerecho.getLayout();

        layout.putConstraint(SpringLayout.WEST, this.precio, 230, SpringLayout.WEST, panelDerecho);
        layout.putConstraint(SpringLayout.NORTH, this.precio, 385, SpringLayout.NORTH, panelDerecho);

        panelDerecho.add(this.precio);
    }

    /**
     * Getter del atributo comboBox
     * 
     * @return comboBox combo box
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * Getter del atributo fechaCompra
     * 
     * @return fechaCompra fecha de compra
     */
    public JDatePickerImpl getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Getter del atributo horas
     * 
     * @return horas horas de entrada
     */
    public JComboBox<String> getHoras() {
        return horas;
    }

    /**
     * Getter del atributo x
     * 
     * @return x
     */
    public JTextField getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * Getter del atributo x
     * 
     * @return x
     */
    public JTextField getCodigo() {
        return codigo;
    }

    /**
     * Getter del atributo x
     * 
     * @return x
     */
    public JButton getVender() {
        return vender;
    }

}
