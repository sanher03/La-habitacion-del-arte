package Sorteo;

import Entrada.Comprada;
import java.time.*;
import java.util.*;

/**
 * Esta clase implementa el sorteo valido para un plazo
 * 
 * @author Ignacio Sánchez
 */
public class SorteoPlazoDado extends Sorteo {

  // Atributos
  private LocalDate fInicio;
  private LocalDate fFinal;

  // Constructor

  /**
   * Constructor de la clase SorteoPlazoDado.
   * @param nEntradas Número de entradas para el sorteo.
   * @param fInicioInscripcion Fecha de inicio de la inscripción.
   * @param fFinInscripcion Fecha de fin de la inscripción.
   * @param fInicio Fecha de inicio del plazo para la visita.
   * @param fFinal Fecha final del plazo para la visita.
   */
  public SorteoPlazoDado(
    int nEntradas,
    LocalDate fInicioInscripcion,
    LocalDate fFinInscripcion,
    LocalDate fInicio,
    LocalDate fFinal
  ) {
    super(nEntradas, fInicioInscripcion, fFinInscripcion);
    this.fInicio = fInicio;
    this.fFinal = fFinal;
  }

  // Métodos

  /**
   * Obtiene el tipo de sorteo.
   * @return Tipo de sorteo (PARA_PLAZO_DADO).
   */
  public TipoSorteo getTipoSorteo() {
    return TipoSorteo.PARA_PLAZO_DADO;
  }

  /**
   * Obtiene la fecha de inicio del plazo para la visita.
   * @return Fecha de inicio del plazo para la visita.
   */
  public LocalDate getFechaInicioVisita() {
    return this.fInicio;
  }

  /**
   * Obtiene la fecha final del plazo para la visita.
   * @return Fecha final del plazo para la visita.
   */
  public LocalDate getFechaFinalVisita() {
    return this.fFinal;
  }

  /**
   * Valida una entrada para el sorteo.
   * @param entrada Entrada a validar.
   * @param codigo Código de sorteo.
   * @return true si la entrada es válida, false en caso contrario.
   */
  public boolean validarEntrada(Comprada entrada, UUID codigo) {
    // Solo se puede comprar una entrada con un código de sorteo
    if (entrada.getNumEntradas() > 1) return false;

    // Verifica si el código de sorteo está en la lista de códigos generados
    if (!this.getCodigos().contains(codigo)) {
      return false;
    }

    // Verifica si la fecha de visita está dentro del plazo dado
    if (!fInicio.isBefore(entrada.getFechaVisita())) {
      return false;
    }

    if (!fFinal.isAfter(entrada.getFechaVisita())) {
      return false;
    }

    return true;
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

    return !codigos_aux.isEmpty() && LocalDate.now().isAfter(fFinal);
  }
}
