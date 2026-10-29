package Exposicion.Descuento;

import Entrada.Comprada;
import Usuario.ClienteRegistrado;
import java.time.LocalDate;

/**
 * Esta clase da soporte a los descuentos por compra
 *
 * @author Fabio Desio
 */
public class DescuentoPorCompra extends Descuento {

  private int nEntradas;

  /**
   * Constructor de la clase descuento por compra
   * @param descuento estipulado
   * @param nEntradas número de entradas compradas
   * @throws IllegalArgumentException número de entradas inválido
   */
  public DescuentoPorCompra(double descuento, int nEntradas)
    throws IllegalArgumentException {
    super(descuento);
    if (nEntradas <= 0) throw new IllegalArgumentException(
      "Número de entradas inválido, debe ser mayor cero"
    );
    this.nEntradas = nEntradas;
  }

  /* GETTERS Y SETTERS */
  /**
   * Getter del atributo nEntradas
   * @return nEntradas
   */
  public int getNEntradas() {
    return nEntradas;
  }

  /**
   * Setter del atributo nEntradas
   * @param nEntradas número de entradas
   * @throws IllegalArgumentException número de entradas inválido
   */
  public void setNEntradas(int nEntradas) throws IllegalArgumentException {
    if (nEntradas <= 0) throw new IllegalArgumentException(
      "Número de entradas inválido, debe ser mayor cero"
    );
    this.nEntradas = nEntradas;
  }

  /**
   * Método que indica si el descuento es por antelación
   *
   * @return true si es por antelación, false en caso contrario
   */
  public boolean isPorAntelacion() {
    return false;
  }

  /**
   * Método que indica si el descuento es por compra
   *
   * @return true si es por compra, false en caso contrario
   */
  public boolean isPorCompra() {
    return true;
  }

  @Override
  /**
   * Método para verificar si el descuento por antelación es aplicable
   * @param fecha fecha para la que se desea aplicar el descuento
   * @return false, ya que este descuento es específico para compras
   */
  public boolean checkDescuentoPorAntelacion(LocalDate fecha) {
    return false;
  }

  @Override
  /**
   * Método para verificar si el descuento por compra es aplicable
   * @param cliente cliente registrado que realiza la compra
   * @return true si el descuento es aplicable, false en caso contrario
   * @throws NullPointerException cliente nulo
   */
  public boolean checkDescuentoPorCompra(ClienteRegistrado cliente) throws NullPointerException {
    int nEntradas = 0;

    if (cliente == null) throw new NullPointerException("Cliente inválido, no puede ser nulo");

    // Calcular el número total de entradas compradas por el cliente
    for (Comprada comprada : cliente.getEntradas()) {
      nEntradas += comprada.getNumEntradas();
    }

    // Verificar si el número de entradas compradas cumple con el requisito para aplicar el descuento
    return nEntradas >= this.nEntradas;
  }
}
