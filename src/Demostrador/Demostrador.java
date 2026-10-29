package Demostrador;

import Entrada.*;
import Exposicion.*;
import Obra.*;
import Sala.*;
import Sistema.*;
import Sorteo.*;
import Usuario.*;
import Utils.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Esta clase es la encargada de las funcionalidades del programa. Contiene el main y prueba
 * una serie de métodos que no necesitan dela introduccion de informacion mediante la
 * interfaz de usuario.
 * 
 * @author Fabio Desio 
 */
public class Demostrador {

  public static void main(String[] args) throws Exception {
    Sistema sistema = Sistema.getInstance();
    Gestor gestor = null;
    Empleado empActivo = null;
    ClienteRegistrado clActivo = null;
    Notificacion noti = null;
    Status st = Status.OK;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int i, j = 1;

    FileWriterHelper fs = new FileWriterHelper("demostrador.txt");

    fs.writeLineN(
      "--------------------------------------------------------------------------------\n" + //
      "------------------------------ FICHERO DEMOSTRADOR -----------------------------\n" + //
      "--------------------------------------------------------------------------------" + //
      ""
    );
    fs.writeLineN(
      "[-] Creado fichero para la demostración del funcionamiento de nuestra aplicación"
    );

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] INICIO SESIÓN GESTOR");

    gestor = sistema.loginGestor("antonio", "password");

    if (gestor == null) {
      fs.writeLineN(
        "\tERROR: Los credenciales (USUARIO: antonio, PASSWORD: password) son inválidos."
      );
    }

    fs.writeLineN("[-] REPETIMOS INICIO SESIÓN GESTOR");

    gestor = sistema.loginGestor("JefeAlberto", "Password123");

    if (gestor != null) {
      fs.writeLineN(
        "\tOK: Los credenciales (USUARIO: JefeAlberto, PASSWORD: Password123) son válidos."
      );
    }

    sistema.setUsuarioActivo(gestor);
    fs.writeLine("[+] El gestor es el usuario activo.");
    fs.writeLineN("\tUSUARIO_ACTIVO: " + gestor.getUsuario());

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS NUEVOS CLIENTES");

    /* Primer ejemplo de cliente registrado */
    ClienteRegistrado c1 = new ClienteRegistrado(
      "holamundo",
      "00000001A",
      true
    );

    /* Segundo ejemplo de cliente registrado */
    ClienteRegistrado c2 = new ClienteRegistrado(
      "malakito23",
      "20000000B",
      false
    );

    List<ClienteRegistrado> ClienteRegistrados = new ArrayList<>(
      Arrays.asList(c1, c2)
    );

    for (i = 0; i < ClienteRegistrados.size(); i++) {
      ClienteRegistrado e = ClienteRegistrados.get(i);
      fs.writeLine(
        "[+] C" +
        (i + 1) +
        ": Instanciado el cliente " +
        (i + 1) +
        " del sistema"
      );
      fs.writeLine("\t - DNI: " + e.getDni());
      fs.writeLine("\t - PASSWORD: " + e.getPassword());
      fs.writeLineN(
        "\t - ENVIA_NOTIFICACIONES: " + e.isRecibirNotificaciones()
      );
    }

    fs.writeLineN(
      "[-] Actualizamos la lista de clientes registrados del sistema"
    );
    sistema.addClientes(c1, c2);

    fs.writeLine("[+] Lista de clientes actualizada: ");
    i = 1;
    for (ClienteRegistrado c : sistema.getClientes()) {
      fs.writeLine("\tC" + i + ": " + c.getDni());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS NUEVOS EMPLEADOS");

    Empleado e1 = new Empleado(
      "123456789F",
      "Calle la Retuerta",
      567282342,
      67313719,
      true,
      true,
      true
    );

    Empleado e2 = new Empleado(
      "00000002D",
      "Calle la Marmota",
      367225342,
      26786786,
      true,
      false,
      false
    );

    Empleado e3 = new Empleado(
      "00000003F",
      "Avenida de Asturias",
      767282321,
      62873648,
      false,
      false,
      true
    );

    List<Empleado> empleados = new ArrayList<>(Arrays.asList(e1, e2, e3));

    for (i = 0; i < empleados.size(); i++) {
      Empleado e = empleados.get(i);
      fs.writeLine(
        "[+] E" +
        (i + 1) +
        ": Instanciado el empleado " +
        (i + 1) +
        " del sistema"
      );
      fs.writeLine("\t - DNI: " + e.getDni());
      fs.writeLine("\t - PASSWORD: " + e.getPassword());
      fs.writeLine("\t - NSS: " + e.getNss());
      fs.writeLine("\t - DIRECCIÓN: " + e.getDireccion());
      fs.writeLine("\t - NUMCUENTA: " + e.getNumCuenta());
      fs.writeLine("\t - ENVIA_NOTIFICACIONES: " + e.isEnviarNotificaciones());
      fs.writeLine("\t - CAMBIA_CLIMATIZACIÓN: " + e.isCambiarClimatizacion());
      fs.writeLineN("\t - VENDE_ENTRADAS: " + e.isVenderEntradas());
    }

    fs.writeLineN("[-] Actualizamos la lista de empleados en el sistema");
    sistema.addEmpleados(e1, e2, e3);

    fs.writeLine("[+] Lista de empleados actualizada: ");

    i = 1;
    for (Empleado e : sistema.getEmpleados()) {
      fs.writeLine("\tE" + i + ": " + e.getDni());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLine("[+] Lista de empleados actualizada: ");

    i = 1;
    for (Empleado e : sistema.getEmpleados()) {
      fs.writeLine("\tE" + i + ": " + e.getDni());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS ENTRADAS AL SISTEMA");

    Entrada ent1 = new Entrada(
      1,
      10.5,
      LocalDate.of(2024, 2, 24),
      LocalDate.of(2024, 3, 2),
      LocalTime.of(11, 30)
    );

    Entrada ent2 = new Entrada(
      2,
      15,
      LocalDate.of(2024, 3, 25),
      LocalDate.of(2024, 7, 13),
      LocalTime.of(15, 30)
    );

    Entrada com1 = new Comprada(
      3,
      9,
      LocalDate.of(2024, 3, 1),
      LocalDate.of(2024, 3, 10),
      LocalTime.of(14, 15),
      "56715261",
      c1
    );

    Entrada com2 = new Comprada(
      1,
      8,
      LocalDate.of(2024, 3, 11),
      LocalDate.of(2024, 3, 20),
      LocalTime.of(14, 45),
      "12393847",
      c1
    );

    List<Entrada> entradas = new ArrayList<>(
      Arrays.asList(ent1, ent2, com1, com2)
    );

    j = 1;
    for (i = 0; i < entradas.size(); i++) {
      Entrada e = entradas.get(i);
      boolean comprada = e.isComprada();

      fs.writeLine(
        "[+] " +
        (comprada ? "COM" + j++ : "ENT" + (i + 1)) +
        ": Instanciada la entrada " +
        (comprada ? j + 1 : i + 1) +
        " del sistema"
      );
      fs.writeLine("\t - NUMERO_ENTRADAS: " + e.getNumEntradas());
      fs.writeLine("\t - PRECIO_COMPRA: " + e.getPrecioCompra());
      fs.writeLine(
        "\t - FECHA_COMPRA: " + e.getFechaCompra().format(formatter)
      );
      fs.writeLine(
        "\t - FECHA_VISITA: " + e.getFechaVisita().format(formatter)
      );
      fs.writeLine("\t - HORA_VISITA: " + e.getHora());
      fs.writeLine("\t - ESTADO: " + (comprada ? "COMPRADA" : "NO_COMPRADA"));
      if (comprada) {
        fs.writeLine("\t - NUM_TARJETA: " + e.getNumTarjeta());
      }
      fs.writeLine("");
    }

    fs.writeLineN("[-] Actualizamos la lista de entradas del sistema");
    sistema.addEntradas(ent1, ent2, com1, com2);

    fs.writeLine("[+] Lista de entradas actualizada: ");

    i = 1;
    j = 1;
    for (Entrada e : sistema.getEntradas()) {
      if (e.isComprada()) {
        fs.writeLine("\t" + "COM" + j + ": COMPRADA");
        j++;
      } else {
        fs.writeLine("\t" + "ENT" + i + ": NO_COMPRADA");
      }
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS EXPOSICIONES AL SISTEMA");

    Exposicion exp1 = new ExposicionPermanente(
      "La cueva del oso",
      "Federico Martin",
      "Una exposicion llena de belleza",
      10,
      EstadoExposicion.EN_CREACION
    );
    ExposicionPermanente exp2 = new ExposicionPermanente(
      "La risa de las aves",
      "Francisco Lozoya",
      "Obras pintorescas y graciosas",
      9,
      EstadoExposicion.EN_CREACION
    );
    Exposicion exp3 = new ExposicionTemporal(
      "El suspiro de la muerte",
      "Pablo Sanchez",
      "Dramatismo y horror",
      12,
      EstadoExposicion.EN_CREACION,
      LocalDate.of(2024, 3, 10),
      LocalDate.of(2024, 4, 10)
    );

    List<Exposicion> exposiciones = new ArrayList<>(
      Arrays.asList(exp1, exp2, exp3)
    );

    for (i = 0; i < exposiciones.size(); i++) {
      Exposicion e = exposiciones.get(i);
      fs.writeLine(
        "[+] EXP" +
        (i + 1) +
        ": Instanciada la exposición " +
        (i + 1) +
        " del sistema"
      );
      fs.writeLine("\t - NOMBRE: " + e.getNombre());
      fs.writeLine("\t - AUTOR: " + e.getAutor());
      fs.writeLine("\t - DESCRIPCIÓN: " + e.getDescr());
      fs.writeLine("\t - PRECIO: " + e.getPrecio());
      fs.writeLine("\t - ESTADO_EXPOSICIÓN: " + e.getEstado());
      fs.writeLine(
        "\t - RÉGIMEN: " + (e.isTemporal() ? "TEMPORAL" : "PERMANENTE")
      );
      if (e.isTemporal()) {
        fs.writeLine("\t - FECHA_INICIO: " + e.getfInicio().format(formatter));
        fs.writeLineN("\t - FECHA_FINAL: " + e.getfFinal().format(formatter));
        continue;
      }
      fs.writeLine("");
    }

    fs.writeLineN("[-] Actualizamos la lista de exposiciones del sistema");
    sistema.addExposiciones(exp1, exp2, exp3);

    fs.writeLine("[+] Lista de exposiciones actualizada: ");

    i = 1;
    for (Exposicion e : sistema.getExposiciones()) {
      fs.writeLine("\tEXP" + i + ": " + e.getNombre());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN("[-] Cancelamos la exposición 1");

    exp1.cancelar(LocalDate.of(2024, 2, 12));
    fs.writeLineN("\t[->] ESTADO EXP1: " + exp1.getEstado());

    fs.writeLineN("[-] Interrumpimos la exposición 2");

    exp2.interrumpir(LocalDate.of(2024, 4, 3), LocalDate.of(2024, 4, 4));
    exp2.checkEstadoExposicion();
    fs.writeLineN("\t[->] ESTADO EXP2: " + exp2.getEstado());

    fs.writeLineN("[-] Publicamos la exposición 3");

    exp3.publicar();
    fs.writeLineN("\t[->] ESTADO EXP3: " + exp3.getEstado());

    fs.writeLineN("[-] PUBLICAMOS TODAS LAS EXPOSICIONES");

    fs.writeLine("[+] Lista de exposiciones actualizada: ");

    i = 1;
    for (Exposicion exp : sistema.getExposiciones()) {
      exp.setEstado(EstadoExposicion.DISPONIBLE);
      fs.writeLine("\tEXP" + i + ": " + exp.getEstado());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS SALAS AL SISTEMA");

    Dimensiones dim1 = new Dimensiones(1000, 2000, 230);
    SalaNoClimatizada s1 = new SalaNoClimatizada("Mar", 100, 5, dim1);

    Dimensiones dim2 = new Dimensiones(1600, 1200, 250);
    Temperatura t = new Temperatura(17, 23);
    Humedad h = new Humedad(60, 76);
    SalaClimatizada s2 = new SalaClimatizada("Agua", 200, 4, dim2, t, h);

    Dimensiones dim3 = new Dimensiones(2000, 2000, 450);
    SalaNoClimatizada s3 = new SalaNoClimatizada("Tierra", 300, 5, dim3);

    List<Sala> salas = new ArrayList<>(Arrays.asList(s1, s2, s3));

    for (i = 0; i < salas.size(); i++) {
      Sala e = salas.get(i);
      fs.writeLine(
        "[+] S" + (i + 1) + ": Instanciada la sala " + (i + 1) + " del sistema"
      );
      fs.writeLine("\t - AFORO: " + e.getAforo());
      fs.writeLine("\t - TOMAS_CORRIENTE: " + e.getTomasCorriente());
      fs.writeLine("\t - DIMENSIONES:");
      fs.writeLine("\t\t - ANCHO (cm): " + e.getDimensiones().getAncho());
      fs.writeLine("\t\t - LARGO (cm): " + e.getDimensiones().getLargo());
      fs.writeLine("\t\t - ALTO (cm): " + e.getDimensiones().getAlto());
      fs.writeLine(
        "\t - TIPO: " + (e.isClimatizada() ? "CLIMATIZADA" : "NO_CLIMATIZADA")
      );
      if (e.isClimatizada()) {
        fs.writeLine("\t - TEMPERATURA:");
        fs.writeLine("\t\t - MIN (º): " + e.getTemperatura().getMin());
        fs.writeLine("\t\t - MAX (ª): " + e.getTemperatura().getMax());
        fs.writeLine("\t - HUMEDAD:");
        fs.writeLine("\t\t - MIN (%): " + e.getHumedad().getMin());
        fs.writeLine("\t\t - MAX (%): " + e.getHumedad().getMax());
      }
      fs.writeLine("");
    }

    fs.writeLineN("[-] Actualizamos la lista de salas del sistema");
    sistema.addSalas(s1, s2, s3);
    exp1.addSalas(s1);
    exp2.addSalas(s2);
    exp3.addSalas(s3);

    fs.writeLine("[+] Lista de salas actualizada: ");

    i = 1;
    for (Exposicion e : sistema.getExposiciones()) {
      for (Sala s : e.getSalas()) {
        fs.writeLine(
          "\tS" + i + " -> EXP" + i + ": aforo para " + s.getAforo()
        );
      }
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS OBRAS AL SISTEMA");

    Dimensiones dim_escultura = new Dimensiones(100, 75, 120);
    Escultura escultura = new Escultura(
      "El Pensador",
      "Auguste Rodin",
      1902,
      true,
      "Poliza123",
      1000000.00,
      "Escultura en bronce representando a un hombre en profunda meditación",
      "Bronce",
      dim_escultura,
      t,
      h
    );

    Fotografia foto = new Fotografia(
      "Luna de la cosecha",
      "Ansel Adams",
      1937,
      true,
      "Poliza456",
      500000.00,
      "Una impresionante vista de la luna sobre montañas",
      TipoFotografia.COLOR,
      30.0,
      40.0,
      0.1,
      20.0,
      35.0,
      30.0,
      70.0
    );

    Pintura pintura = new Pintura(
      "Mona Lisa",
      "Leonardo Da Vinci",
      1503,
      true,
      "Poliza04",
      10000000,
      "El retrato de boda de Lisa Gherardini, esposa de Il Giocondo.",
      "Sfumato",
      30,
      25,
      45,
      0,
      30,
      0,
      100
    );

    Dimensiones dim_audiovisual = new Dimensiones(0, 0, 0);
    Audiovisual audiovisual = new Audiovisual(
      "Oppenheimer",
      "Christopher Nolan",
      2023,
      false,
      "Poliza045",
      500,
      "Durante la Segunda Guerra Mundial, el teniente general Leslie Groves designa al físico J. Robert Oppenheimer para un grupo de trabajo que está desarrollando el Proyecto Manhattan, cuyo objetivo consiste en fabricar la primera bomba atómica.",
      LocalTime.of(2, 23, 30),
      "V.O.",
      dim_audiovisual
    );

    List<Obra> obras = new ArrayList<Obra>(
      Arrays.asList(escultura, foto, pintura, audiovisual)
    );

    for (i = 0; i < obras.size(); i++) {
      Obra o = obras.get(i);
      TipoDeObra tipo = o.getTipoObra();

      fs.writeLine(
        "[+] O" + (i + 1) + ": Instanciada la obra " + (i + 1) + " del sistema"
      );
      fs.writeLine("\t - TIPO_OBRA: " + tipo);
      fs.writeLine("\t - NOMBRE: " + o.getNombre());
      fs.writeLine("\t - AUTOR: " + o.getAutor());
      fs.writeLine("\t - AÑO: " + o.getAnio());
      fs.writeLine("\t - PROPIA: " + o.isPropia());
      fs.writeLine("\t - PÓLIZA: " + o.getPoliza());
      fs.writeLine("\t - CUANTÍA_SEGURO: " + o.getCuantiaSeguro());
      fs.writeLine(
        "\t - DESCRIPCION: " + o.getDescripcion().substring(0, 40) + "..."
      );

      if (tipo == TipoDeObra.ESCULTURA) {
        fs.writeLine("\t - MATERIAL: " + escultura.getMaterial());
      } else if (tipo == TipoDeObra.FOTOGRAFIA) {
        fs.writeLine("\t - TIPO_FOTOGRAFÍA: " + foto.getTipo());
      } else if (tipo == TipoDeObra.PINTURA) {
        fs.writeLine("\t - TÉCNICA: " + pintura.getTecnica());
      } else {
        fs.writeLine("\t - DURACIÓN: " + audiovisual.getDuracion());
        fs.writeLineN("\t - IDIOMA: " + audiovisual.getIdioma());
        continue;
      }

      fs.writeLine("\t - DIMENSIONES:");
      fs.writeLine("\t\t - ANCHO (cm): " + o.getDimensiones().getAncho());
      fs.writeLine("\t\t - LARGO (cm): " + o.getDimensiones().getLargo());
      fs.writeLine("\t\t - ALTO (cm): " + o.getDimensiones().getAlto());

      if (o.necesitaClimatizacion()) {
        fs.writeLine("\t - TEMPERATURA:");
        fs.writeLine("\t\t - MIN (º): " + o.getTemp().getMin());
        fs.writeLine("\t\t - MAX (ª): " + o.getTemp().getMax());
        fs.writeLine("\t - HUMEDAD:");
        fs.writeLine("\t\t - MIN (%): " + o.getHumedad().getMin());
        fs.writeLine("\t\t - MAX (%): " + o.getHumedad().getMax());
      }

      fs.writeLine("");
    }

    fs.writeLineN("[-] Actualizamos la lista de obras del sistema");

    fs.writeLine("[+] Lista de obras actualizada: ");
    sistema.addObras(escultura, pintura, foto, audiovisual);

    i = 1;
    for (Obra o : sistema.getObras()) {
      fs.writeLine("\tO" + i + ": " + o.getNombre() + ", " + o.getTipoObra());
      i++;
    }
    fs.writeLine("");

    fs.writeLineN("[-] Expondremos las obras en ciertas exposiciones");

    escultura.exponer(exp2, s2);
    foto.exponer(exp3, s3);
    pintura.exponer(exp2, s2);
    audiovisual.exponer(exp1, s1);

    fs.writeLine("[+] Lista obras por exposición");
    i = 1;
    for (Exposicion e : sistema.getExposiciones()) {
      boolean size0 = e.getObras().size() == 0;
      String exps =
        "\tEXP" +
        i +
        ": " +
        e.getNombre() +
        " -> " +
        (
          size0
            ? "La obra no cumple con las condiciones de la sala."
            : "Obra(s): "
        );

      if (size0) {
        fs.writeLine(exps);
        continue;
      }

      for (Obra o : e.getObras()) {
        exps += o.getNombre() + ", ";
      }
      exps = exps.substring(0, exps.length() - 2) + ".";
      fs.writeLine(exps);
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] AÑADIMOS SORTEOS AL SISTEMA");

    SorteoFechaHora sor1 = new SorteoFechaHora(
      20,
      LocalDate.of(2024, 4, 1),
      LocalDate.of(2024, 4, 15),
      LocalDate.of(2024, 4, 20),
      LocalTime.of(17, 30)
    );

    SorteoMientrasDureExp sor2 = new SorteoMientrasDureExp(
      1,
      LocalDate.of(2024, 4, 2),
      LocalDate.of(2024, 4, 29)
    );

    SorteoPlazoDado sor3 = new SorteoPlazoDado(
      5,
      LocalDate.of(2024, 5, 1),
      LocalDate.of(2024, 5, 11),
      LocalDate.of(2024, 5, 12),
      LocalDate.of(2024, 5, 26)
    );

    List<Sorteo> sorteos = new ArrayList<Sorteo>(
      Arrays.asList(sor1, sor2, sor3)
    );

    for (i = 0; i < sorteos.size(); i++) {
      Sorteo s = sorteos.get(i);
      TipoSorteo ts = s.getTipoSorteo();
      fs.writeLine(
        "[+] SOR" +
        (i + 1) +
        ": Instanciado el sorteo " +
        (i + 1) +
        " del sistema"
      );
      fs.writeLine("\t - TIPO_SORTEO: " + ts);
      fs.writeLine("\t - NUM_ENTRADAS: " + s.getnEntradas());
      fs.writeLine(
        "\t - INICIO_INSCRIPCIÓN: " +
        s.getfInicioInscripcion().format(formatter)
      );
      fs.writeLine(
        "\t - FIN_INSCRIPCIÓN: " + s.getfFinInscripcion().format(formatter)
      );

      if (ts == TipoSorteo.PARA_FECHA_HORA) {
        fs.writeLine(
          "\t - FECHA_VISITA: " + sor1.getFechaVisita().format(formatter)
        );
        fs.writeLine("\t - HORA_VISITA: " + sor1.getHoraVisita());
      } else if (ts == TipoSorteo.PARA_PLAZO_DADO) {
        fs.writeLine(
          "\t - FECHA_INICIO_VISITA: " +
          sor3.getFechaInicioVisita().format(formatter)
        );
        fs.writeLine(
          "\t - FECHA_FINAL_VISITA: " +
          sor3.getFechaFinalVisita().format(formatter)
        );
      }
      fs.writeLine("");
    }

    fs.writeLine("[-] Actualizamos la lista de sorteos del sistema");
    sistema.addSorteos(sor1, sor2, sor3);

    i = 1;
    for (Sorteo o : sistema.getSorteos()) {
      fs.writeLine(
        "\tSOR" +
        i +
        ": " +
        o.getTipoSorteo() +
        ", " +
        o.getnEntradas() +
        " entrada(s) sorteada(s)."
      );
      i++;
    }
    fs.writeLine("");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] REALIZACIÓN SORTEO");

    fs.writeLineN("[-] Añadimos el sorteo 2 a la exposición 1");
    exp1.setSorteo(sor2);

    fs.writeLine("[+] Sorteo añadido a la exposición 1");

    fs.writeLineN("\t EXP1 -> SOR2: " + exp1.getSorteo().getEstado());

    fs.writeLineN("[-] Registramos manualmente al cliente 1 como participante");

    Participante par = new Participante(1, c1);
    sor2.inscribirParticipante(par);

    Participante temp = sor2.getParticipantes().get(0);
    fs.writeLine("[+] Participante añadido al sorteo 2");
    fs.writeLineN(
      "\tPARTICIPANTE -> DNI: " +
      temp.getCliente().getDni() +
      ", ENTRADAS_SOLICITADAS: " +
      temp.getNumEntradas()
    );

    fs.writeLineN(
      "[-] Realizamos sorteo y se envía una notificación al ganador"
    );
    st = sor2.sortear();

    fs.writeLineN(
      "\t[->] SORTEO_EXITOSO: " + (st == Status.OK ? "TRUE" : "FALSE")
    );

    fs.writeLineN("[-] Cerramos sesión del gestor");

    sistema.cerrarSesion();

    sistema.setUsuarioActivo(null);
    fs.writeLineN("[+] USUARIO_ACTIVO: NULL");

    fs.writeLineN("[-] Loggeamos al cliente ganador");

    clActivo = sistema.loginCliente("00000001A", "holamundo");

    if (clActivo != null) {
      fs.writeLineN(
        "\tOK: Los credenciales (DNI: 00000001A, PASSWORD: holamundo) son válidos."
      );
    }

    sistema.setUsuarioActivo(clActivo);
    fs.writeLineN("[+] USUARIO_ACTIVO: " + clActivo.getDni());

    fs.writeLineN("[-] Extraemos sus notificaciones");

    noti = clActivo.getNotificaciones().get(0);
    fs.writeLineN("[+] NOTIFICACIÓN -> CÓDIGO ENTRADA: " + noti.getCuerpo());

    fs.writeLineN("[-] El cliente activo compra la entrada con el código");

    exp1.comprarEntrada(
      clActivo,
      1,
      LocalDate.of(2024, 4, 7),
      LocalTime.of(17, 30),
      "1234567890123456",
      noti.getCuerpo()
    );

    fs.writeLine("[+] Entradas cliente: ");
    fs.writeLineN(
      "\t - ENTRADA COMPRADA PARA LA EXPOSICIÓN 2 a las " +
      clActivo.getEntradas().get(0).getHora()
    );

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] ACCIONES DE EMPLEADO");

    fs.writeLineN("[-] Cerramos sesión del cliente");

    sistema.cerrarSesion();

    sistema.setUsuarioActivo(null);
    fs.writeLineN("[+] USUARIO_ACTIVO: NULL");

    fs.writeLineN("[-] Loggeamos a un empleado con permisos");

    empActivo = sistema.loginEmpleado("123456789F", "empleado2024");

    if (empActivo != null) {
      fs.writeLineN(
        "\tOK: Los credenciales (DNI: 123456789F, PASSWORD: empleado2024) son válidos."
      );
    }

    sistema.setUsuarioActivo(empActivo);
    fs.writeLineN("[+] USUARIO_ACTIVO: " + empActivo.getDni());

    fs.writeLineN("[-] Creamos notificación para enviar");

    Notificacion n = new Notificacion(empActivo, "Ejemplo de notificación");

    fs.writeLine("[+] N1: Notificación instanciada");
    fs.writeLine("\t - EMPLEADO_DNI: " + n.getEmisor().getDni());
    fs.writeLineN("\t - CUERPO: " + n.getCuerpo());

    fs.writeLineN(
      "[-] Enviamos la notificación a todos los usuarios del sistema con permisos"
    );

    st = sistema.enviarNotificacionEmp(n);

    fs.writeLineN("[+] ESTADO_ENVÍO: " + st);

    fs.writeLine("[-] Comprobamos que lo han recibido todos los clientes");

    i = 1;
    for (ClienteRegistrado c : sistema.getClientes()) {
      String str = "\t - C" + i;
      j = 1;
      if (!c.isRecibirNotificaciones()) {
        str +=
          " -> ERROR: el cliente no tiene activada la opción de recibir notificaciones\n";
      } else {
        for (Notificacion notificacion : c.getNotificaciones()) {
          str += "\n\t\t - N" + j + ": " + notificacion.getCuerpo();
          j++;
        }
      }
      i++;
      fs.writeLine(str);
    }
    fs.writeLine("");

    fs.writeLineN(
      "[-] Ahora vendemos una entrada a un cliente no registrado para la exposición 2"
    );

    exp2.venderEntrada(23, LocalTime.of(18, 30), "1234567890123456");

    fs.writeLineN(
      "[+] Entrada vendida para EXP2:" +
      "\n\t - NUM_ENTRADAS: " +
      exp2.getEntradas().get(0).getNumEntradas() +
      "\n\t - HORA: " +
      exp2.getEntradas().get(0).getHora() +
      "\n\t - NUM_TARJETA: " +
      exp2.getEntradas().get(0).getNumTarjeta()
    );

    fs.writeLineN("[-] Ahora cambiamos la temperatura de la sala 2");

    fs.writeLineN(
      "[+] TEMPERATURA_ANTES: " +
      s2.getTemperatura().getMin() +
      "º - " +
      s2.getTemperatura().getMax() +
      "º"
    );

    s2.setTemperatura(19, 25);

    fs.writeLineN(
      "[+] TEMPERATURA_DESPUÉS: " +
      s2.getTemperatura().getMin() +
      "º - " +
      s2.getTemperatura().getMax() +
      "º"
    );

    fs.writeLineN("[-] Cerramos sesión del empleado");

    sistema.cerrarSesion();

    sistema.setUsuarioActivo(null);
    fs.writeLineN("[+] USUARIO_ACTIVO: NULL");

    fs.writeLineN(
      "--------------------------------------------------------------------------------"
    );

    fs.writeLineN("[-] Loggeamos al gestor para pruebas finales");

    gestor = sistema.loginGestor("JefeAlberto", "Password123");

    if (gestor != null) {
      fs.writeLineN(
        "\tOK: Los credenciales (USUARIO: JefeAlberto, PASSWORD: Password123) son válidos."
      );
    }

    sistema.setUsuarioActivo(gestor);
    fs.writeLine("[+] El gestor es el usuario activo.");
    fs.writeLineN("\tUSUARIO_ACTIVO: " + gestor.getUsuario());

    fs.writeLineN(
      "[+] Número de obras antes de cargar desde fichero: " +
      sistema.getObras().size()
    );

    fs.writeLineN("[-] Cargamos más obras desde fichero");

    st = sistema.cargarObrasDeFichero("resources/obras.csv");

    fs.writeLineN(
      "[+] Número de obras después de cargar desde fichero: " +
      sistema.getObras().size()
    );

    fs.writeLineN("[-] Guardamos los datos");

    sistema.guardarDatos();

    fs.writeLineN("[+] DATOS GUARDADOS");

    fs.writeLineN("[-] Setteamos a NULL la instancia del SISTEMA");

    Sistema.setInstanceNull();

    fs.writeLineN("[+] INSTANCIA ANULADA");

    fs.writeLineN("[-] Cargamos los datos");

    sistema = Sistema.getInstance();

    sistema.cargarDatos();

    fs.writeLineN("[+] DATOS CARGADOS -> comprobamos lista de obras");

    System.err.println(sistema.getObras());

    i = 1;
    for (Obra o : sistema.getObras()) {
      fs.writeLine("\tO" + i + ": " + o.getNombre() + ", " + o.getTipoObra());
      i++;
    }
    fs.writeLine("");

    fs.close();
  }
}
