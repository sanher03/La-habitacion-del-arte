package Interfaz.Paneles.Common;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;

import Sistema.Sistema;
import Exposicion.*;
import java.util.List;

/**
 * Panel para listar las exposiciones
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelListaExposiciones extends JPanel{
    JTable tabla;
    JScrollPane scroll;
    TablaExposiciones expsTabla;

    /**
     * Constructor del PanelListaExposiciones
     * 
     * @param botonRenderer botón renderizado para las celdas
     */
    public PanelListaExposiciones(TableCellRenderer botonrenderer) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); 

        expsTabla = new TablaExposiciones();

        tabla = expsTabla.create(botonrenderer);
        scroll = new JScrollPane(tabla);
        this.add(scroll, BorderLayout.CENTER);
    }

    /**
     * Función para actualizar el panel
     */
    public void update(){
        List<Exposicion> expos = Sistema.getInstance().exposicionesDisponibles();
        expsTabla.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), expos);
    }

    /**
     * Getter del atributo tablaExposiciones
     * 
     * @return tablaExposiciones
     */
    public TablaExposiciones getExpsTabla() {
        return expsTabla;
    }

    /**
     * Getter del atributo tabla
     * 
     * @return tabla
     */
    public JTable getTabla(){
        return tabla;
    }
}
