package Interfaz.Paneles.Gestor.Exposiciones;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import Exposicion.Exposicion;
import Exposicion.Descuento.Descuento;
import Exposicion.Descuento.DescuentoPorAntelacion;
import Exposicion.Descuento.DescuentoPorCompra;
import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.Paneles.Common.PanelTitulo;

/**
 * Panel descuento existente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio 
 */
public class PanelDescuentoExistente extends JPanel {

    PanelTitulo titulo;
    SpringLayout layout;
    JPanel panelInferior;
    JSplitPane completo;
    Exposicion expo;
    JLabel textoTipo, porcentaje, porcentajeValor, nEntradas, nEntradasValor;

    /**
     * Constructor panel descuento existente
     */
    public PanelDescuentoExistente() {
        super(new BorderLayout());
    }

    /**
     * Método para actualizar el panel
     * 
     * @param expo exposición
     */
    public void update(Exposicion expo) {
        this.expo = expo;

        this.removeAll();

        titulo = new PanelTitulo("Descuentos de \"" + expo.getNombre() + "\"");

        panelInferior = new JPanel(new SpringLayout());
        layout = (SpringLayout) panelInferior.getLayout();

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        List<Descuento> descuentos = expo.getDescuentos();

        if (descuentos.get(0) != null) {
            DescuentoPorCompra d = (DescuentoPorCompra) descuentos.get(0);

            textoTipo = new JLabel("Descuento por compra");
            FormatUtils.formatBoldComponent(
                    textoTipo,
                    FormatUtils.DEFAULT,
                    FormatUtils.TITLE_SIZE);

            porcentaje = new JLabel("Porcentaje de descuento: ");
            FormatUtils.formatBoldComponent(
                    porcentaje,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            porcentajeValor = new JLabel(Double.toString(d.getDescuento() * 100) + " %");

            FormatUtils.formatPlainComponent(
                    porcentajeValor,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            nEntradas = new JLabel("Número de entradas: ");
            FormatUtils.formatBoldComponent(
                    nEntradas,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            nEntradasValor = new JLabel(Integer.toString(d.getNEntradas()));

            FormatUtils.formatPlainComponent(
                    nEntradasValor,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            layout.putConstraint(SpringLayout.WEST, textoTipo, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, porcentaje, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, porcentajeValor, 30, SpringLayout.EAST, porcentaje);
            layout.putConstraint(SpringLayout.WEST, nEntradas, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, nEntradasValor, 30, SpringLayout.EAST, nEntradas);

            layout.putConstraint(SpringLayout.NORTH, textoTipo, 30, SpringLayout.NORTH, panelInferior);
            layout.putConstraint(SpringLayout.NORTH, porcentaje, 60, SpringLayout.NORTH, textoTipo);
            layout.putConstraint(SpringLayout.NORTH, porcentajeValor, 60, SpringLayout.NORTH, textoTipo);
            layout.putConstraint(SpringLayout.NORTH, nEntradas, 30, SpringLayout.NORTH, porcentaje);
            layout.putConstraint(SpringLayout.NORTH, nEntradasValor, 30, SpringLayout.NORTH, porcentaje);

            panelInferior.add(textoTipo);
            panelInferior.add(porcentaje);
            panelInferior.add(porcentajeValor);
            panelInferior.add(nEntradas);
            panelInferior.add(nEntradasValor);

        }

        if (descuentos.get(1) != null) {
            DescuentoPorAntelacion d = (DescuentoPorAntelacion) descuentos.get(1);

            textoTipo = new JLabel("Descuento por antelación");
            FormatUtils.formatBoldComponent(
                    textoTipo,
                    FormatUtils.DEFAULT,
                    FormatUtils.TITLE_SIZE);

            porcentaje = new JLabel("Porcentaje de descuento: ");
            FormatUtils.formatBoldComponent(
                    porcentaje,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            porcentajeValor = new JLabel(Double.toString(d.getDescuento() * 100) + " %");

            FormatUtils.formatPlainComponent(
                    porcentajeValor,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            nEntradas = new JLabel("Meses de antelación: ");
            FormatUtils.formatBoldComponent(
                    nEntradas,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            nEntradasValor = new JLabel(Integer.toString(d.getNMeses()));

            FormatUtils.formatPlainComponent(
                    nEntradasValor,
                    FormatUtils.DEFAULT,
                    FormatUtils.TEXT_SIZE);

            layout.putConstraint(SpringLayout.WEST, textoTipo, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, porcentaje, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, porcentajeValor, 30, SpringLayout.EAST, porcentaje);
            layout.putConstraint(SpringLayout.WEST, nEntradas, 30, SpringLayout.WEST, panelInferior);
            layout.putConstraint(SpringLayout.WEST, nEntradasValor, 30, SpringLayout.EAST, nEntradas);

            layout.putConstraint(SpringLayout.NORTH, textoTipo, 180, SpringLayout.NORTH, panelInferior);
            layout.putConstraint(SpringLayout.NORTH, porcentaje, 60, SpringLayout.NORTH, textoTipo);
            layout.putConstraint(SpringLayout.NORTH, porcentajeValor, 60, SpringLayout.NORTH, textoTipo);
            layout.putConstraint(SpringLayout.NORTH, nEntradas, 30, SpringLayout.NORTH, porcentaje);
            layout.putConstraint(SpringLayout.NORTH, nEntradasValor, 30, SpringLayout.NORTH, porcentaje);

            panelInferior.add(textoTipo);
            panelInferior.add(porcentaje);
            panelInferior.add(porcentajeValor);
            panelInferior.add(nEntradas);
            panelInferior.add(nEntradasValor);

        }

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }
}
