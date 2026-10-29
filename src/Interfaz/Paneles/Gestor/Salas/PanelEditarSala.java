package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;

import Sala.Sala;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para editar sala
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEditarSala extends JPanel {
    SpringLayout layout;
    JLabel aforo, tomas_corriente, tem_min, tem_max, hum_min, hum_max;
    JTextField text_aforo, text_corriente, text_t1, text_t2, text_h1, text_h2;
    JButton boton;
    JPanel panel;

    /**
     * Constructor del panel
     */
    public PanelEditarSala(){
        super();

        
        this.setLayout(new BorderLayout());
        aforo = new JLabel("Aforo:");
        tomas_corriente = new JLabel("Num Tomas de Corriente:");
        tem_min = new JLabel("Temperatura min:");
        tem_max = new JLabel("Temperatura max:");
        hum_min = new JLabel("Humedad min:");
        hum_max = new JLabel("Humedad max:");

        boton = new JButton("Guardar cambios");
        boton.setPreferredSize(new Dimension(200, 50));

        panel = new JPanel();
        layout = new SpringLayout();
        panel.setLayout(layout);

        this.add(panel, BorderLayout.CENTER);
        
        
    }

    /**
     * Método para actualizar el panel
     * 
     * @param sala sala
     */
    public void update(Sala sala){

        if(text_aforo!= null){
            panel.removeAll();
        }


        text_aforo = new JTextField(String.valueOf(sala.getAforo()));
        text_aforo.setPreferredSize(new Dimension(50, 20));

        text_corriente = new JTextField(String.valueOf(sala.getTomasCorriente()));
        text_corriente.setPreferredSize(new Dimension(50, 20));

        layout.putConstraint(SpringLayout.WEST, aforo, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, aforo, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.EAST, tomas_corriente, -80, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, tomas_corriente, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_aforo, 0, SpringLayout.HORIZONTAL_CENTER, aforo);
        layout.putConstraint(SpringLayout.NORTH, text_aforo, 10, SpringLayout.SOUTH, aforo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_corriente, 0, SpringLayout.HORIZONTAL_CENTER, tomas_corriente);
        layout.putConstraint(SpringLayout.NORTH, text_corriente, 10, SpringLayout.SOUTH, tomas_corriente);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton, 0, SpringLayout.HORIZONTAL_CENTER,panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, boton, -200, SpringLayout.VERTICAL_CENTER,panel);


        if(sala.isClimatizada()){

            text_t1 = new JTextField(String.valueOf(sala.getTemperatura().getMin()));
            text_t1.setPreferredSize(new Dimension(100, 20));
            text_t2 = new JTextField(String.valueOf(sala.getTemperatura().getMax()));
            text_t2.setPreferredSize(new Dimension(100, 20));
            text_h1 = new JTextField(String.valueOf(sala.getHumedad().getMin()));
            text_h1.setPreferredSize(new Dimension(100, 20));
            text_h2 = new JTextField(String.valueOf(sala.getHumedad().getMax()));
            text_h2.setPreferredSize(new Dimension(100, 20));

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tem_min, 0, SpringLayout.HORIZONTAL_CENTER, aforo);
            layout.putConstraint(SpringLayout.NORTH, tem_min, 30, SpringLayout.SOUTH, text_aforo);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tem_max, 0, SpringLayout.HORIZONTAL_CENTER, tomas_corriente);
            layout.putConstraint(SpringLayout.NORTH, tem_max, 30, SpringLayout.SOUTH, text_corriente);
 
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_t1, 0, SpringLayout.HORIZONTAL_CENTER, tem_min);
            layout.putConstraint(SpringLayout.NORTH, text_t1, 10, SpringLayout.SOUTH, tem_min);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_t2, 0, SpringLayout.HORIZONTAL_CENTER, tem_max);
            layout.putConstraint(SpringLayout.NORTH, text_t2, 10, SpringLayout.SOUTH, tem_max);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hum_min, 0, SpringLayout.HORIZONTAL_CENTER, tem_min);
            layout.putConstraint(SpringLayout.NORTH, hum_min, 30, SpringLayout.SOUTH, text_t1);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hum_max, 0, SpringLayout.HORIZONTAL_CENTER, tem_max);
            layout.putConstraint(SpringLayout.NORTH, hum_max, 30, SpringLayout.SOUTH, text_t2);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_h1, 0, SpringLayout.HORIZONTAL_CENTER, hum_min);
            layout.putConstraint(SpringLayout.NORTH, text_h1, 10, SpringLayout.SOUTH, hum_min);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_h2, 0, SpringLayout.HORIZONTAL_CENTER,hum_max);
            layout.putConstraint(SpringLayout.NORTH, text_h2, 10, SpringLayout.SOUTH, hum_max);

            panel.add(tem_min);
            panel.add(tem_max);
            panel.add(hum_min);
            panel.add(hum_max);
            panel.add(text_t1);
            panel.add(text_t2);
            panel.add(text_h1);
            panel.add(text_h2);
        }

        panel.add(aforo);
        panel.add(tomas_corriente);
        panel.add(text_aforo);
        panel.add(text_corriente);

        
        panel.add(boton);
        
    }

    /**
     * Setter del controlador editar
     * 
     * @param c actionListener
     */
    public void setControladorEditar(ActionListener c){
        boton.addActionListener(c);
    }


    /**
     * Getter de texto aforo
     * 
     * @return texto aforo
     */
    public String getText_aforo() {
        return text_aforo.getText();
    }

    /**
     * Getter de texto corriente
     * 
     * @return texto corriente
     */
    public String getText_corriente() {
        return text_corriente.getText();
    }

    /**
     * Getter de texto t1
     * 
     * @return texto t1
     */
    public String getText_t1() {
        return text_t1.getText();
    }

    /**
     * Getter de texto t2
     * 
     * @return texto t2
     */
    public String getText_t2() {
        return text_t2.getText();
    }

    /**
     * Getter de texto h1
     * 
     * @return texto h1
     */
    public String getText_h1() {
        return text_h1.getText();
    }

    /**
     * Getter de texto h2
     * 
     * @return texto h2
     */
    public String getText_h2() {
        return text_h2.getText();
    }

    /**
     * Getter de botón
     * 
     * @return botón
     */
    public JButton getBoton() {
        return boton;
    }

    
}
