package Entrada;

import Usuario.ClienteRegistrado;

import java.time.*;

/**
 * Clase para dar soporte a las entradas compradas
 *
 * @author Fabio Desio
 */
public class Comprada extends Entrada {

  private String numTarjeta;
  private ClienteRegistrado cliente;

  /**
   * Constructor de la clase entrada comprada
   * @param numEntradas a comprar
   * @param precioCompra total de la compra
   * @param fechaCompra fecha para la que se compra la entrada
   * @param fechaVisita fecha de visita
   * @param hora para la que se compra la entrada
   * @param numTarjeta asociada a la compra
   * @param cliente comprador de la entrada
   */
  public Comprada(
    int numEntradas,
    double precioCompra,
    LocalDate fechaCompra,
    LocalDate fechaVisita,
    LocalTime hora,
    String numTarjeta,
    ClienteRegistrado cliente
  ) {
    super(numEntradas, precioCompra, fechaCompra, fechaVisita, hora);
    this.numTarjeta = numTarjeta;
    this.cliente = cliente;
  }

  /* GETTERS Y SETTERS */

  /**
   * Getter del atributo numTarjeta
   * @return numTarjeta
   */
  @Override
  public String getNumTarjeta() {
    return numTarjeta;
  }

  /**
   * Getter del atributo cliente registrado
   * @return cliente
   */
  public ClienteRegistrado getCliente() {
    return cliente;
  }

  /**
   * Setter del atributo numTarjeta
   * @param numTarjeta numero de tarjeta
   */
  public void setNumTarjeta(String numTarjeta) {
    this.numTarjeta = numTarjeta;
  }

  /**
   * Setter del atributo cliente registrado
   * @param cliente cliente que compra la entrada
   */
  public void setCliente(ClienteRegistrado cliente) {
    this.cliente = cliente;
  }

  /**
   * devuelve si la entrada es comprada
   * @return true
   */
  @Override
  public boolean isComprada() {
    return true;
  }
}
