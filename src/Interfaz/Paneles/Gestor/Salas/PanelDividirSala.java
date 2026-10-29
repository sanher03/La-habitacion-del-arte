package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;

import Sala.Sala;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel de dividir sala
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelDividirSala extends JPanel{
    SpringLayout layout;
    PanelDerechoSala panel_derecho1, panel_derecho2;
    JButton boton;
    JPanel panel;
    JSplitPane splitPane;
    public PanelDividirSala(){
        super();

        
        this.setLayout(new BorderLayout());

        panel_derecho1 = new PanelDerechoSala("Subsala 1");

        panel_derecho2 = new PanelDerechoSala("Subsala 2");
        

        boton = new JButton("Guardar cambios");
        boton.setPreferredSize(new Dimension(200, 50));

        
    }

    /**
     * Método para actualizar el panel
     * 
     * @param sala sala
     */
    public void update(Sala sala){
        if(splitPane!= null){
            this.remove(splitPane);
        }
        panel_derecho1.update(sala.isClimatizada());
        panel_derecho2.update(sala.isClimatizada());
        

        panel = new JPanel();
        layout = new SpringLayout();
        

        layout.putConstraint(SpringLayout.EAST, boton, -30, SpringLayout.EAST,panel);
        layout.putConstraint(SpringLayout.NORTH, boton, 30, SpringLayout.NORTH,panel);
        panel.add(boton);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_derecho1, panel_derecho2);
        splitPane1.setDividerLocation(400); // Ancho inicial del panel superior
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane1, panel);
        splitPane.setDividerLocation(800); // Ancho inicial del panel superior
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario
       
        
        this.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Setter del controlador dividir
     * 
     * @param c actionListener
     */
    public void setControladorDividir(ActionListener c){
        boton.addActionListener(c);
    }

    /**
     * Getter del panel derecho de sala 1
     * 
     * @return panelderecho1
     */
    public PanelDerechoSala getPanel_derecho1() {
        return panel_derecho1;
    }

    /**
     * Getter del panel derecho de sala 2
     * 
     * @return panelderecho2
     */
    public PanelDerechoSala getPanel_derecho2() {
        return panel_derecho2;
    }

    /**
     * Getter del botón
     * 
     * @return botón botón
     */
    public JButton getBoton() {
        return boton;
    }

}
