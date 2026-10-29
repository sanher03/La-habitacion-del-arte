package Sorteo;

import java.io.Serializable;

import Usuario.ClienteRegistrado;

/**
 * Clase que representa un participante en un sorteo.
 * Implementa la interfaz Serializable para permitir la serialización de objetos.
 * 
 * @author Ignacio Sánchez
 */
public class Participante implements Serializable {

    private int numEntradas; // Número de entradas del participante
    private ClienteRegistrado cliente; // Cliente registrado que participa en el sorteo

    /**
     * Constructor de la clase Participante.
     * @param numEntradas Número de entradas que tiene el participante (1 o 2).
     * @param cliente Cliente registrado que participa en el sorteo.
     * @throws IllegalArgumentException Si el número de entradas no es 1 ni 2.
     */
    public Participante(int numEntradas, ClienteRegistrado cliente) throws IllegalArgumentException {
        if (numEntradas != 1 && numEntradas != 2) {
            throw new IllegalArgumentException("Solo se pueden pedir 1 o 2 entradas.");
        }
        this.numEntradas = numEntradas;
        this.cliente = cliente;
    }

    /**
     * Obtiene el cliente registrado que participa en el sorteo.
     * @return Cliente registrado que participa en el sorteo.
     */
    public ClienteRegistrado getCliente() {
        return cliente;
    }

    /**
     * Obtiene el número de entradas del participante.
     * @return Número de entradas del participante.
     */
    public int getNumEntradas() {
        return numEntradas;
    }

    /**
     * Establece el número de entradas del participante.
     * @param numEntradas Nuevo número de entradas del participante.
     */
    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

}
