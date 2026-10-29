package Utils;

import java.io.*;

/**
 * Clase para dar soporte a las dimensiones de un objeto o sala
 *
 * @author Víctor Sanz de Vergas
 */
public class Dimensiones implements Serializable {

  private double ancho;
  private double largo;
  private double alto;

  /**
   * Constructor de la clase Dimensionse
   *
   * @param ancho double con el ancho
   * @param largo double con el largo
   * @param alto double con el alto
   */
  public Dimensiones(double ancho, double alto, double largo) {
    this.ancho = ancho;
    this.largo = largo;
    this.alto = alto;
  }

  /**
   * Getter del atributo ancho
   *
   * @return double del ancho
   */
  public double getAncho() {
    return ancho;
  }

  /**
   * Getter del atributo largo
   *
   * @return double del largo
   */
  public double getLargo() {
    return largo;
  }

  /**
   * Getter del atributo alto
   *
   * @return double del alto
   */
  public double getAlto() {
    return alto;
  }

  /**
   * Setter del atributo ancho
   *
   * @param ancho double del ancho
   */
  public void setAncho(double ancho) {
    this.ancho = ancho;
  }

  /**
   * Setter del atributo largo
   *
   * @param largo double del largo
   */
  public void setLargo(double largo) {
    this.largo = largo;
  }

  /**
   * Setter del atributo alto
   *
   * @param alto double del alto
   */
  public void setAlto(double alto) {
    this.alto = alto;
  }

  /**
   * Checker para comprobar si dos dimensiones son compatibles
   *
   * @param dim a checkear
   * @return true o false
   */
  public boolean checkDimensiones(Dimensiones dim) {
    return (
      this.alto > dim.alto && this.ancho > dim.ancho && this.largo > dim.largo
    );
  }
}
