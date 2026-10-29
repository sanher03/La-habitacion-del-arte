package Interfaz.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import Sistema.Sistema;

import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Empleado.PanelModificarDatosEmp;
import Usuario.Empleado;

/**
 * Controlador modificar datos de empleado
 */
public class ControladorModificarDatosEmp implements ActionListener {
    private PanelModificarDatosEmp panel;

    /**
     * Contructor del controlador
     * @param panel panel datos empleado
     */
    public ControladorModificarDatosEmp(PanelModificarDatosEmp panel) {
        this.panel = panel;
    }

    /**
     * action listener
     * 
     * @param arg0 evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String nss = panel.getNSS().trim();
        String numCuenta = panel.getNumCuenta().trim();

        if (!validarDatosEmp(nss, numCuenta)) {
            JOptionPane.showMessageDialog(panel,
                    "Error en la modificación: los campos a modificar deben estar completos", "Error de modificación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!InterfazUtils.isNumeric(nss) || !InterfazUtils.isNumeric(numCuenta)) {
            JOptionPane.showMessageDialog(panel,
                    "Error en la modificación: el NSS y el número de cuenta deben estar formados exclusivamente por números.",
                    "Error de modificación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        long nssValue = Long.parseLong(nss);
        long numCuentaValue = Long.parseLong(numCuenta);

        Empleado emp = (Empleado) Sistema.getInstance().getUsuarioActivo();
        emp.setNss(nssValue);
        emp.setNumCuenta(numCuentaValue);
        JOptionPane.showMessageDialog(panel,
                String.format("Modificación completada con éxito. Nuevos campos:\n - NSS: " + emp.getNss()
                        + "\n - Número de cuenta: " + emp.getNumCuenta()),
                "Éxito en la modificación",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * validar datos empleado
     * @param nss numero seguridad social
     * @param numCuenta numero de cuenta
     * @return true o false
     */
    public boolean validarDatosEmp(String nss, String numCuenta) {
        return !(nss.trim().isEmpty() || numCuenta.trim().isEmpty() || nss.equals("Introduzca el NSS")
                || numCuenta.equals("Introduzca el número de cuenta"));
    }
}
