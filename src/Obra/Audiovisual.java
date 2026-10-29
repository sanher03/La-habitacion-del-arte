package Obra;

import Utils.*;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exposicion.TipoDeObra;
import Sala.*;

/**
 * Clase que representa una obra audiovisual.
 * 
 * @author Ignacio Sánchez
 */
public class Audiovisual extends Obra {
    String idioma; // Idioma de la obra audiovisual
    LocalTime duracion; // Duracion de la obra audiovisual

    /**
     * Constructor de la clase Audiovisual.
     * 
     * @param nombre         nombre de la obra
     * @param autor          autor de la obra
     * @param anio           año de creación de la obra
     * @param propia         indica si la obra es propia o externa
     * @param poliza         número de póliza de seguro de la obra
     * @param cuantia_seguro cuantía del seguro de la obra
     * @param descr          descripción de la obra
     * @param duracion       duración de la obra en formato LocalTime
     * @param idioma         idioma de la obra
     * @param ancho          ancho de la obra
     * @param largo          largo de la obra
     * @param alto           alto de la obra
     * @throws IllegalArgumentException alguno de los argumentos es null
     * @throws NullPointerException     alguno de los argumentos es invalido
     */
    public Audiovisual(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro,
            String descr, LocalTime duracion, String idioma,
            double ancho, double largo, double alto) throws IllegalArgumentException, NullPointerException {

        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, ancho, largo, alto);

        if (duracion == null) {
            throw new NullPointerException("La duración no puede ser nula.");
        }

        if (idioma == null || idioma.isEmpty()) {
            throw new NullPointerException("El idioma no puede ser nulo o vacío.");
        }

        if (ancho <= 0 || largo <= 0 || alto <= 0) {
            throw new IllegalArgumentException("Las dimensiones (ancho, largo, alto) deben ser mayores que cero.");
        }

        this.duracion = duracion;
        this.idioma = idioma;
    }

    /**
     * Constructor de la clase Audiovisual.
     * 
     * @param nombre         nombre de la obra
     * @param autor          autor de la obra
     * @param anio           año de creación de la obra
     * @param propia         indica si la obra es propia o externa
     * @param poliza         número de póliza de seguro de la obra
     * @param cuantia_seguro cuantía del seguro de la obra
     * @param descr          descripción de la obra
     * @param duracion       duración de la obra en formato LocalTime
     * @param idioma         idioma de la obra
     * @param dim            dimensiones de la obra
     * @throws IllegalArgumentException alguno de los argumentos es null
     * @throws NullPointerException     alguno de los argumentos es invalido
     */
    public Audiovisual(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro,
            String descr, LocalTime duracion, String idioma,
            Dimensiones dim) throws IllegalArgumentException, NullPointerException {
        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, dim);

        if (duracion == null) {
            throw new NullPointerException("La duración no puede ser nula.");
        }

        if (idioma == null || idioma.isEmpty()) {
            throw new NullPointerException("El idioma no puede ser nulo o vacío.");
        }

        if (dim == null) {
            throw new NullPointerException("El idioma no puede ser nulo o vacío.");
        }

        this.duracion = duracion;
        this.idioma = idioma;
    }

    /**
     * Método getter del atributo duracion.
     * 
     * @return duracion
     */
    public LocalTime getDuracion() {
        return duracion;
    }

    /**
     * Método setter del atributo duracion.
     * 
     * @param duracion duración de la obra en formato LocalTime
     */
    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    /**
     * Método que indica si la obra audiovisual necesita climatización.
     * 
     * @return false, ya que las obras audiovisuales no necesitan climatización
     */
    public boolean necesitaClimatizacion() {
        return false;
    }

    /**
     * Método que devuelve el tipo de obra, en este caso, audiovisual.
     * 
     * @return TipoDeObra.AUDIOVISUAL
     */
    public TipoDeObra getTipoObra() {
        return TipoDeObra.AUDIOVISUAL;
    }

    /**
     * Método getter del atributo idioma.
     * 
     * @return idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Método para cargar una obra audiovisual a partir de un arreglo de campos
     * leídos de un archivo.
     * 
     * @param campos arreglo de campos leídos del archivo
     * @return obra audiovisual
     */
    public static Audiovisual cargarAudiovisualDeFichero(String... campos) {
        if (!campos[0].equals("AUDIOVISUAL"))
            return null;

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

        String idioma = campos[12];

        Pattern patron = Pattern.compile("(\\d{2})h(\\d{2})m(\\d{2})s");

        // Coincidencia del patrón en el String
        Matcher matcher = patron.matcher(campos[11]);

        LocalTime duracion = null;
        if (matcher.matches()) {
            // Extraer los valores de horas, minutos y segundos
            int horas = Integer.parseInt(matcher.group(1));
            int minutos = Integer.parseInt(matcher.group(2));
            int segundos = Integer.parseInt(matcher.group(3));

            // Crear un objeto LocalTime con los valores extraídos
            duracion = LocalTime.of(horas, minutos, segundos);
        }

        double ancho = 0;

        double alto = 0;

        double largo = 0;

        Dimensiones dim = new Dimensiones(ancho, alto, largo);

        return new Audiovisual(titulo, autor, anio, propia, poliza, cuantia_seguro, descr, duracion, idioma, dim);

    }

    @Override
    /**
     * Método que indica si la obra es audiovisual.
     * 
     * @return false, ya que este método se sobrescribe en las subclases para
     *         devolver el valor adecuado
     */
    public boolean isAudiovisual() {
        return true;
    }

    @Override
    /**
     * metodo abstracto para decidir si una obra cabe en una sala
     * 
     * @param sala sala a comprobar
     */
    public boolean cabeEnSala(Sala s) {
        return s.getTomasCorriente() > 0;
    }

}
