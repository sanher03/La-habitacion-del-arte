package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Paneles.Common.PanelTitulo;
import Sala.Sala;
import Sistema.Sistema;

import java.util.List;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel programar exposición
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelProgramarExposicion extends JPanel {
    SpringLayout layout;
    PanelTitulo titulo;
    JLabel nombre, autor, descr, precio, imagen, tipo, fecha1_nombre, fecha2_nombre, archivo, salas_nombre;
    JTextField text_nombre, text_autor, text_precio;
    JTextArea text_descr;
    JButton boton, boton_imagen;
    JRadioButton opcion1, opcion2;
    JDatePickerImpl fecha1, fecha2;
    ButtonGroup buttonGroup;
    JComboBox<String> sala;
    JPanel panel;
    JSplitPane splitPane;

    /**
     * Constructor del panel
     */
    public PanelProgramarExposicion() {
        super();

        this.setLayout(new BorderLayout());

        titulo = new PanelTitulo("Programar exposición");

        layout = new SpringLayout();
        panel = new JPanel(layout);

        nombre = new JLabel("Nombre:");
        autor = new JLabel("Autor:");
        descr = new JLabel("Descripción:");
        precio = new JLabel("Precio:");
        imagen = new JLabel("Ruta a la imagen:");
        salas_nombre = new JLabel("Sala:");
        tipo = new JLabel("Tipo:");

        boton_imagen = new JButton("Examinar archivo...");

        buttonGroup = new ButtonGroup();
        opcion1 = new JRadioButton("Permanente");
        opcion2 = new JRadioButton("Temporal");
        buttonGroup.add(opcion1);
        buttonGroup.add(opcion2);

        boton = new JButton("Crear exposición");
        FormatUtils.formatItalicComponent(boton, FormatUtils.DEFAULT, 12);
        boton.setPreferredSize(new Dimension(200, 50));

        this.updateRestart();

    }

    /**
     * Método para actualizar el panel
     */
    public void update() {
        if (fecha1 != null) {
            panel.remove(fecha1);
        }
        if (fecha2 != null) {
            panel.remove(fecha2);
        }
        if (fecha1_nombre != null) {
            panel.remove(fecha1_nombre);
        }
        if (fecha2_nombre != null) {
            panel.remove(fecha2_nombre);
        }

        if (opcion2.isSelected()) {
            fecha1_nombre = new JLabel("Fecha de inicio:");
            fecha2_nombre = new JLabel("Fecha de fin:");

            fecha1 = DatePickerPanel.create();
            fecha2 = DatePickerPanel.create();

            layout.putConstraint(SpringLayout.NORTH, fecha1_nombre, 30, SpringLayout.SOUTH, opcion2);
            layout.putConstraint(SpringLayout.WEST, fecha1_nombre, 50, SpringLayout.WEST, tipo);

            layout.putConstraint(SpringLayout.NORTH, fecha1, 20, SpringLayout.SOUTH, fecha1_nombre);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, 0, SpringLayout.HORIZONTAL_CENTER,
                    fecha1_nombre);

            layout.putConstraint(SpringLayout.VERTICAL_CENTER, fecha2_nombre, 0, SpringLayout.VERTICAL_CENTER,
                    fecha1_nombre);
            layout.putConstraint(SpringLayout.EAST, fecha2_nombre, -100, SpringLayout.EAST, panel);

            layout.putConstraint(SpringLayout.NORTH, fecha2, 20, SpringLayout.SOUTH, fecha2_nombre);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2, 0, SpringLayout.HORIZONTAL_CENTER,
                    fecha2_nombre);

            panel.add(fecha1_nombre);
            panel.add(fecha1);
            panel.add(fecha2_nombre);
            panel.add(fecha2);
        }
    }

    /**
     * Método para actualizar el panel
     * 
     * @param nombre nombre
     */
    public void update(String nombre) {
        if (archivo != null) {
            panel.remove(archivo);
        }
        archivo = new JLabel(nombre);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, archivo, 0, SpringLayout.VERTICAL_CENTER, boton_imagen);
        layout.putConstraint(SpringLayout.WEST, archivo, 20, SpringLayout.EAST, boton_imagen);

        panel.add(archivo);

    }

    /**
     * Método para actualizar restart el panel
     */
    public void updateRestart() {

        panel.removeAll();
        if (splitPane != null) {
            this.remove(splitPane);
        }

        layout = new SpringLayout();
        panel = new JPanel(layout);

        text_nombre = new JTextField();
        text_nombre.setPreferredSize(new Dimension(200, 20));

        text_autor = new JTextField();
        text_autor.setPreferredSize(new Dimension(200, 20));

        text_descr = new JTextArea(5, 30);
        text_descr.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

        text_precio = new JTextField();
        text_precio.setPreferredSize(new Dimension(50, 20));

        int len = Sistema.getInstance().getSalasSinExpo().size() + 1;
        List<Sala> salas = Sistema.getInstance().getSalasSinExpo();

        String comboBoxItems[] = new String[len];

        comboBoxItems[0] = "Seleccionar Sala";

        for (int i = 0; i < len - 1; i++) {
            comboBoxItems[i + 1] = salas.get(i).getNombre();
        }
        sala = new JComboBox<String>(comboBoxItems);

        layout.putConstraint(SpringLayout.NORTH, nombre, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nombre, 20, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, text_nombre, 0, SpringLayout.NORTH, nombre);
        layout.putConstraint(SpringLayout.EAST, text_nombre, 400, SpringLayout.WEST, nombre);

        layout.putConstraint(SpringLayout.NORTH, autor, 20, SpringLayout.SOUTH, nombre);
        layout.putConstraint(SpringLayout.WEST, autor, 0, SpringLayout.WEST, nombre);

        layout.putConstraint(SpringLayout.NORTH, text_autor, 0, SpringLayout.NORTH, autor);
        layout.putConstraint(SpringLayout.EAST, text_autor, 400, SpringLayout.WEST, autor);

        layout.putConstraint(SpringLayout.NORTH, descr, 20, SpringLayout.SOUTH, autor);
        layout.putConstraint(SpringLayout.WEST, descr, 0, SpringLayout.WEST, autor);

        layout.putConstraint(SpringLayout.NORTH, text_descr, 0, SpringLayout.NORTH, descr);
        layout.putConstraint(SpringLayout.WEST, text_descr, 0, SpringLayout.WEST, text_autor);

        layout.putConstraint(SpringLayout.NORTH, precio, 100, SpringLayout.SOUTH, descr);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, descr);

        layout.putConstraint(SpringLayout.NORTH, text_precio, 0, SpringLayout.NORTH, precio);
        layout.putConstraint(SpringLayout.WEST, text_precio, 0, SpringLayout.WEST, text_descr);

        layout.putConstraint(SpringLayout.NORTH, imagen, 20, SpringLayout.SOUTH, precio);
        layout.putConstraint(SpringLayout.WEST, imagen, 0, SpringLayout.WEST, precio);

        layout.putConstraint(SpringLayout.NORTH, boton_imagen, 0, SpringLayout.NORTH, imagen);
        layout.putConstraint(SpringLayout.WEST, boton_imagen, 0, SpringLayout.WEST, text_precio);

        layout.putConstraint(SpringLayout.NORTH, salas_nombre, 20, SpringLayout.SOUTH, imagen);
        layout.putConstraint(SpringLayout.WEST, salas_nombre, 0, SpringLayout.WEST, imagen);

        layout.putConstraint(SpringLayout.NORTH, sala, 0, SpringLayout.NORTH, salas_nombre);
        layout.putConstraint(SpringLayout.WEST, sala, 0, SpringLayout.WEST, boton_imagen);

        layout.putConstraint(SpringLayout.NORTH, tipo, 20, SpringLayout.SOUTH, salas_nombre);
        layout.putConstraint(SpringLayout.WEST, tipo, 0, SpringLayout.WEST, salas_nombre);

        layout.putConstraint(SpringLayout.NORTH, opcion1, 0, SpringLayout.NORTH, tipo);
        layout.putConstraint(SpringLayout.WEST, opcion1, 0, SpringLayout.WEST, sala);

        layout.putConstraint(SpringLayout.NORTH, opcion2, 30, SpringLayout.NORTH, tipo);
        layout.putConstraint(SpringLayout.WEST, opcion2, 0, SpringLayout.WEST, boton_imagen);

        layout.putConstraint(SpringLayout.NORTH, boton, -400, SpringLayout.SOUTH, panel);
        layout.putConstraint(SpringLayout.EAST, boton, -50, SpringLayout.EAST, panel);

        panel.add(nombre);
        panel.add(autor);
        panel.add(descr);
        panel.add(precio);
        panel.add(imagen);
        panel.add(salas_nombre);
        panel.add(text_nombre);
        panel.add(text_autor);
        panel.add(text_descr);
        panel.add(text_precio);
        panel.add(boton_imagen);
        panel.add(sala);
        panel.add(tipo);
        panel.add(opcion1);
        panel.add(opcion2);
        panel.add(boton);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panel);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane, BorderLayout.CENTER);

    }

    /**
     * Setter del controlador imagen
     * 
     * @param c actionListener
     */
    public void setControladorImagen(ActionListener c) {
        boton_imagen.addActionListener(c);
    }

    /**
     * Setter del controlador buttonGroup
     * 
     * @param c actionListener
     */
    public void setControladorButtonGroup(ActionListener l) {
        opcion1.addActionListener(l);
        opcion2.addActionListener(l);
    }

    /**
     * Setter del controlador crear
     * 
     * @param c actionListener
     */
    public void setControladorCrear(ActionListener l) {
        boton.addActionListener(l);
    }

    /**
     * Getter del nombre
     * 
     * @return nombre
     */
    public String getText_nombre() {
        return text_nombre.getText();
    }

    /**
     * Getter del autor
     * 
     * @return autor
     */
    public String getText_autor() {
        return text_autor.getText();
    }

    /**
     * Getter del precio
     * 
     * @return precio
     */
    public String getText_precio() {
        return text_precio.getText();
    }

    /**
     * Getter del descr
     * 
     * @return descr
     */
    public String getText_descr() {
        return text_descr.getText();
    }

    /**
     * Getter de la opción 1
     * 
     * @returm opción 1
     */
    public JRadioButton getOpcion1() {
        return opcion1;
    }

    /**
     * Getter del opción2
     * 
     * @return opción2
     */
    public JRadioButton getOpcion2() {
        return opcion2;
    }

    /**
     * Getter del fecha1
     * 
     * @return fecha1
     */
    public JDatePickerImpl getFecha1() {
        return fecha1;
    }

    /**
     * Getter del fecha2
     * 
     * @return fecha2
     */
    public JDatePickerImpl getFecha2() {
        return fecha2;
    }

    /**
     * Getter de sala
     * 
     * @return sala
     */
    public JComboBox<String> getSala() {
        return sala;
    }

}
