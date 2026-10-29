package Interfaz.Controladores;

import java.awt.CardLayout;
import java.time.LocalTime;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Interfaz.Paneles.Gestor.Obras.PanelNuevaObraManual;
import Interfaz.Paneles.Gestor.Obras.PanelObra;
import Interfaz.Paneles.Gestor.Obras.TablaObrasGestor;
import Obra.Audiovisual;
import Obra.Escultura;
import Obra.Fotografia;
import Obra.Pintura;
import Obra.TipoFotografia;
import Sistema.Sistema;
import Utils.Dimensiones;
import Utils.Humedad;
import Utils.Temperatura;

/**
 * Clase que actúa de controlador para añadir una obra manualmente
 * 
 * @author Ignacio Sánchez
 */
public class ControladorAñadirObraManual {
    /**
     * Completa el panel de creación de una obra
     * @param panel panel a completar
     * @param tipoObra combobox con el tipo de obra a añadir
     */
    public static void completarPanelCreacion(PanelNuevaObraManual panel, JComboBox<String> tipoObra) {
        String item = (String) tipoObra.getSelectedItem();
        if (item.equals("Seleccione tipo de obra")) {
            panel.update(0);
            return;
        } else if (item.equals("Pintura")) {
            panel.update(1);
            panel.completarPintura();
            return;
        } else if (item.equals("Escultura")) {
            panel.update(2);
            panel.completarEscultura();
            return;
        } else if (item.equals("Fotografía")) {
            panel.update(3);
            panel.completarFotografía();
            return;
        } else {
            panel.update(4);
            panel.completarAudiovisual();
            return;
        }
    }

    /**
     * Crea y añade una pintura
     * @param vista panel
     * @param table tabla
     * @param valorNombre nombre
     * @param valorAutor autor
     * @param valorFecha fecha
     * @param valorDescr descripcion
     * @param opcion1 boton1 
     * @param opcion2 boton2
     * @param valorPoliza poliza
     * @param valorCuantia cuantia poliza
     * @param alto alto
     * @param ancho ancho
     * @param largo largo
     * @param tempMin temperatura minima
     * @param tempMax temperatura máxima
     * @param humMin humedad mínima
     * @param humMax humedad máxima
     * @param valorTecnica tecnica
     */
    public static void createPintura(PanelObra vista, JTable table, JTextField valorNombre, JTextField valorAutor,
            JTextField valorFecha, JTextArea valorDescr,
            JRadioButton opcion1, JRadioButton opcion2, JTextField valorPoliza, JTextField valorCuantia,
            JTextField alto, JTextField ancho, JTextField largo, JTextField tempMin, JTextField tempMax,
            JTextField humMin, JTextField humMax, JTextField valorTecnica) {

        try {
            String nombre = valorNombre.getText();
            String autor = valorAutor.getText();
            int fecha = Integer.parseInt(valorFecha.getText());
            String descr = valorDescr.getText();
            String poliza = valorPoliza.getText();
            double cuantia = Double.parseDouble(valorCuantia.getText());
            boolean propia;
            if (opcion1.isSelected() || opcion2.isSelected()) {
                propia = opcion1.isSelected();
            } else {
                JOptionPane.showMessageDialog(null, "Debe especificar si la obra es propia o ajena.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double altoNum = Double.parseDouble(alto.getText());
            double anchoNum = Double.parseDouble(ancho.getText());
            double largoNum = 0;
            if (!largo.getText().equals("")) {
                largoNum = Double.parseDouble(largo.getText());
            }
            if (largoNum != 0) {
                throw new IllegalArgumentException("Una fotografía no puede tener largo.");
            }
            Dimensiones dim = new Dimensiones(anchoNum, altoNum, largoNum);
            Temperatura temp = null;
            if (!tempMin.getText().equals("") || !tempMax.getText().equals("")) {
                double min = Double.parseDouble(tempMin.getText());
                double max = Double.parseDouble(tempMax.getText());
                temp = new Temperatura(min, max);
            }
            Humedad hum = null;
            if (!humMin.getText().equals("") || !humMax.getText().equals("")) {
                double min = Double.parseDouble(humMin.getText());
                double max = Double.parseDouble(humMax.getText());
                hum = new Humedad(min, max);
            }
            String tecnica = valorTecnica.getText();

            Pintura pintura = new Pintura(nombre, autor, fecha, propia, poliza, cuantia, descr, tecnica, dim, temp,
                    hum);

            Sistema.getInstance().addObras(pintura);

            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)table.getModel(), Sistema.getInstance().getObras());

            vista.getDetallesObra().update(pintura);

            ((CardLayout) vista.getLayout()).show(vista, "Detalles obra");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Crea y añade una escultura
     * @param vista panel
     * @param table tabla
     * @param valorNombre nombre
     * @param valorAutor autor
     * @param valorFecha fecha
     * @param valorDescr descripcion
     * @param opcion1 boton1 
     * @param opcion2 boton2
     * @param valorPoliza poliza
     * @param valorCuantia cuantia poliza
     * @param alto alto
     * @param ancho ancho
     * @param largo largo
     * @param tempMin temperatura minima
     * @param tempMax temperatura máxima
     * @param humMin humedad mínima
     * @param humMax humedad máxima
     * @param valorMaterial material
     */
    public static void createEscultura(PanelObra vista, JTable table, JTextField valorNombre, JTextField valorAutor,
            JTextField valorFecha, JTextArea valorDescr,
            JRadioButton opcion1, JRadioButton opcion2, JTextField valorPoliza, JTextField valorCuantia,
            JTextField alto, JTextField ancho, JTextField largo, JTextField tempMin, JTextField tempMax,
            JTextField humMin, JTextField humMax, JTextField valorMaterial) {

        try {
            String nombre = valorNombre.getText();
            String autor = valorAutor.getText();
            int fecha = Integer.parseInt(valorFecha.getText());
            String descr = valorDescr.getText();
            String poliza = valorPoliza.getText();
            double cuantia = Double.parseDouble(valorCuantia.getText());
            boolean propia;
            if (opcion1.isSelected() || opcion2.isSelected()) {
                propia = opcion1.isSelected();
            } else {
                JOptionPane.showMessageDialog(null, "Debe especificar si la obra es propia o ajena.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double altoNum = Double.parseDouble(alto.getText());
            double anchoNum = Double.parseDouble(ancho.getText());
            double largoNum = Double.parseDouble(largo.getText());
            Dimensiones dim = new Dimensiones(anchoNum, altoNum, largoNum);
            Temperatura temp = null;
            if (!tempMin.getText().equals("") || !tempMax.getText().equals("")) {
                double min = Double.parseDouble(tempMin.getText());
                double max = Double.parseDouble(tempMax.getText());
                temp = new Temperatura(min, max);
            }
            Humedad hum = null;
            if (!humMin.getText().equals("") || !humMax.getText().equals("")) {
                double min = Double.parseDouble(humMin.getText());
                double max = Double.parseDouble(humMax.getText());
                hum = new Humedad(min, max);
            }
            String material = valorMaterial.getText();

            Escultura escultura = new Escultura(nombre, autor, fecha, propia, poliza, cuantia, descr, material, dim,
                    temp, hum);

            Sistema.getInstance().addObras(escultura);

            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)table.getModel(), Sistema.getInstance().getObras());

            vista.getDetallesObra().update(escultura);

            ((CardLayout) vista.getLayout()).show(vista, "Detalles obra");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Crea y añade una fotografia
     * @param vista panel
     * @param table tabla
     * @param valorNombre nombre
     * @param valorAutor autor
     * @param valorFecha fecha
     * @param valorDescr descripcion
     * @param opcion1 boton1 
     * @param opcion2 boton2
     * @param valorPoliza poliza
     * @param valorCuantia cuantia poliza
     * @param alto alto
     * @param ancho ancho
     * @param largo largo
     * @param tempMin temperatura minima
     * @param tempMax temperatura máxima
     * @param humMin humedad mínima
     * @param humMax humedad máxima
     * @param tipoFoto tipo
     */
    public static void createFotografia(PanelObra vista, JTable table, JTextField valorNombre, JTextField valorAutor,
            JTextField valorFecha, JTextArea valorDescr,
            JRadioButton opcion1, JRadioButton opcion2, JTextField valorPoliza, JTextField valorCuantia,
            JTextField alto, JTextField ancho, JTextField largo, JTextField tempMin, JTextField tempMax,
            JTextField humMin, JTextField humMax, JComboBox<String> tipoFoto) {

        try {
            String nombre = valorNombre.getText();
            String autor = valorAutor.getText();
            int fecha = Integer.parseInt(valorFecha.getText());
            String descr = valorDescr.getText();
            String poliza = valorPoliza.getText();
            double cuantia = Double.parseDouble(valorCuantia.getText());
            boolean propia;
            if (opcion1.isSelected() || opcion2.isSelected()) {
                propia = opcion1.isSelected();
            } else {
                JOptionPane.showMessageDialog(null, "Debe especificar si la obra es propia o ajena.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double altoNum = Double.parseDouble(alto.getText());
            double anchoNum = Double.parseDouble(ancho.getText());
            double largoNum = 0;
            if (!largo.getText().equals("")) {
                largoNum = Double.parseDouble(largo.getText());
            }
            if (largoNum != 0) {
                throw new IllegalArgumentException("Una fotografía no puede tener largo.");
            }
            Dimensiones dim = new Dimensiones(anchoNum, altoNum, largoNum);
            Temperatura temp = null;
            if (!tempMin.getText().equals("") || !tempMax.getText().equals("")) {
                double min = Double.parseDouble(tempMin.getText());
                double max = Double.parseDouble(tempMax.getText());
                temp = new Temperatura(min, max);
            }
            Humedad hum = null;
            if (!humMin.getText().equals("") || !humMax.getText().equals("")) {
                double min = Double.parseDouble(humMin.getText());
                double max = Double.parseDouble(humMax.getText());
                hum = new Humedad(min, max);
            }
            String valorTipoFoto = (String) tipoFoto.getSelectedItem();
            TipoFotografia tipo = TipoFotografia.BYN;
            if (valorTipoFoto.equals("Seleccionar tipo")) {
                throw new IllegalArgumentException("Debe seleccionar un tipo de fotografía.");
            } else if (valorTipoFoto.equals("Blanco y negro")) {
                tipo = TipoFotografia.BYN;
            } else {
                tipo = TipoFotografia.COLOR;
            }

            Fotografia foto = new Fotografia(nombre, autor, fecha, propia, poliza, cuantia, descr, tipo, dim, temp,
                    hum);

            Sistema.getInstance().addObras(foto);

            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)table.getModel(), Sistema.getInstance().getObras());

            vista.getDetallesObra().update(foto);

            ((CardLayout) vista.getLayout()).show(vista, "Detalles obra");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Crear audiovisual
     * @param vista panel
     * @param table tabla
     * @param valorNombre nombre
     * @param valorAutor autor
     * @param valorFecha fecha
     * @param valorDescr descripcion
     * @param opcion1 boton1
     * @param opcion2 boton2
     * @param valorPoliza poliza
     * @param valorCuantia cuantia
     * @param idioma idioma
     * @param horas horas
     * @param minutos minutos
     * @param segundos segundos
     */
    public static void createAudiovisual(PanelObra vista, JTable table, JTextField valorNombre, JTextField valorAutor,
            JTextField valorFecha, JTextArea valorDescr,
            JRadioButton opcion1, JRadioButton opcion2, JTextField valorPoliza, JTextField valorCuantia,
            JTextField idioma, JTextField horas, JTextField minutos, JTextField segundos) {

        try {
            String nombre = valorNombre.getText();
            String autor = valorAutor.getText();
            int fecha = Integer.parseInt(valorFecha.getText());
            String descr = valorDescr.getText();
            String poliza = valorPoliza.getText();
            double cuantia = Double.parseDouble(valorCuantia.getText());
            boolean propia;
            if (opcion1.isSelected() || opcion2.isSelected()) {
                propia = opcion1.isSelected();
            } else {
                JOptionPane.showMessageDialog(null, "Debe especificar si la obra es propia o ajena.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valorIdioma = idioma.getText();
            int horasNum = Integer.parseInt(horas.getText());
            int minutosNum = Integer.parseInt(minutos.getText());
            int segundosNum = Integer.parseInt(segundos.getText());

            LocalTime duracion = LocalTime.of(horasNum, minutosNum, segundosNum);

            Audiovisual video = new Audiovisual(nombre, autor, fecha, propia, poliza, cuantia, descr, duracion, valorIdioma, 0, 0, 0);

            Sistema.getInstance().addObras(video);

            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)table.getModel(), Sistema.getInstance().getObras());

            vista.getDetallesObra().update(video);

            ((CardLayout) vista.getLayout()).show(vista, "Detalles obra");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
