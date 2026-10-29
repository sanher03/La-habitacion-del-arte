package Usuario;

import java.io.Serializable;
import java.util.*;

/**
 * Clase para dar soporte a la clase de usuario
 *
 * @author Fabio Desio
 */
public abstract class Usuario implements Serializable {

  private String password;
  private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

  /**
   * Constructor para la clase de usuario
   * 
   * @param password del usuario en cuestión
   */
  public Usuario(String password) {
    this.password = password;
  }

  /**
   * Getter del atributo password
   *
   * @return contraseña del usuario
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter del atributo password
   *
   * @param password del usuario
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Getter para el atributo enviarNotificaciones
   * 
   * @return false
   */
  public boolean isEnviarNotificaciones() {
    return false;
  }

  /**
   * Getter para el atributo cambiarClimatizacion
   * 
   * @return false
   */
  public boolean isCambiarClimatizacion() {
    return false;
  }

  /**
   * Getter para el atributo venderEntradas
   * 
   * @return false
   */
  public boolean isVenderEntradas() {
    return false;
  }

  /**
   * Getter de atributo notificaciones
   * 
   * @return notificaciones
   */
  public List<Notificacion> getNotificaciones() {
    return notificaciones;
  }

  /**
   * Setter del atributo notificaciones
   * 
   * @param notificaciones notificaciones a añadir
   */
  public void addNotificaciones(Notificacion... notificaciones) {
    for (Notificacion n : notificaciones) {
      this.notificaciones.add(n);
    }
  }

  /**
   * Getter del identificador del usuario
   * 
   * @return identificador
   */
  public abstract String getIdentificacion();

  /**
   * Getter del tipo de usuario
   * 
   * @return tipo de usuario
   */
  public abstract TipoUsuario getTipoUsuario();

}
