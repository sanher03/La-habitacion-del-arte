package Interfaz.Controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.Exposicion;
import Interfaz.Paneles.Common.DateConversion;
import Interfaz.Paneles.Gestor.Exposiciones.*;
import Sistema.Sistema;
import Sorteo.Sorteo;
import Sorteo.SorteoFechaHora;
import Sorteo.SorteoMientrasDureExp;
import Sorteo.SorteoPlazoDado;
import Utils.Status;

/**
 * Controlador sorteos gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class ControladorSorteosGestor {

    /**
     * Crear sorteo mientras dura exposicion
     * @param vista panel de sorteo
     * @param comboBox combobox
     * @param inicio inicio inscripcion
     * @param fin fin inscripcion
     * @param expo exposicion a la que añadir el sorteo
     */
    public static void createSorteoMientrasDureExp(PanelSorteo vista, JComboBox<String> comboBox, JDatePickerImpl inicio,
            JDatePickerImpl fin, Exposicion expo) {
        Date selectedValue1 = (Date) inicio.getModel().getValue();
        Date selectedValue2 = (Date) fin.getModel().getValue();
        String comboBoxItem = (String) comboBox.getSelectedItem();

        if (comboBoxItem.equals("Número de entradas")) {
            JOptionPane.showMessageDialog(null, "Si quiete crear un sorteo debe seleccionar un número de entradas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (selectedValue1 != null && selectedValue2 != null) {
            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha es posterior a la segunda.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int nEntradas = Integer.parseInt(comboBoxItem);
                SorteoMientrasDureExp sorteo = new SorteoMientrasDureExp(nEntradas, selectedDate1, selectedDate2);
                expo.setSorteo(sorteo);
                Sistema.getInstance().addSorteos(sorteo);
                PanelSorteoCreado sorteoCreado = vista.getSorteoCreado();
                sorteoCreado.update(sorteo);
                sorteoCreado.revalidate();
                vista.revalidate();
                JOptionPane.showMessageDialog(null, "SorteoMientrasDureExpo creado.", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                ((CardLayout) vista.getLayout()).show(vista, "Sorteo creado");
                
                return;
            }

        } else if ((selectedValue1 != null && selectedValue2 == null)
                || (selectedValue1 == null && selectedValue2 != null)) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un intervalo entre dos fechas.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Crear sorteo plazo dado
     * @param vista panel de sorteos
     * @param comboBox combobox
     * @param inicio inicio inscripcion
     * @param fin fin inscripcion
     * @param fecha1 fecha inicio plazo
     * @param fecha2 fecha fin plazo
     * @param expo exposicion a la que añadir sorteo
     */
    public static void createSorteoPlazoDado(PanelSorteo vista, JComboBox<String> comboBox, JDatePickerImpl inicio,
            JDatePickerImpl fin, JDatePickerImpl fecha1, JDatePickerImpl fecha2, Exposicion expo) {
        Date selectedValue1 = (Date) inicio.getModel().getValue();
        Date selectedValue2 = (Date) fin.getModel().getValue();
        Date selectedValue3 = (Date) fecha1.getModel().getValue();
        Date selectedValue4 = (Date) fecha2.getModel().getValue();
        String comboBoxItem = (String) comboBox.getSelectedItem();

        if (comboBoxItem.equals("Número de entradas")) {
            JOptionPane.showMessageDialog(null, "Si quiere crear un sorteo debe seleccionar un número de entradas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (selectedValue1 != null && selectedValue2 != null && selectedValue3 != null
                && selectedValue4 != null) {
            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            LocalDate selectedDate3 = DateConversion.convertToLocalDateViaInstant(selectedValue3);
            LocalDate selectedDate4 = DateConversion.convertToLocalDateViaInstant(selectedValue4);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha de inscripción es posterior a la segunda.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (selectedDate3.isBefore(selectedDate2)) {
                JOptionPane.showMessageDialog(null,
                        "La fecha de inicio del plazo de visita es anterior a la de fin de inscripción.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (selectedDate4.isBefore(selectedDate3)) {
                JOptionPane.showMessageDialog(null, "La fecha de fin del plazo de visita es anterior a la de inicio.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int nEntradas = Integer.parseInt(comboBoxItem);
                SorteoPlazoDado sorteo = new SorteoPlazoDado(nEntradas, selectedDate1, selectedDate2, selectedDate3,
                        selectedDate4);
                expo.setSorteo(sorteo);
                Sistema.getInstance().addSorteos(sorteo);
                PanelSorteoCreado sorteoCreado = vista.getSorteoCreado();
                sorteoCreado.update(sorteo);
                sorteoCreado.revalidate();
                vista.revalidate();
                JOptionPane.showMessageDialog(null, "SorteoPlazoDado creado.", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                ((CardLayout) vista.getLayout()).show(vista, "Sorteo creado");
                return;
            }

        } else if ((selectedValue1 == null || selectedValue2 == null || selectedValue3 == null
                || selectedValue4 == null)) {
            JOptionPane.showMessageDialog(null, "Debe rellenar los cuatro campos de fecha.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Crear sorteo fecha hora
     * @param vista panel de sorteos
     * @param comboBox combobox
     * @param inicio inicio de isncripcion
     * @param fin fin de inscripcion
     * @param fecha1 fecha de visita
     * @param horas hora de visita
     * @param expo exposicion a la que añadir el sorteo
     */
    public static void createSorteoFechaHora(PanelSorteo vista, JComboBox<String> comboBox, JDatePickerImpl inicio,
            JDatePickerImpl fin, JDatePickerImpl fecha1, JComboBox<String> horas, Exposicion expo) {
        Date selectedValue1 = (Date) inicio.getModel().getValue();
        Date selectedValue2 = (Date) fin.getModel().getValue();
        Date selectedValue3 = (Date) fecha1.getModel().getValue();
        String hora = (String) horas.getSelectedItem();
        String comboBoxItem = (String) comboBox.getSelectedItem();

        if (comboBoxItem.equals("Número de entradas")) {
            JOptionPane.showMessageDialog(null, "Si quiere crear un sorteo debe seleccionar un número de entradas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (selectedValue1 != null && selectedValue2 != null || selectedValue3 != null
                || !comboBoxItem.equals("Seleccionar hora")) {
            LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            LocalDate selectedDate3 = DateConversion.convertToLocalDateViaInstant(selectedValue3);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha de inscripción es posterior a la segunda.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (selectedDate3.isBefore(selectedDate2)) {
                JOptionPane.showMessageDialog(null, "La fecha de visita es posterior a la de fin de inscripción.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int nEntradas = Integer.parseInt(comboBoxItem);
                LocalTime horaParsed = LocalTime.parse(hora);
                SorteoFechaHora sorteo = new SorteoFechaHora(nEntradas, selectedDate1, selectedDate2, selectedDate3,
                        horaParsed);
                expo.setSorteo(sorteo);
                Sistema.getInstance().addSorteos(sorteo);
                PanelSorteoCreado sorteoCreado = vista.getSorteoCreado();
                sorteoCreado.update(sorteo);
                sorteoCreado.revalidate();
                vista.revalidate();
                JOptionPane.showMessageDialog(null, "SorteoFechaHora creado.", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                ((CardLayout) vista.getLayout()).show(vista, "Sorteo creado");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Completar panel de creación
     * @param panel panel de creación de sorteo
     * @param tipoSorteo tipo de sorteo
     */
    public static void completarPanelCreacion(PanelSorteoCrear panel, JComboBox<String> tipoSorteo) {
        String item = (String) tipoSorteo.getSelectedItem();
        if (item.equals("Seleccione tipo de sorteo")) {
            return;
        } else if (item.equals("Para fecha y hora")) {
            panel.update(panel.getExposicion(), 1);
            panel.completarFechaHora();
            return;
        } else if (item.equals("Para plazo dado")) {
            panel.update(panel.getExposicion(), 2);
            panel.completarPlazoDado();
            return;
        } else {
            panel.update(panel.getExposicion(), 3);
            panel.completarMientrasDureExpo();
            return;
        }
    }

    /**
     * Sortear
     * @param panel panel de sorteos
     * @param sorteo sorteo a sortear
     */
    public static void sortear(PanelSorteoCreado panel, Sorteo sorteo) {
        if (sorteo.sortear() == Status.OK) {
            JOptionPane.showMessageDialog(null, "Se han sorteado las entradas con éxito.", "Sorteo completado", JOptionPane.INFORMATION_MESSAGE);
            panel.update(sorteo);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "No se puedo realizar el sorteo satisfactoriamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
