package Interfaz.Controladores;

import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.Exposicion;
import Interfaz.Paneles.Common.DateConversion;

/**
 * Controlador extadísticas exposición
 * 
 * @author Ignacio Sánchez
 */
public class ControladorEstadisticasExpo {

    /**
     * comprobar estadísticas exposición
     * @param expo exposicion
     * @param inicio fecha inicio
     * @param fin fecha fin
     */
    public static void comprobarEstadisticas(Exposicion expo, JDatePickerImpl inicio, JDatePickerImpl fin) {
        Date selectedValue1 = (Date) inicio.getModel().getValue();
        Date selectedValue2 = (Date) fin.getModel().getValue();

        if (selectedValue1 != null && selectedValue2 != null) {
            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha es posterior a la segunda.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int entradasTotales = 0;
                double precioTotal = 0;
                entradasTotales = expo.getNumEntradasTotal(selectedDate1, selectedDate2);
                precioTotal = expo.getDineroRecaudado(selectedDate1, selectedDate2);
                JOptionPane.showMessageDialog(null, "Entradas vendidas totales: " + entradasTotales
                        + "\nDinero recaudado total: " + precioTotal + " €", "Estadísticas globales",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        } else if ((selectedValue1 != null && selectedValue2 == null)
                || (selectedValue1 == null && selectedValue2 != null)) {
            JOptionPane.showMessageDialog(null, "Si quiere filtrar por fecha debe elegir un intervalo.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            int entradasTotales = 0;
            double precioTotal = 0;
            entradasTotales = expo.getNumEntradasTotales();
            precioTotal = expo.getDineroRecaudadoTotal();
            JOptionPane.showMessageDialog(null,
                    "Entradas vendidas totales: " + entradasTotales + "\nDinero recaudado total: " + precioTotal + " €",
                    expo.getNombre(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

}
