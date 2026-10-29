package Exposicion;

import Entrada.*;
import Utils.Status;
import Sistema.Sistema;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

/**
 * Clase para dar soporte a exposiciones temporales
 *
 * @author Fabio Desio
 */
public class ExposicionTemporal extends Exposicion {

  private LocalDate fInicio, fFinal;

  /**
   * Constructor de la exposición temporal
   * 
   * @param autor   de la exposición temporal
   * @param nombre  de la exposición temporal
   * @param descr   de la exposición temporal
   * @param precio  de la exposición temporal
   * @param estado  de la exposición temporal
   * @param fInicio de la exposición temporal
   * @param fFinal  de la exposición temporal
   */
  public ExposicionTemporal(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado,
      LocalDate fInicio,
      LocalDate fFinal) {
    super(nombre, autor, descr, precio, estado);
    validarFechas(fInicio, fFinal);
    this.setEstadoSiguiente(EstadoExposicion.TERMINADA);
    this.fInicio = fInicio;
    this.fFinal = fFinal;
  }

    /**
   * Constructor de la exposición temporal
   * 
   * @param autor   de la exposición temporal
   * @param nombre  de la exposición temporal
   * @param descr   de la exposición temporal
   * @param precio  de la exposición temporal
   * @param estado  de la exposición temporal
   * @param fInicio de la exposición temporal
   * @param fFinal  de la exposición temporal
   */
  public ExposicionTemporal(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado, String imagen,
      LocalDate fInicio,
      LocalDate fFinal) {
    super(nombre, autor, descr, precio, estado, imagen);
    validarFechas(fInicio, fFinal);
    this.setEstadoSiguiente(EstadoExposicion.TERMINADA);
    this.fInicio = fInicio;
    this.fFinal = fFinal;
  }

  /**
   * Método para prorrogar la fecha de una exposición temporal
   *
   * @param fecha nueva fecha final de la exposición temporal
   * @return OK, ERROR en caso de prórroga satisfactoria
   */
  public Status prorrogar(LocalDate fecha) {
    if (fecha == null)
      throw new IllegalArgumentException("La fecha de prórroga es obligatoria");
    if (this.getEstado() != EstadoExposicion.COMENZADA &&
        this.getEstado() != EstadoExposicion.DISPONIBLE)
      return Status.ERROR;

    if (this.getfFinal().isAfter(fecha))
      return Status.ERROR;

    /* Setteamos la fecha estipulada */
    this.setfFinal(fecha);

    return Status.OK;
  }

  /**
   * Método para cambiar el estado de una exposición temporal a CANCELADA
   *
   * @return OK, ERROR en caso de cambio satisfactorio
   */
  public Status cancelar(LocalDate fecha) {

    if (getEstado() == EstadoExposicion.CANCELADA || getEstado() == EstadoExposicion.TERMINADA) throw new IllegalStateException("La exposición ya está cancelada.");
    if (fecha == null || fecha.isBefore(LocalDate.now()))
      throw new IllegalArgumentException("La fecha de cancelación debe ser hoy o posterior.");

    long diasEntreFechas = ChronoUnit.DAYS.between(LocalDate.now(), fecha);
  
    if (diasEntreFechas < 7)
      throw new IllegalArgumentException("Debe cancelar mínimo con 7 días de antelación.");

    Collection<Entrada> entradas = new ArrayList<Entrada>(this.getEntradas());

    for (Entrada e : entradas) {
      LocalDate fechaVisita = e.getFechaVisita();

      if (fechaVisita.isAfter(fecha)) {
        if (e.isComprada()) {
          String numTarjeta = e.getNumTarjeta();
          if (!TeleChargeAndPaySystem.isValidCardNumber(numTarjeta))
            return Status.ERROR;

          try {
            // Pago en negativo (devolver el dinero)
            TeleChargeAndPaySystem.charge(numTarjeta, "Entrada", -e.getPrecioCompra(), true);
          } catch (InvalidCardNumberException er) {
            return Status.ERROR;
          } catch (FailedInternetConnectionException er) {
            return Status.ERROR;
          } catch (OrderRejectedException er) {
            return Status.ERROR;
          }

        }
        this.getEntradas().remove(e);
        Sistema.getInstance().getEntradas().remove(e);
        if (e instanceof Comprada) {
          ((Comprada) e).getCliente().getEntradas().remove(e);
        }
      }
    }

    this.setEstado(EstadoExposicion.CANCELADA);
    this.setfFinal(fecha);
    this.setEstadoSiguiente(EstadoExposicion.CANCELADA);
    return Status.OK;
  }

  private static void validarFechas(LocalDate inicio, LocalDate fin) {
    if (inicio == null || fin == null || fin.isBefore(inicio))
      throw new IllegalArgumentException("El periodo de la exposición no es válido");
  }

  /* GETTERS Y SETTERS */
  /**
   * Getter del atributo fecha de inicio
   * 
   * @return fInicio de la exposición temporal
   */
  @Override
  public LocalDate getfInicio() {
    return fInicio;
  }

  /**
   * Getter del atributo fecha final
   * 
   * @return fFinal de la exposición temporal
   */
  @Override
  public LocalDate getfFinal() {
    return fFinal;
  }

  /**
   * Setter del atributo fecha de inicio
   * 
   * @param fInicio de la exposición temporal
   */
  public void setfInicio(LocalDate fInicio) {
    this.fInicio = fInicio;
  }

  /**
   * Setter del atributo fecha final
   * 
   * @param fFinal de la exposición temporal
   */
  public void setfFinal(LocalDate fFinal) {
    this.fFinal = fFinal;
  }

  /**
   * Método que indica si la exposición es permanente
   *
   * @return true si es permanente, false en caso contrario
   */
  public boolean isPermanente() {
    return false;
  }

  /**
   * Método que indica si la exposición es temporal
   *
   * @return true si es temporal, false en caso contrario
   */
  public boolean isTemporal() {
    return true;
  }
}
