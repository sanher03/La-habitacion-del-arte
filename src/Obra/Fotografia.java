package Obra;

import Exposicion.TipoDeObra;
import Utils.*;

/**
 * Clase que representa una fotografía.
 * Extiende de la clase ObraClimatizada ya que puede requerir condiciones específicas de temperatura y humedad.
 * 
 * @author Ignacio Sanchez
 */
public class Fotografia extends ObraClimatizada {
    private TipoFotografia tipo;

    /**
     * Constructor de Fotografia.
     * @param nombre Nombre de la fotografía.
     * @param autor Autor de la fotografía.
     * @param anio Año de creación de la fotografía.
     * @param propia Indica si la fotografía pertenece al centro o es externa.
     * @param poliza Número de póliza de seguro.
     * @param cuantia_seguro Cuantía del seguro.
     * @param descr Descripción de la fotografía.
     * @param tipo Tipo de la fotografía (color o blanco y negro).
     * @param ancho Ancho de la fotografía.
     * @param largo Largo de la fotografía.
     * @param alto Alto de la fotografía.
     * @param tempMin Temperatura mínima requerida para conservación.
     * @param tempMax Temperatura máxima requerida para conservación.
     * @param humMin Humedad mínima requerida para conservación.
     * @param humMax Humedad máxima requerida para conservación.
     */
    public Fotografia(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro, String descr, TipoFotografia tipo,
            double ancho, double largo, double alto, double tempMin, double tempMax, double humMin, double humMax) {
        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, ancho, largo, alto, tempMin, tempMax, humMin, humMax);

        this.tipo = tipo;
    }

    /**
     * Constructor de Fotografia.
     * @param nombre Nombre de la fotografía.
     * @param autor Autor de la fotografía.
     * @param anio Año de creación de la fotografía.
     * @param propia Indica si la fotografía pertenece al centro o es externa.
     * @param poliza Número de póliza de seguro.
     * @param cuantia_seguro Cuantía del seguro.
     * @param descr Descripción de la fotografía.
     * @param tipo Tipo de la fotografía (color o blanco y negro).
     * @param dim Dimensiones de la fotografía.
     * @param temp Temperatura requerida para conservación.
     * @param hum Humedad requerida para conservación.
     */
    public Fotografia(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro, String descr, TipoFotografia tipo,
            Dimensiones dim, Temperatura temp, Humedad hum) {
        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, dim, temp, hum);

        this.tipo = tipo;
    }

    /**
     * Obtiene el tipo de la fotografía.
     * @return Tipo de la fotografía.
     */
    public TipoFotografia getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la fotografía.
     * @param tipo Tipo de la fotografía.
     */
    public void setTipo(TipoFotografia tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el tipo de obra, que en este caso es una fotografía.
     * @return Tipo de obra.
     */
    public TipoDeObra getTipoObra() {
        return TipoDeObra.FOTOGRAFIA;
    }

    /**
     * Carga una fotografía a partir de los datos leídos desde un archivo.
     * @param campos Parámetros leídos desde el archivo.
     * @return Fotografía cargada.
     */
    public static Fotografia cargarFotografiaDeFichero(String ... campos) {
        if (!campos[0].equals("FOTOGRAFIA"))
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

        TipoFotografia tipo = TipoFotografia.COLOR;
        if (campos[10].equals("Blanco y negro")) {
            tipo = TipoFotografia.BYN;
        }

        double ancho = Double.parseDouble(campos[13]);

        double alto = Double.parseDouble(campos[14]);

        double largo = 0;

        Dimensiones dim = new Dimensiones(ancho, alto, largo);

        Temperatura temp = null;
        if (!campos[16].equals("")) {
            String[] rango_temp = campos[16].split("--");

            double temp_min = Double.parseDouble(rango_temp[0]);

            double temp_max = Double.parseDouble(rango_temp[1]);

            temp = new Temperatura(temp_min, temp_max);
        } 

        Humedad humedad = null;
        if (!campos[17].equals("")) {
            String[] rango_humedad = campos[17].split("--");

            double humedad_min = Double.parseDouble(rango_humedad[0]);

            double humedad_max = Double.parseDouble(rango_humedad[1]);

            humedad = new Humedad(humedad_min, humedad_max);
        } 

        return new Fotografia(titulo, autor, anio, propia, poliza, cuantia_seguro, descr, tipo, dim, temp, humedad);

    }
}
