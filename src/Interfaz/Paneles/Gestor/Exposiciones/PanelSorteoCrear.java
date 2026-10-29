package Interfaz.Paneles.Gestor.Exposiciones;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import org.jdatepicker.impl.JDatePickerImpl;

import Exposicion.Exposicion;
import Interfaz.Paneles.Common.DatePickerPanel;
import Interfaz.Controladores.ControladorSorteosGestor;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Sistema.Sistema;

/**
 * Panel sorteo crear
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelSorteoCrear extends JPanel {
    PanelTitulo titulo;
    JPanel panelInferior;
    JSplitPane completo;
    JButton crear;
    ActionListener anterior;
    JDatePickerImpl inicio, fin, fecha1, fecha2;
    Exposicion expo;
    JComboBox<String> comboBox, tipoSorteo, horas;
    SpringLayout layout;
    JLabel labelFecha1, labelFecha2, labelHoras;
    PanelSorteo panel;

    /**
     * Constructor del panel sorteo crear
     * 
     * @param panel panel
     */
    public PanelSorteoCrear(PanelSorteo panel) {
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

        String[] opciones2 = { "Seleccione tipo de sorteo", "Para fecha y hora", "Para plazo dado",
                "Mientras dure la exposición" };

        tipoSorteo = new JComboBox<>(opciones2);

        tipoSorteo.setSelectedIndex(index);

        setTipoSorteoControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tipoSorteo, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, tipoSorteo, 80, SpringLayout.NORTH, panelInferior);

        panelInferior.add(tipoSorteo);

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Método para crear el panel común
     */
    public void panelComun() {
        JLabel nEntradas = new JLabel("Seleccione el número de entradas a destinar al sorteo:");

        String[] opciones1 = new String[101];

        opciones1[0] = "Número de entradas";

        for (int i = 1; i <= 100; i++) {
            opciones1[i] = Integer.toString(i);
        }

        // Creamos el JComboBox
        comboBox = new JComboBox<>(opciones1);

        // Creamos un JScrollPane y añadimos el JComboBox a él
        JScrollPane scrollPane = new JScrollPane(comboBox);
        scrollPane.setPreferredSize(new Dimension(200, 50)); // Tamaño deseado del JScrollPane

        JLabel label = new JLabel("Seleccione el intervalo en el que los clientes podrán inscribirse:");
        JLabel fecha1Label = new JLabel("Inicio:");
        JLabel fecha2Label = new JLabel("Fin:");
        inicio = DatePickerPanel.create();

        fin = DatePickerPanel.create();

        crear = new SuperButton("Crear sorteo", 250);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nEntradas, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, comboBox, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1Label, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2Label, 180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inicio, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fin, 180, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, nEntradas, 50, SpringLayout.SOUTH, tipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, comboBox, 50, SpringLayout.SOUTH, nEntradas);
        layout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.SOUTH, comboBox);
        layout.putConstraint(SpringLayout.NORTH, fecha1Label, 50, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.NORTH, fecha2Label, 50, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.NORTH, inicio, 50, SpringLayout.SOUTH, fecha1Label);
        layout.putConstraint(SpringLayout.NORTH, fin, 50, SpringLayout.SOUTH, fecha2Label);

        panelInferior.add(nEntradas);
        panelInferior.add(comboBox);
        panelInferior.add(label);
        panelInferior.add(fecha1Label);
        panelInferior.add(fecha2Label);
        panelInferior.add(inicio);
        panelInferior.add(fin);
    }

    /**
     * Método para completar sorteo fecha hora
     */
    public void completarFechaHora() {

        panelComun();

        fecha1 = DatePickerPanel.create();

        horas = horasDisponibles();

        labelFecha1 = new JLabel("Fecha de visita:");
        labelHoras = new JLabel("Hora de visita:");

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFecha1, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelHoras, 180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, horas, 180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, crear, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, labelFecha1, 50, SpringLayout.SOUTH, fin);
        layout.putConstraint(SpringLayout.NORTH, labelHoras, 50, SpringLayout.SOUTH, fin);
        layout.putConstraint(SpringLayout.NORTH, fecha1, 50, SpringLayout.SOUTH, labelFecha1);
        layout.putConstraint(SpringLayout.NORTH, horas, 50, SpringLayout.SOUTH, labelFecha1);
        layout.putConstraint(SpringLayout.NORTH, crear, 50, SpringLayout.SOUTH, fecha1);

        panelInferior.add(labelFecha1);
        panelInferior.add(labelHoras);
        panelInferior.add(fecha1);
        panelInferior.add(horas);
        panelInferior.add(crear);

        setCrearSorteoFechaHoraControlador(panel);

    }

    /**
     * Método para completar sorteo plazo dado
     */
    public void completarPlazoDado() {

        panelComun();

        fecha1 = DatePickerPanel.create();

        fecha2 = DatePickerPanel.create();

        labelFecha1 = new JLabel("Inicio:");
        labelFecha2 = new JLabel("Fin:");

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFecha1, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFecha2, 180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha1, -180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha2, 180, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, crear, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, labelFecha1, 50, SpringLayout.SOUTH, fin);
        layout.putConstraint(SpringLayout.NORTH, labelFecha2, 50, SpringLayout.SOUTH, fin);
        layout.putConstraint(SpringLayout.NORTH, fecha1, 50, SpringLayout.SOUTH, labelFecha1);
        layout.putConstraint(SpringLayout.NORTH, fecha2, 50, SpringLayout.SOUTH, labelFecha1);
        layout.putConstraint(SpringLayout.NORTH, crear, 50, SpringLayout.SOUTH, fecha1);

        panelInferior.add(labelFecha1);
        panelInferior.add(labelFecha2);
        panelInferior.add(fecha1);
        panelInferior.add(fecha2);
        panelInferior.add(crear);

        setCrearSorteoPlazoDadoControlador(panel);
    }

    /**
     * Método para completar sorteo mientras dure expo
     */
    public void completarMientrasDureExpo() {
        panelComun();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, crear, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, crear, 50, SpringLayout.SOUTH, fin);

        panelInferior.add(crear);

        setCrearSorteoMientrasDureExpoControlador(panel);

    }

    /**
     * Setter del controlador para crear sorteo plazo dado
     * @param vista vista
     */
    public void setCrearSorteoPlazoDadoControlador(PanelSorteo vista) {

        crear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosGestor.createSorteoPlazoDado(vista, comboBox, inicio, fin, fecha1, fecha2, expo);
            }

        });
    }

    /**
     * Setter del controlador para crear sorteo fecha hora
     * @param vista vista
     */
    public void setCrearSorteoFechaHoraControlador(PanelSorteo vista) {

        crear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosGestor.createSorteoFechaHora(vista, comboBox, inicio, fin, fecha1, horas, expo);
            }

        });
    }

    /**
     * Setter del controlador para crear sorteo mientras dure
     * @param vista vista
     */
    public void setCrearSorteoMientrasDureExpoControlador(PanelSorteo vista) {

        crear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosGestor.createSorteoMientrasDureExp(vista, comboBox, inicio, fin, expo);
            }

        });
    }

    /**
     * Setter del controlador para el tipo de sorteo
     */
    public void setTipoSorteoControlador() {

        PanelSorteoCrear panel = this;

        tipoSorteo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosGestor.completarPanelCreacion(panel, tipoSorteo);
            }

        });
    }

    /**
     * Método para extraer las horas disponibles
     */
    public static JComboBox<String> horasDisponibles() {
        List<String> horasEnIntervalo = new ArrayList<>();

        // Generar las horas en punto dentro del intervalo
        LocalTime horaActual = Sistema.getInstance().getHorario().getApertura();
        LocalTime horaFin = Sistema.getInstance().getHorario().getCierre();
        while (horaActual.isBefore(horaFin) || horaActual.equals(horaFin)) {
            // Agregar la hora actual a la lista de horas en punto
            horasEnIntervalo.add(horaActual.toString());

            // Incrementar la hora actual en 1 hora
            horaActual = horaActual.plusHours(1);
        }

        horasEnIntervalo.add(0, "Seleccionar hora");

        JComboBox<String> comboBox = new JComboBox<>(horasEnIntervalo.toArray(new String[0]));

        return comboBox;
    }

    /**
     * Getter de la exposición
     * @return expo
     */
    public Exposicion getExposicion() {
        return expo;
    }

}
