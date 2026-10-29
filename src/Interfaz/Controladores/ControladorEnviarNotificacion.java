package Interfaz.Controladores;

import javax.swing.JOptionPane;

import Interfaz.Paneles.Empleado.PanelEnviarNotificacion;
import Usuario.Empleado;
import Usuario.Notificacion;
import Sistema.Sistema;

/**
 * Controlador para enviar notificaciones
 * 
 * @author Ignacio Sánchez
 */
public class ControladorEnviarNotificacion {
    private PanelEnviarNotificacion vista;

    /**
     * Controlador enviar notificacion
     * @param vista panel enviar notificacion
     */
    public ControladorEnviarNotificacion(PanelEnviarNotificacion vista){
        this.vista = vista;
    }

    /**
     * Enviar notificación
     */
    public void enviarNotificacion() {
        try { 
            Empleado emp = (Empleado)Sistema.getInstance().getUsuarioActivo();
            Notificacion n = new Notificacion(emp, vista.getCuerpo());
            Sistema.getInstance().enviarNotificacionEmp(n);
            JOptionPane.showMessageDialog(vista, "Notifiación enviada");
            vista.update();
            vista.revalidate();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }
    
}
