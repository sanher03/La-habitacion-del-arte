package Interfaz.Controladores;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Interfaz.Paneles.Cliente.Sorteos.PanelDetallesSorteo;
import Sistema.Sistema;
import Sorteo.Participante;
import Sorteo.Sorteo;
import Usuario.ClienteRegistrado;

/**
 * Controlador sorteos de cliente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class ControladorSorteosCliente {

    /**
     * Incribir participante en sorteo
     * @param panel panel detalles sorteos
     * @param sorteo sorteo a inscribirse
     * @param entradas numero de entradas
     */
    public static void inscribirse(PanelDetallesSorteo panel, Sorteo sorteo, JComboBox<String> entradas) {
        String nEntradas = (String) entradas.getSelectedItem();

        if (nEntradas.equals("Seleccione número de entradas")) {
            JOptionPane.showMessageDialog(null, "Debe elegir un número de entradas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {
                sorteo.inscribirParticipante(new Participante(Integer.parseInt(nEntradas), (ClienteRegistrado) Sistema.getInstance().getUsuarioActivo()));
                JOptionPane.showMessageDialog(null, "El cliente se ha inscrito satisfactoriamente en el sorteo.", "Inscripción completada", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            return;
            
        }
    }

}
