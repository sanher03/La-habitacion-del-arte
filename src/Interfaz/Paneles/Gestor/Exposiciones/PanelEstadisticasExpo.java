package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.Exposicion;
import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Controladores.ControladorEstadisticasExpo;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel estadísticas expo
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEstadisticasExpo extends JPanel{
    PanelTitulo titulo;
    JPanel panelInferior;
    JSplitPane completo;
    JButton comprobar;
    ActionListener anterior;
    JDatePickerImpl inicio, fin;

    /**
     * Constructor del panel estadísticas expo
     */
    public PanelEstadisticasExpo(){
        super(new BorderLayout());

        titulo = new PanelTitulo("");

        SpringLayout layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        JLabel label = new JLabel("Seleccione dos fechas si desea comprobar un intervalo:");
        JLabel fecha1 = new JLabel("Inicio:");
        JLabel fecha2 = new JLabel("Fin:");
        inicio = DatePickerPanel.create();

        fin = DatePickerPanel.create();

        comprobar = new SuperButton("Comprobar estadísticas", 250);


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, -180, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2, 180, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inicio, -180, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fin, 180, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, comprobar, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, label, 230, SpringLayout.NORTH, panelInferior);
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

        this.add(completo, BorderLayout.CENTER);

    }

    /**
     * Función para modificar el panel
     * @param expo exposición
     */
    public void update(Exposicion expo) {
        titulo.getTitulo().setText(expo.getNombre());

        if (anterior != null) {
            comprobar.removeActionListener(anterior);
        }

        comprobar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorEstadisticasExpo.comprobarEstadisticas(expo, inicio, fin);
                
            }
            
        });
    }

    /**
     * Getter del título
     * @return título
     */
    public PanelTitulo getTitulo() {
        return titulo;
    }
    
}
