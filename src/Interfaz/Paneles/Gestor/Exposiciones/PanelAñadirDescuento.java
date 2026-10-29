package Interfaz.Paneles.Gestor.Exposiciones;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Exposicion.Exposicion;
import Interfaz.Controladores.ControladorDescuentosGestor;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;

/**
 * Panel para añadir descuento
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelAñadirDescuento extends JPanel {
    PanelTitulo titulo;
    SpringLayout layout;
    JPanel panelInferior;
    JSplitPane completo;
    Exposicion expo;
    JLabel textoTipo, porcentaje, nEntradas;
    JTextField porcentajeValor, nEntradasValor;
    JComboBox<String> tipoDescuento;
    JButton aniadir;
    PanelDescuentos panel;

    /**
     * Constructor del panel
     * 
     * @param panel panelDescuentos
     */
    public PanelAñadirDescuento(PanelDescuentos panel) {
        super(new BorderLayout());
        this.panel = panel;
    }

    /**
     * Función para actualizar el panel
     * 
     * @param expo  exposición
     * @param index índice
     */
    public void update(Exposicion expo, int index) {
        this.expo = expo;

        this.removeAll();

        titulo = new PanelTitulo("Crear sorteo para \"" + expo.getNombre() + "\"");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        String[] opciones2 = { "Seleccione tipo de descuento", "Por compra", "Por antelación" };

        tipoDescuento = new JComboBox<>(opciones2);

        tipoDescuento.setSelectedIndex(index);

        setTipoDescuentoControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tipoDescuento, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, tipoDescuento, 80, SpringLayout.NORTH, panelInferior);

        panelInferior.add(tipoDescuento);

        aniadir = new SuperButton("Añadir descuento", 250);

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();

    }

    /**
     * Método para completar el panel descuento por compra
     */
    public void completarPanelDescuentoPorCompra() {
        textoTipo = new JLabel("Descuento por compra");

        porcentaje = new JLabel("Porcentaje de descuento (%): ");

        porcentajeValor = new JTextField();
        porcentajeValor.setPreferredSize(new Dimension(40, 20));

        nEntradas = new JLabel("Número de entradas: ");

        nEntradasValor = new JTextField();
        nEntradasValor.setPreferredSize(new Dimension(40, 20));

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoTipo, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, porcentaje, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, porcentajeValor, 30, SpringLayout.EAST, porcentaje);
        layout.putConstraint(SpringLayout.WEST, nEntradas, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, nEntradasValor, 30, SpringLayout.EAST, nEntradas);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, aniadir, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, textoTipo, 50, SpringLayout.NORTH, tipoDescuento);
        layout.putConstraint(SpringLayout.NORTH, porcentaje, 50, SpringLayout.NORTH, textoTipo);
        layout.putConstraint(SpringLayout.NORTH, porcentajeValor, 50, SpringLayout.NORTH, textoTipo);
        layout.putConstraint(SpringLayout.NORTH, nEntradas, 50, SpringLayout.NORTH, porcentaje);
        layout.putConstraint(SpringLayout.NORTH, nEntradasValor, 50, SpringLayout.NORTH, porcentaje);
        layout.putConstraint(SpringLayout.NORTH, aniadir, 50, SpringLayout.NORTH, nEntradas);

        panelInferior.add(textoTipo);
        panelInferior.add(porcentaje);
        panelInferior.add(porcentajeValor);
        panelInferior.add(nEntradas);
        panelInferior.add(nEntradasValor);
        panelInferior.add(aniadir);

        setAniadirPorCompra();

        this.revalidate();
        this.repaint();
    }

    /**
     * Método para completar el panel descuento por antelacíón
     */
    public void completarPanelDescuentoPorAntelación() {
        textoTipo = new JLabel("Descuento por antelación");

        porcentaje = new JLabel("Porcentaje de descuento (%): ");

        porcentajeValor = new JTextField();
        porcentajeValor.setPreferredSize(new Dimension(40, 20));

        nEntradas = new JLabel("Número de meses: ");

        nEntradasValor = new JTextField();
        nEntradasValor.setPreferredSize(new Dimension(40, 20));

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoTipo, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, porcentaje, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, porcentajeValor, 30, SpringLayout.EAST, porcentaje);
        layout.putConstraint(SpringLayout.WEST, nEntradas, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, nEntradasValor, 30, SpringLayout.EAST, nEntradas);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, aniadir, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, textoTipo, 50, SpringLayout.NORTH, tipoDescuento);
        layout.putConstraint(SpringLayout.NORTH, porcentaje, 50, SpringLayout.NORTH, textoTipo);
        layout.putConstraint(SpringLayout.NORTH, porcentajeValor, 50, SpringLayout.NORTH, textoTipo);
        layout.putConstraint(SpringLayout.NORTH, nEntradas, 50, SpringLayout.NORTH, porcentaje);
        layout.putConstraint(SpringLayout.NORTH, nEntradasValor, 50, SpringLayout.NORTH, porcentaje);
        layout.putConstraint(SpringLayout.NORTH, aniadir, 50, SpringLayout.NORTH, nEntradas);

        panelInferior.add(textoTipo);
        panelInferior.add(porcentaje);
        panelInferior.add(porcentajeValor);
        panelInferior.add(nEntradas);
        panelInferior.add(nEntradasValor);
        panelInferior.add(aniadir);

        setAniadirPorAntelacion();

        this.revalidate();
        this.repaint();
    }

    /**
     * Setter del controlador del tipo de descuento
     */
    public void setTipoDescuentoControlador() {
        PanelAñadirDescuento panel = this;

        tipoDescuento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorDescuentosGestor.completarPanelCreacion(panel, tipoDescuento);
            }

        });
    }

    /**
     * Setter del controlador de añadir por compra
     */
    public void setAniadirPorCompra() {
        aniadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorDescuentosGestor.crearPorCompra(porcentajeValor, nEntradasValor, panel);
            }

        });
    }

    /**
     * Setter del controlador de añadir por antelación
     */
    public void setAniadirPorAntelacion() {
        aniadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorDescuentosGestor.crearPorAntelacion(porcentajeValor, nEntradasValor, panel);
            }

        });
    }

    /**
     * Getter de la exposición
     * @return exposición
     */
    public Exposicion getExpo() {
        return expo;
    }

}
