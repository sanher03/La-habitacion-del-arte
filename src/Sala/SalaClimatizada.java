package Sala;

import Utils.*;

import Obra.*;
import Sistema.Sistema;

/**
 * Clase para dar soporte a las salas climatizadas
 * 
 * @author Víctor Sanz de Vergas
 */
public class SalaClimatizada extends Sala {
    private Temperatura temperatura;
    private Humedad humedad;

    /**
     * Constructor de la clase SalaClimatizada con atributos pasados como objetos
     * 
     * @param id                id de la
     * @param aforo
     * @param numTomasCorriente
     * @param dim
     * @throws NullPointerException Si alguna referencia es null
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public SalaClimatizada(String nombre, int aforo, int numTomasCorriente, Dimensiones dim, Temperatura temperatura,
            Humedad humedad) throws IllegalArgumentException, NullPointerException{
        
        super(nombre, aforo, numTomasCorriente, dim);
        if(humedad == null){
            throw new NullPointerException("La humedad no es accesible");
        }
        this.humedad = humedad;
        if(temperatura == null){
            throw new NullPointerException("La temperatura no es accesible");
        }
        this.temperatura = temperatura;
    }

    /**
     * Constructor de la clase SalaClimatizada que inicializa una sala con
     * condiciones climáticas.
     * 
     * @param aforo             Aforo máximo de la sala
     * @param numTomasCorriente Número de tomas de corriente disponibles en la sala
     * @param ancho             Ancho de la sala
     * @param largo             Largo de la sala
     * @param alto              Alto de la sala
     * @param temp_min          Temperatura mínima admitida en la sala
     * @param temp_max          Temperatura máxima admitida en la sala
     * @param hum_min           Humedad mínima admitida en la sala
     * @param hum_max           Humedad máxima admitida en la sala
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public SalaClimatizada(String nombre, int aforo, int numTomasCorriente, double ancho, double largo, double alto, double temp_min,
            double temp_max, double hum_min, double hum_max) throws IllegalArgumentException{
        super(nombre, aforo, numTomasCorriente, ancho, largo, alto);
        this.humedad = new Humedad(hum_min, hum_max);
        this.temperatura = new Temperatura(temp_min, temp_max);
    }

    /**
     * Getter del atributo temperatura
     * 
     * @return objeto temperatura
     */
    public Temperatura getTemperatura() {
        return temperatura;
    }

    /**
     * Setter del atributo temperatura
     * 
     * @param temperatura objeto referente a la temperatura de la sala
     * @throws NullPointerException Si alguna referencia es null
     */
    public void setTemperatura(Temperatura temperatura) throws NullPointerException, IllegalArgumentException{
        if(temperatura == null) throw new NullPointerException("La temperatura es inaccesible");

        Sistema sist = Sistema.getInstance();

        if (!sist.getUsuarioActivo().isCambiarClimatizacion()) {
            throw new IllegalStateException("El empleado no tiene permisos para cambiar la climatización.");
        }

        for (Obra o : getObras()) {
            if (!temperatura.checkTemperatura(o.getTemp()))
                throw new IllegalArgumentException("La temperatura no cumple las restricciones de todas las obras.");
        }
        this.temperatura = temperatura;
    }

    /**
     * Setter del atributo temperatura
     * 
     * @param temp_min temperatura minima de la sala
     * @param temp_max temperatura máxima de la sala
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public void setTemperatura(double temp_min, double temp_max) throws IllegalArgumentException, IllegalStateException{
        Temperatura temperatura = new Temperatura(temp_min, temp_max);

        Sistema sist = Sistema.getInstance();

        if (!sist.getUsuarioActivo().isCambiarClimatizacion()) {
            throw new IllegalStateException("El empleado no tiene permisos para cambiar la climatización.");
        }

        for (Obra o : getObras()) {
            if (!temperatura.checkTemperatura(o.getTemp()))
                throw new IllegalArgumentException("La temperatura no cumple las restricciones de todas las obras.");
        }

        this.temperatura = temperatura;
    }

    /**
     * Getter del atributo humedad
     * 
     * @return objeto referente a la humedad de la sala
     */
    public Humedad getHumedad() {
        return humedad;
    }

    /**
     * Setter del atributo humedad
     * 
     * @param humedad objeto referente a la humedad de la sala
     * @throws NullPointerException Si la humedad es null
     */
    public void setHumedad(Humedad humedad) throws NullPointerException{
        if(humedad == null){
            throw new NullPointerException("El objeto humedad es inaccesible");
        }

        Sistema sist = Sistema.getInstance();

        if (!sist.getUsuarioActivo().isCambiarClimatizacion()) {
            throw new IllegalStateException("El empleado no tiene permisos para cambiar la climatización.");
        }

        for (Obra o : getObras()) {
            if (!humedad.checkHumedad(o.getHumedad()))
                throw new IllegalArgumentException("La humedad no cumple las restricciones de todas las obras.");
        }

        this.humedad = humedad;
    }

    /**
     * Setter del atributo humedad recibiendo como argumento los parámetros de
     * humedad
     * 
     * @param hum_min humedad minima de la sala
     * @param hum_max humedad máxima de la sala
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public void setHumedad(double hum_min, double hum_max) throws IllegalArgumentException{
        Humedad humedad = new Humedad(hum_min, hum_max);

        Sistema sist = Sistema.getInstance();

        if (!sist.getUsuarioActivo().isCambiarClimatizacion()) {
            throw new IllegalStateException("El empleado no tiene permisos para cambiar la climatización.");
        }

        for (Obra o : getObras()) {
            if (!humedad.checkHumedad(o.getHumedad()))
                throw new IllegalArgumentException("La humedad no cumple las restricciones de todas las obras.");
        }

        this.humedad = humedad;
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
     * @return OK o ERROR en funcion de si hay ido bien o mal
     * @throws NullPointerException Si alguna referencia es null
     * @throws IllegalArgumentException Si los argumentos son invalidos
     * @throws IllegalStateException Si la sala ya tiene asociada un exposición
     */
    public void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1, int aforo2, int numTomasCorriente1,
            int numTomasCorriente2, Temperatura t1, Temperatura t2, Humedad h1, Humedad h2) throws IllegalArgumentException, NullPointerException, IllegalStateException{
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
        if(t1 == null){
            throw new NullPointerException("La temperatura de la primera subsala es inaccesible o vacía");
        }
        if(t2 == null){
            throw new NullPointerException("La temperatura de la segunda subsala es inaccesible o vacía");
        }
        if(h1 == null){
            throw new NullPointerException("La humedad de la primera subsala es inaccesible o vacía");
        }
        if(h2 == null){
            throw new NullPointerException("La humedad de la segunda subsala es inaccesible o vacía");
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

        subsala1 = new SalaClimatizada(nombre1, aforo1, numTomasCorriente1,
                new Dimensiones(ancho1, dim.getLargo(), dim.getAlto()), t1, h1);
        subsala2 = new SalaClimatizada(nombre2, aforo2, numTomasCorriente2,
                new Dimensiones(ancho2, dim.getLargo(), dim.getAlto()), t2, h2);

        Sistema.getInstance().getSalas().add(subsala1);
        Sistema.getInstance().getSalas().add(subsala2);


    }

    /**
     * la sala es climatizada
     */
    public boolean isClimatizada() {
        return true;
    }

    /**
     * Divide la sala en dos secciones con dimensiones y capacidades especificadas,
     * sin configurar condiciones climáticas para cada sección.
     * 
     * @param ancho1             Ancho de la primera sección
     * @param ancho2             Ancho de la segunda sección
     * @param aforo1             Aforo máximo de la primera sección
     * @param aforo2             Aforo máximo de la segunda sección
     * @param numTomasCorriente1 Número de tomas de corriente disponibles en la
     *                           primera sección
     * @param numTomasCorriente2 Número de tomas de corriente disponibles en la
     *                           segunda sección
     * @throws IllegalCallerException Si se llama a la funcion sin temperaturas y humedades en los argumentos
     * 
     */
    public void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1, int aforo2, int numTomasCorriente1,
            int numTomasCorriente2) throws IllegalCallerException{
        throw new IllegalCallerException("No posible dividir una sala climatizada sin indicar temperatura y humedad");
    }

}
