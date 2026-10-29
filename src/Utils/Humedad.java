package Utils;

import java.io.*;

/** 
 * Clase para dar soporte a la humedad
 * 
 * @author Víctor Sanz de Vergas
*/
public class Humedad implements Serializable{
    /*Atributos de la clase Humedad */
    private double min;
    private double max;
    
    /**
     * Constructor de la clase Humedad
     * 
     * @param min minimo de humedad
     * @param max maximo de humedad
     * 
     * @throws IllegalArgumentException el minimo es mayor que el maximo
     */
    public Humedad(double min, double max) throws IllegalArgumentException {
        if (min > max) throw new IllegalArgumentException("El mínimo de humedad es mayor que el maximo.");
        this.min = min;
        this.max = max;
    }

    /**
     * Getter del atributo minimo
     * 
     * @return
     */
    public double getMin() {
        return min;
    }

    /**
     * Setter del atributo minimo
     * 
     * @param min minimo de humedad
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * Getter del atributo maximo
     * 
     * @return
     */
    public double getMax() {
        return max;
    }

    /**
     * Setter del atributo maximo
     * 
     * @param max maximo de humedad
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * Checker para comprobar si dos humedades son compatibles
     * 
     * @param humedad a checkear
     * @return true o false
     */
    public boolean checkHumedad(Humedad humedad) {

        if (humedad == null) {
            return false;
        }

        return this.min >= humedad.min && this.max <= humedad.max;
    }
}
