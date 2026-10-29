package Interfaz.Paneles.Gestor.Obras;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import Interfaz.Controladores.ControladorObrasCentral;
/**
 * Panel de obras central del gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelObrasGestorCentral extends JPanel {
    PanelLateralGestorObras panel_lateral;
    PanelListaObrasGestor lista_obras;
    PanelObra panel_derecho;
    JPanel panel_vacio;
    PanelDetallesObra ver_detalles;
    PanelNuevaObra panel_añadir;

    public PanelObrasGestorCentral() {
        super();

        this.setLayout(new BorderLayout());

        panel_lateral = new PanelLateralGestorObras();

        lista_obras = new PanelListaObrasGestor();

        panel_derecho = new PanelObra(lista_obras.getTabla());

        panel_lateral.setControladorDetallesObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelDetallesObra(panel_derecho, panel_derecho.getDetallesObra(), lista_obras.getTabla());
                
            }
            
        });

        panel_lateral.setControladorNuevaObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelNuevaObra(panel_derecho);
                
            }
            
        });

        panel_lateral.setControladorExponerObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelExponerObra(panel_derecho, panel_derecho.getExponerObra(), lista_obras.getTabla());
                
            }
            
        });

        panel_lateral.setControladorAlmacenarObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelAlmacenarObra(panel_derecho, panel_derecho.getAlmacenarObra(), lista_obras.getTabla());
                
            }
            
        });

        panel_lateral.setControladorRetirarObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelRetirarObra(panel_derecho, panel_derecho.getRetirarObra(), lista_obras.getTabla());
                
            }
            
        });

        panel_lateral.setControladorRestaurarObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelRestaurarObra(panel_derecho, panel_derecho.getRestaurarObra(), lista_obras.getTabla());
                
            }
            
        });

        panel_lateral.setControladorPrestarObra(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.mostrarPanelPrestarObra(panel_derecho, panel_derecho.getPrestarObra(), lista_obras.getTabla());
                
            }
            
        });

    
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_lateral, lista_obras);
        splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panel_derecho);
        splitPane2.setDividerLocation(1200); // Ancho inicial del panel izquierdo
        splitPane2.setDividerSize(5); // Tamaño del delimitador
        splitPane2.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane2, BorderLayout.CENTER); //
    }

    public void update(){
      lista_obras.update();  
    }
}
