package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Exposicion.*;
import Sistema.Sistema;

import java.util.List;
import java.awt.*;

/**
 * Panel lista de exposiciones de gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelListaExpoGestor extends JPanel{
    JScrollPane scroll;
    JTable tabla;
   
    /**
     * Constructor del panel
     */
    public PanelListaExpoGestor(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro
        if(scroll != null){
            this.remove(scroll);
        }
        
        tabla = TablaExposicionesGestor.create();
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto

        System.out.println(tabla);
        
        scroll = new JScrollPane(tabla);

        // Añadir el scroll con la tabla al panel donde se vaya a mostrar
        this.add(scroll, BorderLayout.CENTER);
        
        
        update();
    }

    /**
     * Getter de la tabla
     * @return tabla
     */
    public JTable getTabla(){
        return tabla;
    }

    /**
     * Método para actualizar el panel
     */
    public void update(){
        
        List<Exposicion> exposiciones = Sistema.getInstance().getExposiciones();
        TablaExposicionesGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), exposiciones);;

    }

}
