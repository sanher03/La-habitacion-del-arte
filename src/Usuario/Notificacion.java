package Usuario;

import java.io.Serializable;

/**
 * Clase para dar soporte a las notificaciones
 * 
 * @author Fabio Desio
 */
public class Notificacion implements Serializable{

  private Empleado emisor;
  private String cuerpo;
  private boolean leida;
  /**
   * Constructor de la clase notificacion
   * @param emisor empleado
   * @param cuerpo de la notificación
   */
  public Notificacion(Empleado emisor, String cuerpo) {
    this.emisor = emisor;
    this.cuerpo = cuerpo;
  }

  /**
   * Getter del atributo emisor
   * @return emisor
   */
  public Empleado getEmisor() {
    return emisor;
  }

  /**
   * Getter del atributo cuerpo
   * @return cuerpo
   */
  public String getCuerpo() {
    return cuerpo;
  }

  /**
   * Setter del atributo emisor
   * @param emisor
   */
  public void setEmisor(Empleado emisor) {
    this.emisor = emisor;
  }

  /**
   * Setter del atributo cuerpo
   * @param cuerpo
   */
  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }


  /**
   * Getter del atributo leida
   * 
   * @return True o False dependiendo de si la notificacion ha sido leida o no
   */
  public boolean isLeida() {
    return leida;
  }


  /**
   * Setter del atributo leida
   * 
   * @param leida booleano que decide si esta leida o no
   */
  public void setLeida(boolean leida) {
    this.leida = leida;
  }
 
}
