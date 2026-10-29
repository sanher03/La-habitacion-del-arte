package Demostrador;

import Entrada.Comprada;
import Entrada.Entrada;
import Exposicion.Descuento.DescuentoPorAntelacion;
import Exposicion.Descuento.DescuentoPorCompra;
import Exposicion.EstadoExposicion;
import Exposicion.Exposicion;
import Exposicion.ExposicionPermanente;
import Exposicion.ExposicionTemporal;
import Exposicion.TipoDeObra;
import Horario.Horario;
import Obra.Audiovisual;
import Obra.Escultura;
import Obra.EstadosObra;
import Obra.Fotografia;
import Obra.Obra;
import Obra.Pintura;
import Obra.TipoFotografia;
import Sala.Sala;
import Sala.SalaClimatizada;
import Sala.SalaNoClimatizada;
import Sistema.Sistema;
import Sorteo.EstadosSorteo;
import Sorteo.Participante;
import Sorteo.Sorteo;
import Sorteo.SorteoFechaHora;
import Sorteo.SorteoMientrasDureExp;
import Sorteo.SorteoPlazoDado;
import Usuario.ClienteRegistrado;
import Usuario.Empleado;
import Usuario.Notificacion;
import Utils.Dimensiones;
import Utils.Humedad;
import Utils.Status;
import Utils.Temperatura;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Regenera un conjunto amplio de datos para probar los perfiles y flujos del sistema.
 */
public final class GenerarSistema {

  private static final String ARCHIVO_DATOS = "sistema_rellenado.dat";
  private static final int NUM_CLIENTES = 12;
  private static final int NUM_EMPLEADOS = 6;
  private static final int NUM_OBRAS = 36;
  private static final int NUM_EXPOSICIONES = 14;
  private static final int NUM_SORTEOS = 6;

  private GenerarSistema() {}

  public static void main(String[] args) {
    Sistema sistema = Sistema.getInstance();
    limpiarDatos(sistema);
    sistema.setHorario(new Horario(9, 30, 20, 0));
    sistema.setNMeses(3);

    poblarUsuarios(sistema);
    List<Sala> salas = poblarSalas(sistema);
    List<Obra> obras = poblarObras(sistema);
    List<Exposicion> exposiciones = poblarExposiciones(sistema, salas, obras);
    poblarDescuentos(exposiciones);
    poblarSorteos(sistema, exposiciones);
    poblarEntradas(sistema, exposiciones);
    poblarNotificaciones(sistema);

    sistema.setUsuarioActivo(null);
    sistema.guardarDatos();

    Sistema.setInstanceNull();
    sistema = Sistema.getInstance();
    comprobarDatos(sistema);
    mostrarResumen(sistema);
  }

  private static void limpiarDatos(Sistema sistema) {
    sistema.getEntradas().clear();
    sistema.getClientes().clear();
    sistema.getEmpleados().clear();
    sistema.getObras().clear();
    sistema.getSalas().clear();
    sistema.getSorteos().clear();
    sistema.getExposiciones().clear();
    sistema.getGestor().getNotificaciones().clear();
    sistema.setUsuarioActivo(null);
  }

  private static void poblarUsuarios(Sistema sistema) {
    List<ClienteRegistrado> clientes = new ArrayList<>();
    clientes.add(new ClienteRegistrado("holamundo", "00000001A", true));
    clientes.add(new ClienteRegistrado("malakito23", "20000000B", true));
    clientes.add(new ClienteRegistrado("cliente2026", "30000003C", false));
    for (int i = clientes.size(); i < NUM_CLIENTES; i++) {
      clientes.add(new ClienteRegistrado(
          "cliente" + (i + 1) + "2026",
          String.format("%08d%c", 40000000 + i, 'D' + i - 3),
          i % 3 != 0));
    }
    sistema.addClientes(clientes.toArray(new ClienteRegistrado[0]));

    Empleado[] empleados = {
        new Empleado("123456789F", "Calle de las Artes 10", 567282342L,
            67313719L, true, true, true),
        new Empleado("00000002D", "Avenida del Museo 24", 367225342L,
            26786786L, true, false, true),
        new Empleado("00000003F", "Plaza Central 5", 767282321L,
            62873648L, false, true, false),
        new Empleado("00000004G", "Calle del Prado 8", 167282344L,
            41873648L, false, false, true),
        new Empleado("00000005H", "Paseo del Arte 16", 267282345L,
            51873649L, true, true, false),
        new Empleado("00000006J", "Avenida de la Cultura 3", 367282346L,
            61873650L, true, false, false)
    };
    sistema.addEmpleados(empleados);
  }

  private static List<Sala> poblarSalas(Sistema sistema) {
    List<Sala> salas = new ArrayList<>();

    SalaClimatizada salaDivisible = new SalaClimatizada(
        "Pabellón climatizado", 240, 8, 1200, 900, 600, 15, 25, 35, 75);
    sistema.addSalas(salaDivisible);
    salaDivisible.dividirSala(
        "Pabellón norte", "Pabellón sur", 600, 600, 120, 120, 4, 4,
        new Temperatura(15, 25), new Temperatura(15, 25),
        new Humedad(35, 75), new Humedad(35, 75));
    salas.add(salaDivisible.getSubsala1());
    salas.add(salaDivisible.getSubsala2());

    for (int i = 0; i < 6; i++) {
      SalaClimatizada sala = new SalaClimatizada(
          "Galería climatizada " + (i + 1), 100 + i * 10, 4 + i % 3,
          900, 800, 600, 15, 25, 35, 75);
      sistema.addSalas(sala);
      salas.add(sala);
    }

    for (int i = 0; i < 5; i++) {
      SalaNoClimatizada sala = new SalaNoClimatizada(
          "Sala polivalente " + (i + 1), 80 + i * 10, 6 + i,
          900, 800, 600);
      sistema.addSalas(sala);
      salas.add(sala);
    }

    return salas;
  }

  private static List<Obra> poblarObras(Sistema sistema) {
    List<Obra> obras = new ArrayList<>();
    for (int i = 0; i < NUM_OBRAS; i++) {
      Obra obra = crearObra(i);
      obras.add(obra);
    }
    sistema.addObras(obras.toArray(new Obra[0]));

    obras.get(32).prestar();
    obras.get(33).restaurar();
    obras.get(34).retirar();
    return obras;
  }

  private static Obra crearObra(int indice) {
    int tipo = indice % 4;
    int numero = indice + 1;
    String nombre = "Colección " + numero;
    String autor = "Artista " + numero;
    String poliza = "PAD-" + String.format("%04d", numero);
    String descripcion = "Pieza de muestra " + numero + " de la colección del centro.";
    Dimensiones dimensiones = new Dimensiones(80 + indice % 5 * 5, 70, 40);
    Temperatura temperatura = new Temperatura(10, 30);
    Humedad humedad = new Humedad(20, 80);

    switch (tipo) {
      case 0:
        return new Pintura(nombre, autor, 1980 + indice, true, poliza,
            5000 + indice * 250, descripcion, "Óleo sobre lienzo",
            dimensiones, temperatura, humedad);
      case 1:
        return new Escultura(nombre, autor, 1980 + indice, true, poliza,
            7000 + indice * 300, descripcion, "Bronce",
            dimensiones, temperatura, humedad);
      case 2:
        return new Fotografia(nombre, autor, 1980 + indice, true, poliza,
            2500 + indice * 150, descripcion,
            indice % 2 == 0 ? TipoFotografia.COLOR : TipoFotografia.BYN,
            dimensiones, temperatura, humedad);
      default:
        return new Audiovisual(nombre, autor, 1980 + indice, true, poliza,
            3000 + indice * 100, descripcion, LocalTime.of(0, 8 + indice % 8, 0),
            "Español", dimensiones);
    }
  }

  private static List<Exposicion> poblarExposiciones(
      Sistema sistema, List<Sala> salas, List<Obra> obras) {
    LocalDate hoy = LocalDate.now();
    List<Exposicion> exposiciones = new ArrayList<>();
    for (int i = 0; i < NUM_EXPOSICIONES; i++) {
      Exposicion exposicion;
      if (i < 8) {
        exposicion = new ExposicionPermanente(
            "Exposición permanente " + (i + 1), "Centro PADSOF",
            "Recorrido de muestra por distintas disciplinas artísticas.",
            8 + i, EstadoExposicion.EN_CREACION,
            rutaImagen(i));
      } else {
        LocalDate inicio = i == 13 ? hoy.plusDays(35) : hoy.minusDays(15);
        LocalDate fin = inicio.plusDays(90 + i);
        exposicion = new ExposicionTemporal(
            "Exposición temporal " + (i - 7), "Colección PADSOF",
            "Muestra temporal con obras de varias disciplinas.",
            10 + i, EstadoExposicion.EN_CREACION, rutaImagen(i), inicio, fin);
      }

      sistema.addExposiciones(exposicion);
      Sala sala = salas.get(i % 8);
      exposicion.addSalas(sala);
      obras.get(i * 2).exponer(exposicion, sala);
      obras.get(i * 2 + 1).exponer(exposicion, sala);
      exposiciones.add(exposicion);
    }

    for (int i = 0; i < exposiciones.size(); i++) {
      exposiciones.get(i).setEstado(
          i == 12 ? EstadoExposicion.EN_CREACION
              : i == 0 || i == 9 ? EstadoExposicion.COMENZADA
                  : EstadoExposicion.DISPONIBLE);
    }

    ExposicionPermanente permanenteInterrumpible =
        (ExposicionPermanente) exposiciones.get(7);
    Status interrupcion = permanenteInterrumpible.interrumpir(
        hoy.plusDays(20), hoy.plusDays(25));
    if (interrupcion != Status.OK)
      throw new IllegalStateException("No se pudo programar la interrupción de muestra.");

    return exposiciones;
  }

  private static void poblarDescuentos(List<Exposicion> exposiciones) {
    for (int i = 0; i < exposiciones.size(); i++) {
      if (i % 3 == 0)
        exposiciones.get(i).addDescuento(new DescuentoPorCompra(0.10, 3));
      if (i % 3 != 1)
        exposiciones.get(i).addDescuento(new DescuentoPorAntelacion(0.05, 2));
    }
  }

  private static void poblarSorteos(
      Sistema sistema, List<Exposicion> exposiciones) {
    LocalDate hoy = LocalDate.now();
    List<ClienteRegistrado> clientes = sistema.getClientes();

    SorteoFechaHora sorteoFechaAbierto = new SorteoFechaHora(
        3, hoy.minusDays(2), hoy.plusDays(14), hoy.plusDays(30),
        LocalTime.of(11, 0));
    SorteoMientrasDureExp sorteoDuracionAbierto = new SorteoMientrasDureExp(
        4, hoy.minusDays(2), hoy.plusDays(14));
    SorteoPlazoDado sorteoPlazoAbierto = new SorteoPlazoDado(
        2, hoy.minusDays(2), hoy.plusDays(14),
        hoy.plusDays(20), hoy.plusDays(60));

    SorteoFechaHora sorteoFechaFinalizado = new SorteoFechaHora(
        2, hoy.minusDays(30), hoy.minusDays(10), hoy.plusDays(35),
        LocalTime.of(12, 0));
    SorteoMientrasDureExp sorteoDuracionFinalizado = new SorteoMientrasDureExp(
        2, hoy.minusDays(30), hoy.minusDays(10));
    SorteoPlazoDado sorteoPlazoFinalizado = new SorteoPlazoDado(
        2, hoy.minusDays(30), hoy.minusDays(10),
        hoy.plusDays(25), hoy.plusDays(70));

    Sorteo[] sorteos = {
        sorteoFechaAbierto, sorteoDuracionAbierto, sorteoPlazoAbierto,
        sorteoFechaFinalizado, sorteoDuracionFinalizado, sorteoPlazoFinalizado
    };
    for (int i = 0; i < sorteos.length; i++) {
      exposiciones.get(i + 1).setSorteo(sorteos[i]);
    }

    sorteoFechaAbierto.inscribirParticipante(new Participante(1, clientes.get(0)));
    sorteoDuracionAbierto.inscribirParticipante(new Participante(2, clientes.get(1)));
    sorteoPlazoAbierto.inscribirParticipante(new Participante(1, clientes.get(2)));

    sorteoFechaFinalizado.getParticipantes().add(new Participante(2, clientes.get(3)));
    sorteoDuracionFinalizado.getParticipantes().add(new Participante(2, clientes.get(4)));
    sorteoPlazoFinalizado.getParticipantes().add(new Participante(2, clientes.get(5)));
    for (Sorteo sorteo : new Sorteo[] {
        sorteoFechaFinalizado, sorteoDuracionFinalizado, sorteoPlazoFinalizado
    }) {
      if (sorteo.sortear() != Status.OK)
        throw new IllegalStateException("No se pudo generar un sorteo finalizado de muestra.");
    }
  }

  private static void poblarEntradas(
      Sistema sistema, List<Exposicion> exposiciones) {
    List<ClienteRegistrado> clientes = sistema.getClientes();
    int reservas = 0;
    for (int i = 0; i < NUM_EXPOSICIONES; i++) {
      Exposicion exposicion = exposiciones.get(i);
      ClienteRegistrado cliente = clientes.get(i % 6);
      LocalDate visita = LocalDate.now().plusDays(10 + i);
      if (exposicion.isTemporal()
          && visita.isBefore(exposicion.getfInicio().plusDays(1)))
        visita = exposicion.getfInicio().plusDays(1);
      if (!visita.isAfter(exposicion.getfFinal() == null
          ? visita : exposicion.getfFinal())) {
        añadirCompra(sistema, exposicion, cliente, 1 + i % 3, visita,
            LocalTime.of(10 + i % 8, 0));
        reservas++;
      }
    }

    for (int i = 0; i < NUM_EXPOSICIONES; i++) {
      Exposicion exposicion = exposiciones.get(i);
      Entrada venta = new Entrada(
          1 + i % 2, exposicion.getPrecio() * (1 + i % 2),
          LocalDate.now(), LocalDate.now().plusDays(5 + i),
          LocalTime.of(12 + i % 7, 0));
      exposicion.addEntradas(venta);
      sistema.addEntradas(venta);
    }
    if (reservas == 0)
      throw new IllegalStateException("No se han creado reservas de muestra.");
  }

  private static void añadirCompra(
      Sistema sistema,
      Exposicion exposicion,
      ClienteRegistrado cliente,
      int cantidad,
      LocalDate visita,
      LocalTime hora) {
    Comprada compra = new Comprada(
        cantidad, exposicion.getPrecio() * cantidad, LocalDate.now(), visita,
        hora, "1234567890123456", cliente);
    exposicion.addEntradas(compra);
    cliente.addEntradas(compra);
    sistema.addEntradas(compra);
  }

  private static void poblarNotificaciones(Sistema sistema) {
    for (ClienteRegistrado cliente : sistema.getClientes()) {
      cliente.addNotificaciones(new Notificacion(
          null, "Bienvenido al centro de exposiciones PADSOF."));
    }

    sistema.setUsuarioActivo(sistema.getEmpleados().get(0));
    sistema.enviarNotificacionEmp(new Notificacion(
        sistema.getEmpleados().get(0), "Consulta la programación actualizada."));
    sistema.setUsuarioActivo(null);
  }

  private static String rutaImagen(int indice) {
    String nombre = indice % 2 == 0 ? "exposicion.jpeg" : "exposicion2.jpeg";
    File imagen = new File("imgs", nombre);
    if (!imagen.isFile())
      throw new IllegalStateException("Falta la imagen de muestra " + imagen.getPath());
    ImageIcon icono = new ImageIcon(imagen.getAbsolutePath());
    if (icono.getIconWidth() <= 0 || icono.getIconHeight() <= 0)
      throw new IllegalStateException("No se pudo cargar la imagen " + imagen.getPath());
    return imagen.getAbsolutePath();
  }

  private static void comprobarDatos(Sistema sistema) {
    if (sistema.getClientes().size() != NUM_CLIENTES
        || sistema.getEmpleados().size() != NUM_EMPLEADOS
        || sistema.getObras().size() != NUM_OBRAS
        || sistema.getExposiciones().size() != NUM_EXPOSICIONES
        || sistema.getSorteos().size() != NUM_SORTEOS
        || sistema.getEntradas().size() < NUM_EXPOSICIONES * 2
        || sistema.getSalas().size() < 13) {
      throw new IllegalStateException(
          "La lectura de " + ARCHIVO_DATOS + " no conserva todos los objetos generados.");
    }

    EnumMap<TipoDeObra, Integer> tipos = new EnumMap<>(TipoDeObra.class);
    for (Obra obra : sistema.getObras())
      tipos.merge(obra.getTipoObra(), 1, Integer::sum);
    if (tipos.size() != 4)
      throw new IllegalStateException("Faltan tipos de obra en los datos de muestra.");

    long sorteosFinalizados = sistema.getSorteos().stream()
        .filter(sorteo -> sorteo.getEstado() == EstadosSorteo.FINALIZADO
            && !sorteo.getCodigos().isEmpty())
        .count();
    if (sorteosFinalizados != 3)
      throw new IllegalStateException("No se conservaron los códigos de sorteo generados.");
    long sorteosActivos = sistema.getSorteos().stream()
        .filter(sorteo -> sorteo.getEstado() == EstadosSorteo.ACTIVO)
        .count();
    if (sorteosActivos != 3)
      throw new IllegalStateException("No se conservaron las inscripciones de sorteos activos.");

    boolean tieneBorrador = sistema.getExposiciones().stream()
        .anyMatch(exposicion -> exposicion.getEstado() == EstadoExposicion.EN_CREACION);
    boolean imagenesCargadas = sistema.getExposiciones().stream()
        .allMatch(exposicion -> exposicion.getImagen() != null
            && exposicion.getImagen().getIconWidth() > 0);
    if (!tieneBorrador || !imagenesCargadas)
      throw new IllegalStateException("Faltan borradores o imágenes en las exposiciones.");

    boolean tienePrestada = sistema.getObras().stream()
        .anyMatch(obra -> obra.getEstado() == EstadosObra.PRESTADA);
    boolean tieneRestauracion = sistema.getObras().stream()
        .anyMatch(obra -> obra.getEstado() == EstadosObra.ENRESTAURACION);
    boolean tieneRetirada = sistema.getObras().stream()
        .anyMatch(obra -> obra.getEstado() == EstadosObra.RETIRADA);
    if (!tienePrestada || !tieneRestauracion || !tieneRetirada)
      throw new IllegalStateException("Faltan estados de gestión de obras.");
  }

  private static void mostrarResumen(Sistema sistema) {
    System.out.println("Datos generados en " + ARCHIVO_DATOS);
    System.out.println("Clientes: " + sistema.getClientes().size());
    System.out.println("Empleados: " + sistema.getEmpleados().size());
    System.out.println("Obras: " + sistema.getObras().size());
    System.out.println("Salas: " + sistema.getSalas().size());
    System.out.println("Exposiciones: " + sistema.getExposiciones().size());
    System.out.println("Sorteos: " + sistema.getSorteos().size());
    System.out.println("Entradas: " + sistema.getEntradas().size());
  }
}
