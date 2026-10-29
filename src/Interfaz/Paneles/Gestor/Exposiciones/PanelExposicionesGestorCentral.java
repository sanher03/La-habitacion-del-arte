package Interfaz.Paneles.Gestor.Exposiciones;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Interfaz.Controladores.ControladorEditarExpo;
import Interfaz.Controladores.ControladorEliminarExpo;
import Interfaz.Controladores.ControladorGestorExpos;
import Interfaz.Controladores.ControladorProgramarExpo;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.TipoBotonExpo;

/**
 * Constructor del panel central de las exposición del gestor
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelExposicionesGestorCentral extends JPanel {
    PanelLateralGestor panel_lateral;
    PanelListaExpoGestor lista_exposiciones;
    PanelProgramarExposicion panel_programar;
    JPanel panel_derecho, panel_vacio;
    PanelEditarExpo panel_editar;
    PanelBorrarExpoCorrecto panel_borrar_correcto;
    PanelBorrarExpoIncorrecto panel_borrar_incorrecto;
    PanelDescuentos panel_descuentos;
    PanelSorteo panel_sorteo;
    PanelEstadisticasExpo panel_estadisticas;

    /**
     * Constructor del panel de exposiciones central del gestor
     */
    public PanelExposicionesGestorCentral() {
        super();

        this.setLayout(new BorderLayout());
        panel_lateral = new PanelLateralGestor();

        lista_exposiciones = new PanelListaExpoGestor();

        panel_derecho = new JPanel(new CardLayout());

        panel_vacio = new JPanel();
        panel_derecho.add(panel_vacio, InterfazUtils.VACIO);

        panel_programar = new PanelProgramarExposicion();
        panel_derecho.add(panel_programar, InterfazUtils.PROGRAMAR_EXPOSICION);

        panel_editar = new PanelEditarExpo();
        panel_derecho.add(panel_editar, InterfazUtils.EDITAR_EXPO);

        panel_borrar_correcto = new PanelBorrarExpoCorrecto();
        panel_derecho.add(panel_borrar_correcto, InterfazUtils.BORRAR_EXPO_CORRECTO);

        panel_borrar_incorrecto = new PanelBorrarExpoIncorrecto();
        panel_derecho.add(panel_borrar_incorrecto, InterfazUtils.BORRAR_EXPO_INCORRECTO);

        panel_descuentos = new PanelDescuentos();
        panel_derecho.add(panel_descuentos, InterfazUtils.DESCUENTOS_EXPO);

        panel_sorteo = new PanelSorteo();
        panel_derecho.add(panel_sorteo, InterfazUtils.SORTEO_EXPO);

        panel_estadisticas = new PanelEstadisticasExpo();
        panel_derecho.add(panel_estadisticas, InterfazUtils.ESTADISTICAS_EXPO);

        ControladorGestorExpos c = new ControladorGestorExpos(this);

        panel_lateral.setControladorProgramarExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.NUEVO);

            }
        });

        panel_lateral.setControladorEditarExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.EDITAR);

            }
        });

        panel_lateral.setControladorBorrarExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.BORRAR);

            }
        });

        panel_lateral.setControladorDescuentosExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.DESCUENTOS);

            }
        });

        panel_lateral.setControladorEstadisticasExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.ESTADISTICAS);

            }
        });

        panel_lateral.setControladorSorteoExpo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarExpo(TipoBotonExpo.SORTEO);

            }
        });

        ControladorProgramarExpo c_programar = new ControladorProgramarExpo(panel_programar, lista_exposiciones, this);

        panel_programar.setControladorButtonGroup(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_programar.seleccionarTipo();

            }
        });

        panel_programar.setControladorImagen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_programar.seleccionarImagen();

            }
        });

        panel_programar.setControladorCrear(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_programar.programarExposicion();

            }
        });

        ControladorEditarExpo c_editar = new ControladorEditarExpo(panel_editar, lista_exposiciones, this);

        panel_editar.setControladorEditar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_editar.editarExpo();

            }
        });

        panel_editar.setControladorCambiar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_editar.cambiarFase();

            }
        });

        panel_editar.setControladorInterrumpir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_editar.interrumpirExpo();

            }
        });

        panel_editar.setControladorCancelar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_editar.cancelarExpo();

            }
        });

        panel_editar.setControladorProrrogar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_editar.prorrogarExpo();

            }
        });

        ControladorEliminarExpo c_eliminar = new ControladorEliminarExpo(lista_exposiciones, this);

        panel_borrar_correcto.setControladorEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_eliminar.borrarExpo();
            }
        });

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_lateral, lista_exposiciones);
        splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panel_derecho);
        splitPane2.setDividerLocation(1200); // Ancho inicial del panel izquierdo
        splitPane2.setDividerSize(5); // Tamaño del delimitador
        splitPane2.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane2, BorderLayout.CENTER); //
    }

    /**
     * Método para actualizar el panel
     */
    public void update() {
        lista_exposiciones.update();
        ((CardLayout) panel_derecho.getLayout()).show(panel_derecho, InterfazUtils.VACIO);
    }

    /**
     * Getter del panelDerecho
     * 
     * @return panelDerecho
     */
    public JPanel getPanel_derecho() {
        return panel_derecho;
    }

    /**
     * Getter del panelProgramar
     * 
     * @return panelProgramar
     */
    public PanelProgramarExposicion getPanel_programar() {
        return panel_programar;
    }

    /**
     * Getter del panelEditar
     * 
     * @return panelEditar
     */
    public PanelEditarExpo getPanel_editar() {
        return panel_editar;
    }

    /**
     * Getter del panelBorrarCorrecto
     * 
     * @return panelBorrarCorrecto
     */
    public PanelBorrarExpoCorrecto getPanel_borrar_correcto() {
        return panel_borrar_correcto;
    }

    /**
     * Getter del panelBorrarIncorrecto
     * 
     * @return panelBorrarIncorrecto
     */
    public PanelBorrarExpoIncorrecto getPanel_borrar_incorrecto() {
        return panel_borrar_incorrecto;
    }

    /**
     * Getter del panelDescuentos
     * 
     * @return panelDescuentos
     */
    public PanelDescuentos getPanel_descuentos() {
        return panel_descuentos;
    }

    /**
     * Getter del panelSorteo
     * 
     * @return panelSorteo
     */
    public PanelSorteo getPanel_sorteo() {
        return panel_sorteo;
    }

    /**
     * Getter del panelEstadísticas
     * 
     * @return panelEstadísticas
     */
    public PanelEstadisticasExpo getPanel_estadisticas() {
        return panel_estadisticas;
    }

    /**
     * Getter de la lista <de exposiciones
     * 
     * @return lista de exposiciones
     */
    public PanelListaExpoGestor getLista_exposiciones() {
        return lista_exposiciones;
    }

}
