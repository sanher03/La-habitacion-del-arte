package Interfaz.Paneles.PanelSuperior;


import Interfaz.Controladores.*;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Invitado.PanelInicio;

import javax.swing.*;
import java.awt.*;

/**
 * PanelSuperior
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSuperior extends JPanel{
    PanelSuperiorInicio panel_superior_inicio;
    PanelSuperiorSesion panel_superior_sesion;
    PanelSuperiorLogin panel_superior_login;
    PanelInicio panelinicio;
   
    /**
     * Constructor del panel superior
     * @param panelinicio panel de inicio
     */
    public PanelSuperior(PanelInicio panelinicio){

        this.panelinicio = panelinicio;

        this.setLayout(new CardLayout());
        panel_superior_inicio = new PanelSuperiorInicio();
        this.add(panel_superior_inicio, InterfazUtils.PANEL_INICIO);

        panel_superior_sesion = new PanelSuperiorSesion();
        this.add(panel_superior_sesion, InterfazUtils.PANEL_SESION);

        panel_superior_login = new PanelSuperiorLogin();
        this.add(panel_superior_login, InterfazUtils.PANEL_LOGIN);

        ControladorCasaSuperior c1 = new ControladorCasaSuperior(this.panelinicio);
        ControladorLoginSuperior c2 = new ControladorLoginSuperior(this.panelinicio);
        panel_superior_inicio.setControladorCasa(c1);
        panel_superior_inicio.setControladorLogin(c2);

        ControladorLogoutSuperior c3 = new ControladorLogoutSuperior(this.panelinicio);
        panel_superior_sesion.setControladorLogout(c3);

        panel_superior_login.setControladorCasa(c1);
    }
}
