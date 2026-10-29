package Interfaz.Paneles.Cliente.Sorteos;

import javax.swing.*;

import Sistema.Sistema;
import Sorteo.Sorteo;

import java.util.List;
import java.awt.*;

/**
 * Panel de la lista de sorteos del clienet 
 */
public class PanelListaSorteosCliente extends JPanel{
    JScrollPane scroll;
    JTable tabla;

    /**
     * Constructor del panel de la lista de sorteos del cliente
     */
    public PanelListaSorteosCliente(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro
        
        update();
    }

    /**
     * Getter de la tabla
     * 
     * @return tabla tabla
     */
    public JTable getTabla(){
        return tabla;
    }

    /**
     * Método para actualizar el panel
     * 
     * @return tabla tabla
     */
    public JTable update(){
        if(scroll != null){
            this.remove(scroll);
        }
        List<Sorteo> sorteos = Sistema.getInstance().getSorteos();
        tabla = TablaSorteosCliente.create(sorteos);
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto
        
        scroll = new JScrollPane(tabla);

        // Añadir el scroll con la tabla al panel donde se vaya a mostrar
        this.add(scroll, BorderLayout.CENTER);

        return tabla;
    }

}
