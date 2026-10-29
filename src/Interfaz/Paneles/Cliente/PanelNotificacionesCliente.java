package Interfaz.Paneles.Cliente;

import javax.swing.*;
import javax.swing.table.*;

import Interfaz.Paneles.Common.BotonRendererNotificacion;
import Interfaz.Paneles.Common.TablaNotificaciones;
import Sistema.Sistema;
import Usuario.Notificacion;

import java.awt.*;

import java.util.List;

/**
 * Panel para mostrar las notificaciones del cliente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelNotificacionesCliente extends JPanel{
    JTable tabla;

    /**
     * Constructor del PanelNotificacionesCliente
     */
    public PanelNotificacionesCliente() {
        this.setLayout(new BorderLayout());

        SpringLayout layout2 = new SpringLayout();
        JPanel panel = new JPanel(layout2);
        JLabel titulo = new JLabel("Notificaciones");
        titulo.setFont(titulo.getFont().deriveFont(24f));

        layout2.putConstraint(SpringLayout.WEST, titulo, 10, SpringLayout.WEST, panel);
        layout2.putConstraint(SpringLayout.NORTH, titulo, 10, SpringLayout.NORTH, panel);
        panel.add(titulo);

        tabla = TablaNotificaciones.create(new BotonRendererNotificacion());
        
        // Es aconsejable crear una barra de scroll para la tabla, por si el número de
        // filas supera el tamaño previsto
        JScrollPane scroll = new JScrollPane(tabla);
        // Añadir el scroll con la tabla al panel donde se vaya a mostrar

        // Crear el panel de contenido para la parte derecha (por ejemplo)
        JPanel panelAbajo = new JPanel(new BorderLayout());
        panelAbajo.setBackground(Color.LIGHT_GRAY); // Color de fondo gris claro
        panelAbajo.add(scroll, BorderLayout.CENTER);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, panelAbajo);
        splitPane1.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        this.add(splitPane1, BorderLayout.CENTER);
    }

    /**
     * Función para modificar la vista
     */
    public void update(){
        List <Notificacion> notificaciones = Sistema.getInstance().getUsuarioActivo().getNotificaciones();
        TablaNotificaciones.modificarContenidoFilas((DefaultTableModel) tabla.getModel(), notificaciones);
    }
}
