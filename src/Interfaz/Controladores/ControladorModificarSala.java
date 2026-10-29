package Interfaz.Controladores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Sala.*;
import Utils.*;
import Sistema.Sistema;
import java.util.List;

import Interfaz.Paneles.Common.TablaSalas;
import Interfaz.Paneles.Empleado.PanelDerechoSalasEmpleado;
import Interfaz.Paneles.Empleado.PanelSalasEmpleado;

/**
 * Controlador para modificar sala
 * 
 * @author Ignacio Sánchez
 */
public class ControladorModificarSala{
    private PanelSalasEmpleado vista;
    private PanelDerechoSalasEmpleado panel_derecho;
    private JTable tabla;
    private Sala sala;

    /**
     * Contructor del controlador
     * @param panel panel salas de empleado
     */
    public ControladorModificarSala(PanelSalasEmpleado panel) {
        this.vista = panel;
        this.panel_derecho = panel.getPanelDerecho();
        this.tabla = panel.getTabla();
    }

    /**
     * Seleccionar sala
     */
    public void seleccionarSala() {
        List<Sala> salas = Sistema.getInstance().getSalasClimatizadas();
        int fila = tabla.getSelectedRow();
        if(fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una sala a modificar");
            return;
        }
        
        for(Sala s: salas){
            if(tabla.getValueAt(fila, 0).equals(s.getNombre())){
                this.sala = s;
                panel_derecho.update(s);
                panel_derecho.revalidate();
                System.out.println("dsadsdadsad");
            }
        }

    }

    /**
     * Guardar cambios
     */
    public void guardarCambios() {
        try{
            sala.setTemperatura(new Temperatura(Double.parseDouble(panel_derecho.getTextt1()), Double.parseDouble(panel_derecho.getTextt2())));
            sala.setHumedad(new Humedad(Double.parseDouble(panel_derecho.getTexth1()), Double.parseDouble(panel_derecho.getTexth2())));
            panel_derecho.update(sala);
            TablaSalas.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getSalasClimatizadas());
            vista.revalidate();
            JOptionPane.showMessageDialog(null, "Modificacion completada");
        }
        catch(IllegalArgumentException | IllegalStateException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
