package Interfaz.Controladores;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import Interfaz.Paneles.Cliente.Sorteos.PanelDetallesSorteo;

import Sistema.Sistema;
import Sorteo.Sorteo;

import java.awt.*;

/**
 * Controlador de muestra de sorteos
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class ControladorSorteosCentral {
    /**
     * Mostrar panel detalles sorteo
     * @param vista vista a cambiar
     * @param panelSorteos panel de sorteos
     * @param tabla tabla de sorteos
     */
    public static void mostrarPanelDetallesSorteo(JPanel vista, PanelDetallesSorteo panelSorteos,
            JTable tabla) {
        List<Sorteo> sorteos = Sistema.getInstance().getSorteos();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un sorteo para comprobar sus detalles",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Sorteo sorteo = sorteos.get(fila);
        panelSorteos.update(sorteo);
        panelSorteos.revalidate();
        ((CardLayout) vista.getLayout()).show(vista, "Detalles sorteo");
    }
}
