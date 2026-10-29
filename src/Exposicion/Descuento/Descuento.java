package Exposicion.Descuento;

import Usuario.ClienteRegistrado;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase provee de soporte para los descuentos
 *
 * @author Fabio Desio
 */
public abstract class Descuento implements Serializable {

  private double descuento;

  /**
   * Constructor de la clase descuento
   * @param descuento descuento
   * @throws IllegalArgumentException valor de descuento incorrecto
   */
  public Descuento(double descuento) throws IllegalArgumentException {
    if (descuento < 0 || descuento > 1)
      throw new IllegalArgumentException("El descuento debe estar entre 0 y 1");
    this.descuento = descuento;
  }

  /* GETTERS Y SETTERS */
  /**
   * Getter del atributo descuento
   * @return descuento
   */
  public double getDescuento() {
    return descuento;
  }

  /**
   * Setter del atributo descuento
   * @param descuento
   * @throws IllegalArgumentException valor de descuento incorrecto
   */
  public void setDescuento(double descuento) throws IllegalArgumentException {
    if (descuento < 0 || descuento > 1)
      throw new IllegalArgumentException("El descuento debe estar entre 0 y 1");
    this.descuento = descuento;
  }

  /**
   * Método abstracto para saber si el tipo de un descuento es por antelación
   * @return true, false según si es por antelación o no
   */
  public abstract boolean isPorAntelacion();

  /**
   * Método abstracto para saber si el tipo de un descuento es por compra
   * @return true, false según si es por compra o no
   */
  public abstract boolean isPorCompra();

  /**
   * Metodo abstracto para comprobar si se puede aplicar un descuento
   * @return true, false según si se puede o no
   */
  public abstract boolean checkDescuentoPorAntelacion(LocalDate fecha);

  /**
   * Metodo abstracto para comprobar si se puede aplicar un descuento
   * @return true, false según si se puede o no
   */
  public abstract boolean checkDescuentoPorCompra(ClienteRegistrado cliente);
}
