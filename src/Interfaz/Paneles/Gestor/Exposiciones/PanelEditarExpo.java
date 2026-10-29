package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.*;
import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Paneles.Common.PanelTitulo;
import Sala.Sala;
import Sistema.Sistema;

import java.util.List;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Panel de editar exposición
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelEditarExpo extends JPanel {
    JPanel panel_creacion, panel_disponible, panel_superior;
    JPanel panel, panel_temporal, panel_permanente, panel_cancelar;
    JLabel salas_nombre, añadir_sala, eliminar_sala, precio, fecha1_nombre, fecha2_nombre, fecha_temporal_nombre,
            fecha_cancelar_nombre;
    JButton boton_editar, boton_cambiar, boton_cancelar, boton_interrumpir, boton_prorrogar;
    JTextField text_precio;
    SpringLayout layout_creacion, layout_temporal, layout_permanente, layout_cancelar;
    JComboBox<String> aniadir_combo, eliminar_combo;
    CardLayout card_layout, layout_superior;
    JSplitPane splitPane, splitPane1;
    JDatePickerImpl fecha1, fecha2, fecha_temporal, fecha_cancelar;

    /**
     * Constructor del panel editar exposición
     */
    public PanelEditarExpo() {
        super();

        this.setLayout(new BorderLayout());

        card_layout = new CardLayout();
        panel = new JPanel();
        panel.setLayout(card_layout);

        // Primera carta con Exposiciones EN_CREACION
        layout_creacion = new SpringLayout();
        panel_creacion = new JPanel(layout_creacion);
        panel.add(panel_creacion, InterfazUtils.EDITAR_CREACION);

        salas_nombre = new JLabel("Salas: ");

        añadir_sala = new JLabel("Añadir sala:");

        eliminar_sala = new JLabel("Eliminar sala:");
        precio = new JLabel("Precio:");

        boton_editar = new JButton("Editar");
        FormatUtils.formatItalicComponent(boton_editar, FormatUtils.DEFAULT, 12);
        boton_editar.setPreferredSize(new Dimension(100, 50));

        boton_cambiar = new JButton("Cambiar de fase");
        FormatUtils.formatItalicComponent(boton_cambiar, FormatUtils.DEFAULT, 12);
        boton_cambiar.setPreferredSize(new Dimension(200, 50));

        /*
         * boton_cancelar = new SuperButton("Cancelar", 100);
         * FormatUtils.formatItalicComponent(boton_cancelar, FormatUtils.DEFAULT, 12);
         */

        panel_disponible = new JPanel(new BorderLayout());
        panel.add(panel_disponible, InterfazUtils.EDITAR_DISPONIBLE);

        layout_superior = new CardLayout();
        panel_superior = new JPanel(layout_superior);

        layout_temporal = new SpringLayout();
        panel_temporal = new JPanel(layout_temporal);
        panel_superior.add(panel_temporal, InterfazUtils.EDITAR_TEMPORAL);

        boton_prorrogar = new JButton("Prorrogar");

        layout_permanente = new SpringLayout();
        panel_permanente = new JPanel(layout_permanente);
        panel_superior.add(panel_temporal, InterfazUtils.EDITAR_PERMANENTE);

        boton_interrumpir = new JButton("Interrumpir");

        layout_cancelar = new SpringLayout();
        panel_cancelar = new JPanel(layout_cancelar);

        boton_cancelar = new JButton("Cancelar");

    }

    /**
     * Método para actualizar el panel en exposiciones temporales
     */
    public void updateTemporal() {
        if (fecha_temporal != null) {
            panel_temporal.removeAll();
        }
        fecha_temporal_nombre = new JLabel("Fecha de inicio:");

        fecha_temporal = DatePickerPanel.create();

        layout_temporal.putConstraint(SpringLayout.NORTH, fecha_temporal_nombre, 30, SpringLayout.NORTH,
                panel_temporal);
        layout_temporal.putConstraint(SpringLayout.WEST, fecha_temporal_nombre, 80, SpringLayout.WEST, panel_temporal);

        layout_temporal.putConstraint(SpringLayout.NORTH, fecha_temporal, 20, SpringLayout.SOUTH,
                fecha_temporal_nombre);
        layout_temporal.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha_temporal, 0, SpringLayout.HORIZONTAL_CENTER,
                fecha_temporal_nombre);

        layout_temporal.putConstraint(SpringLayout.NORTH, boton_prorrogar, 40, SpringLayout.SOUTH, fecha_temporal);
        layout_temporal.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_prorrogar, 0,
                SpringLayout.HORIZONTAL_CENTER, panel_temporal);

        panel_temporal.add(fecha_temporal_nombre);
        panel_temporal.add(fecha_temporal);
        panel_temporal.add(boton_prorrogar);

    }

    /**
     * Método para actualizar el panel en exposiciones permanentes
     */
    public void updatePermanente() {
        if (fecha1 != null) {
            panel_permanente.removeAll();
        }
        fecha1_nombre = new JLabel("Fecha de inicio:");
        fecha2_nombre = new JLabel("Fecha de fin:");

        fecha1 = DatePickerPanel.create();
        fecha2 = DatePickerPanel.create();

        layout_permanente.putConstraint(SpringLayout.NORTH, fecha1_nombre, 30, SpringLayout.NORTH, panel_permanente);
        layout_permanente.putConstraint(SpringLayout.WEST, fecha1_nombre, 80, SpringLayout.WEST, panel_permanente);

        layout_permanente.putConstraint(SpringLayout.NORTH, fecha1, 20, SpringLayout.SOUTH, fecha1_nombre);
        layout_permanente.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, 0, SpringLayout.HORIZONTAL_CENTER,
                fecha1_nombre);

        layout_permanente.putConstraint(SpringLayout.VERTICAL_CENTER, fecha2_nombre, 0, SpringLayout.VERTICAL_CENTER,
                fecha1_nombre);
        layout_permanente.putConstraint(SpringLayout.EAST, fecha2_nombre, -100, SpringLayout.EAST, panel_permanente);

        layout_permanente.putConstraint(SpringLayout.NORTH, fecha2, 20, SpringLayout.SOUTH, fecha2_nombre);
        layout_permanente.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2, 0, SpringLayout.HORIZONTAL_CENTER,
                fecha2_nombre);

        layout_permanente.putConstraint(SpringLayout.NORTH, boton_interrumpir, 40, SpringLayout.SOUTH, fecha2);
        layout_permanente.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_interrumpir, 0,
                SpringLayout.HORIZONTAL_CENTER, panel_permanente);

        panel_permanente.add(fecha1_nombre);
        panel_permanente.add(fecha2_nombre);
        panel_permanente.add(fecha1);
        panel_permanente.add(fecha2);
        panel_permanente.add(boton_interrumpir);
    }

    /**
     * Método para actualizar el panel con cancelar
     */
    public void updateCancelar() {
        if (fecha_cancelar != null) {
            panel_cancelar.removeAll();
        }
        fecha_cancelar_nombre = new JLabel("Fecha de inicio:");

        fecha_cancelar = DatePickerPanel.create();

        layout_cancelar.putConstraint(SpringLayout.NORTH, fecha_cancelar_nombre, 30, SpringLayout.NORTH,
                panel_cancelar);
        layout_cancelar.putConstraint(SpringLayout.WEST, fecha_cancelar_nombre, 80, SpringLayout.WEST, panel_cancelar);

        layout_cancelar.putConstraint(SpringLayout.NORTH, fecha_cancelar, 20, SpringLayout.SOUTH,
                fecha_cancelar_nombre);
        layout_cancelar.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha_cancelar, 0, SpringLayout.HORIZONTAL_CENTER,
                fecha_cancelar_nombre);

        layout_cancelar.putConstraint(SpringLayout.NORTH, boton_cancelar, 40, SpringLayout.SOUTH, fecha_cancelar);
        layout_cancelar.putConstraint(SpringLayout.HORIZONTAL_CENTER, boton_cancelar, 0, SpringLayout.HORIZONTAL_CENTER,
                panel_cancelar);

        panel_cancelar.add(fecha_cancelar_nombre);
        panel_cancelar.add(fecha_cancelar);
        panel_cancelar.add(boton_cancelar);
    }

    /**
     * Método para actualizar el panel con exposición
     * 
     * @param expo exposición
     */
    public void updateCreacion(Exposicion expo) {
        if (text_precio != null) {
            panel_creacion.removeAll();
        }

        text_precio = new JTextField(String.valueOf(expo.getPrecio()));
        text_precio.setPreferredSize(new Dimension(50, 20));

        List<JLabel> salas = new ArrayList<JLabel>();
        for (Sala s : expo.getSalas()) {
            salas.add(new JLabel(s.getNombre()));
        }

        int len_añadir = Sistema.getInstance().getSalasSinExpo().size() + 1;
        List<Sala> salas_sistema = Sistema.getInstance().getSalasSinExpo();

        String comboBoxItems[] = new String[len_añadir];

        comboBoxItems[0] = "Seleccionar Sala";

        for (int i = 0; i < len_añadir - 1; i++) {
            comboBoxItems[i + 1] = salas_sistema.get(i).getNombre();
        }
        aniadir_combo = new JComboBox<String>(comboBoxItems);

        int len_eliminar = expo.getSalas().size() + 1;
        List<Sala> salas_expo = expo.getSalas();

        String comboBoxItems2[] = new String[len_eliminar];

        comboBoxItems2[0] = "Seleccionar Sala";

        for (int i = 0; i < len_eliminar - 1; i++) {
            comboBoxItems2[i + 1] = salas_expo.get(i).getNombre();
        }
        eliminar_combo = new JComboBox<String>(comboBoxItems2);

        layout_creacion.putConstraint(SpringLayout.NORTH, precio, 20, SpringLayout.NORTH, panel_creacion);
        layout_creacion.putConstraint(SpringLayout.WEST, precio, 20, SpringLayout.WEST, panel_creacion);

        layout_creacion.putConstraint(SpringLayout.NORTH, text_precio, 0, SpringLayout.NORTH, precio);
        layout_creacion.putConstraint(SpringLayout.WEST, text_precio, 50, SpringLayout.EAST, precio);

        layout_creacion.putConstraint(SpringLayout.NORTH, salas_nombre, 20, SpringLayout.SOUTH, precio);
        layout_creacion.putConstraint(SpringLayout.WEST, salas_nombre, 0, SpringLayout.WEST, precio);

        int len = salas.size();
        for (int i = 0; i < len; i++) {
            layout_creacion.putConstraint(SpringLayout.NORTH, salas.get(i), i * 20, SpringLayout.NORTH, salas_nombre);
            layout_creacion.putConstraint(SpringLayout.EAST, salas.get(i), 0, SpringLayout.HORIZONTAL_CENTER,
                    panel_creacion);
        }
        layout_creacion.putConstraint(SpringLayout.NORTH, añadir_sala, (len + 2) * 20, SpringLayout.NORTH,
                salas_nombre);
        layout_creacion.putConstraint(SpringLayout.WEST, añadir_sala, 0, SpringLayout.WEST, salas_nombre);

        layout_creacion.putConstraint(SpringLayout.VERTICAL_CENTER, aniadir_combo, 0, SpringLayout.VERTICAL_CENTER,
                añadir_sala);
        layout_creacion.putConstraint(SpringLayout.EAST, aniadir_combo, 0, SpringLayout.HORIZONTAL_CENTER,
                panel_creacion);

        layout_creacion.putConstraint(SpringLayout.NORTH, eliminar_sala, 20, SpringLayout.SOUTH, añadir_sala);
        layout_creacion.putConstraint(SpringLayout.WEST, eliminar_sala, 0, SpringLayout.WEST, añadir_sala);

        layout_creacion.putConstraint(SpringLayout.VERTICAL_CENTER, eliminar_combo, 0, SpringLayout.VERTICAL_CENTER,
                eliminar_sala);
        layout_creacion.putConstraint(SpringLayout.EAST, eliminar_combo, 0, SpringLayout.HORIZONTAL_CENTER,
                panel_creacion);

        layout_creacion.putConstraint(SpringLayout.NORTH, boton_editar, 30, SpringLayout.SOUTH, eliminar_sala);
        layout_creacion.putConstraint(SpringLayout.WEST, boton_editar, 150, SpringLayout.WEST, panel_creacion);

        layout_creacion.putConstraint(SpringLayout.NORTH, boton_cambiar, 0, SpringLayout.NORTH, boton_editar);
        layout_creacion.putConstraint(SpringLayout.WEST, boton_cambiar, 10, SpringLayout.EAST, boton_editar);

        /*
         * layout_creacion.putConstraint(SpringLayout.NORTH, boton_cancelar, 0,
         * SpringLayout.NORTH, boton_cambiar);
         * layout_creacion.putConstraint(SpringLayout.WEST, boton_cancelar, 10,
         * SpringLayout.EAST, boton_cambiar);
         */

        panel_creacion.add(precio);
        panel_creacion.add(text_precio);
        panel_creacion.add(salas_nombre);

        for (int i = 0; i < len; i++) {
            panel_creacion.add(salas.get(i));
        }

        panel_creacion.add(añadir_sala);
        panel_creacion.add(aniadir_combo);
        panel_creacion.add(eliminar_sala);
        panel_creacion.add(eliminar_combo);
        panel_creacion.add(boton_editar);
        panel_creacion.add(boton_cambiar);
        // panel_creacion.add(boton_cancelar);

    }

    /**
     * Método para actualizar el panel
     * 
     * @param expo exposición
     */
    public void update(Exposicion expo) {
        if (splitPane != null) {
            this.remove(splitPane);
        }
        if (expo.getEstado() == EstadoExposicion.EN_CREACION) {
            updateCreacion(expo);
            card_layout.show(panel, InterfazUtils.EDITAR_CREACION);
        } else if (expo.getEstado() == EstadoExposicion.DISPONIBLE || expo.getEstado() == EstadoExposicion.COMENZADA) {
            if (splitPane1 != null) {
                panel_disponible.remove(splitPane1);
            }

            if (expo.isPermanente()) {
                updatePermanente();
                updateCancelar();
                splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_permanente, panel_cancelar);
                splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
                splitPane1.setDividerSize(5); // Tamaño del delimitador
                splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

            } else {
                updateTemporal();
                updateCancelar();
                splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel_temporal, panel_cancelar);
                splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
                splitPane1.setDividerSize(5); // Tamaño del delimitador
                splitPane1.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

            }

            panel_disponible.add(splitPane1, BorderLayout.CENTER);
            card_layout.show(panel, InterfazUtils.EDITAR_DISPONIBLE);
        }

        PanelTitulo titulo = new PanelTitulo("Editar exposición (" + expo.getEstado() + ")");

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panel);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Getter del text precio
     * 
     * @return text precio
     */
    public String getText_precio() {
        return text_precio.getText();
    }

    /**
     * Getter del añadir combo
     * 
     * @return añadir combo
     */
    public JComboBox<String> getAniadir_combo() {
        return aniadir_combo;
    }

    /**
     * Getter del eliminar combo
     * 
     * @return eliminar combo
     */
    public JComboBox<String> getEliminar_combo() {
        return eliminar_combo;
    }

    /**
     * Getter de la fecha1
     * 
     * @return fecha1
     */
    public JDatePickerImpl getFecha1() {
        return fecha1;
    }

    /**
     * Getter de la fecha2
     * 
     * @return fecha2
     */
    public JDatePickerImpl getFecha2() {
        return fecha2;
    }

    /**
     * Getter de la fecha temporal
     * 
     * @return fecha temporal
     */
    public JDatePickerImpl getFecha_temporal() {
        return fecha_temporal;
    }

    /**
     * Getter de la fecha cancelar
     * 
     * @return fecha cancelar
     */
    public JDatePickerImpl getFecha_cancelar() {
        return fecha_cancelar;
    }

    /**
     * Setter del controlador editar
     * 
     * @param c actionListener
     */
    public void setControladorEditar(ActionListener c) {
        boton_editar.addActionListener(c);
    }

    /**
     * Setter del controlador cambiar
     * 
     * @param c actionListener
     */
    public void setControladorCambiar(ActionListener c) {
        boton_cambiar.addActionListener(c);
    }

    /**
     * Setter del controlador cancelar
     * 
     * @param c actionListener
     */
    public void setControladorCancelar(ActionListener c) {
        boton_cancelar.addActionListener(c);
    }

    /**
     * Setter del controlador interrumpir
     * 
     * @param c actionListener
     */
    public void setControladorInterrumpir(ActionListener c) {
        boton_interrumpir.addActionListener(c);
    }

    /**
     * Setter del controlador prorrogar
     * 
     * @param c actionListener
     */
    public void setControladorProrrogar(ActionListener c) {
        boton_prorrogar.addActionListener(c);
    }

}
