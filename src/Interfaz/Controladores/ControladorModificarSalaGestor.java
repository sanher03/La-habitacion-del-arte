package Interfaz.Controladores;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Exposicion.EstadoExposicion;

import java.awt.*;

import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.TipoBotonSala;
import Interfaz.Paneles.Gestor.Salas.*;
import Sala.Sala;
import Sala.SalaClimatizada;
import Sala.SalaNoClimatizada;
import Sistema.Sistema;
import Utils.Dimensiones;
import Utils.Humedad;
import Utils.Temperatura;

/**
 * Controlador modificar sala de gestor
 * 
 * @author Ignacio Sánchez
 */
public class ControladorModificarSalaGestor {
    private PanelSalasGestor vista;
    private JTable tabla;
    private Sala sala;
    private boolean climatizada;

    /**
     * Constructor del controlador
     * @param vista panel de salas de gestor
     */
    public ControladorModificarSalaGestor(PanelSalasGestor vista) {
        this.vista = vista;
        this.tabla = vista.getLista_salas().getTabla();
    }

    /**
     * Seleccionar sala de la lista
     * @param boton tipo de boton
     */
    public void seleccionarSala(TipoBotonSala boton) {
        List<Sala> salas = Sistema.getInstance().getSalasHojas();
        int fila = tabla.getSelectedRow();
        if (boton != TipoBotonSala.NUEVO) {
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una sala a modificar");
                return;
            }

            this.sala = salas.get(fila);
        }

        if (boton == TipoBotonSala.NUEVO) {
            ((CardLayout) vista.getPanel_derecho().getLayout()).show(vista.getPanel_derecho(),
                    InterfazUtils.NUEVA_SALA);
            /*
             * vista.getPanel_nuevo().update(this.sala);
             * vista.getPanel_nuevo().revalidate();
             */
        } else if (boton == TipoBotonSala.EDITAR) {
            ((CardLayout) vista.getPanel_derecho().getLayout()).show(vista.getPanel_derecho(),
                    InterfazUtils.EDITAR_SALA);
            vista.getPanel_editar().update(this.sala);
            vista.getPanel_editar().revalidate();
        } else if (boton == TipoBotonSala.ELIMINAR) {
            ((CardLayout) vista.getPanel_derecho().getLayout()).show(vista.getPanel_derecho(),
                    InterfazUtils.ELIMINAR_SALA);

        } else if (boton == TipoBotonSala.DIVIDIR) {
            ((CardLayout) vista.getPanel_derecho().getLayout()).show(vista.getPanel_derecho(),
                    InterfazUtils.DIVIDIR_SALA);
            vista.getPanel_dividir().update(this.sala);
            vista.getPanel_dividir().revalidate();
        }

    }

    /**
     * Guardar cambios de edición de una sala
     */
    public void guardarCambiosEditar() {
        PanelEditarSala panel_editar = vista.getPanel_editar();

        try {
            sala.setAforo(Integer.parseInt(panel_editar.getText_aforo()));
            sala.setTomasCorriente(Integer.parseInt(panel_editar.getText_corriente()));
            if (this.sala.isClimatizada()) {
                sala.setTemperatura(new Temperatura(Double.parseDouble(panel_editar.getText_t1()),
                        Double.parseDouble(panel_editar.getText_t2())));
                sala.setHumedad(new Humedad(Double.parseDouble(panel_editar.getText_h1()),
                        Double.parseDouble(panel_editar.getText_h2())));
            }

            panel_editar.update(sala);
            TablaSalasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(),
                    Sistema.getInstance().getSalasHojas());
            vista.revalidate();
            JOptionPane.showMessageDialog(null, "Modificacion completada");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * Eliminar una sala
     */
    public void eliminarSala() {

        if (sala.getExpo() != null) {
            if (sala.getExpo().getEstado() != EstadoExposicion.EN_CREACION
                    && sala.getExpo().getEstado() != EstadoExposicion.TERMINADA) {
                JOptionPane.showMessageDialog(null, "Error: La sala alberga una exposición ya en disponibilidad");
                return;
            }
        }
        Sistema.getInstance().getSalas().remove(sala);
        this.sala = null;
        ((CardLayout) vista.getPanel_derecho().getLayout()).show(vista.getPanel_derecho(), InterfazUtils.DEFAULT_SALA);
        TablaSalasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(),
                Sistema.getInstance().getSalasHojas());
        vista.revalidate();
        JOptionPane.showMessageDialog(null, "Modificacion completada");

    }

    /**
     * dividir una sala
     */
    public void dividirSala() {
        PanelDividirSala panel_dividir = vista.getPanel_dividir();

        try {
            if (sala.isClimatizada()) {
                sala.dividirSala(panel_dividir.getPanel_derecho1().getText_nombre(),
                        panel_dividir.getPanel_derecho2().getText_nombre(),
                        Double.parseDouble(panel_dividir.getPanel_derecho1().getText_ancho()),
                        Double.parseDouble(panel_dividir.getPanel_derecho2().getText_ancho()),
                        Integer.valueOf(panel_dividir.getPanel_derecho1().getText_aforo()),
                        Integer.valueOf(panel_dividir.getPanel_derecho2().getText_aforo()),
                        Integer.valueOf(panel_dividir.getPanel_derecho1().getText_corriente()),
                        Integer.valueOf(panel_dividir.getPanel_derecho2().getText_corriente()),
                        new Temperatura(Double.parseDouble(panel_dividir.getPanel_derecho1().getText_t1()),
                                Double.parseDouble(panel_dividir.getPanel_derecho1().getText_t2())),
                        new Temperatura(Double.parseDouble(panel_dividir.getPanel_derecho2().getText_t1()),
                                Double.parseDouble(panel_dividir.getPanel_derecho2().getText_t2())),
                        new Humedad(Double.parseDouble(panel_dividir.getPanel_derecho1().getText_h1()),
                                Double.parseDouble(panel_dividir.getPanel_derecho1().getText_h2())),
                        new Humedad(Double.parseDouble(panel_dividir.getPanel_derecho2().getText_h1()),
                                Double.parseDouble(panel_dividir.getPanel_derecho2().getText_h2())));
            } else {
                sala.dividirSala(panel_dividir.getPanel_derecho1().getText_nombre(),
                        panel_dividir.getPanel_derecho2().getText_nombre(),
                        Integer.valueOf(panel_dividir.getPanel_derecho1().getText_ancho()),
                        Integer.valueOf(panel_dividir.getPanel_derecho2().getText_ancho()),
                        Integer.valueOf(panel_dividir.getPanel_derecho1().getText_aforo()),
                        Integer.valueOf(panel_dividir.getPanel_derecho2().getText_aforo()),
                        Integer.valueOf(panel_dividir.getPanel_derecho1().getText_corriente()),
                        Integer.valueOf(panel_dividir.getPanel_derecho2().getText_corriente()));
            }

            panel_dividir.update(sala.getSubsala1());
            TablaSalasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(),
                    Sistema.getInstance().getSalasHojas());
            vista.revalidate();
            JOptionPane.showMessageDialog(null, "Modificacion completada");
        } catch (IllegalArgumentException | IllegalStateException | IllegalCallerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * Añadir una sala al sistema
     */
    public void aniadirSala() {
        PanelNuevaSala panel_nueva = vista.getPanel_nueva();

        try {
            Sala s = null;
            if (climatizada) {
                s = new SalaClimatizada(panel_nueva.getPanel_derecho().getText_nombre(),
                    Integer.valueOf(panel_nueva.getPanel_derecho().getText_aforo()),
                    Integer.valueOf(panel_nueva.getPanel_derecho().getText_corriente()),
                    new Dimensiones(Double.valueOf(panel_nueva.getPanel_derecho().getText_ancho()),
                            Double.valueOf(panel_nueva.getPanel_derecho().getText_alto()),
                            Double.valueOf(panel_nueva.getPanel_derecho().getText_largo())),
                    new Temperatura(Double.parseDouble(panel_nueva.getPanel_derecho().getText_t1()),
                            Double.parseDouble(panel_nueva.getPanel_derecho().getText_t2())),
                    new Humedad(Double.parseDouble(panel_nueva.getPanel_derecho().getText_h1()),
                            Double.parseDouble(panel_nueva.getPanel_derecho().getText_h2())));
            } else {
                s = new SalaNoClimatizada(panel_nueva.getPanel_derecho().getText_nombre(),
                    Integer.valueOf(panel_nueva.getPanel_derecho().getText_aforo()),
                    Integer.valueOf(panel_nueva.getPanel_derecho().getText_corriente()),
                    new Dimensiones(Double.valueOf(panel_nueva.getPanel_derecho().getText_ancho()),
                            Double.valueOf(panel_nueva.getPanel_derecho().getText_alto()),
                            Double.valueOf(panel_nueva.getPanel_derecho().getText_largo())));
            }

            Sistema.getInstance().addSalas(s);
            TablaSalasGestor.modificarContenidoFilas((DefaultTableModel) tabla.getModel(),
                    Sistema.getInstance().getSalasHojas());
            vista.revalidate();
            JOptionPane.showMessageDialog(null, "Modificacion completada");
        } catch (IllegalArgumentException | IllegalStateException | IllegalCallerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    /**
     * Setter del atributo climatizada
     * @param climatizada elegir añadir climatizada o no
     */
    public void setClimatizada(boolean climatizada) {
        this.climatizada = climatizada;
    }

    
}
