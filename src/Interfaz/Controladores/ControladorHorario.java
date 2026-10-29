package Interfaz.Controladores;

import java.time.LocalTime;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Horario.Horario;
import Interfaz.Paneles.Gestor.Exposiciones.PanelHorario;
import Sistema.Sistema;

/**
 * Controlador del horario
 * 
 * @author Ignacio Sánchez
 */
public class ControladorHorario {
    /**
     * Cambiar horario
     * @param horaInicio apertura
     * @param horaFin cierre
     * @param panel horario panel
     */
    public static void cambiarHorario(JComboBox<String> horaInicio, JComboBox<String> horaFin, PanelHorario panel) {
        String aperturaS = (String) horaInicio.getSelectedItem();
        String cierreS = (String) horaFin.getSelectedItem();

        if (aperturaS.equals("Seleccionar hora de apertura") || cierreS.equals("Seleccionar hora de cierre")) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una hora apertura y de cierre si quiere modificar el horario.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalTime apertura = LocalTime.parse(aperturaS);
        LocalTime cierre = LocalTime.parse(cierreS);

        if (apertura.isAfter(cierre)) {
            JOptionPane.showMessageDialog(null,
                    "La hora de apertura es posterior a la de cierre.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Horario horario = new Horario(apertura, cierre);

        Sistema.getInstance().setHorario(horario);

        panel.update();

        JOptionPane.showMessageDialog(null,
                    "Se ha cambiado el horario.", "Cambio satisfactorio",
                    JOptionPane.INFORMATION_MESSAGE);
    }
}
