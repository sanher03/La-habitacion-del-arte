package Sala;

import Sistema.Sistema;
import Utils.*;

/**
 * Clase para dar soporte a las salas no climatizadas
 * 
 * @author Víctor Sanz de Vergas
 */
public class SalaNoClimatizada extends Sala {

    /**
     * Constructor de la clase Sala no climatizada
     * 
     * @param aforo             aforo de la sala
     * @param numTomasCorriente numero de tomas de corriente de la sala
     * @param dim               objeto referente a las dimensiones de la sala
     * @throws IllegalArgumentException Si la llamada al constructor genera una excepcion por los argumentos
     * @throws NullPointerException Si la llamada al constructor genera una excepcion por uuna referencia a null
     */
    public SalaNoClimatizada(String nombre, int aforo, int numTomasCorriente, Dimensiones dim) throws IllegalArgumentException, NullPointerException{
        super(nombre, aforo, numTomasCorriente, dim);
    }

    /**
     * 
     * @param aforo             aforo de la sala
     * @param numTomasCorriente numero de tomas de corriente de la sala
     * @param ancho             ancho de la sala
     * @param largo             largo de la sala
     * @param alto              alto de la sala
     * @throws IllegalArgumentException Si la llamada al constructor lanza alguna excepcion
     */
    public SalaNoClimatizada(String nombre, int aforo, int numTomasCorriente, double ancho, double largo, double alto) throws IllegalArgumentException{
        super(nombre, aforo, numTomasCorriente, ancho, largo, alto);
    }

    /**
     * Divide la sala en otras dos setteando los atributos subsala2 y subsala2
     * 
     * @param ancho1             ancho de la primera subsala
     * @param ancho2             ancho de la segunda subsala
     * @param aforo1             aforo de la primera subsala
     * @param aforo2             aforo de la segunda subsala
     * @param numTomasCorriente1 numero de tomas de la primera subsala
     * @param numTomasCorriente2 numero de tomas de la segunda subsala
     * @throws NullPointerException Si alguna referencia es null
     * @throws IllegalArgumentException Si los argumentos son invalidos
     * @throws IllegalStateException Si la sala ya tiene asociada un exposición
     */
    public void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1, int aforo2, int numTomasCorriente1,
            int numTomasCorriente2) throws IllegalArgumentException, NullPointerException, IllegalStateException{
                if(ancho1 < 0){
                    throw new IllegalArgumentException("El ancho de la primera subsala no es válido"); 
                }
                if(ancho2 < 0){
                    throw new IllegalArgumentException("El ancho de la segunda subsala no es válido"); 
                }
        
                if(aforo1 < 0){
                    throw new IllegalArgumentException("El aforo de la primera subsala no es válido"); 
                }
                if(aforo2 < 0){
                    throw new IllegalArgumentException("El aforo de la segunda subsala no es válido"); 
                }
        
                if(numTomasCorriente1 < 0){
                    throw new IllegalArgumentException("El número de tomas de corriente de la primera subsala no es válido"); 
                }
                if(numTomasCorriente2 < 0){
                    throw new IllegalArgumentException("El número de tomas de corriente de la segunda subsala no es válido"); 
                }
                if (ancho1 + ancho2 != dim.getAncho()){
                    throw new IllegalArgumentException("Las dimensiones indicadas no son compatibles con las de la sala padre");
                } 
                
                if(aforo1 + aforo2 != aforo){
                    throw new IllegalArgumentException("Los aforos indicadas no son compatibles con la sala padre");
                }
                if(numTomasCorriente1 + numTomasCorriente2 != numTomasCorriente)
                {
                    throw new IllegalArgumentException("Las tomas de corriente indicadas no son compatibles con la sala padre");
                }
                if(this.getExpo() != null){
                    throw new IllegalStateException("La sala está ya en una exposición");
                }

        subsala1 = new SalaNoClimatizada(nombre1, aforo1, numTomasCorriente1,
                new Dimensiones(ancho1, dim.getLargo(), dim.getAlto()));
        subsala2 = new SalaNoClimatizada(nombre2, aforo2, numTomasCorriente2,
                new Dimensiones(ancho2, dim.getLargo(), dim.getAlto()));

        Sistema.getInstance().getSalas().add(subsala1);
        Sistema.getInstance().getSalas().add(subsala2);

    }

    /**
     * la sala no es climatizada
     */
    public boolean isClimatizada() {
        return false;
    }

    /**
     * Divide la sala en dos secciones con dimensiones y capacidades especificadas,
     * y configura las condiciones climáticas para cada sección. Funcion que no hace mas que lanzar una 
     * excepción ya que no tiene sentido que sea llamada de esta manera
     * 
     * @param ancho1             Ancho de la primera sección
     * @param ancho2             Ancho de la segunda sección
     * @param aforo1             Aforo máximo de la primera sección
     * @param aforo2             Aforo máximo de la segunda sección
     * @param numTomasCorriente1 Número de tomas de corriente disponibles en la
     *                           primera sección
     * @param numTomasCorriente2 Número de tomas de corriente disponibles en la
     *                           segunda sección
     * @param t1                 Condiciones de temperatura para la primera sección
     * @param t2                 Condiciones de temperatura para la segunda sección
     * @param h1                 Condiciones de humedad para la primera sección
     * @param h2                 Condiciones de humedad para la segunda sección
     * @throws IllegalCallerException Si se llama a la funcion con argumentos Temperatura y Humedad
     */
    public void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1, int aforo2, int numTomasCorriente1,
            int numTomasCorriente2, Temperatura t1, Temperatura t2, Humedad h1, Humedad h2) throws IllegalCallerException{
        throw new IllegalCallerException("No posible dividir una sala no climatizada en salas climatizadas");
    }

    /**
     * Getter del atributo temperatura
     * @return null
     */
    public void setTemperatura(Temperatura t) {
        return;
    }

    /**
     * Getter del atributo humedad
     * @return null
     */
    public void setHumedad(Humedad h) {
        return;
    }

}
