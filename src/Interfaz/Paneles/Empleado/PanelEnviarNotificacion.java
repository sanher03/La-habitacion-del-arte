package Interfaz.Paneles.Empleado;

import Interfaz.Paneles.Common.PanelTitulo;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel para enviar notificaciones
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEnviarNotificacion extends JPanel{
    JPanel panelNotificacion;
    JLabel text;
    JTextArea cuerpo;
    JSplitPane splitPane;
    JButton boton;
    JPanel titulo;
    SpringLayout layout;
    public PanelEnviarNotificacion(){
        super();
        this.setLayout(new BorderLayout());

        layout = new SpringLayout();
        panelNotificacion = new JPanel(layout);
        
        text = new JLabel("Cuerpo del mensaje:");
    

        boton = new JButton("Enviar mensaje");  
        
        titulo = new PanelTitulo("Enviar mensaje");
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelNotificacion);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        this.add(splitPane, BorderLayout.CENTER);

        
    }

    /**
     * Método para actualizar el panel
     */
    public void update(){
        if(cuerpo != null){
            panelNotificacion.removeAll();
        }
        cuerpo = new JTextArea(15,50);
        cuerpo.setBorder(new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

        layout.putConstraint(SpringLayout.WEST, text, 20, SpringLayout.WEST, panelNotificacion);
        layout.putConstraint(SpringLayout.NORTH, text, 40, SpringLayout.NORTH, panelNotificacion);
        
        layout.putConstraint(SpringLayout.WEST, cuerpo, 20, SpringLayout.WEST, panelNotificacion);
        layout.putConstraint(SpringLayout.NORTH, cuerpo, 60, SpringLayout.NORTH, panelNotificacion);

        layout.putConstraint(SpringLayout.WEST, boton, 200, SpringLayout.WEST, panelNotificacion);
        layout.putConstraint(SpringLayout.NORTH, boton, 20, SpringLayout.SOUTH, cuerpo);

        panelNotificacion.add(text);
        panelNotificacion.add(cuerpo);
        panelNotificacion.add(boton);

        
    }

    /**
     * Getter del atributo cuerpo
     * 
     * @return cuerpo
     */
    public String getCuerpo() {
        return cuerpo.getText();
    }

    /**
     * Setter del controlador enviar
     * @param c actionListener
     */
    public void setControladorEnviar(ActionListener c){
        boton.addActionListener(c);
    }

    
}
