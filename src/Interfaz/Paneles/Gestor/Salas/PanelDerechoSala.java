package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;
import java.awt.*;
import Interfaz.InterfazUtils.FormatUtils;

/**
 * Panel derecho de sala
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelDerechoSala extends JPanel{
    SpringLayout layout;
    JLabel sala_nombre, ancho, aforo, tomas_corriente, tem_min, tem_max, hum_min, hum_max, titulo;
    JTextField text_nombre, text_ancho, text_aforo, text_corriente, text_t1, text_t2, text_h1, text_h2;

    /**
     * Constructor del panel derecho de sala
     * 
     * @param nombre nombre
     */
    public PanelDerechoSala(String nombre){

        layout = new SpringLayout();
        this.setLayout(layout);

        titulo = new JLabel(nombre);
        FormatUtils.formatBoldAndItalicComponent(titulo, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        sala_nombre = new JLabel("Nombre:");
        ancho = new JLabel("Ancho:");
        aforo = new JLabel("Aforo:");
        tomas_corriente = new JLabel("Num Tomas de Corriente:");
        tem_min = new JLabel("Temperatura min:");
        tem_max = new JLabel("Temperatura max:");
        hum_min = new JLabel("Humedad min:");
        hum_max = new JLabel("Humedad max:");

        
    }

    /**
     * Método para actualizar el panel
     * 
     * @param climatizada boolean de si la sala está climatizada o no
     */
    public void update(boolean climatizada){
        if(text_nombre != null){
            this.removeAll();
        }
        text_nombre = new JTextField();
        text_nombre.setPreferredSize(new Dimension(150, 20));

        text_ancho = new JTextField();
        text_ancho.setPreferredSize(new Dimension(50,20));

        text_aforo = new JTextField();
        text_aforo.setPreferredSize(new Dimension(50, 20));

        text_corriente = new JTextField();
        text_corriente.setPreferredSize(new Dimension(50, 20));

        layout.putConstraint(SpringLayout.WEST, titulo, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, titulo, 20, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, sala_nombre, 100, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, sala_nombre, 60, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_nombre, 0, SpringLayout.HORIZONTAL_CENTER, sala_nombre);
        layout.putConstraint(SpringLayout.NORTH, text_nombre, 10, SpringLayout.SOUTH, sala_nombre);

        layout.putConstraint(SpringLayout.EAST, ancho, -80, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, ancho, 60, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_ancho, 0, SpringLayout.HORIZONTAL_CENTER, ancho);
        layout.putConstraint(SpringLayout.NORTH, text_ancho, 10, SpringLayout.SOUTH, ancho);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, aforo, 0, SpringLayout.HORIZONTAL_CENTER, sala_nombre);
        layout.putConstraint(SpringLayout.NORTH, aforo, 30, SpringLayout.SOUTH, text_nombre);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tomas_corriente, 0, SpringLayout.HORIZONTAL_CENTER, ancho);
        layout.putConstraint(SpringLayout.NORTH, tomas_corriente, 30, SpringLayout.SOUTH, text_ancho);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_aforo, 0, SpringLayout.HORIZONTAL_CENTER, aforo);
        layout.putConstraint(SpringLayout.NORTH, text_aforo, 10, SpringLayout.SOUTH, aforo);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text_corriente, 0, SpringLayout.HORIZONTAL_CENTER, tomas_corriente);
        layout.putConstraint(SpringLayout.NORTH, text_corriente, 10, SpringLayout.SOUTH, tomas_corriente);
        

        if(climatizada){

            text_t1 = new JTextField();
            text_t1.setPreferredSize(new Dimension(100, 20));
            text_t2 = new JTextField();
            text_t2.setPreferredSize(new Dimension(100, 20));
            text_h1 = new JTextField();
            text_h1.setPreferredSize(new Dimension(100, 20));
            text_h2 = new JTextField();
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

            this.add(tem_min);
            this.add(tem_max);
            this.add(hum_min);
            this.add(hum_max);
            this.add(text_t1);
            this.add(text_t2);
            this.add(text_h1);
            this.add(text_h2);
        }

        
        this.add(titulo);
        this.add(sala_nombre);
        this.add(text_nombre);
        this.add(ancho);
        this.add(text_ancho);
        this.add(aforo);
        this.add(tomas_corriente);
        this.add(text_aforo);
        this.add(text_corriente);
    }


    /**
     * Getter de texto ancho
     * 
     * @return texto ancho
     */
    public String getText_ancho(){
        return text_ancho.getText();
    }
    

    /**
     * Getter de texto nombre
     * 
     * @return texto nombre
     */
    public String getText_nombre() {
        return text_nombre.getText();
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
}
