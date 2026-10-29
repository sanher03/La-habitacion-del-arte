package Interfaz.Paneles.Gestor.Exposiciones;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import Horario.Horario;
import Interfaz.Controladores.ControladorHorario;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Sistema.Sistema;

/**
 * Panel del horario
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelHorario extends JPanel {

    SpringLayout layout;
    JComboBox<String> horaInicio, horaFin;
    private PanelTitulo titulo;
    private JPanel panelInferior;
    private JSplitPane completo;
    private JButton confirmar;

    /**
     * Constructor del panel horario
     */
    public PanelHorario() {
        super(new BorderLayout());
        update();
    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        this.removeAll();

        JLabel textoApertura = new JLabel("Apertura:");

        horaInicio = horasDisponibles();

        JLabel textoCierre = new JLabel("Cierre:");

        horaFin = horasDisponibles();

        Horario horario = Sistema.getInstance().getHorario();

        JLabel horarioTexto = new JLabel(
                "Horario actual: " + horario.getApertura().format(DateTimeFormatter.ofPattern("HH:mm")) + " h. - "
                        + horario.getCierre().format(DateTimeFormatter.ofPattern("HH:mm")) + " h.");

        JLabel cambiarTexto = new JLabel("Seleccione una nueva de apertura y cierre si quiere cambiar el horario.");

        confirmar = new SuperButton("Confirmar", 150);

        setConfirmarControlador();

        titulo = new PanelTitulo("Cambiar horario");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, horarioTexto, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cambiarTexto, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoApertura, -100, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoCierre, 100, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, horaInicio, -100, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, horaFin, 100, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmar, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, horarioTexto, 200, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, cambiarTexto, 100, SpringLayout.VERTICAL_CENTER,
                horarioTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoApertura, 100, SpringLayout.NORTH, cambiarTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoCierre, 100, SpringLayout.VERTICAL_CENTER,
                cambiarTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, horaInicio, 100, SpringLayout.VERTICAL_CENTER, textoApertura);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, horaFin, 100, SpringLayout.VERTICAL_CENTER,
                textoApertura);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, confirmar, 100, SpringLayout.VERTICAL_CENTER,
                horaInicio);

        panelInferior.add(horarioTexto);
        panelInferior.add(cambiarTexto);
        panelInferior.add(textoApertura);
        panelInferior.add(textoCierre);
        panelInferior.add(horaInicio);
        panelInferior.add(horaFin);
        panelInferior.add(confirmar);

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Método para extraer las horas disponibles
     * @return horas disponibles
     */
    public static JComboBox<String> horasDisponibles() {
        List<String> horasEnIntervalo = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            horasEnIntervalo.add(LocalTime.of(i, 0, 0).format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        horasEnIntervalo.add(0, "Seleccionar hora");

        JComboBox<String> comboBox = new JComboBox<>(horasEnIntervalo.toArray(new String[0]));

        return comboBox;
    }

    /**
     * Setter del controlador para confirmar
     */
    public void setConfirmarControlador() {
        PanelHorario panel = this;

        confirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorHorario.cambiarHorario(horaInicio, horaFin, panel);
            }

        });
    }
}
