package Interfaz.Paneles.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Sala.*;

/**
 * Panel derecho de la pestaña de salas de empleado
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class PanelDerechoSalasEmpleado extends JPanel{
    SpringLayout layout;
    JLabel tem_min, tem_max, hum_min, hum_max;
    JTextField textt1, textt2, texth1, texth2;
    JButton boton;
    JPanel panel;
    public PanelDerechoSalasEmpleado(){
        super();

        
        this.setLayout(new BorderLayout());
        tem_min = new JLabel("Temperatura min:");
        tem_max = new JLabel("Temperatura max:");
        hum_min = new JLabel("Humedad min:");
        hum_max = new JLabel("Humedad max:");

        boton = new JButton("Guardar cambios");
        boton.setPreferredSize(new Dimension(200, 50));

        
    }

    /**
     * Método para actualizar el panel
     * @param sala sala
     */
    public void update(Sala sala){

        if(panel!= null){
            this.remove(panel);
        }

        panel = new JPanel();
        layout = new SpringLayout();
        panel.setLayout(layout);
        
        textt1 = new JTextField(String.valueOf(sala.getTemperatura().getMin()));
        textt1.setPreferredSize(new Dimension(100, 20));
        textt2 = new JTextField(String.valueOf(sala.getTemperatura().getMax()));
        textt2.setPreferredSize(new Dimension(100, 20));
        texth1 = new JTextField(String.valueOf(sala.getHumedad().getMin()));
        texth1.setPreferredSize(new Dimension(100, 20));
        texth2 = new JTextField(String.valueOf(sala.getHumedad().getMax()));
        texth2.setPreferredSize(new Dimension(100, 20));

        

        layout.putConstraint(SpringLayout.WEST, tem_min, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tem_min, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.EAST, tem_max, -80, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, tem_max, 30, SpringLayout.NORTH, panel);
 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textt1, 0, SpringLayout.HORIZONTAL_CENTER, tem_min);
        layout.putConstraint(SpringLayout.NORTH, textt1, 10, SpringLayout.SOUTH, tem_min);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textt2, 0, SpringLayout.HORIZONTAL_CENTER, tem_max);
        layout.putConstraint(SpringLayout.NORTH, textt2, 10, SpringLayout.SOUTH, tem_max);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hum_min, 0, SpringLayout.HORIZONTAL_CENTER, tem_min);
        layout.putConstraint(SpringLayout.NORTH, hum_min, 30, SpringLayout.SOUTH, textt1);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hum_max, 0, SpringLayout.HORIZONTAL_CENTER, tem_max);
        layout.putConstraint(SpringLayout.NORTH, hum_max, 30, SpringLayout.SOUTH, textt2);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texth1, 0, SpringLayout.HORIZONTAL_CENTER, hum_min);
        layout.putConstraint(SpringLayout.NORTH, texth1, 10, SpringLayout.SOUTH, hum_min);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texth2, 0, SpringLayout.HORIZONTAL_CENTER,hum_max);
        layout.putConstraint(SpringLayout.NORTH, texth2, 10, SpringLayout.SOUTH, hum_max);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton, 0, SpringLayout.HORIZONTAL_CENTER,panel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, boton, -200, SpringLayout.VERTICAL_CENTER,panel);




        panel.add(tem_min);
        panel.add(tem_max);
        panel.add(hum_min);
        panel.add(hum_max);
        panel.add(textt1);
        panel.add(textt2);
        panel.add(texth1);
        panel.add(texth2);
        panel.add(boton);
        this.add(panel, BorderLayout.CENTER);

        
    }

    /**
     * Getter del min temp
     * 
     * @return min temp
     */
    public String getTextt1() {
        return textt1.getText();
    }

    /**
     * Getter del max temp
     * 
     * @return max temp
     */
    public String getTextt2() {
        return textt2.getText();
    }

    /**
     * Getter del min hum
     * 
     * @return min hum
     */
    public String getTexth1() {
        return texth1.getText();
    }

    /**
     * Getter del max hum 
     * @return max hum
     */
    public String getTexth2() {
        return texth2.getText();
    }

    /**
     * Setter del controlador del cambio de sala
     * @param c actionListener
     */
    public void setControladorCambiosSala(ActionListener c){
        boton.addActionListener(c);
    }
}
