package Sorteo;

import Entrada.*;
import java.time.*;
import java.util.*;

/**
 * Esta clase implementa el sorteo valido para una fecha y hora concretos
 * 
 * @author Ignacio Sánchez
 */
public class SorteoFechaHora extends Sorteo {

  // Atributos
  private LocalDate fecha;
  private LocalTime hora;

  // Constructor

  /**
   * Constructor de la clase SorteoFechaHora.
   * 
   * @param nEntradas          Número de entradas para el sorteo.
   * @param fInicioInscripcion Fecha de inicio de la inscripción.
   * @param fFinInscripcion    Fecha de fin de la inscripción.
   * @param fecha              Fecha del sorteo.
   * @param hora               Hora del sorteo.
   */
  public SorteoFechaHora(
      int nEntradas,
      LocalDate fInicioInscripcion,
      LocalDate fFinInscripcion,
      LocalDate fecha,
      LocalTime hora) {
    super(nEntradas, fInicioInscripcion, fFinInscripcion);
    this.fecha = fecha;
    this.hora = hora;
  }

  // Métodos

  /**
   * Obtiene el tipo de sorteo.
   * 
   * @return Tipo de sorteo (PARA_FECHA_HORA).
   */
  public TipoSorteo getTipoSorteo() {
    return TipoSorteo.PARA_FECHA_HORA;
  }

  /**
   * Valida una entrada para el sorteo.
   * 
   * @param entrada Entrada a validar.
   * @param codigo  Código de sorteo.
   * @return true si la entrada es válida, false en caso contrario.
   */
  public boolean validarEntrada(Comprada entrada, UUID codigo) {
    // Solo se puede comprar una entrada con un código de sorteo
    if (entrada.getNumEntradas() > 1)
      return false;

    // Verifica si el código de sorteo está en la lista de códigos generados
    if (!this.getCodigos().contains(codigo)) {
      return false;
    }

    // Verifica si la fecha de visita de la entrada es igual a la fecha del sorteo
    if (!entrada.getFechaVisita().isEqual(fecha)) {
      return false;
    }

    // Verifica si la hora de visita de la entrada es igual a la hora del sorteo
    if (!entrada.getHora().equals(hora)) {
      return false;
    }

    return true;
  }

  // Getters y setters

  /**
   * Obtiene la fecha de visita del sorteo.
   * 
   * @return Fecha de visita.
   */
  public LocalDate getFechaVisita() {
    return this.fecha;
  }

  /**
   * Obtiene la hora de visita del sorteo.
   * 
   * @return Hora de visita.
   */
  public LocalTime getHoraVisita() {
    return this.hora;
  }

  /**
   * Establece la fecha de visita del sorteo.
   * 
   * @param fecha Fecha de visita a establecer.
   */
  public void setFechaVisita(LocalDate fecha) {
    this.fecha = fecha;
  }

  /**
   * Establece la hora de visita del sorteo.
   * 
   * @param hora Hora de visita a establecer.
   */
  public void setHoraVisita(LocalTime hora) {
    this.hora = hora;
  }

  @Override
  /**
   * Comprueba si alguno de los códigos pertenece a este sorteo y ya no sirve
   * 
   * @param codigos codigos a comprobar
   */
  public boolean hasCodigoInservible(List<UUID> codigos) {
    List<UUID> codigos_aux = new ArrayList<UUID>(codigos);

    codigos_aux.retainAll(this.getCodigos());

    codigos.removeAll(codigos_aux);

    return !codigos_aux.isEmpty() && (LocalDate.now().isAfter(fecha) || (LocalDate.now().isEqual(fecha) && (LocalTime.now().isAfter(hora))));
  }
}
