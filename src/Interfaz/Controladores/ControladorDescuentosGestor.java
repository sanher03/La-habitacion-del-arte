package Interfaz.Controladores;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Exposicion.Descuento.DescuentoPorAntelacion;
import Exposicion.Descuento.DescuentoPorCompra;
import Interfaz.Paneles.Gestor.Exposiciones.PanelAñadirDescuento;
import Interfaz.Paneles.Gestor.Exposiciones.PanelDescuentos;

/**
 * Clase controlador para descuentos del gestor
 * 
 * @author Ignacio Sánchez
 */
public class ControladorDescuentosGestor {
    public static void completarPanelCreacion(PanelAñadirDescuento panel, JComboBox<String> tipoDescuento) {
        String item = (String) tipoDescuento.getSelectedItem();
        if (item.equals("Seleccione tipo de descuento")) {
            panel.update(panel.getExpo(), 0);
            return;
        } else if (item.equals("Por compra")) {
            panel.update(panel.getExpo(), 1);
            panel.completarPanelDescuentoPorCompra();
            return;
        } else {
            panel.update(panel.getExpo(), 2);
            panel.completarPanelDescuentoPorAntelación();
            return;
        }
    }

    /**
     * Crear descuento por compra
     * @param valorDescuento descuento
     * @param nEntradas entradas
     * @param panel panel
     */
    public static void crearPorCompra(JTextField valorDescuento, JTextField nEntradas, PanelDescuentos panel) {
        try {
            double descuento = Double.parseDouble(valorDescuento.getText())/100;
            int entradas = Integer.parseInt(nEntradas.getText());
            DescuentoPorCompra d = new DescuentoPorCompra(descuento, entradas);
            panel.getAniadir().getExpo().addDescuento(d);
            panel.getAniadir().update(panel.getAniadir().getExpo(), 0);
            panel.getExistente().update(panel.getAniadir().getExpo());
            JOptionPane.showMessageDialog(null, "Se ha añadido el descuento correctamente.", "Descuento añadido", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Crear descuento por antelación
     * @param valorDescuento descuento
     * @param nMeses meses
     * @param panel panel
     */
    public static void crearPorAntelacion(JTextField valorDescuento, JTextField nMeses, PanelDescuentos panel) {
        try {
            double descuento = Double.parseDouble(valorDescuento.getText())/100;
            int meses = Integer.parseInt(nMeses.getText());
            DescuentoPorAntelacion d = new DescuentoPorAntelacion(descuento, meses);
            panel.getAniadir().getExpo().addDescuento(d);
            panel.getExistente().update(panel.getAniadir().getExpo());
            panel.getAniadir().update(panel.getAniadir().getExpo(), 0);
            JOptionPane.showMessageDialog(null, "Se ha añadido el descuento correctamente.", "Descuento añadido", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
