package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;

import Interfaz.Controladores.ControladorModificarSalaGestor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel nueva sala
 */
public class PanelNuevaSala extends JPanel{
    
    SpringLayout layout;
    PanelDerechoAniadirSala panel_derecho;
    JButton boton;
    JPanel panelSuperior, panelInferior, panel;
    JSplitPane splitPane, splitPane2;
    JComboBox<String> tipoSala;
    ControladorModificarSalaGestor c;

    /**
     * Constructor del panel
     * 
     * @param c controlador modificarSalaGestor
     */
    public PanelNuevaSala(ControladorModificarSalaGestor c){
        super();

        this.c = c;

        
        this.setLayout(new BorderLayout());

        panel_derecho = new PanelDerechoAniadirSala("Añadir sala", c);

        boton = new JButton("Añadir");
        boton.setPreferredSize(new Dimension(200, 50));
        

        String[] opciones = {"Seleccionar tipo de sala", "Climatizada", "No climatizada"};

        tipoSala = new JComboBox<>(opciones);

        tipoSala.setSelectedIndex(0);

        setControladorTipoSala();

        panelSuperior = new JPanel(new SpringLayout());
        SpringLayout layoutSup = (SpringLayout)panelSuperior.getLayout();

        layoutSup.putConstraint(SpringLayout.HORIZONTAL_CENTER, tipoSala, 0, SpringLayout.HORIZONTAL_CENTER, panelSuperior);
        layoutSup.putConstraint(SpringLayout.NORTH, tipoSala, 30, SpringLayout.NORTH, panelSuperior);

        panelSuperior.add(tipoSala);

        panelInferior = new JPanel(new BorderLayout());

        splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelSuperior, panelInferior);
        splitPane2.setDividerLocation(100); // Ancho inicial del panel superior
        splitPane2.setDividerSize(5); // Tamaño del delimitador
        splitPane2.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario
       
        
        this.add(splitPane2, BorderLayout.CENTER);

        
    }

    /**
     * Método para actualizar el panel
     * 
     * @param climatizada boolean que indica si la obra está climatizada o no
     */
    public void update(boolean climatizada){
        if(splitPane!= null){
            panelInferior.remove(splitPane);
        }
        panel_derecho.update(climatizada);

        c.setClimatizada(climatizada);
        

        panel = new JPanel();
        layout = new SpringLayout();
        

        layout.putConstraint(SpringLayout.EAST, boton, -30, SpringLayout.EAST,panel);
        layout.putConstraint(SpringLayout.NORTH, boton, 30, SpringLayout.NORTH,panel);
        panel.add(boton);

        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_derecho, panel);
        splitPane.setDividerLocation(500); // Ancho inicial del panel superior
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario
        
        
        panelInferior.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Setter del controlador añadir
     * 
     * @param c actionListener
     */
    public void setControladorAñadir(ActionListener c){
        boton.addActionListener(c);
    }

    /**
     * Getter del panel derecho
     * 
     * @return panelderecho
     */
    public PanelDerechoAniadirSala getPanel_derecho() {
        return panel_derecho;
    }

    /**
     * Getter del botón
     * 
     * @return botón
     */
    public JButton getBoton() {
        return boton;
    }

    /**
     * Setter del controlador tipo sala
     */
    public void setControladorTipoSala() {
        tipoSala.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (((String) tipoSala.getSelectedItem()).equals("Climatizada")){
                    update(true);
                    revalidate();
                } else if (((String) tipoSala.getSelectedItem()).equals("No climatizada")){
                    update(false);
                    revalidate();
                }
            }
            
        });
    }
}
