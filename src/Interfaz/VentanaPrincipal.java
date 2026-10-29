package Interfaz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Interfaz.Paneles.Cliente.PanelCliente;
import Interfaz.Paneles.Empleado.PanelEmpleado;
import Interfaz.Paneles.Gestor.PanelGestor;
import Interfaz.Paneles.Invitado.PanelInicio;
import Interfaz.Paneles.PanelSuperior.PanelSuperior;
import Sistema.Sistema;
import Interfaz.InterfazUtils.InterfazUtils;

/**
 * Ventana principal del programa
 */
public class VentanaPrincipal extends JFrame{
    private static VentanaPrincipal instance;
    private JPanel cartas;
    private JPanel panel_superior;
    private JPanel ventana;
    PanelInicio panelinicio;
    PanelCliente panelCliente;
    PanelEmpleado panelEmpleado;
    PanelGestor panelGestor;

    /**
     * Instanciación de la ventana principal
     */
    public static VentanaPrincipal getInstance() {
        if(instance == null){
                instance = new VentanaPrincipal();
            return instance;
        }
        return instance;
    }
    

    /**
     * Constructor de la ventana principal
     */
    private VentanaPrincipal(){
        super("Mi GUI");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Llama a tu función aquí
                Sistema.getInstance().guardarDatos();
            }
        });

        
        ventana = new JPanel(new BorderLayout());
        
        cartas = new JPanel(new CardLayout());
        panelinicio = new PanelInicio();
        cartas.add(panelinicio, InterfazUtils.INICIO);

        panelinicio.update();

        panelCliente = new PanelCliente();
        cartas.add(panelCliente, InterfazUtils.CLIENTE);

        panelEmpleado = new PanelEmpleado();
        cartas.add(panelEmpleado, InterfazUtils.EMPLEADO);

        panelGestor = new PanelGestor();
        cartas.add(panelGestor, InterfazUtils.GESTOR);

        panel_superior = new PanelSuperior(panelinicio);
        
        ((CardLayout)cartas.getLayout()).show(cartas, InterfazUtils.INICIO);
        ((CardLayout)panel_superior.getLayout()).show(panel_superior, InterfazUtils.PANEL_INICIO);

        cartas.setPreferredSize(new Dimension(InterfazUtils.DIM_X, InterfazUtils.DIM_Y));
        
        ventana.add(panel_superior, BorderLayout.NORTH);
        ventana.add(cartas, BorderLayout.CENTER);

        this.add(ventana);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(InterfazUtils.DIM_X, InterfazUtils.DIM_Y);
        
    }


    /**
     * Getter de panelCliente
     * 
     * @return panelCliente
     */
    public PanelCliente getPanelCliente() {
        return panelCliente;
    }


    /**
     * Getter de panelEmpleado
     * 
     * @return panelEmpleado
     */
    public PanelEmpleado getPanelEmpleado() {
        return panelEmpleado;
    }


    /**
     * Getter de panelGestor
     * 
     * @return panelGestor
     */
    public PanelGestor getPanelGestor() {
        return panelGestor;
    }


    /**
     * Getter de panelInicio
     * 
     * @return panelInicio
     */
    public PanelInicio getPanelinicio() {
        return panelinicio;
    }


    /**
     * Getter de cartas
     * 
     * @return cartas
     */
    public JPanel getCartas(){
        return cartas;
    }

    /**
     * Getter de panelSuperior
     * 
     * @return panelSuperior
     */
    public JPanel getPanel_superior(){
        return panel_superior;
    }

    
    /**
     * Main 
     * @param args args
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getInstance();
                instance.setVisible(true);
            }
        });
        // final VentanaPrincipal instance = new VentanaPrincipal();
    }


    public JPanel getVentana() {
        return ventana;
    }
}
