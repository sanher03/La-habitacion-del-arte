package Obra;

import Utils.*;

import Sala.*;

/**
 * Clase abstracta que representa una obra que requiere condiciones específicas de temperatura y humedad.
 * Extiende de la clase Obra para heredar sus atributos y métodos.
 * 
 * @author Ignacio Sánchez
 */
public abstract class ObraClimatizada extends Obra {

  private Temperatura temp;
  private Humedad humedad;

  /**
   * Constructor de una obra climatizada.
   * @param nombre Nombre de la obra.
   * @param autor Autor de la obra.
   * @param anio Año de creación de la obra.
   * @param propia Indica si la obra pertenece al centro o es externa.
   * @param poliza Número de póliza de seguro.
   * @param cuantia_seguro Cuantía del seguro.
   * @param descr Descripción de la obra.
   * @param dim Dimensiones de la obra.
   * @param temp Temperatura requerida para conservación.
   * @param humedad Humedad requerida para conservación.
   */
  public ObraClimatizada(
    String nombre,
    String autor,
    int anio,
    boolean propia,
    String poliza,
    double cuantia_seguro,
    String descr,
    Dimensiones dim,
    Temperatura temp,
    Humedad humedad
  ) {
    super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, dim);
    this.temp = temp;
    this.humedad = humedad;
  }

  /**
   * Constructor de una obra climatizada.
   * @param nombre Nombre de la obra.
   * @param autor Autor de la obra.
   * @param anio Año de creación de la obra.
   * @param propia Indica si la obra pertenece al centro o es externa.
   * @param poliza Número de póliza de seguro.
   * @param cuantia_seguro Cuantía del seguro.
   * @param descr Descripción de la obra.
   * @param ancho Ancho de la obra.
   * @param largo Largo de la obra.
   * @param alto Alto de la obra.
   * @param tempMin Temperatura mínima requerida para conservación.
   * @param tempMax Temperatura máxima requerida para conservación.
   * @param humMin Humedad mínima requerida para conservación.
   * @param humMax Humedad máxima requerida para conservación.
   */
  public ObraClimatizada(
    String nombre,
    String autor,
    int anio,
    boolean propia,
    String poliza,
    double cuantia_seguro,
    String descr,
    double ancho,
    double largo,
    double alto,
    double tempMin,
    double tempMax,
    double humMin,
    double humMax
  ) {
    super(
      nombre,
      autor,
      anio,
      propia,
      poliza,
      cuantia_seguro,
      descr,
      ancho,
      largo,
      alto
    );
    temp = new Temperatura(tempMin, tempMax);
    humedad = new Humedad(humMin, humMax);
  }

  /**
   * Obtiene la temperatura requerida para la conservación de la obra.
   * @return Temperatura requerida.
   */
  @Override
  public Temperatura getTemp() {
    return temp;
  }

  /**
   * Establece la temperatura requerida para la conservación de la obra.
   * @param temp Temperatura requerida.
   */
  public void setTemp(Temperatura temp) {
    this.temp = temp;
  }

  /**
   * Obtiene la humedad requerida para la conservación de la obra.
   * @return Humedad requerida.
   */
  @Override
  public Humedad getHumedad() {
    return humedad;
  }

  /**
   * Establece la humedad requerida para la conservación de la obra.
   * @param humedad Humedad requerida.
   */
  public void setHumedad(Humedad humedad) {
    this.humedad = humedad;
  }

  /**
   * Verifica si la obra necesita condiciones específicas de temperatura y humedad para su conservación.
   * @return true si necesita climatización, false en caso contrario.
   */
  public boolean necesitaClimatizacion() {
    if (temp == null && humedad == null) {
      return false;
    }
    return true;
  }

  @Override
  /**
   * metodo para decidir si una obra cabe en una sala
   * 
   * @param sala sala a comprobar
   */
  public boolean cabeEnSala(Sala s) {
    if (s.getDimensiones().checkDimensiones(dim)) {
      if (this.necesitaClimatizacion()) {
        if (!s.isClimatizada())
          return false;

        if (this.getTemp() != null) {
          if (!s.getTemperatura().checkTemperatura(this.getTemp()))
            return false;
        }

        if (this.getHumedad() != null) {
          if (!s.getHumedad().checkHumedad(this.getHumedad()))
            return false;
        }
      }

      return true;
    }

    return false;
  }
}
