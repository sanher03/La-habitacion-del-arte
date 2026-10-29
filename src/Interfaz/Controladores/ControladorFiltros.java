package Interfaz.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.Exposicion;
import Exposicion.TipoDeObra;
import Interfaz.Paneles.Common.DateConversion;
import Interfaz.Paneles.Common.PanelExposicionesFiltrar;
import Interfaz.Paneles.Common.PanelListaExposiciones;
import Interfaz.Paneles.Common.TablaExposiciones;
import Sistema.Sistema;

/**
 * Controlador filtros búsqueda
 * 
 * @author Ignacio Sánchez
 */
public class ControladorFiltros implements ActionListener {
    private TablaExposiciones expsTabla;
    private JDatePickerImpl fecha1, fecha2;
    private JRadioButton temporal, permanente;
    private JCheckBox casilla1, casilla2, casilla3, casilla4;
    private JTable table;

    /**
     * Contructor del controlador
     * @param filtros panel de filtros
     * @param panelExposiciones panel de exposiciones
     */
    public ControladorFiltros(PanelExposicionesFiltrar filtros, PanelListaExposiciones panelExposiciones) {

        this.fecha1 = filtros.getFecha1();
        this.fecha2 = filtros.getFecha2();
        this.temporal = filtros.getOpcion1();
        this.permanente = filtros.getOpcion2();
        this.casilla1 = filtros.getCasilla1();
        this.casilla2 = filtros.getCasilla2();
        this.casilla3 = filtros.getCasilla3();
        this.casilla4 = filtros.getCasilla4();
        this.table = panelExposiciones.getTabla();
        this.expsTabla = panelExposiciones.getExpsTabla();
    }

    /**
     * metodo para implementar la accion a realizar
     * 
     * @param arg0 evento
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        List<Exposicion> exposiciones = new ArrayList<>(Sistema.getInstance().exposicionesDisponibles());
        
        String message = "Filtros: \nfecha1:";
        Date selectedValue1 = (Date) fecha1.getModel().getValue();
        Date selectedValue2 = (Date) fecha2.getModel().getValue();
        LocalDate selectedDate1;
        LocalDate selectedDate2;
        if (selectedValue1 != null && selectedValue2 != null) {
            selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
            selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
            if (selectedDate2.isBefore(selectedDate1)) {
                JOptionPane.showMessageDialog(null, "La primera fecha es posterior a la segunda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            exposiciones.retainAll(Sistema.getInstance().buscarPorFecha(selectedDate1, selectedDate2));
            message += "\nfecha1:" + selectedDate1 + "\nfecha2:" + selectedDate2;
        } else if ((selectedValue1 != null && selectedValue2 == null) || (selectedValue1 == null && selectedValue2 != null)) {
            JOptionPane.showMessageDialog(null, "Si quiere filtrar por fecha debe elegir un intervalo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        message += "\nTipo: ";
        if (temporal.isSelected()) {
            message += "Temporal";
            exposiciones.retainAll(Sistema.getInstance().buscarPorTemporal());
        } else if (permanente.isSelected()) {
            message += "Permanente";
            exposiciones.retainAll(Sistema.getInstance().buscarPorPermanente());
        } else {
            message += "Ambos tipos";
        }
        List<TipoDeObra> tipos = new ArrayList<>();
        message += "\nCon obras de tipo: \n\t";
        if (casilla1.isSelected()) {
            tipos.add(TipoDeObra.PINTURA);
            message += "pinturas ";
        }
        if (casilla2.isSelected()) {
            tipos.add(TipoDeObra.ESCULTURA);
            message += "esculturas ";
        }
        if (casilla3.isSelected()) {
            tipos.add(TipoDeObra.FOTOGRAFIA);
            message += "fotografias ";
        }
        if (casilla4.isSelected()) {
            tipos.add(TipoDeObra.AUDIOVISUAL);
            message += "audiovisuales ";
        }

        if (!tipos.isEmpty()) {
            exposiciones.retainAll(Sistema.getInstance().buscarPorTipo(tipos.toArray(new TipoDeObra[0])));
        }

        JOptionPane.showMessageDialog(null, message, "Filtros seleccionados", JOptionPane.INFORMATION_MESSAGE);
        expsTabla.modificarContenidoFilas((DefaultTableModel)table.getModel(), exposiciones);
    }
}
