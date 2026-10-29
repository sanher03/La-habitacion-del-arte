package Interfaz.Paneles.Common;

import java.time.*;
import java.util.*;

/**
 * Clase para convertir fechas
 */
public class DateConversion {
    /**
     * Método para convertir la fecha a Instant
     * 
     * @return fecha convertida
     */
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
}
