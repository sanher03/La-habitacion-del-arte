package Interfaz.Controladores;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePickerImpl;

import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.DateConversion;
import Sistema.Sistema;

/**
 * Controlador estadísticas globales
 * 
 * @auhtor Ignacio Sánchez
 */
public class ControladorEstadisticasGlobales {
    /**
     * volver atrás
     * @param vista vista
     */
    public static void volverAtras(JPanel vista) {
        CardLayout layout = (CardLayout) vista.getLayout();

        layout.show(vista, InterfazUtils.EXPO_PRINCIPAL);
    }

    /**
     * mostrar estadísticas globales
     * @param vista
     */
    public static void mostrarEstadisticasGlobales(JPanel vista) {
        CardLayout layout = (CardLayout) vista.getLayout();

        layout.show(vista, InterfazUtils.ESTADISTICAS);
    }
    
    /**
     * Comprobar las estadísticas 
     * @param inicio fecha inicio
     * @param fin fecha fin
     */
    public static void comprobarEstadisticas(JDatePickerImpl inicio, JDatePickerImpl fin) {
        Date selectedValue1 = (Date) inicio.getModel().getValue();
        Date selectedValue2 = (Date) fin.getModel().getValue();

        if (selectedValue1 != null && selectedValue2 != null) {
            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha es posterior a la segunda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int entradasTotales = 0;
                double precioTotal = 0;
                entradasTotales = Sistema.getInstance().getNumEntradasTotales(selectedDate1, selectedDate2);
                precioTotal = Sistema.getInstance().getDineroRecaudadoTotal(selectedDate1, selectedDate2);
                JOptionPane.showMessageDialog(null, "Entradas vendidas totales: " + entradasTotales + "\nDinero recaudado total: " + precioTotal + " €", "Estadísticas globales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
        } else if ((selectedValue1 != null && selectedValue2 == null) || (selectedValue1 == null && selectedValue2 != null)) {
            JOptionPane.showMessageDialog(null, "Si quiere filtrar por fecha debe elegir un intervalo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            int entradasTotales = 0;
                double precioTotal = 0;
                entradasTotales = Sistema.getInstance().getNumEntradasTotales();
                precioTotal = Sistema.getInstance().getDineroRecaudadoTotal();
                JOptionPane.showMessageDialog(null, "Entradas vendidas totales: " + entradasTotales + "\nDinero recaudado total: " + precioTotal + " €", "Estadísticas globales", JOptionPane.INFORMATION_MESSAGE);
                return;
        }
    }

}
