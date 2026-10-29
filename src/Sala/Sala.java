package Sala;

import Utils.*;
import Exposicion.*;
import Obra.*;

import java.io.*;
import java.util.*;

/**
 * Clase abstracta para dar soporte a las salas
 * 
 * @author Víctor Sanz de Vergas
 */
public abstract class Sala implements Serializable {
    /* Atributos de la clase abstracta Sala */
    protected String nombre;
    protected int aforo;
    protected int numTomasCorriente;
    protected Dimensiones dim;
    protected Sala subsala1;
    protected Sala subsala2;
    private Exposicion expo;
    private List<Obra> obras = new ArrayList<Obra>();

    /**
     * Constructor de la clase sala recibiendo como argumento un objeto Dimensiones
     * 
     * @param aforo             aforo de la sala
     * @param numTomasCorriente numero de tomas de corriente de la sala
     * @param dim               dimension de la sala
     * @throws NullPointerException     Si alguna referencia es null
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public Sala(String nombre, int aforo, int numTomasCorriente, Dimensiones dim)
            throws IllegalArgumentException, NullPointerException {
        if (aforo < 0)
            throw new IllegalArgumentException("El aforo es invalido");

        if (numTomasCorriente < 0)
            throw new IllegalArgumentException("El numero de tomas de corriente es inválido");

        if (dim == null)
            throw new NullPointerException("La dimension es inaccesible");
        this.nombre = nombre;
        this.aforo = aforo;
        this.numTomasCorriente = numTomasCorriente;
        this.dim = dim;
        this.subsala1 = null;
        this.subsala2 = null;
        expo = null;

    }

    /**
     * Constructor de la clase sala recibiendo como argumento los atributos de la
     * clase Dimensiones
     * 
     * @param aforo             aforo de la sala
     * @param numTomasCorriente numero de tomas de corriente de la sala
     * @param ancho             ancho de la sala
     * @param largo             largo de la sala
     * @param alto              alto de la sala
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public Sala(String nombre, int aforo, int numTomasCorriente, double ancho, double largo, double alto)
            throws IllegalArgumentException {
        if (aforo < 0)
            throw new IllegalArgumentException("El aforo es invalido");

        if (numTomasCorriente < 0)
            throw new IllegalArgumentException("El numero de tomas de corriente es inválido");

        this.nombre = nombre;
        this.dim = new Dimensiones(ancho, largo, alto);
        this.aforo = aforo;
        this.numTomasCorriente = numTomasCorriente;
        this.subsala1 = null;
        this.subsala2 = null;

        expo = null;
    }

    /**
     * 
     * Obtener el aforo de la sala
     * 
     * @return aforo de la sala
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * 
     * Obtener el numero de tomas de corriente de la sala
     * 
     * @return numero de tomas de corriente
     */
    public int getTomasCorriente() {
        return numTomasCorriente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * Obtener las dimensiones de la sala
     * 
     * @return dimensiones de la sala
     */
    public Dimensiones getDimensiones() {
        return dim;
    }

    /**
     * Getter del atributo obras
     * 
     * @return obras de la sala
     */
    public List<Obra> getObras() {
        return obras;
    }

    /**
     * 
     * Setter para el atributo aforo
     * 
     * @param aforo integer con el aforo de la sala
     * @throws IllegalArgumentException Si el numero del aforo no es correcto
     */
    public void setAforo(int aforo) throws IllegalArgumentException {
        if (aforo < 0)
            throw new IllegalArgumentException("El aforo es invalido");

        this.aforo = aforo;
    }

    /**
     * 
     * Setter para el atributo numTomasCorriente
     * 
     * @param numTomasCorriente integer con el numero de tomas de la sala
     * @throws IllegalArgumentException Si el numero de tomas de corriente no es
     *                                  correcto
     */
    public void setTomasCorriente(int numTomasCorriente) throws IllegalArgumentException {
        if (numTomasCorriente < 0)
            throw new IllegalArgumentException("El numero de tomas de corriente es inválido");

        this.numTomasCorriente = numTomasCorriente;
    }

    /**
     * 
     * Setter para el atributo Dimensiones
     * 
     * @param dim objeto dimensiones de la sala
     * @throws NullPointerException Si la dimension es inaccesible
     */
    public void setDimensiones(Dimensiones dim) throws NullPointerException {
        if (dim == null)
            throw new NullPointerException("La dimension es inaccesible");

        this.dim = dim;
    }

    /**
     * 
     * Setter para el atributo dim recibiendo los atributos de la clase Dimensiones
     * 
     * @param ancho double con ancho de la sala
     * @param largo double con largo de la sala
     * @param alto  double con alto de la sala
     * @throws IllegalArgumentException Si los argumentos son invalidos
     */
    public void setDimensiones(double ancho, double largo, double alto) throws IllegalArgumentException {
        this.dim = new Dimensiones(ancho, largo, alto);
    }

    /**
     * Comprueba si una sala es climatizada
     * 
     * @return true o false
     */
    public abstract boolean isClimatizada();

    /**
     * Getter del atributo temperatura
     * 
     * @return null
     */
    public Temperatura getTemperatura() {
        return null;
    }

    /**
     * Getter del atributo humedad
     * 
     * @return null
     */
    public Humedad getHumedad() {
        return null;
    }

    /**
     * Getter de la primera subsala
     * 
     * @return subsala hija 1
     */
    public Sala getSubsala1() {
        return subsala1;
    }

    /**
     * Getter de la segunda subsala
     * 
     * @return subsala hija 2
     */
    public Sala getSubsala2() {
        return subsala2;
    }

    /**
     * getter del atributo exposicion
     * 
     * @return exposicion en la que está la sala
     */
    public Exposicion getExpo() {
        return expo;
    }

    /**
     * setter del atributo exposicion
     *
     * @param expo exposicion a la que hay que asignar la sala
     * @throws NullPointerException Si se hace una referencia a null
     */
    public void setExpo(Exposicion expo) {
        this.expo = expo;
    }

    /**
     * metodo abstracto para cambiar temperatura
     */
    public abstract void setTemperatura(Temperatura t);

    /**
     * metodo abstracto para cambiar humedad
     */
    public abstract void setHumedad(Humedad h);

    /**
     * metodo abstracto para dividir sala
     * 
     * @return OK si se puede, ERROR en caso contrario
     */
    public abstract void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1,
            int aforo2, int numTomasCorriente1, int numTomasCorriente2, Temperatura t1, Temperatura t2, Humedad h1,
            Humedad h2)
            throws IllegalArgumentException, NullPointerException, IllegalStateException, IllegalCallerException;

    /**
     * metodo abstracto para dividir sala
     * 
     * @return OK si se puede, ERROR en caso contrario
     */
    public abstract void dividirSala(String nombre1, String nombre2, double ancho1, double ancho2, int aforo1,
            int aforo2, int numTomasCorriente1, int numTomasCorriente2)
            throws IllegalArgumentException, NullPointerException, IllegalStateException, IllegalCallerException;

    /**
     * checkea el estado de las obras y si debe cambiar
     * 
     */
    public void checkEstadoSala() {
        if (expo == null) {
            return;
        }

        EstadoExposicion estado = expo.getEstado();

        if (estado == EstadoExposicion.CANCELADA || estado == EstadoExposicion.TERMINADA) {
            expo = null;
            expo.getSalas().remove(this);
            this.getObras().clear();
        }

    }

}
