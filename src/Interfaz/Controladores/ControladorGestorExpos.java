package Interfaz.Controladores;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import Exposicion.EstadoExposicion;
import Exposicion.Exposicion;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Gestor.Exposiciones.PanelSorteo;
import Interfaz.Paneles.Gestor.Exposiciones.PanelSorteoCreado;
import Interfaz.Paneles.Gestor.Exposiciones.PanelSorteoCrear;
import Interfaz.Paneles.Gestor.Exposiciones.PanelDescuentos;
import Interfaz.Paneles.Gestor.Exposiciones.PanelAñadirDescuento;
import Interfaz.Paneles.Gestor.Exposiciones.PanelDescuentoExistente;
import Interfaz.Paneles.Gestor.Exposiciones.PanelExposicionesGestorCentral;
import Interfaz.Paneles.Common.*;
import Sistema.Sistema;
import Sorteo.Sorteo;

import java.awt.*;

/**
 * Controlador exposiciones gestor
 * 
 * @author Ignacio Sánchez
 */
public class ControladorGestorExpos {
    private PanelExposicionesGestorCentral vista;
    private JTable tabla;
    private Exposicion expo;

    /**
     * Constructor del controlador
     * @param vista panel exposiciones central
     */
    public ControladorGestorExpos(PanelExposicionesGestorCentral vista) {
        this.vista = vista;
        this.tabla = vista.getLista_exposiciones().getTabla();
    }

    /**
     * Seleccionar exposicion
     * @param tipo tipo de boton
     */
    public void seleccionarExpo(TipoBotonExpo tipo) {
        List<Exposicion> exposiciones = Sistema.getInstance().getExposiciones();
        JPanel panelDerecho = vista.getPanel_derecho();
        int fila = tabla.getSelectedRow();
        if (fila == -1 && tipo != TipoBotonExpo.NUEVO) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una exposicion para realizar esa acción",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(tipo != TipoBotonExpo.NUEVO){
            this.expo = exposiciones.get(fila);
        }
        
        
        if(tipo == TipoBotonExpo.NUEVO){
            vista.getPanel_programar().updateRestart();
            ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.PROGRAMAR_EXPOSICION);
        }
        else if(tipo == TipoBotonExpo.EDITAR){
            vista.getPanel_editar().update(expo);
            vista.getPanel_editar().revalidate();
            ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.EDITAR_EXPO);
        }
        else if(tipo == TipoBotonExpo.BORRAR){
            if(expo.getEstado() == EstadoExposicion.EN_CREACION || expo.getEstado() == EstadoExposicion.CANCELADA || expo.getEstado() == EstadoExposicion.TERMINADA){
                ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.BORRAR_EXPO_CORRECTO);
            }
            else{
                ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.BORRAR_EXPO_INCORRECTO);
            }
        }
        else if(tipo == TipoBotonExpo.DESCUENTOS){
            PanelDescuentos panelDescuentos = vista.getPanel_descuentos();
            PanelDescuentoExistente descuentoExistente = panelDescuentos.getExistente();
            descuentoExistente.update(expo);
            descuentoExistente.revalidate();
            PanelAñadirDescuento descuentoAniadir = panelDescuentos.getAniadir();
            descuentoAniadir.update(expo, 0);
            descuentoAniadir.revalidate();
            panelDescuentos.revalidate();
            ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.DESCUENTOS_EXPO);
        }
        else if(tipo == TipoBotonExpo.SORTEO){
            PanelSorteo panelSorteo = vista.getPanel_sorteo();
            Sorteo sorteo = expo.getSorteo();
            if (sorteo != null) {
                PanelSorteoCreado sorteoCreado = panelSorteo.getSorteoCreado();
                sorteoCreado.update(sorteo);
                sorteoCreado.revalidate();
                panelSorteo.revalidate();
                ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.SORTEO_EXPO);
                ((CardLayout) panelSorteo.getLayout()).show(panelSorteo, "Sorteo creado");
            } else {
                PanelSorteoCrear sorteoCrear = panelSorteo.getSorteoCrear();
                sorteoCrear.update(expo, 0);
                sorteoCrear.revalidate();
                panelSorteo.revalidate();
                ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.SORTEO_EXPO);
                ((CardLayout) panelSorteo.getLayout()).show(panelSorteo, "Sorteo crear");
            }
        }
        else if(tipo == TipoBotonExpo.ESTADISTICAS){
            vista.getPanel_estadisticas().update(expo);
            vista.getPanel_estadisticas().revalidate();
            ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, InterfazUtils.ESTADISTICAS_EXPO);
        }
        
    }

   
}
