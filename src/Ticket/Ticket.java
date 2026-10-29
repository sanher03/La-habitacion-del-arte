package Ticket;

import Entrada.*;
import Exposicion.*;
import es.uam.eps.padsof.tickets.*;
import java.io.File;

/**
 * La clase Ticket representa un ticket de entrada a una exposición.
 * Esta implementación es solo un ejemplo.
 * 
 * @author Ignacio Sánchez
 */
public class Ticket implements ITicketInfo { // Implementa la interfaz ITicketInfo

  private Entrada entrada; // La entrada asociada al ticket
  private double descuento; // El descuento aplicado al precio del ticket
  private Exposicion expo; // La exposición a la que se asocia el ticket
  private int id; // El identificador único del ticket
  private double precioOriginal;

  /**
   * Constructor de la clase Ticket.
   * @param entrada La entrada asociada al ticket
   * @param descuento El descuento aplicado al precio del ticket
   * @param expo La exposición a la que se asocia el ticket
   * @param id El identificador único del ticket
   */
  public Ticket(Entrada entrada, double descuento, Exposicion expo, int id) {
    this.entrada = entrada;
    this.descuento = descuento;
    this.expo = expo;
    this.id = id;
    this.precioOriginal = entrada.getPrecioCompra();
  }

  /**
   * Obtiene el identificador único del ticket.
   * @return El identificador único del ticket
   */
  public int getIdTicket() {
    return id;
  }

  // Los siguientes métodos son opcionales

  /**
   * Obtiene el nombre del centro de exposiciones.
   * @return El nombre del centro de exposiciones
   */
  public String getExhibitionCenterName() {
    return "Centro de exposiciones";
  }

  /**
   * Obtiene el nombre de la exposición asociada al ticket.
   * @return El nombre de la exposición
   */
  public String getExhibitionName() {
    return expo.getNombre();
  }

  /**
   * Obtiene el número de tickets asociados.
   * @return El número de tickets
   */
  public int getNumberOfTickets() {
    return entrada.getNumEntradas();
  }

  /**
   * Obtiene la fecha y hora del ticket.
   * @return La fecha y hora del ticket
   */
  public String getTicketDateTime() {
    String dateTime = entrada.getFechaVisita().toString();
    dateTime +=
      " " +
      String.format("%02d", entrada.getHora().getHour()) +
      ":" +
      String.format("%02d", entrada.getHora().getMinute()) +
      ":" +
      String.format("%02d", entrada.getHora().getSecond());
    return dateTime;
  }

  /**
   * Obtiene el precio del ticket.
   * @return El precio del ticket
   */
  public double getPrice() {
    return precioOriginal;
  }

  /**
   * Obtiene el descuento aplicado al precio del ticket.
   * @return El descuento aplicado
   */
  public double getDiscount() {
    return precioOriginal * descuento;
  }

  /**
   * Obtiene el precio pagado después del descuento.
   * @return El precio pagado
   */
  public double getPayedPrice() {
    return precioOriginal - precioOriginal * descuento;
  }

  /**
   * Obtiene la ruta de la imagen asociada al ticket.
   * @return La ruta de la imagen
   */
  public String getPicture() {
    return (
      "." + File.separator + "resources" + File.separator + "centrocentro.jpg"
    );
  }
}
