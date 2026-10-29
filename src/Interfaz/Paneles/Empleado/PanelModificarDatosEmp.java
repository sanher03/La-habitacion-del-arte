package Interfaz.Paneles.Empleado;

import java.awt.event.ActionListener;

import Interfaz.Paneles.Gestor.PanelRegistrarEmpleado;
import Usuario.Empleado;

/**
 * Panel para modificar datos de empleado
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelModificarDatosEmp extends PanelRegistrarEmpleado {

    /**
     * Función para actualizar el panel
     * 
     * @param emp empleado
     */
    public void update(Empleado emp) {
        super.update();
        titulo.getTitulo().setText("Modificar datos");

        valorDNI.setEditable(false);
        valorDNI.setFocusable(false);
        valorDNI.setText(emp.getDni());

        valorNSS.setText(Long.toString(emp.getNss()));

        valorDireccion.setEditable(false);
        valorDireccion.setFocusable(false);
        valorDireccion.setText(emp.getDireccion());

        valorNumCuenta.setText(Long.toString(emp.getNumCuenta()));

        envNotis.setSelected(emp.isEnviarNotificaciones());
        envNotis.setEnabled(false);

        cambiarClim.setSelected(emp.isCambiarClimatizacion());
        cambiarClim.setEnabled(false);

        venderEntradas.setSelected(emp.isVenderEntradas());
        venderEntradas.setEnabled(false);

        botonRegistrarEmpleado.setText("Modificar datos");
    }

    /**
     * Setter del controlador modificar datos de empleado
     * @param c actionListener
     */
    public void setControladorModificarDatosEmp(ActionListener c) {
        botonRegistrarEmpleado.addActionListener(c);
    }

}