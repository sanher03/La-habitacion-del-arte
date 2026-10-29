package Interfaz.Controladores;

import javax.swing.*;

import Exposicion.EstadoExposicion;
import Exposicion.ExposicionPermanente;
import Exposicion.ExposicionTemporal;
import Exposicion.Exposicion;

import Sala.*;
import Sistema.Sistema;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

import Interfaz.Paneles.Common.DateConversion;
import Interfaz.Paneles.Gestor.Exposiciones.PanelExposicionesGestorCentral;
import Interfaz.Paneles.Gestor.Exposiciones.PanelListaExpoGestor;
import Interfaz.Paneles.Gestor.Exposiciones.PanelProgramarExposicion;

public class ControladorProgramarExpo {
    private PanelProgramarExposicion vista;
    private String archivo;
    private PanelExposicionesGestorCentral central;

    public ControladorProgramarExpo(PanelProgramarExposicion vista, PanelListaExpoGestor lista, PanelExposicionesGestorCentral central) {
        this.vista = vista;
        this.archivo = null;
        this.central = central;
    }

    public void seleccionarTipo(){
        vista.update();
        vista.revalidate();
        vista.repaint();
    }

    public void seleccionarImagen(){
        JFileChooser fileChooser = new JFileChooser();

        String userDir = System.getProperty("user.dir");
        String subDir = "resources";
        fileChooser.setCurrentDirectory(new File(userDir, subDir));

        int result = fileChooser.showOpenDialog(vista);

        if(result == JFileChooser.APPROVE_OPTION){
            java.io.File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(vista, "Archivo seleccionado: " + selectedFile.getName());
            this.archivo = selectedFile.getAbsolutePath();
            vista.update(selectedFile.getName());
            vista.revalidate();
        }
    }

    public void programarExposicion(){
        try{
            if(((String)vista.getSala().getSelectedItem()).equals("Seleccionar Sala")){
                JOptionPane.showMessageDialog(null, "Selecciona un tipo de exposicion a crear");
                return;
            }
            if(this.archivo == null){
                JOptionPane.showMessageDialog(null, "Selecciona una imagen para la exposición");
                return;
            }
            Exposicion expo;
            String nombre_archivo = new String(this.archivo);
            if(vista.getOpcion1().isSelected()){
                expo = new ExposicionPermanente(vista.getText_nombre(), vista.getText_autor(), vista.getText_descr(), 
                                                Double.parseDouble(vista.getText_precio()), EstadoExposicion.EN_CREACION, nombre_archivo);
               
            }
            else if(vista.getOpcion2().isSelected()){
                Date selectedValue1 = (Date) vista.getFecha1().getModel().getValue();
                Date selectedValue2 = (Date) vista.getFecha2().getModel().getValue();

                LocalDate selectedDate1 = DateConversion.convertToLocalDateViaInstant(selectedValue1);
                LocalDate selectedDate2 = DateConversion.convertToLocalDateViaInstant(selectedValue2);
                expo = new ExposicionTemporal(vista.getText_nombre(), vista.getText_autor(), vista.getText_descr(), 
                                                Double.parseDouble(vista.getText_precio()), EstadoExposicion.EN_CREACION, nombre_archivo, selectedDate1, selectedDate2);
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecciona un tipo de exposicion a crear");
                return;
            }

            for(Sala s: Sistema.getInstance().getSalasHojas()){
                if(((String)vista.getSala().getSelectedItem()).equals(s.getNombre())){
                    expo.addSalas(s);
                }
            }


            Sistema.getInstance().getExposiciones().add(expo);

            central.update();
            vista.revalidate();
            this.archivo = null;
            JOptionPane.showMessageDialog(null, "Modificacion completada");
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
