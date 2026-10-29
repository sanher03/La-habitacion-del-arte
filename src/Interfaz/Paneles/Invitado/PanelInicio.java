package Interfaz.Paneles.Invitado;

import Interfaz.Controladores.ControladorLogin;
import Interfaz.Controladores.ControladorRegistro;
import Interfaz.InterfazUtils.InterfazUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel de inicio
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelInicio extends JPanel {
    private LoginPanel loginPanel;
    private PanelCasaInicio casaPanel;
    private PanelRegistro registroPanel;

    /**
     * Constructor del panel
     */
    public PanelInicio() {
        CardLayout layout = new CardLayout();
        this.setLayout(layout);

        loginPanel = new LoginPanel();

        casaPanel = new PanelCasaInicio();

        registroPanel = new PanelRegistro();

        this.add(casaPanel, InterfazUtils.CASA_INICIO);
        this.add(loginPanel, InterfazUtils.LOGIN_INICIO);
        this.add(registroPanel, InterfazUtils.REGISTRO_INICIO);

        ControladorLogin c1 = new ControladorLogin(this);

        loginPanel.setControladorLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.iniciarSesion();
            }
        });

        loginPanel.setControladorTipoUsuario(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.cambiarTipo();
            }
        });

        loginPanel.setControladorRegistrarse(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.cambiarRegistrar();
            }
        });

        ControladorRegistro c2 = new ControladorRegistro(this.registroPanel);

        registroPanel.setControladorRegistro(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c2.registrar();
            }
        });
    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        loginPanel.update();
        casaPanel.update();
    }

    /**
     * Getter del panel de login
     * 
     * @return panel de login
     */
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    /**
     * Getter del panel de registro
     * 
     * @return panel de registro
     */
    public PanelRegistro getRegistroPanel() {
        return registroPanel;
    }

}
