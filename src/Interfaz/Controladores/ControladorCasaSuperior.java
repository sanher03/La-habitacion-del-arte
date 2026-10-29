package Interfaz.Controladores;

import java.awt.*;
import java.awt.event.*;

import Interfaz.VentanaPrincipal;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Invitado.PanelInicio;

/**
 * Controlador de panel superior
 */
public class ControladorCasaSuperior implements ActionListener {
    private PanelInicio vista;
    private VentanaPrincipal ventana;

    /**
     * Constructor de la clase 
     * @param vista panel
     */
    public ControladorCasaSuperior(PanelInicio vista) {
        this.vista = vista;
    }


    /**
     * action listener
     * 
     * @param arg0 evento
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ventana = VentanaPrincipal.getInstance();
        ((CardLayout)vista.getLayout()).show(vista, InterfazUtils.CASA_INICIO);
        ((CardLayout)ventana.getPanel_superior().getLayout()).show(ventana.getPanel_superior(), InterfazUtils.PANEL_INICIO);

    }
}
