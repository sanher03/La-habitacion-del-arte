package Interfaz.Controladores;


import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Exposicion.Exposicion;
import Exposicion.ExposicionPermanente;
import Exposicion.ExposicionTemporal;
import Interfaz.VentanaPrincipal;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.DateConversion;
import Interfaz.Paneles.Gestor.Exposiciones.PanelEditarExpo;
import Interfaz.Paneles.Gestor.Exposiciones.PanelExposicionesGestorCentral;
import Interfaz.Paneles.Gestor.Exposiciones.PanelListaExpoGestor;
import Sala.Sala;
import Sistema.Sistema;
import Utils.Status;

/**
 * Controlador editar exposición
 * 
 * @author Ignacio Sánchez
 */
public class ControladorEditarExpo {
    private PanelEditarExpo vista;
    private JTable tabla;
    private PanelListaExpoGestor lista;
    private PanelExposicionesGestorCentral central;

    /**
     * Constructor del controlador
     * @param vista vista
     * @param lista panel de expos
     * @param central panel central
     */
    public ControladorEditarExpo(PanelEditarExpo vista, PanelListaExpoGestor lista, PanelExposicionesGestorCentral central) {
        this.vista = vista;
        this.tabla = lista.getTabla();
        this.central = central;
        this.lista = lista;
    }

    /**
     * Editar exposición
     */
    public void editarExpo(){
        Exposicion expo = getExposicionSeleccionada();
        if (expo == null) return;
        try {
            expo.setPrecio(Double.parseDouble(vista.getText_precio()));

            if(!((String)vista.getAniadir_combo().getSelectedItem()).equals("Seleccionar Sala")){
                for(Sala s: Sistema.getInstance().getSalasSinExpo()){
                    if(((String)vista.getAniadir_combo().getSelectedItem()).equals(s.getNombre())){
                        expo.addSalas(s);
                        s.setExpo(expo);
                    }
                }
            }

            if(!((String)vista.getEliminar_combo().getSelectedItem()).equals("Seleccionar Sala")){
                String nombreSala = (String) vista.getEliminar_combo().getSelectedItem();
                Sala salaAEliminar = expo.getSalas().stream()
                    .filter(sala -> nombreSala.equals(sala.getNombre()))
                    .findFirst()
                    .orElse(null);
                if (salaAEliminar != null) {
                    expo.getSalas().remove(salaAEliminar);
                    salaAEliminar.setExpo(null);
                }
            }
            JOptionPane.showMessageDialog(vista, "Exposición modificada satisfactoriamente");
            VentanaPrincipal.getInstance().getPanelGestor().update();
            vista.revalidate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    /**
     * Cambiar fase de la exposición
     */
    public void cambiarFase(){
        Exposicion expo = getExposicionSeleccionada();
        if (expo == null) return;

        try{
            expo.publicar();
            JOptionPane.showMessageDialog(vista, "La exposición "+ expo.getNombre()+" se encuentra ahora en fase de disponibilidad");
            VentanaPrincipal.getInstance().getPanelGestor().update();
            vista.revalidate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Cancelar la exposición
     */
    public void cancelarExpo(){
        Exposicion expo = getExposicionSeleccionada();
        if (expo == null) return;

        try{
            Date selectedValue = (Date) vista.getFecha_cancelar().getModel().getValue();

            LocalDate selectedDate = DateConversion.convertToLocalDateViaInstant(selectedValue);
            if (expo.cancelar(selectedDate) != Status.OK) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar la exposición.");
                return;
            }

            JOptionPane.showMessageDialog(vista, "Exposición cancelada satisfactoriamente");
            VentanaPrincipal.getInstance().getPanelGestor().update();
            vista.revalidate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Interrumpir la exposición
     */
    public void interrumpirExpo(){
        Exposicion expo = getExposicionSeleccionada();
        if (expo == null) return;

        try{
            Date selectedValue1 = (Date) vista.getFecha1().getModel().getValue();
            Date selectedValue2 = (Date) vista.getFecha2().getModel().getValue();

            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            ((ExposicionPermanente)expo).interrumpir(selectedDate1, selectedDate2);

            JOptionPane.showMessageDialog(vista, "Exposición interrumpida satisfactoriamente");
            VentanaPrincipal.getInstance().getPanelGestor().update();
            vista.revalidate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * prorrogar exposición
     */
    public void prorrogarExpo(){
        Exposicion expo = getExposicionSeleccionada();
        if (expo == null) return;

        try{
            Date selectedValue = (Date) vista.getFecha_temporal().getModel().getValue();

            LocalDate selectedDate = DateConversion.convertToLocalDateViaInstant(selectedValue);
            ((ExposicionTemporal)expo).prorrogar(selectedDate);

            JOptionPane.showMessageDialog(vista, "Exposición prorrogada satisfactoriamente");
            lista.update();
            ((CardLayout)central.getPanel_derecho().getLayout()).show(central.getPanel_derecho(), InterfazUtils.VACIO);
            vista.update(expo);
            vista.revalidate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Exposicion getExposicionSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una exposición.");
            return null;
        }

        int filaModelo = tabla.convertRowIndexToModel(fila);
        if (filaModelo >= Sistema.getInstance().getExposiciones().size()) {
            JOptionPane.showMessageDialog(vista, "La exposición seleccionada ya no está disponible.");
            return null;
        }
        return Sistema.getInstance().getExposiciones().get(filaModelo);
    }

}
