package Interfaz.Paneles.Gestor;

import javax.swing.*;


import Interfaz.Controladores.ControladorEstadisticasGlobales;
import Interfaz.Controladores.ControladorRegistrarEmpleado;
import Interfaz.Paneles.Cliente.PanelNotificacionesCliente;
import Interfaz.Paneles.Common.PanelTitulo;

import Interfaz.Paneles.Gestor.Exposiciones.*;
import Interfaz.Paneles.Gestor.Obras.PanelObrasGestorCentral;
import Interfaz.Paneles.Gestor.Salas.*;

import java.awt.event.*;

/**
 * Panel del gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelGestor extends JTabbedPane{
    private PanelTitulo titulo;
    private PanelExposicionesGestor panel_exposiciones;
    private PanelSalasGestor panel_salas;
    private PanelNotificacionesCliente panel_notificacion;
    private PanelRegistrarEmpleado registrar_empleado;
    private PanelHorario panel_horario;
    private PanelObrasGestorCentral panel_obras;

    /**
     * Constructor del panel gestor
     */
    public PanelGestor(){
        super();

        panel_exposiciones = new PanelExposicionesGestor();

        panel_notificacion = new PanelNotificacionesCliente();


        titulo = new PanelTitulo("Listado de exposiciones", "Estadísticas globales");

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panel_exposiciones);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario


        panel_exposiciones = new PanelExposicionesGestor();
        

        titulo.setControladorBoton(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorEstadisticasGlobales.mostrarEstadisticasGlobales(panel_exposiciones);
                
            }
            
        });

        panel_exposiciones.getEstadisticas().getTitulo().setControladorBoton(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorEstadisticasGlobales.volverAtras(panel_exposiciones);
            }
            
        });

        
        panel_salas = new PanelSalasGestor();
        
        registrar_empleado = new PanelRegistrarEmpleado();

        ControladorRegistrarEmpleado c_registrar = new ControladorRegistrarEmpleado(registrar_empleado);

        registrar_empleado.setControladorRegistrarEmpleado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                c_registrar.registrar();
            }
        });

        panel_horario = new PanelHorario();

        panel_obras = new PanelObrasGestorCentral();

        this.addTab("Exposiciones", panel_exposiciones);
        this.addTab("Modificar salas", panel_salas);
        this.addTab("Obras", panel_obras);
        this.addTab("Registrar emplado", registrar_empleado);
        this.addTab("Notificaciones", panel_notificacion);
        this.addTab("Horario", panel_horario);


        this.setSelectedIndex(0);

    }

    /**
     * Función para actualizar el panel
     */
    public void update(){
        registrar_empleado.update();
        panel_exposiciones.update();
        panel_salas.update();
        panel_obras.update();
        panel_notificacion.update();
    }   

}
