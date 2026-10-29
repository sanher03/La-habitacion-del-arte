package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Sala.*;
import Sistema.Sistema;

import java.util.List;

import java.awt.*;

/**
 * Panel lista salas gestor 
 */
public class PanelListaSalasGestor extends JPanel{
    JScrollPane scroll;
    JTable tabla;

    /**
     * Constructor del panel
     */
    public PanelListaSalasGestor(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro

        if(scroll != null){
            this.remove(scroll);
        }
        
        tabla = TablaSalasGestor.create();
        
        scroll = new JScrollPane(tabla);

        // Añadir el scroll con la tabla al panel donde se vaya a mostrar
        this.add(scroll, BorderLayout.CENTER);
        
        //update();
    }

    /**
     * Getter de tabla
     * 
     * @return tabla
     */
    public JTable getTabla(){
        return tabla;
    }

    /**
     * Método para actualizar el panel
     */
    public void update(){
        List<Sala> salas = Sistema.getInstance().getSalasHojas();
        TablaSalasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(), salas);

    }
}
