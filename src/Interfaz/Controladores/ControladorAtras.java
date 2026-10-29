package Interfaz.Controladores;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Interfaz.InterfazUtils.InterfazUtils;

/**
 * Controlador para volver atrás
 * 
 * @author Ignacio Sánchez
 */
public class ControladorAtras implements ActionListener{
    private JPanel cartas;

    /**
     * constructor del controlador
     * @param cartas panel con card layout
     */
    public ControladorAtras(JPanel cartas) {
        this.cartas = cartas;
    }


    /**
     * action listener
     * 
     * @param arg0 evento
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ((CardLayout)cartas.getLayout()).show(cartas, InterfazUtils.LISTA_EXPOS);
    }
}
