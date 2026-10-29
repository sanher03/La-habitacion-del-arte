package Obra;

import Exposicion.TipoDeObra;
import Utils.*;

/**
 * Clase que representa una pintura, la cual es una obra climatizada.
 * Extiende de la clase ObraClimatizada para heredar sus atributos y métodos.
 * 
 * @author Ignacio Sánchez
 */
public class Pintura extends ObraClimatizada {

  private String tecnica;

  /**
   * Constructor de una pintura.
   * @param nombre Nombre de la pintura.
   * @param autor Autor de la pintura.
   * @param anio Año de creación de la pintura.
   * @param propia Indica si la pintura pertenece al centro o es externa.
   * @param poliza Número de póliza de seguro.
   * @param cuantia_seguro Cuantía del seguro.
   * @param descr Descripción de la pintura.
   * @param tecnica Técnica utilizada en la pintura.
   * @param ancho Ancho de la pintura.
   * @param largo Largo de la pintura.
   * @param alto Alto de la pintura.
   * @param tempMin Temperatura mínima requerida para conservación.
   * @param tempMax Temperatura máxima requerida para conservación.
   * @param humMin Humedad mínima requerida para conservación.
   * @param humMax Humedad máxima requerida para conservación.
   */
  public Pintura(
    String nombre,
    String autor,
    int anio,
    boolean propia,
    String poliza,
    double cuantia_seguro,
    String descr,
    String tecnica,
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
      alto,
      tempMin,
      tempMax,
      humMin,
      humMax
    );
    this.tecnica = tecnica;
  }

  /**
   * Constructor de una pintura.
   * @param nombre Nombre de la pintura.
   * @param autor Autor de la pintura.
   * @param anio Año de creación de la pintura.
   * @param propia Indica si la pintura pertenece al centro o es externa.
   * @param poliza Número de póliza de seguro.
   * @param cuantia_seguro Cuantía del seguro.
   * @param descr Descripción de la pintura.
   * @param tecnica Técnica utilizada en la pintura.
   * @param dim Dimensiones de la pintura.
   * @param temp Temperatura requerida para conservación.
   * @param hum Humedad requerida para conservación.
   */
  public Pintura(
    String nombre,
    String autor,
    int anio,
    boolean propia,
    String poliza,
    double cuantia_seguro,
    String descr,
    String tecnica,
    Dimensiones dim,
    Temperatura temp,
    Humedad hum
  ) {
    super(
      nombre,
      autor,
      anio,
      propia,
      poliza,
      cuantia_seguro,
      descr,
      dim,
      temp,
      hum
    );
    this.tecnica = tecnica;
  }

  /**
   * Obtiene la técnica utilizada en la pintura.
   * @return Técnica utilizada.
   */
  public String getTecnica() {
    return tecnica;
  }

  /**
   * Establece la técnica utilizada en la pintura.
   * @param tecnica Técnica utilizada.
   */
  public void setTecnica(String tecnica) {
    this.tecnica = tecnica;
  }

  /**
   * Devuelve el tipo de obra, que en este caso es una pintura.
   * @return Tipo de obra.
   */
  public TipoDeObra getTipoObra() {
    return TipoDeObra.PINTURA;
  }

  /**
   * Crea una pintura a partir de los parámetros leídos de un fichero.
   * @param campos Parámetros del fichero.
   * @return Pintura creada a partir de los parámetros.
   */
  public static Pintura cargarPinturaDeFichero(String... campos) {
    if (!campos[0].equals("CUADRO")) return null;

    boolean propia = false;
    if (campos[1].equals("CENTRO")) {
      propia = true;
    } else if (campos[1].equals("EXTERNA")) {
      propia = false;
    }

    String titulo = campos[2];

    String autor = campos[3];

    int anio = Integer.parseInt(campos[4]);

    String descr = campos[5];

    double cuantia_seguro = Double.parseDouble(campos[6]);

    String poliza = campos[7];

    String tecnica = campos[8];

    double ancho = Double.parseDouble(campos[13]);

    double alto = Double.parseDouble(campos[14]);

    double largo = 0;

    Dimensiones dim = new Dimensiones(ancho, alto, largo);

    Temperatura temp = null;
    if (!campos[16].equals("")) {
      String[] rango_temp = campos[16].split("--");

      double temp_min = Double.parseDouble(rango_temp[0]);

      double temp_max = Double.parseDouble(rango_temp[1]);

      temp = new Temperatura(temp_min, temp_max);
    }

    Humedad humedad = null;
    if (!campos[17].equals("")) {
      String[] rango_humedad = campos[17].split("--");

      double humedad_min = Double.parseDouble(rango_humedad[0]);

      double humedad_max = Double.parseDouble(rango_humedad[1]);

      humedad = new Humedad(humedad_min, humedad_max);
    }

    return new Pintura(
      titulo,
      autor,
      anio,
      propia,
      poliza,
      cuantia_seguro,
      descr,
      tecnica,
      dim,
      temp,
      humedad
    );
  }
}
