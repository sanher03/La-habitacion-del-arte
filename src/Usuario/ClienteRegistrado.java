package Usuario;

import java.util.*;
import java.util.stream.Collectors;
import java.time.*;

import Entrada.*;
import Sistema.Sistema;

/**
 * Clase para dar soporte a cliente registrado
 *
 * @author Fabio Desio
 */
public class ClienteRegistrado extends Usuario {

  private String dni;
  private boolean recibirNotificaciones;
  private List<Comprada> entradas;
  private List<UUID> codigos;
  private LocalDate fechaPenalizacion;

  /**
   * Constructor de la clase cliente registrado
   * 
   * @param password              del cliente registrado
   * @param dni                   del cliente registrado
   * @param recibirNotificaciones si permite recibir o no notificaciones
   * @throws IllegalArgumentException ya existe un cliente registrado con ese dni
   */
  public ClienteRegistrado(
      String password,
      String dni,
      boolean recibirNotificaciones) throws IllegalArgumentException {
    super(password);
    Sistema sistema = Sistema.getInstance();
    for (ClienteRegistrado c : sistema.getClientes()) {
      if (c.getDni().equals(dni)) {
        throw new IllegalArgumentException("Ya existe un cliente registrado con ese dni.");
      }
    }
    this.dni = dni;
    this.recibirNotificaciones = recibirNotificaciones;
    this.entradas = new ArrayList<>();
    this.codigos = new ArrayList<>();
  }

  /**
   * Metodo para ver notificaciones (tendra sentido con la interfaz gráfica)
   * 
   * @return null
   */
  public List<Notificacion> verNotificaciones() {
    return null;
  }

  /* GETTERS Y SETTERS */
  /**
   * Getter de atributo dni
   * 
   * @return dni
   */
  public String getDni() {
    return dni;
  }

  /**
   * Getter de atributo recibirNotificaciones
   * 
   * @return recibirNotificaciones
   */
  public boolean isRecibirNotificaciones() {
    return recibirNotificaciones;
  }

  /**
   * Setter del atributo dni
   * 
   * @param dni del cliente
   */
  public void setDni(String dni) {
    this.dni = dni;
  }

  /**
   * Setter del atributo recibirNotificaciones
   * 
   * @param recibirNotificaciones boolean
   */
  public void setRecibirNotificaciones(boolean recibirNotificaciones) {
    this.recibirNotificaciones = recibirNotificaciones;
  }

  /**
   * Getter del atributo entradas
   * 
   * @return entradas compradas por el cliente
   */
  public List<Comprada> getEntradas() {
    return entradas;
  }

  /**
   * añadir entradas a un cliente
   * 
   * @param entradasCompradas entradas compradas
   */
  public void addEntradas(Comprada... entradasCompradas) {
    List<Comprada> entradasFilt = Arrays
        .stream(entradasCompradas)
        .filter(elemento -> elemento != null)
        .collect(Collectors.toList());

    this.entradas.addAll(entradasFilt);
  }

  /**
   * añadir codigos a un cliente
   * 
   * @param codigos codigos
   */
  public void addcodigos(UUID... codigos) {
    List<UUID> codigosFilt = Arrays
        .stream(codigos)
        .filter(elemento -> elemento != null)
        .collect(Collectors.toList());

    this.codigos.addAll(codigosFilt);
  }

  /**
   * Getter de los codigos de un cliente
   * 
   * @return codigos de sorteo de un cliente
   */
  public List<UUID> getCodigos() {
    return codigos;
  }

  /**
   * Setter del atributo fecha de penalización
   * 
   * @param fechaPenalizacion fecha hasta la que un cliente no se podrá inscribir en un sorteo
   */
  public void setFechaPenalizacion(LocalDate fechaPenalizacion) {
    this.fechaPenalizacion = fechaPenalizacion;
  }

  /**
   * Getter del atributo fecha de penalizacion
   * 
   * @return fecha de penalizacion
   */ 
  public LocalDate getFechaPenalizacion() {
    return fechaPenalizacion;
  }

  /**
   * Getter del identificador del usuario
   * 
   * @return identificador
   */
  public String getIdentificacion() {
    return dni;
  }

  /**
   * Getter del tipo de usuario
   * 
   * @return tipo de usuario
   */
  public TipoUsuario getTipoUsuario(){
    return TipoUsuario.CLIENTE;
  }
  
}
