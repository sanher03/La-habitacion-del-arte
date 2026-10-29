package Entrada;

import java.time.*;
import java.io.*;

/**
 * Clase para dar soporte a la entrada
 * 
 * @author Fabio Desio
 */
public class Entrada implements Serializable {

  private int numEntradas;
  private double precioCompra;
  private LocalDate fechaCompra;
  private LocalDate fechaVisita;
  private LocalTime hora;

  /**
   * Constructor de la clase entrada
   * @param numEntradas que se incluyen
   * @param precioCompra de las entradas
   * @param fechaCompra fecha de compra
   * @param fechaVisita fecha de visita
   * @param hora de la exposición
   */
  public Entrada(
    int numEntradas,
    double precioCompra,
    LocalDate fechaCompra,
    LocalDate fechaVisita,
    LocalTime hora
  ) {
    this.numEntradas = numEntradas;
    this.precioCompra = precioCompra;
    this.fechaCompra = fechaCompra;
    this.fechaVisita = fechaVisita;
    this.hora = hora;
  }

  /**
   * Getter del atributo numEntradas
   * @return numEntradas
   */
  public int getNumEntradas() {
    return numEntradas;
  }

  /**
   * Getter del atributo precioCompra
   * @return precioCompra
   */
  public double getPrecioCompra() {
    return precioCompra;
  }

  /**
   * Getter del atributo fechaCompra
   * @return fechaCompra
   */
  public LocalDate getFechaCompra() {
    return fechaCompra;
  }

  /**
   * Getter del atributo hora
   * @return hora de la visita
   */
  public LocalTime getHora() {
    return hora;
  }

  /**
   * Setter del atributo numEntradas
   * @param numEntradas numero de entradas
   */
  public void setNumEntradas(int numEntradas) {
    this.numEntradas = numEntradas;
  }

  /**
   * Setter del atributo precioCompra
   * @param precioCompra precio de la compra
   */
  public void setPrecioCompra(double precioCompra) {
    this.precioCompra = precioCompra;
  }

  /**
   * Setter del atributo fechaCompra
   * @param fechaCompra fecha de la compra
   */
  public void setFechaCompra(LocalDate fechaCompra) {
    this.fechaCompra = fechaCompra;
  }

  /**
   * Setter del atributo hora
   * @param hora hora de la compra
   */
  public void setHora(LocalTime hora) {
    this.hora = hora;
  }

  /**
   * Getter del atributo fecha visita
   * @return fecha visita
   */
  public LocalDate getFechaVisita() {
    return fechaVisita;
  }

  /**
   * Setter del atributo fecha visita
   * @param fechaVisita fecha visita
   */
  public void setFechaVisita(LocalDate fechaVisita) {
    this.fechaVisita = fechaVisita;
  }
  
  /**
   * devuelve si la entrada es comprada
   * @return true si lo es, false en caso contrario
   */
  public boolean isComprada() {
    return false;
  }

  /**
   * devuelve cero porque las entradas no tienen numero de tarjeta
   * @return 0
   */
  public String getNumTarjeta() {
    return null;
  }
}
