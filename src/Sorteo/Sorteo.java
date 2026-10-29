package Sorteo;

import Entrada.Comprada;
import Exposicion.Exposicion;
import Sistema.Sistema;
import Usuario.*;
import Utils.*;
import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 * Clase abstracta que representa un sorteo.
 * Implementa la interfaz Serializable para permitir la serialización de
 * objetos.
 * 
 * @author Ignacio Sánchez
 */
public abstract class Sorteo implements Serializable {

  private int nEntradas; // Número de entradas para el sorteo
  private LocalDate fInicioInscripcion; // Fecha de inicio de la inscripción
  private LocalDate fFinInscripcion; // Fecha de fin de la inscripción
  private List<Participante> participantes; // Lista de participantes en el sorteo
  private EstadosSorteo estado; // Estado actual del sorteo
  private List<UUID> codigos; // Códigos generados para el sorteo
  private Exposicion exposicion;

  /**
   * Constructor de la clase Sorteo.
   * 
   * @param nEntradas          Número de entradas disponibles para el sorteo.
   * @param fInicioInscripcion Fecha de inicio de la inscripción.
   * @param fFinInscripcion    Fecha de fin de la inscripción.
   */
  public Sorteo(
      int nEntradas,
      LocalDate fInicioInscripcion,
      LocalDate fFinInscripcion) {
    this.nEntradas = nEntradas;
    this.fInicioInscripcion = fInicioInscripcion;
    this.fFinInscripcion = fFinInscripcion;
    estado = EstadosSorteo.ACTIVO;
    participantes = new ArrayList<Participante>();
    codigos = new ArrayList<UUID>();
  }

  /**
   * Obtiene el número de entradas disponibles para el sorteo.
   * 
   * @return Número de entradas disponibles.
   */
  public int getnEntradas() {
    return nEntradas;
  }

  /**
   * Establece el número de entradas disponibles para el sorteo.
   * 
   * @param nEntradas Número de entradas a establecer.
   */
  public void setnEntradas(int nEntradas) {
    this.nEntradas = nEntradas;
  }

  /**
   * Establece la lista de participantes en el sorteo.
   * 
   * @param participantes Lista de participantes a establecer.
   */
  public void setParticipantes(List<Participante> participantes) {
    this.participantes = participantes;
  }

  /**
   * Obtiene el estado actual del sorteo.
   * 
   * @return Estado actual del sorteo.
   */
  public EstadosSorteo getEstado() {
    return estado;
  }

  /**
   * Establece el estado actual del sorteo.
   * 
   * @param estado Estado a establecer.
   */
  public void setEstado(EstadosSorteo estado) {
    this.estado = estado;
  }

  /**
   * Obtiene la fecha de inicio de la inscripción al sorteo.
   * 
   * @return Fecha de inicio de la inscripción.
   */
  public LocalDate getfInicioInscripcion() {
    return fInicioInscripcion;
  }

  /**
   * Establece la fecha de inicio de la inscripción al sorteo.
   * 
   * @param fInicioInscripcion Fecha de inicio de la inscripción a establecer.
   */
  public void setfInicioInscripcion(LocalDate fInicioInscripcion) {
    this.fInicioInscripcion = fInicioInscripcion;
  }

  /**
   * Obtiene la fecha de fin de la inscripción al sorteo.
   * 
   * @return Fecha de fin de la inscripción.
   */
  public LocalDate getfFinInscripcion() {
    return fFinInscripcion;
  }

  /**
   * Establece la fecha de fin de la inscripción al sorteo.
   * 
   * @param fFinInscripcion Fecha de fin de la inscripción a establecer.
   */
  public void setfFinInscripcion(LocalDate fFinInscripcion) {
    this.fFinInscripcion = fFinInscripcion;
  }

  /**
   * Obtiene la lista de participantes en el sorteo.
   * 
   * @return Lista de participantes.
   */
  public List<Participante> getParticipantes() {
    return participantes;
  }

  /**
   * Setter del atributo exposicion
   * 
   * @param exposicion exposicion asociada al sorteo
   */
  public void setExposicion(Exposicion exposicion) {
    this.exposicion = exposicion;
  }

  /**
   * Comprueba si un cliente ya está inscrito en un sorteo
   * @param cliente cliente a comprobar
   * @return true si ya se inscribió, false en caso contrario
   */
  public boolean clienteYaInscrito(ClienteRegistrado cliente) {
    for (Participante p : this.participantes) {
      if (p.getCliente().equals(cliente)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Inscribe un participante en el sorteo.
   * 
   * @param participante Participante a inscribir.
   * @return Estado de la operación de inscripción (OK si fue exitosa, ERROR si no
   *         fue exitosa).
   */
  public Status inscribirParticipante(Participante participante) throws IllegalStateException{
    LocalDate fechaActual = LocalDate.now();
    ClienteRegistrado cliente = participante.getCliente();

    if (estado == EstadosSorteo.FINALIZADO) {
      return Status.ERROR;
    }

    if (clienteYaInscrito(participante.getCliente())) {
      throw new IllegalStateException("El cliente ya se ha apuntado a este sorteo.");
    }

    if (cliente.getFechaPenalizacion() != null && cliente.getFechaPenalizacion().isAfter(fechaActual))
      throw new IllegalStateException("El cliente sufre una penalización por no haber usado alguno de sus códigos de sorteo.");

    if (hasCodigoInservible(cliente.getCodigos())) {
      cliente.setFechaPenalizacion(fechaActual.plusMonths(Sistema.nMesesPenalizacion));
      throw new IllegalStateException("El cliente sufre una penalización por no haber usado alguno de sus códigos de sorteo.");
    }

    if (fInicioInscripcion.isBefore(fechaActual) &&
        fFinInscripcion.isAfter(fechaActual)) {
      this.participantes.add(participante);
    } else {
      throw new IllegalStateException("La fecha de inscripción ha finalizado.");
    }

    return Status.OK;
  }

  /**
   * Sortear entradas disponibles en el sorteo
   * 
   * @return
   */
  public Status sortear() {
    if (estado == EstadosSorteo.FINALIZADO ||
        LocalDate.now().isBefore(fFinInscripcion))
      return Status.ERROR;

    Random random = new Random();
    int nEntradas = this.nEntradas;

    while (nEntradas > 0 && !participantes.isEmpty()) {
      int numeroAleatorio = random.nextInt(participantes.size());

      Participante ganador = participantes.get(numeroAleatorio);

      int ents_participante = ganador.getNumEntradas();

      while (nEntradas > 0 && ents_participante > 0) {
        nEntradas -= 1;
        ents_participante -= 1;

        UUID codigo = UUID.randomUUID();

        codigos.add(codigo);
        ganador.getCliente().addcodigos(codigo);

        /* notificar ganador */
        notificarGanadorSorteo(ganador, codigo.toString());
      }

      this.participantes.remove(ganador);
    }

    this.estado = EstadosSorteo.FINALIZADO;

    return Status.OK;
  }

  /**
   * Valida la entrada para un codigo de sorteo concreto.
   * 
   * @param entrada entrada que se quiere comprar
   * @param codigo  codigo del sorteo
   * @return true si es válida, false en caso contrario
   */
  public abstract boolean validarEntrada(Comprada entrada, UUID codigo);

  /**
   * Getter de los códigos del sorteo
   * 
   * @return codigos del sorteo
   */
  public List<UUID> getCodigos() {
    return codigos;
  }

  /**
   * Envia una notificacion al ganador del sorteo
   *
   * @param participante participante ganador del sorteo
   * @param cuerpo       mensaje de la notificacion
   */
  private void notificarGanadorSorteo(
      Participante participante,
      String cuerpo) {
    Notificacion noti = new Notificacion(null, cuerpo);

    participante.getCliente().addNotificaciones(noti);
  }

  /**
   * Devuelve el tipo de sorteo.
   * 
   * @return Tipo de sorteo.
   */
  public abstract TipoSorteo getTipoSorteo();

  /**
   * Comprueba si en la lista hay algún codigo inservible
   * 
   * @param codigos codigos a comprobar
   * @return true o false
   */
  public abstract boolean hasCodigoInservible(List<UUID> codigos);

  /**
   * envia una notificación a determinados usuarios si comienza o finaliza el
   * plazo de inscripción
   */
  public void checkEstadoSorteo() {
    LocalDate fechaActual = LocalDate.now();

    Sistema sist = Sistema.getInstance();

    Notificacion noti = null;

    if (fechaActual.isEqual(fInicioInscripcion)) {
      noti = new Notificacion(null,
          "El sorteo de la exposición" + exposicion.getNombre() + " ha abierto su inscripción.");
      sist.enviarNotificacionSist(noti);
    } else if (fechaActual.isEqual(fFinInscripcion)) {
      noti = new Notificacion(null,
          "El sorteo de la exposición" + exposicion.getNombre() + " ha finalizado su inscripción.");
      sist.getGestor().addNotificaciones(noti);
    }
  }

  /**
   * Getter del atributo exposición
   * 
   * @return exposicion del sorteo
   */
  public Exposicion getExposicion() {
    return exposicion;
  }
}
