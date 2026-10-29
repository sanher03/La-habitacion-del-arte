package Interfaz.Controladores;

import javax.swing.JOptionPane;

import Exposicion.*;

import Interfaz.Paneles.Empleado.PanelVenderEntradas;
import java.time.LocalTime;

/**
 * Controlador de venta de entradas 
 *  
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class ControladorVenderEntradas {
    private PanelVenderEntradas vista;
    private Exposicion expo;

    /**
     * Constructor del controlador
     * @param vista panel venta de entradas
     * @param exposicion exposicion a atender
     */
    public ControladorVenderEntradas(PanelVenderEntradas vista, Exposicion exposicion) {
        this.vista = vista;
        this.expo = exposicion;
    }

    /**
     * Mostrar precio según número de entradas
     */
    public void controladorNumEntradas(){
        if (((String) vista.getComboBox().getSelectedItem())
                        .equals("Número de entradas")) {
            return;
        } else {
            int numEntradas = Integer.valueOf(
                    (String) vista.getComboBox().getSelectedItem());
            vista.update(
                      numEntradas * expo.getPrecio(), vista.getPanelDerechoAbajo());
        }
        vista.revalidate();
    }

    /**
     * Vender entradas
     */
    public void controladorVender(){
        String tarjeta =vista.getNumTarjeta().getText();
        String numEntradasS = (String) vista.getComboBox().getSelectedItem();
        String horaS = (String)vista.getHoras().getSelectedItem();
        if (numEntradasS.equals("Número de entradas") || horaS.equals("Seleccionar hora")) {
            JOptionPane.showMessageDialog(null, "Debe selecionar un número de entradas y una hora", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int numEntradas = Integer.valueOf(
                        (String) vista.getComboBox().getSelectedItem());
        LocalTime hora = LocalTime.parse(horaS);
        try {
            expo.venderEntrada(numEntradas, hora, tarjeta);
            JOptionPane.showMessageDialog(null, "La compra se ha realizado con éxito.", "Filtros seleccionados", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
