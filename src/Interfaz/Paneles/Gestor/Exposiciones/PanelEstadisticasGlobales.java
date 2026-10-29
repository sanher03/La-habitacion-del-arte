package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel de estadísitcas globales
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEstadisticasGlobales extends JPanel{
    PanelTitulo titulo;
    JButton atras;
    JPanel panelInferior;
    JSplitPane completo;
    SpringLayout layout;
    JLabel label, fecha1, fecha2;
    JDatePickerImpl inicio, fin;
    JButton comprobar;

    /**
     * Constructor del panel
     */
    public PanelEstadisticasGlobales() {
        super(new BorderLayout());

        titulo = new PanelTitulo("Estadísticas globales", "Atrás");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        label = new JLabel("Seleccione dos fechas si desea comprobar un intervalo:");
        fecha1 = new JLabel("Inicio:");
        fecha2 = new JLabel("Fin:");

        comprobar = new SuperButton("Comprobar estadísticas", 250);
        

        this.add(completo, BorderLayout.CENTER);

    }

    /**
     * Función para actualizar el panel
     */
    public void update(){
        if(inicio!=null){
            panelInferior.removeAll();
        }
        inicio = DatePickerPanel.create();

        fin = DatePickerPanel.create();


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, -200, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2, 200, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inicio, -200, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fin, 200, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, comprobar, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, label, 280, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.NORTH, fecha1, 50, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.NORTH, fecha2, 50, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.NORTH, inicio, 50, SpringLayout.SOUTH, fecha1);
        layout.putConstraint(SpringLayout.NORTH, fin, 50, SpringLayout.SOUTH, fecha2);
        layout.putConstraint(SpringLayout.NORTH, comprobar, 50, SpringLayout.SOUTH, fin);

        panelInferior.add(label);
        panelInferior.add(fecha1);
        panelInferior.add(fecha2);
        panelInferior.add(inicio);
        panelInferior.add(fin);
        panelInferior.add(comprobar);
    }

    /**
     * Getter del título
     * @return título
     */
    public PanelTitulo getTitulo() {
        return titulo;
    }

    /**
     * Setter del controlador comprobar
     * @param c actionListener
     */
    public void setControladorComprobar(ActionListener c){
        comprobar.addActionListener(c);
    }

    /**
     * Getter del inicio
     * @return inicio
     */
    public JDatePickerImpl getInicio() {
        return inicio;
    }

    /**
     * Getter del fin
     * @return fin
     */
    public JDatePickerImpl getFin() {
        return fin;
    }

    
}
