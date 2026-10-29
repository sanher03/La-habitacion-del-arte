package Horario;

import java.io.Serializable;
import java.time.*;

/**
 * Esta clase define el horario de apertura y cierre cada dia en el centro de exposiciones
 * 
 * @author Victor Sanz de Vergas
 * 
 */
public class Horario implements Serializable{
    private LocalTime apertura; /*Hora de apertura del centro de exposiciones */
    private LocalTime cierre; /*Hora de cierre del centro de exposiciones */
    
    /**
     * Constructor para la clase Horario
     * 
     * @param apertura objeto LocalTime con la hora de apertura
     * @param cierre objeto LocalTime con la hora de apertura
     */
    public Horario(LocalTime apertura, LocalTime cierre) {
        this.apertura = apertura;
        this.cierre = cierre;
    }

    /**
     * Constructor para la clase Horario recibiendo como argumentos las horas, minutos y segundos
     * 
     * @param hora_apertura int con la hora de apertura
     * @param minuto_apertura int con el minuto de apertura
     * @param hora_cierre int con la hora de cierre
     * @param minuto_cierre int con el minuto de cierre
     */
    public Horario(int hora_apertura, int minuto_apertura, int hora_cierre, int minuto_cierre) {
        this.apertura = LocalTime.of(hora_apertura, minuto_apertura);
        this.cierre = LocalTime.of(hora_cierre, minuto_cierre);
    }

    /**
     * Getter de la hora de apertura
     * 
     * @return objeto asociado a la hora de apertura
     */
    public LocalTime getApertura() {
        return apertura;
    }

    /**
     * Setter de la hora de apertura
     * 
     * @param apertura objeto asociado a la hora de apertura
     */
    public void setApertura(LocalTime apertura) {
        this.apertura = apertura;
    }

    /**
     * Setter de la hora de apertura con la hora y minuto como argumentos
     * 
     * @param hora_apertura int con la hora de apertura
     * @param minuto_apertura int con el minuto de apertura
     */
    public void setApertura(int hora_apertura, int minuto_apertura) {
        this.apertura = LocalTime.of(hora_apertura, minuto_apertura);
    }

    /**
     * Getter de la hora de cierre
     * 
     * @return objeto LocalTime de la hora de cierre
     */
    public LocalTime getCierre() {
        return cierre;
    }

    /**
     * Setter de la hora de cierre
     * 
     * @param cierre objeto Localtime de la hora de cierre
     */
    public void setCierre(LocalTime cierre) {
        this.cierre = cierre;
    }

    /**
     * Setter de la hora de cierre con la hora y minuto como argumentos
     * 
     * @param hora_cierre int con la hora de cierre
     * @param minuto_cierre int con el minuto de cierre
     */
    public void setCierre(int hora_cierre, int minuto_cierre) {
        this.cierre = LocalTime.of(hora_cierre, minuto_cierre);
    }

    
}
