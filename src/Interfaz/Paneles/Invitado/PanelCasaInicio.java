package Interfaz.Paneles.Invitado;

import Interfaz.Controladores.ControladorAtras;
import Interfaz.Controladores.ControladorFiltros;
import Interfaz.Controladores.MouseListenerTabla;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel casa inicio
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelCasaInicio extends JPanel {
    private PanelListaExposiciones panelDerecho;
    private PanelVerExposicion panelCompra;
    JPanel panel_titulo;

    /**
     * Constructor del panel
     */
    public PanelCasaInicio() {

        super(new CardLayout());

    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        CardLayout layout = (CardLayout) this.getLayout();

        PanelExposicionesFiltrar filtros = new PanelExposicionesFiltrar();

        panelDerecho = new PanelListaExposiciones(new BotonRendererVer());

        filtros.setControladorFiltros(new ControladorFiltros(filtros, panelDerecho));

        panel_titulo = new PanelTitulo("Exposiciones disponibles");

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_titulo, panelDerecho);
        splitPane1.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filtros, splitPane1);
        splitPane.setDividerLocation(230); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        panelCompra = new PanelVerExposicion();

        panelDerecho.getExpsTabla()
                .setControladorTabla(new MouseListenerTabla(panelDerecho.getExpsTabla(), panelCompra, this));

        panelCompra.setControladorAtras(new ControladorAtras(this));

        this.add(splitPane, InterfazUtils.LISTA_EXPOS);
        this.add(panelCompra, InterfazUtils.VER_COMPRAR_VENDER);

        layout.show(this, InterfazUtils.LISTA_EXPOS);
    }

    /**
     * Getter del panel de compra
     * 
     * @return panel de compra
     */
    public PanelVerExposicion getPanelCompra() {
        return panelCompra;
    }

    /**
     * Getter de la tabla de exposiciones
     * 
     * @return tabla de exposiciones
     */
    public TablaExposiciones getTablaExposiciones() {
        return panelDerecho.getExpsTabla();
    }

    /**
     * Main
     * 
     * @param args args
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Prueba");
        ventana.add(new PanelCasaInicio());
        // ventana.setResizable(true);
        ventana.setVisible(true);
    }
}
