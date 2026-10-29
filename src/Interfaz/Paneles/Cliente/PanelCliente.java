package Interfaz.Paneles.Cliente;

import javax.swing.*;

import Interfaz.Controladores.ControladorAtras;
import Interfaz.Controladores.ControladorFiltros;
import Interfaz.Controladores.MouseListenerTabla;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.*;

import java.awt.*;

import Interfaz.Paneles.Cliente.Sorteos.*;

/**
 * Panel para el cliente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelCliente extends JTabbedPane {

    private PanelListaExposiciones panelDerecho;
    private PanelComprarEntradas panelCompra;
    private JPanel panelExposiciones, titulo;
    private PanelNotificacionesCliente panel_notificaciones;
    private PanelSorteosCentral panel_sorteos;

    /**
     * Constructor del PanelCliente
     */
    public PanelCliente() {
        super();

        PanelExposicionesFiltrar filtros = new PanelExposicionesFiltrar();

        CardLayout layout = new CardLayout();
        panelExposiciones = new JPanel(layout);

        panelDerecho = new PanelListaExposiciones(new BotonRendererComprar());

        filtros.setControladorFiltros(new ControladorFiltros(filtros, panelDerecho));

        titulo = new PanelTitulo("Exposiciones disponibles");

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelDerecho);
        splitPane1.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filtros, splitPane1);
        splitPane.setDividerLocation(230); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(true); // Habilitar redimensionamiento manual por el usuario

        // A eliminar despues

        panel_notificaciones = new PanelNotificacionesCliente();

        panelCompra = new PanelComprarEntradas();

        panelCompra.setControladorAtras(new ControladorAtras(panelExposiciones));

        panelDerecho.getExpsTabla().setControladorTabla(
                new MouseListenerTabla(panelDerecho.getExpsTabla(), panelCompra, panelExposiciones));

        panelExposiciones.add(splitPane, InterfazUtils.LISTA_EXPOS);
        panelExposiciones.add(panelCompra, InterfazUtils.VER_COMPRAR_VENDER);

        layout.show(panelExposiciones, InterfazUtils.LISTA_EXPOS);

        panel_sorteos = new PanelSorteosCentral();

        this.addTab("Exposiciones", panelExposiciones);
        this.addTab("Sorteos", panel_sorteos);
        this.addTab("Notificaciones", panel_notificaciones);

        this.setSelectedIndex(0);

    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        panelDerecho.update();
        panel_notificaciones.update();
    }

    /**
     * Ǵetter del atributo PanelCompra
     * 
     * @return panelCompra panelComprarEntradas
     */
    public PanelComprarEntradas getPanelCompra() {
        return panelCompra;
    }

    /**
     * Getter del atributo PanelExposiciones
     * 
     * @return panelExposiciones panelExposiciones
     */
    public JPanel getPanelExposiciones() {
        return panelExposiciones;
    }

    /**
     * Getter de TablaExposiciones
     * 
     * @return tablaExposiciones TablaExposiciones
     */
    public TablaExposiciones getTablaExposiciones() {
        return panelDerecho.getExpsTabla();
    }

}
