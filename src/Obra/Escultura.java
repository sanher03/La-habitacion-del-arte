package Obra;

import Exposicion.TipoDeObra;
import Utils.*;

/**
 * Clase que representa una escultura.
 * Extiende de la clase ObraClimatizada ya que puede requerir condiciones específicas de temperatura y humedad.
 * 
 * @author Ignacio Sánchez
 */
public class Escultura extends ObraClimatizada {

    private String material;

    /**
     * Constructor de Escultura.
     * @param nombre Nombre de la escultura.
     * @param autor Autor de la escultura.
     * @param anio Año de creación de la escultura.
     * @param propia Indica si la escultura pertenece al centro o es externa.
     * @param poliza Número de póliza de seguro.
     * @param cuantia_seguro Cuantía del seguro.
     * @param descr Descripción de la escultura.
     * @param material Material utilizado en la escultura.
     * @param ancho Ancho de la escultura.
     * @param largo Largo de la escultura.
     * @param alto Alto de la escultura.
     * @param tempMin Temperatura mínima requerida para conservación.
     * @param tempMax Temperatura máxima requerida para conservación.
     * @param humMin Humedad mínima requerida para conservación.
     * @param humMax Humedad máxima requerida para conservación.
     */
    public Escultura(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro, String descr, String material, double ancho,
            double largo, double alto, double tempMin, double tempMax, double humMin, double humMax) {
        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, ancho, largo, alto, tempMin, tempMax, humMin, humMax);

        this.material = material;

    }

    /**
     * Constructor de Escultura.
     * @param nombre Nombre de la escultura.
     * @param autor Autor de la escultura.
     * @param anio Año de creación de la escultura.
     * @param propia Indica si la escultura pertenece al centro o es externa.
     * @param poliza Número de póliza de seguro.
     * @param cuantia_seguro Cuantía del seguro.
     * @param descr Descripción de la escultura.
     * @param material Material utilizado en la escultura.
     * @param dim Dimensiones de la escultura.
     * @param temp Temperatura requerida para conservación.
     * @param hum Humedad requerida para conservación.
     */
    public Escultura(String nombre, String autor, int anio, boolean propia, String poliza, double cuantia_seguro, String descr, String material,
            Dimensiones dim, Temperatura temp, Humedad hum) {
        super(nombre, autor, anio, propia, poliza, cuantia_seguro, descr, dim, temp, hum);

        this.material = material;

    }

    /**
     * Obtiene el material de la escultura.
     * @return Material de la escultura.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Establece el material de la escultura.
     * @param material Material de la escultura.
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Devuelve el tipo de obra, que en este caso es una escultura.
     * @return Tipo de obra.
     */
    public TipoDeObra getTipoObra() {
        return TipoDeObra.ESCULTURA;
    }

    /**
     * Carga una escultura a partir de los datos leídos desde un archivo.
     * @param campos Parámetros leídos desde el archivo.
     * @return Escultura cargada.
     */
    public static Escultura cargarEsculturaDeFichero(String ... campos) {
        if (!campos[0].equals("ESCULTURA"))
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

        String material = campos[9];

        double ancho = Double.parseDouble(campos[13]);

        double alto = Double.parseDouble(campos[14]);

        double largo = Double.parseDouble(campos[15]);

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

        return new Escultura(titulo, autor, anio, propia, poliza, cuantia_seguro, descr, material, dim, temp, humedad);

    }
}
