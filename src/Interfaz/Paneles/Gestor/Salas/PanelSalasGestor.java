package Interfaz.Paneles.Gestor.Salas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Interfaz.Controladores.ControladorModificarSalaGestor;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.TipoBotonSala;

/**
 * Panel salas gestor
 */
public class PanelSalasGestor extends JPanel{
    PanelLateralSalasGestor panel_lateral;
    PanelListaSalasGestor lista_salas;
    JPanel panel_derecho, panel_default;
    PanelEditarSala panel_editar;
    PanelEliminarSala panel_eliminar;
    PanelDividirSala panel_dividir;
    PanelNuevaSala panel_nueva;

    /**
     * Constructor del panel 
     */
    public PanelSalasGestor() {
        super();

        this.setLayout(new BorderLayout());
        panel_lateral = new PanelLateralSalasGestor();

        lista_salas = new PanelListaSalasGestor();

        panel_derecho = new JPanel(new CardLayout());

        

        panel_editar = new PanelEditarSala();
        panel_derecho.add(panel_editar, InterfazUtils.EDITAR_SALA);


        panel_eliminar = new PanelEliminarSala();
        panel_derecho.add(panel_eliminar, InterfazUtils.ELIMINAR_SALA);

        panel_dividir = new PanelDividirSala();
        panel_derecho.add(panel_dividir, InterfazUtils.DIVIDIR_SALA);

        panel_default = new JPanel();
        panel_derecho.add(panel_default, InterfazUtils.DEFAULT_SALA);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel_lateral, lista_salas);
        splitPane1.setDividerLocation(200); // Ancho inicial del panel izquierdo
        splitPane1.setDividerSize(5); // Tamaño del delimitador
        splitPane1.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario
        
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panel_derecho);
        splitPane2.setDividerLocation(1300); // Ancho inicial del panel izquierdo
        splitPane2.setDividerSize(5); // Tamaño del delimitador
        splitPane2.setEnabled(true); // Deshabilitar redimensionamiento manual por el usuario

        ControladorModificarSalaGestor c = new ControladorModificarSalaGestor(this);

        panel_nueva = new PanelNuevaSala(c);
        panel_derecho.add(panel_nueva, InterfazUtils.NUEVA_SALA);

        panel_lateral.setControladorNuevo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarSala(TipoBotonSala.NUEVO);
            }
        });

        

        panel_lateral.setControladorEditar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarSala(TipoBotonSala.EDITAR);
            }
        });

        panel_editar.setControladorEditar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.guardarCambiosEditar();
            }
        });

        panel_lateral.setControladorEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarSala(TipoBotonSala.ELIMINAR);
            }
        });

        panel_eliminar.setControladorEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.eliminarSala();;
            }
        });

        panel_lateral.setControladorDividir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.seleccionarSala(TipoBotonSala.DIVIDIR);
            }
        });

        panel_dividir.setControladorDividir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.dividirSala();
            }
        });

        panel_nueva.setControladorAñadir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.aniadirSala();
            }
        });

        JPanel titulo1 = new PanelTitulo("Listado de salas");

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo1, splitPane2);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane2, BorderLayout.CENTER); //
    }

    /**
     * Método para actualizar el panel
     */
    public void update(){
        lista_salas.update();
        ((CardLayout)panel_derecho.getLayout()).show(panel_derecho, InterfazUtils.DEFAULT_SALA);

    }

    /**
     * Getter del panel editar
     * 
     * @return panel editar
     */
    public PanelEditarSala getPanel_editar() {
        return panel_editar;
    }

    /**
     * Getter del panel eliminar
     * 
     * @return panel eliminar
     */
    public PanelEliminarSala getPanel_eliminar() {
        return panel_eliminar;
    }

    /**
     * Getter del panel dividir
     * 
     * @return panel dividir
     */
    public PanelDividirSala getPanel_dividir() {
        return panel_dividir;
    }

    /**
     * Getter del panel lista salas
     * 
     * @return panel lista salas
     */
    public PanelListaSalasGestor getLista_salas() {
        return lista_salas;
    }

    /**
     * Getter del panel nueva
     * 
     * @return panel nueva
     */
    public PanelNuevaSala getPanel_nueva() {
        return panel_nueva;
    }

    /**
     * Getter del panel derecho
     * 
     * @return panel derecho
     */
    public JPanel getPanel_derecho() {
        return panel_derecho;
    }
}
