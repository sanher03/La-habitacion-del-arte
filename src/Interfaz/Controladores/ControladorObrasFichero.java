package Interfaz.Controladores;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interfaz.Paneles.Gestor.Obras.PanelNuevaObraFichero;
import Interfaz.Paneles.Gestor.Obras.TablaObrasGestor;
import Sistema.Sistema;
import Utils.Status;

/**
 * Controlador para añadir obras por fichero
 * 
 * @author Ignacio Sánchez
 */
public class ControladorObrasFichero {
    /**
     * File chooser para elegir fichero
     * @param panel panel donde mostrarlo
     */
    public static void setControladorElegirFichero(PanelNuevaObraFichero panel) {
        JFileChooser fileChooser = new JFileChooser();

        String userDir = System.getProperty("user.dir");
        String subDir = "resources";
        fileChooser.setCurrentDirectory(new File(userDir, subDir));

        // Mostrar el selector de archivos
        int result = fileChooser.showOpenDialog(panel);

        // Verificar si el usuario seleccionó un archivo
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            java.io.File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(panel, "Archivo seleccionado: " + selectedFile.getName());
            panel.update(selectedFile);
        }
    }

    /**
     * Cargar obras de fichero
     * @param table tabla de obras
     * @param filename nombre del fichero
     */
    public static void importarObrasDeFichero(JTable table, String filename) {
        String completeFilename = "resources/" + filename;
        if (Sistema.getInstance().cargarObrasDeFichero(completeFilename) == Status.OK) {
            JOptionPane.showMessageDialog(null, "Se han importado las obras del fichero " + filename + ".", "Obras importadas", JOptionPane.INFORMATION_MESSAGE);
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)table.getModel(), Sistema.getInstance().getObras());
        } else {
            JOptionPane.showMessageDialog(null, "No se ha podido importar las obras del fichero " + filename + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
