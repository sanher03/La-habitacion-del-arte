package Interfaz.Paneles.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.swing.JFormattedTextField.AbstractFormatter;
 
/**
 * Clase para formatear fechas
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class DateLabelFormatter extends AbstractFormatter {
 
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
    /**
     * Método sobreescrito para convertir de string a fechas formateadas
     * 
     * @return fecha formateada
     * @throws ParseException para parseos fallidos
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
 
    /**
     * Método sobreescrito para convertir de fechas a string
     * 
     * @param value valor a convertir
     * @return fecha pasada a string o "" si el valor pasado por argumento es nulo
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
         
        return "";
    }
 
}
