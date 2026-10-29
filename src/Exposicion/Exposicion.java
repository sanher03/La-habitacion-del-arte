package Exposicion;

import Entrada.*;
import Exposicion.Descuento.*;
import Obra.*;
import Sala.*;
import Sistema.*;
import Sorteo.*;
import Ticket.Ticket;
import Usuario.ClienteRegistrado;
import Utils.*;
import es.uam.eps.padsof.telecard.*;
import es.uam.eps.padsof.tickets.*;
import java.io.File;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

/**
 * Esta clase provee de soporte para la organización de exposiones
 *
 * @author Fabio Desio
 */
public abstract class Exposicion implements Serializable {

  private String nombre, autor, descr;
  private double precio;
  private EstadoExposicion estado;
  private EstadoExposicion estadoSiguiente;
  private ArrayList<Descuento> descuentos;
  private Sorteo sorteo;
  private ArrayList<Entrada> entradas;
  private List<Sala> salas;
  private List<Obra> obras;
  private ImageIcon imagen;

  /**
   * Constructor para la clase abstracta Exposición
   *
   * @param nombre de la exposición
   * @param autor  de la exposición
   * @param descr  de la exposición
   * @param estado de la exposición
   * @param precio de la exposición
   * @param sorteo que se puede realizar en la exposición
   * @throws IllegalArgumentException para argumentos inválidos
   * @throws NullPointerException     para argumentos nulos
   */
  public Exposicion(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado) throws IllegalArgumentException, NullPointerException {
    if (nombre == null)
      throw new NullPointerException(
          "Nombre inválido, no puede estar vacío.");
    this.nombre = nombre;
    if (autor == null)
      throw new NullPointerException(
          "Autor inválido, no puede estar vacío.");
    this.autor = autor;
    if (descr == null)
      throw new NullPointerException(
          "Descripción inválida, no puede estar vacía.");
    this.descr = descr;
    if (precio < 0)
      throw new IllegalArgumentException(
          "Precio inválido, no puede ser menor que cero.");
    this.precio = precio;
    if (estado == null)
      throw new NullPointerException(
          "Descripción inválida, no puede estar vacía.");
    this.estado = estado;
    this.sorteo = null;
    this.descuentos = new ArrayList<Descuento>();
    this.descuentos.add(null);
    this.descuentos.add(null);
    this.entradas = new ArrayList<Entrada>();
    this.obras = new ArrayList<Obra>();
    this.salas = new ArrayList<Sala>();
  }

    /**
   * Constructor para la clase abstracta Exposición
   *
   * @param nombre de la exposición
   * @param autor  de la exposición
   * @param descr  de la exposición
   * @param estado de la exposición
   * @param precio de la exposición
   * @param sorteo que se puede realizar en la exposición
   * @param rutaImagen string con la ruta a la imagen 
   * @throws IllegalArgumentException para argumentos inválidos
   * @throws NullPointerException     para argumentos nulos
   */
  public Exposicion(
      String nombre,
      String autor,
      String descr,
      double precio,
      EstadoExposicion estado,
      String rutaImagen) throws IllegalArgumentException, NullPointerException {
    this(nombre, autor, descr, precio, estado);
    imagen = new ImageIcon(rutaImagen);
  }
  /**
   * getter del atributo salas
   *
   * @return salas de la exposicion
   */
  public List<Sala> getSalas() {
    return salas;
  }

  /**
   * getter del atributo precio
   *
   * @return precio entradas expo
   */
  public double getPrecio() {
    return precio;
  }

  /**
   * setter del atributo precio
   *
   * @param precio entradas expo
   * @throws IllegalArgumentException argumento inválido
   */
  public void setPrecio(double precio) throws IllegalArgumentException {
    if (precio < 0)
      throw new IllegalArgumentException(
          "Precio inválido, no puede ser menor que cero.");
    this.precio = precio;
  }

  /**
   * getter del atributo obras
   *
   * @return obras de la exposicion
   */
  public List<Obra> getObras() {
    return obras;
  }

  /**
   * comprueba si la exposicion tiene obras de un tipo especifico
   *
   * @return true o false
   */
  public boolean tieneObraDeTipo(TipoDeObra tipo) {
    for (Obra obra : obras) {
      if (obra.getTipoObra() == tipo)
        return true;
    }

    return false;
  }

  /**
   * getter de la fecha de inicio de la exposicion
   *
   * @return null
   */
  public LocalDate getfInicio() {
    return null;
  }

  /**
   * getter de la fecha de fin de la exposicion
   *
   * @return null
   */
  public LocalDate getfFinal() {
    return null;
  }

  /**
   * Función privada para checkear si el array de descuentos ya está lleno
   *
   * @return true en caso afirmativo, false en el otro caso
   */
  private boolean descuentosLlenos() {
    return this.descuentos.get(0) != null && this.descuentos.get(1) != null;
  }

  /**
   * Función para añadir un descuento al array de descuentos
   *
   * @param desc descuento a añadir
   * @return OK, ERROR según si se ha realizado la inserción
   * @throws IllegalStateException estado incorrecto del array de descuentos
   */
  public void addDescuento(Descuento desc) {
    int index = desc.isPorCompra() ? 0 : 1;

    /* Checkeamos que no esté lleno el array de descuentos */
    if (descuentosLlenos())
      throw new IllegalStateException(
          "Número máximo de descuentos alcanzado");

    /* Checkeamos que no haya descuentos del tipo especificado en el array */
    if (this.descuentos.get(index) != null)
      throw new IllegalStateException(
          "El descuento de tipo " +
              (desc.isPorCompra() ? "por compra " : "por antelación ") +
              "ya ha sido añadido.");

    this.descuentos.set(index, desc);
  }

  /**
   * Función para quitar un descuento del array de descuentos
   *
   * @param porCompra booleano que nos indica si queremos quitar el
   *                  descuento por compra o por antelación.
   * @return OK, ERROR según si se ha quitado el descuento del array o no
   */
  public void quitarDescuento(boolean porCompra) {
    int index = porCompra ? 0 : 1;

    /* Setteamos en el array de descuentos el descuento correspondiente a null */
    this.descuentos.set(index, null);
  }

  /**
   * Función para calcular el número de entradas totales que se han vendido
   * para el periodo entre dos fechas en una exposición.
   *
   * @param fInicio fecha de inicio
   * @param fFin    fecha de fin
   * @return número total de entradas según condiciones
   */
  public int getNumEntradasTotal(LocalDate fInicio, LocalDate fFin) {
    int numEntradasTotal = 0;

    /* Iteramos por el array de entradas */
    for (Entrada entrada : this.entradas) {
      /* Comprobamos las condiciones */
      LocalDate fecha = entrada.getFechaCompra();
      if (fecha.isBefore(fInicio) || fecha.isAfter(fFin))
        continue;

      numEntradasTotal += entrada.getNumEntradas();
    }

    return numEntradasTotal;
  }

  /*
   * TODO: TENER EN CUENTA POSIBLES DESCUENTOS (el precio pagado ya tiene en
   * cuenta descuentos)
   */
  public double getDineroRecaudado(LocalDate fInicio, LocalDate fFin) {
    double dinero = 0;

    /* Iteramos por el array de entradas */
    for (Entrada entrada : this.entradas) {
      /* Comprobamos las condiciones */
      LocalDate fecha = entrada.getFechaCompra();
      if (fecha.isBefore(fInicio) || fecha.isAfter(fFin))
        continue;
      dinero += entrada.getPrecioCompra();
    }

    return dinero;
  }

  /**
   * Calcula las entradas totales vendidas en la exposicion
   * 
   * @return numero total de entradas
   */
  public int getNumEntradasTotales() {
    int numEntradasTotal = 0;

    /* Iteramos por el array de entradas */
    for (Entrada entrada : this.entradas) {

      numEntradasTotal += entrada.getNumEntradas();
    }

    return numEntradasTotal;
  }

  /**
   * Calcula el dinero recaudado en la exposicion
   * 
   * @return dinero total reacaudado
   */
  public double getDineroRecaudadoTotal() {
    double dinero = 0;

    /* Iteramos por el array de entradas */
    for (Entrada entrada : this.entradas) {

      dinero += entrada.getPrecioCompra();
    }

    return dinero;
  }

  /**
   * Devuelve el precio con el descuento aplicado
   * 
   * @param cliente    cliente que realiza la compra
   * @param fecha      fecha de compra
   * @param numEntrada numero de entradas
   * @return precio a pagar
   */
  public double precioAPagar(ClienteRegistrado cliente, LocalDate fecha, int numEntrada) {
    double desc = 0;

    Descuento descuentoCompra = descuentos.get(0);
    if (descuentoCompra != null && descuentoCompra.checkDescuentoPorCompra(cliente)) {
      desc += descuentoCompra.getDescuento();
    }

    Descuento descuentoAntelacion = descuentos.get(1);
    if (descuentoAntelacion != null
        && descuentoAntelacion.checkDescuentoPorAntelacion(fecha)) {
      desc += descuentoAntelacion.getDescuento();
    }

    return this.precio * numEntrada * (1 - Math.min(desc, 1.0));
  }

  /**
   * Comprar entrada con codigo de sorteo
   *
   * @param cliente    cliente que compra
   * @param numEntrada numero de entradas que necesita
   * @param fecha      fecha de visita
   * @param hora       hora de visita
   * @param numTarjeta numero de tarjeta
   * @param codigo     codigo de sorteo
   * @return OK si la compra es valida, ERROR en caso contrario
   * @throws IllegalStateException         para estados inválidos
   * @throws NonExistentFileException      si no existe el file
   * @throws UnsupportedImageTypeException si no soporta la imagen
   */
  public Status comprarEntrada(
      ClienteRegistrado cliente,
      int numEntrada,
      LocalDate fecha,
      LocalTime hora,
      String numTarjeta,
      String codigo) throws IllegalStateException, NonExistentFileException, UnsupportedImageTypeException {
    validarDatosCompra(cliente, numEntrada, fecha, hora);
    UUID code = UUID.fromString(codigo);

    if (estado != EstadoExposicion.COMENZADA &&
        estado != EstadoExposicion.DISPONIBLE)
      throw new IllegalStateException("La exposición no está ni comenzada ni disponible");

    LocalDate fechaFinExp = this.getfFinal();

    if (isPermanente() && fechaFinExp != null) {
      // La expo esta interrumpida
      if (estadoSiguiente == EstadoExposicion.INTERRUMPIDA) {
        if (!fecha.isBefore(this.getfInicio()) && !fecha.isAfter(fechaFinExp))
          throw new IllegalStateException("La exposición se encuentra interrumpida");
      } else if (estadoSiguiente == EstadoExposicion.CANCELADA) {
        if (fecha.isAfter(fechaFinExp))
          throw new IllegalStateException("La exposición se encuentra cancelada");
      }
    } else if (isTemporal() &&
        (fecha.isBefore(getfInicio()) || fecha.isAfter(fechaFinExp))) {
      throw new IllegalStateException("La fecha seleccionada está fuera del periodo de la exposición temporal");
    }

    int aforo = 0;

    for (Sala sala : this.salas) {
      aforo += sala.getAforo();
    }

    int aforoOcupado = getEntradasPorHora(fecha, hora);

    if (aforo - aforoOcupado < numEntrada)
      throw new IllegalStateException("No queda aforo suficiente");

    double precio = this.precio * numEntrada;

    Comprada entrada = new Comprada(
        numEntrada,
        precio,
        LocalDate.now(),
        fecha,
        hora,
        numTarjeta,
        cliente);

    boolean valid = false;
    if (sorteo != null) {
      valid = sorteo.validarEntrada(entrada, code);
    }

    if (!valid)
      throw new IllegalStateException("El código no ha podido ser validado");
    /* Con sorteo la entrada es gratuita, no hace falta pagar */

    /* Generar ticket */
    TicketSystem.createTicket(
        new Ticket(entrada, 1, this, Sistema.getInstance().getIdEntradas()),
        "." + File.separator + "tmp" // Output folder
    );

    entrada.setPrecioCompra(0.0);

    sorteo.getCodigos().remove(code);

    this.addEntradas(entrada);
    cliente.getEntradas().add(entrada);

    Sistema sist = Sistema.getInstance();

    sist.getEntradas().add(entrada);

    return Status.OK;
  }

  /**
   * Comprar entrada
   *
   * @param cliente    cliente que compra
   * @param numEntrada numero de entradas que necesita
   * @param fecha      fecha de visita
   * @param hora       hora de visita
   * @param numTarjeta numero de tarjeta
   * @return OK si la compra es valida, ERROR en caso contrario
   * @throws IllegalStateException         para estados inválidos
   * @throws NonExistentFileException      si no existe el file
   * @throws UnsupportedImageTypeException si no soporta la imagen
   * @throws InvalidCardNumberException    si la tarjeta no es válida
   * @throws OrderRejectedException        si la compra ha sido denegada
   * 
   */
  public Status comprarEntrada(
      ClienteRegistrado cliente,
      int numEntrada,
      LocalDate fecha,
      LocalTime hora,
      String numTarjeta) throws IllegalStateException, NonExistentFileException, UnsupportedImageTypeException,
      InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException {
    validarDatosCompra(cliente, numEntrada, fecha, hora);
    if (estado != EstadoExposicion.COMENZADA &&
        estado != EstadoExposicion.DISPONIBLE)
      throw new IllegalStateException("La exposición no está ni comenzada ni disponible");

    LocalDate fechaFinExp = this.getfFinal();

    if (isPermanente() && fechaFinExp != null) {
      // La expo esta interrumpida
      if (estadoSiguiente == EstadoExposicion.INTERRUMPIDA) {
        if (!fecha.isBefore(this.getfInicio()) && !fecha.isAfter(fechaFinExp))
          throw new IllegalStateException("La exposición se encuentra interrumpida");
      } else if (estadoSiguiente == EstadoExposicion.CANCELADA) {
        if (fecha.isAfter(fechaFinExp))
          throw new IllegalStateException("La exposición se encuentra cancelada");
      }
    } else if (isTemporal() &&
        (fecha.isBefore(getfInicio()) || fecha.isAfter(fechaFinExp))) {
      throw new IllegalStateException("La fecha seleccionada está fuera del periodo de la exposición temporal");
    }

    int aforo = 0;

    for (Sala sala : this.salas) {
      aforo += sala.getAforo();
    }

    int aforoOcupado = getEntradasPorHora(fecha, hora);

    if (aforo - aforoOcupado < numEntrada)
      throw new IllegalStateException("No queda aforo suficiente");

    double desc = 0.0;

    Descuento descuentoCompra = descuentos.get(0);
    if (descuentoCompra != null && descuentoCompra.checkDescuentoPorCompra(cliente)) {
      desc += descuentoCompra.getDescuento();
    }

    Descuento descuentoAntelacion = descuentos.get(1);
    if (descuentoAntelacion != null
        && descuentoAntelacion.checkDescuentoPorAntelacion(fecha)) {
      desc += descuentoAntelacion.getDescuento();
    }
    desc = Math.min(desc, 1.0);

    double precio = this.precio * numEntrada;
    double precioFinal = precio * (1 - desc);

    Comprada entrada = new Comprada(
        numEntrada,
        precio,
        LocalDate.now(),
        fecha,
        hora,
        numTarjeta,
        cliente);

    /* Pagar entrada */
    if (!TeleChargeAndPaySystem.isValidCardNumber(numTarjeta))
      throw new IllegalArgumentException("La tarjeta no es válida");
    TeleChargeAndPaySystem.charge(numTarjeta, "Entrada", precioFinal, true);
    /* Generar ticket */
    TicketSystem.createTicket(
        new Ticket(entrada, desc, this, Sistema.getInstance().getIdEntradas()),
        "." + File.separator + "tmp" // Output folder
    );

    entrada.setPrecioCompra(precioFinal);

    this.addEntradas(entrada);
    cliente.getEntradas().add(entrada);

    Sistema sist = Sistema.getInstance();

    sist.getEntradas().add(entrada);

    return Status.OK;
  }

  /**
   * Venta de entradas para el mismo dia
   *
   * @param numEntrada numero de entradas a comprar
   * @param hora       hora de visita
   * @param numTarjeta numero de tarjeta
   * @return OK si la compra es valida, ERROR en caso contrario
   * @throws IllegalStateException         para estados inválidos
   * @throws NonExistentFileException      si no existe el file
   * @throws UnsupportedImageTypeException si no soporta la imagen
   * @throws InvalidCardNumberException    si la tarjeta no es válida
   * @throws OrderRejectedException        si la compra ha sido denegada
   */
  public Status venderEntrada(
      int numEntrada,
      LocalTime hora,
      String numTarjeta) throws IllegalStateException, NonExistentFileException, UnsupportedImageTypeException,
      InvalidCardNumberException, OrderRejectedException, FailedInternetConnectionException {
    if (numEntrada <= 0 || hora == null)
      throw new IllegalArgumentException("El número de entradas y la hora deben ser válidos");
    if (estado != EstadoExposicion.COMENZADA &&
        estado != EstadoExposicion.DISPONIBLE)
      throw new IllegalStateException("La exposición no está ni comenzada ni disponible");

    Sistema sistema = Sistema.getInstance();

    if (sistema.getUsuarioActivo() == null || !sistema.getUsuarioActivo().isVenderEntradas()) {
      throw new IllegalStateException("El empleado no tiene permiso para vender entradas.");
    }

    int aforo = 0;
    for (Sala sala : this.salas) {
      aforo += sala.getAforo();
    }

    int aforoOcupado = getEntradasPorHora(LocalDate.now(), hora);

    if (aforo - aforoOcupado < numEntrada)
      throw new IllegalStateException("No queda aforo suficiente");

    LocalDate fecha = LocalDate.now();
    double precio = this.precio * numEntrada;

    Entrada entrada = new Entrada(numEntrada, precio, fecha, fecha, hora);

    /* Pagar entrada */
    if (!TeleChargeAndPaySystem.isValidCardNumber(numTarjeta))
      throw new IllegalArgumentException("La tarjeta no es válida");
    TeleChargeAndPaySystem.charge(numTarjeta, "Entrada", precio, true);

    this.addEntradas(entrada);

    Sistema sist = Sistema.getInstance();

    sist.getEntradas().add(entrada);

    /* Generar ticket */
    TicketSystem.createTicket(
        new Ticket(entrada, 0.0, this, Sistema.getInstance().getIdEntradas()),
        "." + File.separator + "tmp" // Output folder
    );

    return Status.OK;
  }

  /**
   * Función para calcular el número de entradas que se han vendido para una fecha
   * y hora en específico.
   *
   * @param fechaExp fecha a calcular
   * @param horaExp  hora a calcular
   * @return número de entradas totales según condiciones
   */
  public int getEntradasPorHora(LocalDate fechaExp, LocalTime horaExp) {
    int numEntradasTotal = 0;

    /* Iteramos por el array de entradas */
    for (Entrada entrada : this.entradas) {
      LocalDate fecha = entrada.getFechaVisita();
      LocalTime hora = entrada.getHora();

      /* Comprobamos las condiciones */
      if (!fecha.isEqual(fechaExp) || !hora.equals(horaExp))
        continue;

      numEntradasTotal += entrada.getNumEntradas();
    }

    return numEntradasTotal;
  }

  /**
   * Método para cambiar el estado de la exposición de EN_CREACIÓN a DISPONIBLE
   *
   * @return OK, ERROR según si se ha cambiado el estado satisfactoriamente
   * @throws IllegalStateException estados inválidos
   */
  public Status publicar() throws IllegalStateException {
    if (this.estado != EstadoExposicion.EN_CREACION)
      throw new IllegalStateException("La exposición no puede ser publicada porque no se encuentra en creación");

    if (precio == 0.0)
      throw new IllegalStateException("El precio de la exposición es 0€");

    /* Setteamos el estado de la exposición a DISPONIBLE */
    this.setEstado(EstadoExposicion.DISPONIBLE);

    return Status.OK;
  }

  /**
   * checkea el estado de las exposiciones y si debe cambiar
   * 
   * @return true o false
   */
  public Status checkEstadoExposicion() {
    LocalDate fecha1 = this.getfInicio();
    LocalDate fecha2 = this.getfFinal();

    if (fecha1 == null && fecha2 == null)
      return Status.ERROR;

    if (fecha1 != null) {
      if (fecha1.isEqual(LocalDate.now())) {
        if (this.isPermanente()) {
          estado = EstadoExposicion.INTERRUMPIDA;
        } else {
          estado = EstadoExposicion.COMENZADA;
        }
      }
    }

    if (fecha2 != null) {
      if (fecha2.isEqual(LocalDate.now())) {
        estado = estadoSiguiente;
      }
    }

    for (Obra o : obras) {
      o.checkEstadoObra();
    }

    for (Sala s : salas) {
      s.checkEstadoSala();
    }

    return Status.OK;
  }

  /* -------------------- MÉTODOS ABSTRACTOS ---------------------- */
  /**
   * Método abstracto para saber si el tipo de una exposición es permanente
   *
   * @return true, false según si es permanente o no
   */
  public abstract boolean isPermanente();

  /**
   * Método abstracto para saber si el tipo de una exposición es temporal
   *
   * @return true, false según si es temporal o no
   */
  public abstract boolean isTemporal();

  /**
   * Método abstracto para cancelar una exposición según condicinoes
   * 
   * @param fecha fecha de inicio de la cancelación
   *
   * @return OK, ERROR según si se ha cancelado satisfactoriamente o no
   */
  public abstract Status cancelar(LocalDate fecha);

  /**
   * borra una exposición si no esta publicada
   * 
   * @throws IllegalStateException
   */
  public void borrar() throws IllegalStateException {
    if (estado == EstadoExposicion.EN_CREACION) {
      Sistema sistema = Sistema.getInstance();

      sistema.getExposiciones().remove(this);
    } else {
      throw new IllegalStateException("La exposición esta publicada.");
    }
  }

  /* -------------------- GETTERS Y SETTERS ----------------------- */
  /**
   * Getter del atributo nombre
   *
   * @return String con el nombre de la exposición
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Getter del atributo autor
   *
   * @return String con el autor de la exposición
   */
  public String getAutor() {
    return autor;
  }

  /**
   * Getter del atributo descripción
   *
   * @return String con la descripción de la exposición
   */
  public String getDescr() {
    return descr;
  }

  /**
   * Getter del atributo estado
   *
   * @return Estadosxposicion con el estado de la exposición
   */
  public EstadoExposicion getEstado() {
    return estado;
  }

  /**
   * Getter del atributo descuentos
   *
   * @return descuentos de la exposición
   */
  public ArrayList<Descuento> getDescuentos() {
    return descuentos;
  }

  /**
   * Getter del atributo sorteo
   *
   * @return sorteo
   */
  public Sorteo getSorteo() {
    return sorteo;
  }

  /**
   * Getter del atributo entradas
   *
   * @return entradas
   */
  public ArrayList<Entrada> getEntradas() {
    return entradas;
  }

  /**
   * Getter del atributo imagen
   * 
   * @return imagen
   */
  public ImageIcon getImagen() {
    return imagen;
  }

  /**
   * Setter del atributo imagen
   * 
   * @param imagen a settear
   */
  public void setImagen(ImageIcon imagen) {
    this.imagen = imagen;
  }

  

  /**
   * Setter del atributo nombre
   *
   * @param nombre de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setNombre(String nombre) throws NullPointerException {
    if (nombre == null)
      throw new NullPointerException(
          "Nombre inválido, no puede estar vacío");
    this.nombre = nombre;
  }

  /**
   * Setter del atributo autor
   *
   * @param autor de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setAutor(String autor) throws NullPointerException {
    if (autor == null)
      throw new NullPointerException(
          "Autor inválido, no puede estar vacío");
    this.autor = autor;
  }

  /**
   * Setter del atributo estadoSiguiente
   *
   * @param estadoSiguiente
   * @throws NullPointerException argumento nulo
   */
  public void setEstadoSiguiente(EstadoExposicion estadoSiguiente)
      throws NullPointerException {
    if (estadoSiguiente == null)
      throw new NullPointerException(
          "EstadoSiguiente inválido, no puede estar vacío");
    this.estadoSiguiente = estadoSiguiente;
  }

  /**
   * Setter del atributo descripción
   *
   * @param descr de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setDescr(String descr) throws NullPointerException {
    if (descr == null)
      throw new NullPointerException(
          "Descripción inválida, no puede estar vacía");
    this.descr = descr;
  }

  /**
   * Setter del atributo estado
   *
   * @param estado de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setEstado(EstadoExposicion estado) throws NullPointerException {
    if (estado == null)
      throw new NullPointerException(
          "Estado inválido, no puede estar vacío");
    this.estado = estado;
  }

  /**
   * Setter del atributo descuentos
   *
   * @param descuentos de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setDescuentos(ArrayList<Descuento> descuentos)
      throws NullPointerException {
    if (descuentos == null)
      throw new NullPointerException(
          "Argumento inválido, descuentos no puede estar vacío");
    this.descuentos = descuentos;
  }

  /**
   * Setter del atributo sorteo
   *
   * @param sorteo de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setSorteo(Sorteo sorteo) throws NullPointerException {
    if (sorteo == null)
      throw new NullPointerException(
          "Sorteo inválido, no puede estar vacío");
    this.sorteo = sorteo;
    sorteo.setExposicion(this);
    Sistema.getInstance().addSorteos(sorteo);
  }

  /**
   * Setter del atributo entradas
   *
   * @param entradas de la exposición
   * @throws NullPointerException argumento nulo
   */
  public void setEntradas(ArrayList<Entrada> entradas)
      throws NullPointerException {
    if (entradas == null)
      throw new NullPointerException(
          "Argumento inválido, entradas no puede estar vacío");
    this.entradas = entradas;
  }

  /*
   * --------------- MÉTODOS PARA AÑADIR O QUITAR ELEMENTOS DE ARRAYLISTS
   * ------------------
   */
  /**
   * Método para añadir entradas al array de entradas
   *
   * @param entradas a añadir
   */
  public void addEntradas(Entrada... entradas) {
    List<Entrada> entradasFilt = Arrays
        .stream(entradas)
        .filter(elemento -> elemento != null)
        .collect(Collectors.toList());

    this.entradas.addAll(entradasFilt);
  }

  private void validarDatosCompra(
      ClienteRegistrado cliente,
      int numEntrada,
      LocalDate fecha,
      LocalTime hora) {
    if (cliente == null)
      throw new IllegalArgumentException("El cliente es obligatorio");
    if (numEntrada <= 0)
      throw new IllegalArgumentException("El número de entradas debe ser mayor que cero");
    if (fecha == null || hora == null)
      throw new IllegalArgumentException("La fecha y hora de visita son obligatorias");
  }

  /**
   * añade salas en las que se realiza la exposicion
   *
   * @param salas a añadir
   */
  public void addSalas(Sala... salas) {
    List<Sala> salasFilt = Arrays
        .stream(salas)
        .filter(elemento -> elemento != null)
        .collect(Collectors.toList());

    for (Sala sala : salasFilt) {
      sala.setExpo(this);
    }

    this.salas.addAll(salasFilt);
  }

  /**
   * añade obras a la exposicion
   *
   * @param obras a añadir
   */
  public void addObras(Obra... obras) {
    List<Obra> obrasFilt = Arrays
        .stream(obras)
        .filter(elemento -> elemento != null)
        .collect(Collectors.toList());

    this.obras.addAll(obrasFilt);
  }

  /**
   * quita obras de la exposicion
   *
   * @param obras a quitar
   */
  public void removeObras(Obra... obras) {
    this.obras.removeAll(Arrays.asList(obras));
  }

}
