package Obra;

import Exposicion.EstadoExposicion;
import Exposicion.Exposicion;
import Exposicion.TipoDeObra;
import Sala.Sala;
import Utils.*;
import java.io.Serializable;

/**
 * Esta clase engloba los diferentes tipos de los que puede ser una obra.
 * 
 * @author Ignacio Sánchez
 */
public abstract class Obra implements Serializable {

  public String nombre;
  public String autor;
  public int anio;
  public boolean propia;
  public String poliza;
  public double cuantia_seguro;
  public String descr;
  public EstadosObra estado;
  public Dimensiones dim;
  public Exposicion exposicion;
  public Sala sala;

  /**
   * Constructor de la clase Obra con las dimensiones como parámetros
   *
   * @param nombre         de la obra
   * @param autor          de la obra
   * @param anio           de la obra
   * @param propia         de la obra
   * @param poliza         de la obra
   * @param cuantia_seguro de la obra
   * @param descr          de la obra
   * @param ancho          de la obra
   * @param largo          de la obra
   * @param alto           de la obra
   */
  public Obra(
      String nombre,
      String autor,
      int anio,
      boolean propia,
      String poliza,
      double cuantia_seguro,
      String descr,
      double ancho,
      double largo,
      double alto) {
    this.nombre = nombre;
    this.autor = autor;
    this.anio = anio;
    this.propia = propia;
    this.poliza = poliza;
    this.cuantia_seguro = cuantia_seguro;
    this.descr = descr;
    this.estado = EstadosObra.ALMACEN;
    dim = new Dimensiones(ancho, largo, alto);
  }

  /**
   * Constructor de la clase obra con las dimensiones como objeto
   * 
   * @param nombre         de la obra
   * @param autor          de la obra
   * @param anio           de la obra
   * @param propia         de la obra
   * @param poliza         de la obra
   * @param cuantia_seguro de la obra
   * @param descr          de la obra
   * @param dim            de la obra
   */
  public Obra(
      String nombre,
      String autor,
      int anio,
      boolean propia,
      String poliza,
      double cuantia_seguro,
      String descr,
      Dimensiones dim) {
    this.nombre = nombre;
    this.autor = autor;
    this.anio = anio;
    this.propia = propia;
    this.poliza = poliza;
    this.cuantia_seguro = cuantia_seguro;
    this.descr = descr;
    this.estado = EstadosObra.ALMACEN;
    this.dim = dim;
  }

  /**
   * Getter del atributo nombre
   *
   * @return nombre de la obra
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Setter del atributo nombre
   *
   * @param nombre nuevo nombre de la obra
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Getter del atributo autor
   *
   * @return autor de la obra
   */
  public String getAutor() {
    return autor;
  }

  /**
   * Setter del atributo autor
   *
   * @param autor autor de la obra
   */
  public void setAutor(String autor) {
    this.autor = autor;
  }

  /**
   * Getter del atributo anio
   *
   * @return año de realización
   */
  public int getAnio() {
    return anio;
  }

  /**
   * Setter del atributo anio
   *
   * @param anio año de realización
   */
  public void setAnio(int anio) {
    this.anio = anio;
  }

  /**
   * Getter del atributo propia
   *
   * @return true si es propia, false si no
   */
  public boolean isPropia() {
    return propia;
  }

  /**
   * Setter del atributo propia
   *
   * @param propia boolean
   */
  public void setPropia(boolean propia) {
    this.propia = propia;
  }

  /**
   * Getter de la poliza de seguro
   *
   * @return poliza de seguro
   */
  public String getPoliza() {
    return poliza;
  }

  /**
   * Setter del atributo poliza
   *
   * @param poliza poliza de seguro
   */
  public void setPoliza(String poliza) {
    this.poliza = poliza;
  }

  /**
   * Getter del atributo cuantía seguro
   * 
   * @return cuantia_seguro
   */
  public double getCuantiaSeguro() {
    return cuantia_seguro;
  }

  /**
   * Setter del atributo cuantía seguro
   * 
   * @param cuantiaSeguro cuantía seguro
   */
  public void setCuantiaSeguro(double cuantiaSeguro) {
    this.cuantia_seguro = cuantiaSeguro;
  }

  /**
   * Getter del atributo descripción
   * 
   * @return descr
   */
  public String getDescripcion() {
    return descr;
  }

  /**
   * Setter del atributo descrición
   * 
   * @param descripcion cuantía seguro
   */
  public void setDescripcion(String descripcion) {
    this.descr = descripcion;
  }

  /**
   * Getter del atributo dim
   * 
   * @return dim
   */
  public Dimensiones getDimensiones() {
    return dim;
  }

  /**
   * Setter del atributo dim
   * 
   * @param dim dimensiones
   */
  public void setDimensiones(Dimensiones dim) {
    this.dim = dim;
  }

  /**
   * confirma si la obra necesita climatizacion
   * 
   * @return true o false
   */
  public abstract boolean necesitaClimatizacion();

  /**
   * devuelve el tipo de obra que es
   * 
   * @return tipo
   */
  public abstract TipoDeObra getTipoObra();

  /**
   * metodo abstracto para decidir si una obra cabe en una sala
   * 
   * @param s sala a comprobar
   */
  public abstract boolean cabeEnSala(Sala s);

  /**
   * Setter del atributo sala
   * 
   * @param sala sala en la que se expone la obra
   */
  public void setSala(Sala sala) {
    this.sala = sala;
  }

  /**
   * getter del atributo temp
   * 
   * @return temp
   */
  public Temperatura getTemp() {
    return null;
  }

  /**
   * getter del atributo humedad
   * 
   * @return temp
   */
  public Humedad getHumedad() {
    return null;
  }

  /**
   * getter del atributo estado
   * 
   * @return estado
   */
  public EstadosObra getEstado() {
    return this.estado;
  }

  /**
   * getter del atributo exposicion
   * 
   * @return exposicion
   */
  public Exposicion getExposicion() {
    return this.exposicion;
  }

  /**
   * añadir una obra a una exposicion
   * 
   * @param exposicion a la que añadir la obra
   * @return true o false
   * @throws IllegalStateException para estados inválidos
   */
  public Status exponer(Exposicion exposicion, Sala s) throws IllegalStateException {
    if (!(estado == EstadosObra.ALMACEN &&
        exposicion.getEstado() == EstadoExposicion.EN_CREACION))
      throw new IllegalStateException("No se puede exponer una obra que no esté en creación ni exponer en una exposición que no esté en creación");

    if (exposicion.isTemporal())
      if (!propia)
        throw new IllegalStateException("No se puede exponer una obra que no sea propia en una exposición temporal");

    if (cabeEnSala(s)) {
      exposicion.addObras(this);
      this.exposicion = exposicion;
      sala = s;
      s.getObras().add(this);
      estado = EstadosObra.EXPOSICION;
    } else {
      throw new IllegalStateException("La obra a exponer no cabe en la sala");
    }

    return Status.OK;
  }

  /**
   * retirar una obra
   * 
   * @return ok si se pudo, error en caso contrario
   * @throws IllegalStateException para estados inválidos
   */
  public Status retirar() throws IllegalStateException {
    if (estado != EstadosObra.EXPOSICION && estado != EstadosObra.ALMACEN)
      throw new IllegalStateException("Solo se pueden retirar obras del almacén o de una exposición");

    if (estado == EstadosObra.EXPOSICION) {
      this.exposicion.removeObras(this);

      this.exposicion = null;
    }
    estado = EstadosObra.RETIRADA;

    return Status.OK;
  }

  /**
   * almacenar una obra
   * 
   * @return ok si se pudo, error en caso contrario
   * @throws IllegalStateException para estados inválidos
   */
  public Status almacenar() throws IllegalStateException {
    if (estado == EstadosObra.RETIRADA)
      throw new IllegalStateException("La obra no se puede almacenar porque está retirada");

    if (estado == EstadosObra.EXPOSICION) {
      this.exposicion.removeObras(this);

      this.exposicion = null;
    }

    estado = EstadosObra.ALMACEN;

    return Status.OK;
  }

  /**
   * restaurar una obra
   * 
   * @return ok si se pudo, error en caso contrario
   * @throws IllegalStateException para estados inválidos
   */
  public Status restaurar() throws IllegalStateException {
    if (estado != EstadosObra.ALMACEN)
      throw new IllegalStateException("La obra no se puede restaurar porque no está en el almacén");
    if (!propia)
      throw new IllegalStateException("La obra no se puede restaurar porque no es propia");

    estado = EstadosObra.ENRESTAURACION;

    return Status.OK;
  }

  /**
   * prestar una obra
   * 
   * @return ok si se pudo, error en caso contrario
   * @throws IllegalStateException para estados inválidos
   */
  public Status prestar() throws IllegalStateException {
    if (estado != EstadosObra.ALMACEN)
      throw new IllegalStateException("La obra no se puede prestar porque no está en el almacén");
    ;
    if (!propia)
      throw new IllegalStateException("La obra no se puede prestar porque no es propia");

    estado = EstadosObra.PRESTADA;

    return Status.OK;
  }

  /**
   * devuelve si la obra es audiovisual
   *
   * @return true si es verdad, false en caso contrario
   */
  public boolean isAudiovisual() {
    return false;
  }

  /**
   * devuelve la cuantia del seguro de la obra
   * 
   * @return precio del seguro
   */
  public double getCuantia_seguro() {
    return cuantia_seguro;
  }

  /**
   * getter de la descripcion de la obra
   * 
   * @return descripcion
   */
  public String getDescr() {
    return descr;
  }

  /**
   * getter de las dimensiones de la obra
   * 
   * @return dimensiones
   */
  public Dimensiones getDim() {
    return dim;
  }

  /**
   * checkea el estado de las obras y si debe cambiar
   * 
   */
  public void checkEstadoObra() {
    if (exposicion == null || sala == null) {
      return;
    }

    EstadoExposicion estado = exposicion.getEstado();

    if (estado == EstadoExposicion.CANCELADA || estado == EstadoExposicion.TERMINADA) {
      exposicion = null;
      this.estado = EstadosObra.ALMACEN;
      exposicion.removeObras(this);
      sala.getObras().remove(this);
    }

  }
}
