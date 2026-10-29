package Interfaz.Controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

import Exposicion.Exposicion;
import Interfaz.Paneles.Cliente.PanelComprarEntradas;
import Interfaz.Paneles.Common.DateConversion;
import Sistema.Sistema;
import Usuario.ClienteRegistrado;
import Utils.Status;

/**
 * Controlador para comprar entradas
 * 
 * @author Ignacio Sánchez
 */
public class ControladorCompraEntradas{
    PanelComprarEntradas vista;
    Exposicion expo;
    ClienteRegistrado c;

    /**
     * Constructor del controlador
     * @param vista panels
     * @param expo exposicion
     */
    public ControladorCompraEntradas(PanelComprarEntradas vista, Exposicion expo){
        this.vista = vista;
        this.expo = expo;
        this.c = (ClienteRegistrado)Sistema.getInstance().getUsuarioActivo();
    }

    /**
     * Controlador de las fechas de compra
     */
    public void controladorFecha(){
        if ((Date) vista.getFechaCompra().getModel().getValue() == null || ((String) vista.getComboBox().getSelectedItem())
        .equals("Número de entradas")) {
                return;
        } else {
                int numEntradas = Integer.valueOf(
                                (String) vista.getComboBox().getSelectedItem());
                Date selectedValue1 = (Date) vista.getFechaCompra().getModel().getValue();
                LocalDate fecha = null;
                if (selectedValue1 != null) {
                        fecha = DateConversion.convertToLocalDateViaInstant(selectedValue1);
                }
                vista.update(
                                expo.precioAPagar(
                                                c,
                                                fecha, numEntradas),
                                vista.getPanelDerechoAbajo());
        }
        vista.revalidate();
    }

    /**
     * Controlador para mostrar el precio según el número de entradas
     */
    public void controladorNumEntradas(){
        if (((String) vista.getComboBox().getSelectedItem())
                        .equals("Número de entradas")) {
                return;
        } else {
                int numEntradas = Integer.valueOf(
                                (String) vista.getComboBox().getSelectedItem());
                Date selectedValue1 = (Date) vista.getFechaCompra().getModel().getValue();
                LocalDate fecha = null;
                if (selectedValue1 != null) {
                        fecha = DateConversion.convertToLocalDateViaInstant(selectedValue1);
                }
                vista.update(
                                expo.precioAPagar(
                                                c,
                                                fecha, numEntradas),
                                vista.getPanelDerechoAbajo());
        }
        vista.revalidate();
    }

    /**
     * Controlador de compra de entradas
     */
    public void controladorComprar(){
        String tarjeta = vista.getNumTarjeta().getText();
        String numEntradasS = (String) vista.getComboBox().getSelectedItem();
        String horaS = (String) vista.getHoras().getSelectedItem();
        Date selectedValue1 = (Date) vista.getFechaCompra().getModel().getValue();
        if (numEntradasS.equals("Número de entradas") || horaS.equals("Seleccionar hora")
                        || selectedValue1 == null) {
                JOptionPane.showMessageDialog(null,
                                "Debe selecionar un número de entradas y una hora", "Error",
                                JOptionPane.ERROR_MESSAGE);
                return;
        }
        int numEntradas = Integer.valueOf(
                        (String) vista.getComboBox().getSelectedItem());
        LocalTime hora = LocalTime.parse(horaS);
        LocalDate fecha = DateConversion.convertToLocalDateViaInstant(selectedValue1);
        if (!vista.getCodigo().getText().equals("")) {
                try {
                        expo.comprarEntrada(c, numEntradas, fecha, hora, tarjeta,
                                        vista.getCodigo().getText());
                        JOptionPane.showMessageDialog(null,
                                        "La compra se ha realizado con éxito.",
                                        "Filtros seleccionados",
                                        JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, exc.getMessage(), "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }else{
            try {
                Status st = expo.comprarEntrada(c, numEntradas, fecha, hora, tarjeta);
                if (st == Status.OK) {
                        JOptionPane.showMessageDialog(null, "La compra se ha realizado con éxito.",
                                        "Filtros seleccionados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                        JOptionPane.showMessageDialog(null, "La compra no pudo realizarse.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
}
