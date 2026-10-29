package Exposicion.Descuento;

import Usuario.ClienteRegistrado;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Esta clase da soporte a los descuentos por antelación
 *
 * @author Fabio Desio
 */
public class DescuentoPorAntelacion extends Descuento {

  private int nMeses;

  /**
   * Constructor de la clase descuento por antelación
   * @param descuento estipulado
   * @param nMeses número de meses antelación
   * @throws IllegalArgumentException si los argumentos son incorrectos
   */
  public DescuentoPorAntelacion(double descuento, int nMeses)
    throws IllegalArgumentException {
    super(descuento);
    if (nMeses < 0) throw new IllegalArgumentException(
      "Valor de número de meses incorrecto, debe ser mayor que cero"
    );
    this.nMeses = nMeses;
  }

  /* GETTERS Y SETTERS */
  /**
   * Getter del atributo nMeses
   * @return nMeses
   */
  public int getNMeses() {
    return nMeses;
  }

  /**
   * Setter del atributo nMeses
   * @param nMeses
   * @throws IllegalArgumentException valor de número de meses incorrecto
   */
  public void setNMeses(int nMeses) {
    if (nMeses < 0) throw new IllegalArgumentException(
      "Valor de número de meses incorrecto, debe ser mayor que cero"
    );
    this.nMeses = nMeses;
  }

  /**
   * Método que indica si el descuento es por antelación
   *
   * @return true si es por antelación, false en caso contrario
   */
  public boolean isPorAntelacion() {
    return true;
  }

  /**
   * Método que indica si el descuento es por compra
   *
   * @return true si es por compra, false en caso contrario
   */
  public boolean isPorCompra() {
    return false;
  }

  @Override
  /**
   * Método para verificar si el descuento por antelación es aplicable
   * @param fecha1 fecha de visita
   * @return true si el descuento es aplicable, false en caso contrario
   */
  public boolean checkDescuentoPorAntelacion(LocalDate fecha1)
    {
    if (fecha1 == null || fecha1.isBefore(LocalDate.now())) return false;
    long mesesDeAntelacion = ChronoUnit.MONTHS.between(LocalDate.now(), fecha1);
    return mesesDeAntelacion >= nMeses;
  }

  @Override
  /**
   * Método para verificar si el descuento por compra es aplicable
   * @param cliente cliente registrado que realiza la compra
   * @return false, ya que este descuento es específico para antelación
   */
  public boolean checkDescuentoPorCompra(ClienteRegistrado cliente) {
    return false;
  }
}
