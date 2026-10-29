package Exposicion;

import Entrada.*;
import Utils.Status;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Clase para dar soporte a las exposiciones permanentes
 *
 * @author Fabio
 */
public class ExposicionPermanente extends Exposicion {

  LocalDate fInicioInterrupcion;
  LocalDate fFinInterrupcion;

  /**
   * Constructor de una exposición permanente
   * 
   * @param nombre de la exposición
   * @param autor de la exposición
   * @param descr de la exposición
   * @param precio de la exposición
   * @param estado de la exposición
   */
  public ExposicionPermanente(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado) {
    super(nombre, autor, descr, precio, estado);
    this.setEstadoSiguiente(EstadoExposicion.DISPONIBLE);
    fFinInterrupcion = null;
    fInicioInterrupcion = null;
  }

  /**
   * Constructor de una exposición permanente
   * 
   * @param nombre de la exposición
   * @param autor de la exposición
   * @param descr de la exposición
   * @param precio de la exposición
   * @param estado de la exposición
   * @param imagen de la exposición
   */
  public ExposicionPermanente(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado, String imagen) {
    super(nombre, autor, descr, precio, estado, imagen);
    this.setEstadoSiguiente(EstadoExposicion.DISPONIBLE);
    fFinInterrupcion = null;
    fInicioInterrupcion = null;
  }

  /**
   * Método que indica si la exposición es permanente
   *
   * @return true si es permanente, false en caso contrario
   */
  @Override
  public boolean isPermanente() {
    return true;
  }

  /**
   * Método que indica si la exposición es temporal
   *
   * @return true si es temporal, false en caso contrario
   */
  @Override
  public boolean isTemporal() {
    return false;
  }

  @Override
  public LocalDate getfInicio() {
    return fInicioInterrupcion;
  }

  @Override
  public LocalDate getfFinal() {
    return fFinInterrupcion;
  }

  /**
   * Método para cambiar el estado de una exposición temporal a CANCELADA
   * 
   * @param fecha fecha de inicio de la cancelación
   *
   * @return OK, ERROR en caso de cambio satisfactorio
   */
  public Status cancelar(LocalDate fecha) throws IllegalArgumentException, IllegalStateException {

    if (getEstado() == EstadoExposicion.CANCELADA) throw new IllegalStateException("La exposición ya está cancelada.");

    long diasEntreFechas = ChronoUnit.DAYS.between(LocalDate.now(), fecha);

    // Comprobar si hay al menos 7 días entre las fechas
    if (diasEntreFechas < 7) 
      throw new IllegalArgumentException("Debe cancelar mínimo con 7 días de antelación.");

    fFinInterrupcion = fecha;

    this.setEstadoSiguiente(EstadoExposicion.CANCELADA);

    return Status.OK;
  }

  /**
   * Método para interrumpir una exposición permanente
   * 
   * @param fInicio fecha de inicio de la interrupción
   * @param fFin    fecha de fin de la interrupción
   * @return OK, ERROR según si se ha interrumpido satisfactoriamente o no
   */
  public Status interrumpir(LocalDate fInicio, LocalDate fFin) {
    for (Entrada e : this.getEntradas()) {
      LocalDate fechaVisita = e.getFechaVisita();

      if (fechaVisita.isAfter(fInicio) && fechaVisita.isBefore(fFin)) {
        return Status.ERROR;
      }
    }
    fInicioInterrupcion = fInicio;
    fFinInterrupcion = fFin;

    return Status.OK;
  }
}
