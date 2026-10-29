package Interfaz.Paneles.Empleado;

import Interfaz.Controladores.ControladorAtras;
import Interfaz.Controladores.ControladorEnviarNotificacion;
import Interfaz.Controladores.ControladorModificarSala;
import Interfaz.Controladores.MouseListenerTabla;
import Interfaz.Paneles.Common.BotonRendererVender;
import Interfaz.Paneles.Common.PanelListaExposiciones;
import Interfaz.Paneles.Common.PanelTitulo;
import Usuario.Empleado;
import Interfaz.InterfazUtils.InterfazUtils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Panel del empleado
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEmpleado extends JTabbedPane{

    private JPanel titulo, panel_exposiciones;
    private PanelListaExposiciones panel_lista;
    private PanelSalasEmpleado panel_salas;
    private PanelEnviarNotificacion panel_notificacion;
    private PanelTitulo titulo_salas; 
    private PanelVenderEntradas panel_vender;
    private PanelModificarDatosEmp panel_modificar;

    /**
     * Constructor del panel de empleado
     */
    public PanelEmpleado(){

        super();

        CardLayout layout = new CardLayout();
        panel_exposiciones = new JPanel(layout);

        panel_lista = new PanelListaExposiciones(new BotonRendererVender());

        panel_vender = new PanelVenderEntradas();

        panel_notificacion = new PanelEnviarNotificacion();

        panel_notificacion.update();

        ControladorEnviarNotificacion c_noti = new ControladorEnviarNotificacion(panel_notificacion);

        panel_notificacion.setControladorEnviar(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        c_noti.enviarNotificacion();
                }  
        });


        titulo = new PanelTitulo("Exposiciones disponibles");

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panel_lista);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        titulo_salas = new PanelTitulo("Modificar salas", "Modificar");

        

        panel_salas = new PanelSalasEmpleado();

        ControladorModificarSala c = new ControladorModificarSala(panel_salas);
        

        titulo_salas.setControladorBoton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.seleccionarSala();
            }  
        });

        panel_salas.setControladorPanelDerecho(c);

        JSplitPane splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo_salas, panel_salas);
        splitPane3.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane3.setDividerSize(5); // Tamaño del delimitador
        splitPane3.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        panel_lista.getExpsTabla().setControladorTabla(new MouseListenerTabla(panel_lista.getExpsTabla(), panel_vender, panel_exposiciones));

        panel_vender.setControladorAtras(new ControladorAtras(panel_exposiciones));

        panel_exposiciones.add(splitPane, InterfazUtils.LISTA_EXPOS);
        panel_exposiciones.add(panel_vender, InterfazUtils.VER_COMPRAR_VENDER);

        layout.show(panel_exposiciones, InterfazUtils.LISTA_EXPOS);

        panel_modificar = new PanelModificarDatosEmp();

        this.addTab("Exposiciones", panel_exposiciones);
        this.addTab("Enviar notificacion", panel_notificacion);
        this.addTab("Modificar salas", splitPane3);
        this.addTab("Editar datos", panel_modificar);

        this.setSelectedIndex(0);

    }

    /**
     * Método para actualizar el panel
     * @param e empleado
     */
    public void update(Empleado e){
        panel_lista.update();
        panel_salas.update();
        panel_notificacion.update();
        panel_modificar.update(e);
    }

}
