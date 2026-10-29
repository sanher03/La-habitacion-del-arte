package Interfaz.Controladores;

import javax.swing.*;

import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Gestor.PanelRegistrarEmpleado;

import Sistema.Sistema;
import Usuario.Empleado;

/**
 * Controlador registrar empleado
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class ControladorRegistrarEmpleado{
    private PanelRegistrarEmpleado panelRegistrarEmpleado;

    /**
     * Constructor del controlador
     * @param panelRegistrarEmpleado panel de registro de empleado
     */
    public ControladorRegistrarEmpleado(PanelRegistrarEmpleado panelRegistrarEmpleado) {
        this.panelRegistrarEmpleado = panelRegistrarEmpleado;
    }

    /**
     * Registrar empleado
     */
    public void registrar() {
        
        Sistema sistema = Sistema.getInstance();

        // Obteniendo y limpiando los datos de entrada
        String dni = panelRegistrarEmpleado.getDNI().trim();
        String nss = panelRegistrarEmpleado.getNSS().trim();
        String direccion = panelRegistrarEmpleado.getDireccion().trim();
        String numCuenta = panelRegistrarEmpleado.getNumCuenta().trim();

        // Primera verificación: asegurar que todos los campos estén rellenos
        if (!validarDatos(dni, nss, direccion, numCuenta)) {
            JOptionPane.showMessageDialog(panelRegistrarEmpleado,
                    "Error en el registro: Todos los campos deben estar completos y correctamente rellenados.",
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return; // Salir si algún campo no está correctamente rellenado
        }

        // Verificaciones numéricas
        if (!InterfazUtils.isNumeric(nss) || !InterfazUtils.isNumeric(numCuenta)) {
            JOptionPane.showMessageDialog(panelRegistrarEmpleado,
                    "Error en el registro: El NSS y el número de cuenta deben estar formados exclusivamente por números.",
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        long nssValue = Long.parseLong(nss);
        long numCuentaValue = Long.parseLong(numCuenta);

        // Comprobación de duplicados en el sistema
        if (sistema.checkDniRepetidoEmpleado(dni) || sistema.checkNssRepetidoEmpleado(nssValue)) {
            JOptionPane.showMessageDialog(panelRegistrarEmpleado,
                    "Error en el registro: El DNI/NIF o NSS especificado ya ha sido utilizado.",
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creación y registro del empleado
        Empleado empleado = new Empleado(dni, direccion, nssValue, numCuentaValue,
                panelRegistrarEmpleado.getEnvNotis(),
                panelRegistrarEmpleado.getCambiarClim(),
                panelRegistrarEmpleado.getVenderEntradas());
        sistema.getEmpleados().add(empleado);
        JOptionPane.showMessageDialog(panelRegistrarEmpleado, "Registro completado con éxito.",
                "Éxito en el Registro",
                JOptionPane.INFORMATION_MESSAGE);

        panelRegistrarEmpleado.update();

    }

    /**
     * Validar valores de campos
     * @param dni dni
     * @param nss numero seguridad social
     * @param direccion direccion
     * @param numCuenta numero de cuenta
     * @return true o false
     */
    private boolean validarDatos(String dni, String nss, String direccion, String numCuenta) {
        return !(dni.trim().isEmpty() || nss.trim().isEmpty() ||
                direccion.trim().isEmpty() || numCuenta.trim().isEmpty());
    }

}
