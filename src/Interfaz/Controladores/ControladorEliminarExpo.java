package Interfaz.Controladores;


import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Exposicion.Exposicion;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Gestor.Exposiciones.PanelExposicionesGestorCentral;
import Interfaz.Paneles.Gestor.Exposiciones.PanelListaExpoGestor;
import Interfaz.Paneles.Gestor.Exposiciones.TablaExposicionesGestor;
import Sistema.Sistema;

/**
 * Controlador para borrar exposición
 * 
 * @author Ignacio Sánchez
 */
public class ControladorEliminarExpo {
    private JTable tabla;
    private PanelExposicionesGestorCentral central;

    /**
     * Constructor del controlador
     * @param lista lista exposiciones
     * @param central panel central
     */
    public ControladorEliminarExpo(PanelListaExpoGestor lista, PanelExposicionesGestorCentral central) {
        this.tabla = lista.getTabla();
        this.central = central;
    }

    /**
     * Borrar exposición
     */
    public void borrarExpo(){
        int fila = tabla.getSelectedRow();

        if(fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una sala a modificar");
            return;
        }

        Exposicion expo = Sistema.getInstance().getExposiciones().get(fila);


        try{
            Sistema.getInstance().getExposiciones().remove(expo);
            JOptionPane.showMessageDialog(central, "Exposición eliminada satisfactoriamente");
            TablaExposicionesGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getExposiciones());
            ((CardLayout)central.getPanel_derecho().getLayout()).show(central.getPanel_derecho(), InterfazUtils.VACIO);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}

