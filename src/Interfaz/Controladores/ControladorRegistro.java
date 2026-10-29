package Interfaz.Controladores;

import javax.swing.*;

import Interfaz.Paneles.Invitado.PanelRegistro;

import Sistema.Sistema;
import Usuario.ClienteRegistrado;

/**
 * Controlador del registro de un nuevo cliente
 */
public class ControladorRegistro{
    private PanelRegistro panelRegistro;

    /**
     * Constructor del controlador
     * @param panelRegistro panel de registro
     */
    public ControladorRegistro(PanelRegistro panelRegistro) {
        this.panelRegistro = panelRegistro;
    }

    /**
     * Registro de cliente
     */
    public void registrar() {
        Sistema sistema = Sistema.getInstance();
        // Obtener los datos del formulario
        String dni = panelRegistro.getDNI();
        String password = panelRegistro.getContraseña();
        String repeatPassword = panelRegistro.getRepContraseña();
        boolean recNotis = panelRegistro.isRecNotis();

        if (dni.isEmpty() || dni.equals("") || password.isEmpty() || password.equals("") || repeatPassword.isEmpty()
                || repeatPassword.equals("")) {
            JOptionPane.showMessageDialog(panelRegistro,
                    "Error en el registro: no se han rellenado todos los campos", "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(panelRegistro,
                    "Error en el registro: no concuerdan las contraseñas", "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else if (sistema.checkDniRepetidoCliente(dni)) {
            JOptionPane.showMessageDialog(panelRegistro,
                    "Error en el registro: el DNI/NIF especificado ya ha sido utilizado", "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            ClienteRegistrado cliente = new ClienteRegistrado(password, dni, recNotis);
            sistema.getClientes().add(cliente);
            JOptionPane.showMessageDialog(panelRegistro, "Registro completado con éxito", "Éxito en el registro",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
}
