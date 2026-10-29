package Interfaz.Paneles.Cliente.Sorteos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Interfaz.Controladores.ControladorSorteosCentral;

/**
 * Panel central de los sorteos
 * 
 * @author Ignacio Sánchez - Victor Sanz - Fabio Desio
 */
public class PanelSorteosCentral extends JPanel {
    PanelLateralSorteosCliente panel_lateral;
    PanelListaSorteosCliente lista_sorteos;
    JPanel panel_derecho;
    JPanel panel_vacio;
    PanelDetallesSorteo ver_detalles;

    /**
     * Constructor del panel central de los sorteos
     */
    public PanelSorteosCentral() {
        super();

        this.setLayout(new BorderLayout());
        panel_lateral = new PanelLateralSorteosCliente();

        ver_detalles = new PanelDetallesSorteo();

        lista_sorteos = new PanelListaSorteosCliente();

        panel_lateral.setControladorDetallesSorteo(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosCentral.mostrarPanelDetallesSorteo(panel_derecho, ver_detalles,
                        lista_sorteos.getTabla());

            }

        });

        panel_derecho = new JPanel(new CardLayout());

        panel_vacio = new JPanel();
        panel_derecho.add(panel_vacio, "Vacío");

        panel_derecho.add(ver_detalles, "Detalles sorteo");

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_lateral, lista_sorteos);
        splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panel_derecho);
        splitPane2.setDividerLocation(1200); // Ancho inicial del panel izquierdo
        splitPane2.setDividerSize(5); // Tamaño del delimitador
        splitPane2.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane2, BorderLayout.CENTER); //
    }
}
