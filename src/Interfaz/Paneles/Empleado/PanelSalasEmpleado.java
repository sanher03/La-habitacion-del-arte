package Interfaz.Paneles.Empleado;
import Interfaz.Controladores.ControladorModificarSala;
import Interfaz.Paneles.Common.TablaSalas;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Sistema.Sistema;
import Sala.*;
import java.util.List;

/**
 * Panel de salas de empleado
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSalasEmpleado extends JPanel{
    JScrollPane scroll;
    JTable tabla;
    PanelDerechoSalasEmpleado panelDerecho;
    JSplitPane splitPane;
    public PanelSalasEmpleado(){
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro

        TablaSalas.create();
        
        panelDerecho = new PanelDerechoSalasEmpleado();
        
        List<Sala> salas = Sistema.getInstance().getSalasClimatizadas();
        tabla = TablaSalas.create();

        TablaSalas.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), salas);
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto

        System.out.println(tabla);
        
        scroll = new JScrollPane(tabla);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, panelDerecho);
        splitPane.setDividerLocation(1400); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        // Añadir el scroll con la tabla al panel donde se vaya a mostrar
        this.add(splitPane, BorderLayout.CENTER);
        //update();
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
        List<Sala> salas = Sistema.getInstance().getSalasClimatizadas();
        TablaSalas.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), salas);
    }

    /**
     * Setter del controlador panel derecho
     * 
     * @param c controlador modificar sala
     */
    public void setControladorPanelDerecho(ControladorModificarSala c){
        panelDerecho.setControladorCambiosSala(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.guardarCambios();
            }  
        });
    }
    
    /**
     * Getter del panel derecho
     * 
     * @return panelDerecho
     */
    public PanelDerechoSalasEmpleado getPanelDerecho(){
        return panelDerecho;
    }
    
}
