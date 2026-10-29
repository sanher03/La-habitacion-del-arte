package Interfaz.Controladores;

import javax.swing.*;

import Interfaz.Paneles.Invitado.LoginPanel;
import Interfaz.Paneles.Invitado.PanelInicio;
import Interfaz.VentanaPrincipal;
import Interfaz.InterfazUtils.InterfazUtils;
import Usuario.Empleado;

import java.awt.*;
import Sistema.Sistema;


/**
 * Controlador del login
 */
public class ControladorLogin{
    private LoginPanel vista;
    private PanelInicio inicio;
    private VentanaPrincipal ventana;

    /**
     * Constructor controlador
     * @param inicio panel de inicio
     */
    public ControladorLogin(PanelInicio inicio) {
        this.vista = inicio.getLoginPanel();
        this.inicio = inicio;
        
    }

    /**
     * Iniciar sesión
     */
    public void iniciarSesion() {
        boolean inicio_correcto = true;
        int option_selected;
        this.ventana = VentanaPrincipal.getInstance();
        Sistema sistema = Sistema.getInstance();
        

        if(vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_CLIENTE)){
            if(sistema.loginCliente(vista.getText1String(), vista.getPasswordString()) == null){
                inicio_correcto = false;
            }
            option_selected = InterfazUtils.ACTIVO_CLIENTE;
        }
        else if(vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_EMPLEADO)){
            if(sistema.loginEmpleado(vista.getText1String(), vista.getPasswordString()) == null){
                inicio_correcto = false;
            }
            option_selected = InterfazUtils.ACTIVO_EMPLEADO;
        }
        else if(vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_GESTOR)){
            if(sistema.loginGestor(vista.getText1String(), vista.getPasswordString()) == null){
                inicio_correcto = false;
            }
            option_selected = InterfazUtils.ACTIVO_GESTOR;
        }
        else{
            JOptionPane.showMessageDialog(ventana, "Error: selecciona un tipo de usuario con el que iniciar sesión");
            return;
        }
        if(inicio_correcto == false){
            JOptionPane.showMessageDialog(ventana, "Error: Contraseña o usuario incorrectos");
            return;
        }
        JOptionPane.showMessageDialog(ventana, "Inicio de sesión satisfactorio");
        
        if(option_selected == InterfazUtils.ACTIVO_CLIENTE){
            ventana.getPanelCliente().update();
            ((CardLayout)ventana.getCartas().getLayout()).show(ventana.getCartas(), InterfazUtils.CLIENTE);
        }
        else if(option_selected == InterfazUtils.ACTIVO_EMPLEADO){
            try {
                ventana.getPanelEmpleado().update((Empleado)Sistema.getInstance().getUsuarioActivo());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((CardLayout)ventana.getCartas().getLayout()).show(ventana.getCartas(), InterfazUtils.EMPLEADO);
        }   
        else if(option_selected == InterfazUtils.ACTIVO_GESTOR){
            ventana.getPanelGestor().update();
            ((CardLayout)ventana.getCartas().getLayout()).show(ventana.getCartas(), InterfazUtils.GESTOR);
        }
        ((CardLayout)ventana.getPanel_superior().getLayout()).show(ventana.getPanel_superior(), InterfazUtils.PANEL_SESION);

    }

    /**
     * Cambiar tipo de usuario
     */
    public void cambiarTipo(){
        if(vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_EMPLEADO) || vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_CLIENTE)){
            vista.setUsername("DNI");
        }else if(vista.getTipoUsuarioSelected().equals(InterfazUtils.LOGIN_GESTOR)){
            vista.setUsername("Nombre de usuario");
        }
        else{
            vista.setUsername("");
            return;
        }
        vista.update();
        vista.revalidate();
    }

    /**
     * Cambiar registro
     */
    public void cambiarRegistrar(){
        ventana = VentanaPrincipal.getInstance();
        ((CardLayout)inicio.getLayout()).show(inicio, InterfazUtils.REGISTRO_INICIO);
        ((CardLayout)ventana.getPanel_superior().getLayout()).show(ventana.getPanel_superior(), InterfazUtils.PANEL_INICIO);
    }
}
    