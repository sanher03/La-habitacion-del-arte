package Interfaz.Paneles.Gestor.Exposiciones;

import Interfaz.Controladores.ControladorEstadisticasGlobales;
import Interfaz.InterfazUtils.InterfazUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel de exposiciones de gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelExposicionesGestor extends JPanel{
    PanelEstadisticasGlobales estadisticas;
    PanelExposicionesGestorCentral panel_principal;

    /**
     * Constructor del panel
     */
    public PanelExposicionesGestor(){
        super();
        this.setLayout(new CardLayout());

        estadisticas = new PanelEstadisticasGlobales();
        this.add(estadisticas, InterfazUtils.ESTADISTICAS);

        estadisticas.setControladorComprobar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorEstadisticasGlobales.comprobarEstadisticas(estadisticas.getInicio(), estadisticas.getFin());
            }
        });

        panel_principal = new PanelExposicionesGestorCentral();
        this.add(panel_principal, InterfazUtils.EXPO_PRINCIPAL);


        ((CardLayout)this.getLayout()).show(this, InterfazUtils.EXPO_PRINCIPAL);

    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        estadisticas.update();
        panel_principal.update();
    }

    /**
     * Getter de las estadísticas globales
     * @return estadísticas globales
     */
    public PanelEstadisticasGlobales getEstadisticas() {
        return estadisticas;
    }
}
