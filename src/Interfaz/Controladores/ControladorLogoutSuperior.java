package Interfaz.Controladores;

import Interfaz.VentanaPrincipal;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Invitado.PanelInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controlador de la barra superior de cierre de sesión
 */
public class ControladorLogoutSuperior implements ActionListener {
    private VentanaPrincipal ventana;

    /**
     * Constructor del controlador
     * @param vista
     */
    public ControladorLogoutSuperior(PanelInicio vista) {

    }

    /**
     * action listener
     * 
     * @param arg0 evento
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ventana = VentanaPrincipal.getInstance();
        JOptionPane.showMessageDialog(ventana, "Cierre de sesión satisfactoriamente");
        ((CardLayout)ventana.getCartas().getLayout()).show(ventana.getCartas(), InterfazUtils.INICIO);
        ((CardLayout)ventana.getPanel_superior().getLayout()).show(ventana.getPanel_superior(), InterfazUtils.PANEL_INICIO);
        ventana.getPanelinicio().update();


    }
}
