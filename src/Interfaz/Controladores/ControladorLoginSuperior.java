package Interfaz.Controladores;

import Interfaz.VentanaPrincipal;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Invitado.PanelInicio;

import java.awt.*;
import java.awt.event.*;

/**
 * Controlador del panel superior
 * 
 * @author Ignacio Sánchez
 */
public class ControladorLoginSuperior implements ActionListener {
    private PanelInicio vista;
    private VentanaPrincipal ventana;

    /**
     * Contructor del controlador
     * @param vista panel de inicio
     */
    public ControladorLoginSuperior(PanelInicio vista) {
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
        ((CardLayout)vista.getLayout()).show(vista, InterfazUtils.LOGIN_INICIO);
        vista.getLoginPanel().setUsername("");
        vista.getLoginPanel().update();
        ((CardLayout)ventana.getPanel_superior().getLayout()).show(ventana.getPanel_superior(), InterfazUtils.PANEL_LOGIN);

    }
}
