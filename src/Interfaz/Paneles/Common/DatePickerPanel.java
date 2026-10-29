package Interfaz.Paneles.Common;

import java.util.Properties;

import org.jdatepicker.impl.*;

/**
 * Panel para elegir la fecha
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class DatePickerPanel {

    /**
     * Método para crear un datePicker
     * 
     * @return datePicker creado
     */
    public static JDatePickerImpl create() {
        UtilDateModel model = new UtilDateModel();
        model.setSelected(false);
        // model.setDate(20,04,2014);
        // Need this...W
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        return datePicker;
    }
    
}
