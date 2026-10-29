package Interfaz.Paneles.Gestor.Obras;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Obra.Obra;
import Sistema.Sistema;

import java.util.List;
import java.awt.*;

/**
 * Panel con la lista de obras del gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 * 
 */
public class PanelListaObrasGestor extends JPanel{
    JScrollPane scroll;
    JTable tabla;
    public PanelListaObrasGestor(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro
        tabla = TablaObrasGestor.create();
        scroll = new JScrollPane(tabla);

        // Añadir el scroll con la tabla al panel donde se vaya a mostrar
        this.add(scroll, BorderLayout.CENTER);
    }
 
    /**
     * Getter de la tabla de obras 
     * 
     * @return Jtable con la tabla
     * 
     */
    public JTable getTabla(){
        return tabla;
    }

    /**
     * Actualizador del panel de lista de obras
     * 
     */
    public void update(){
        List<Obra> obras = Sistema.getInstance().getObras();

        TablaObrasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(), obras);

        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previst
        
        

        
    }

}
