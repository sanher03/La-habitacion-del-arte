package Interfaz.Controladores;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Exposicion.Exposicion;
import Interfaz.Paneles.Gestor.Obras.PanelAlmacenar;
import Interfaz.Paneles.Gestor.Obras.PanelDetallesObra;
import Interfaz.Paneles.Gestor.Obras.PanelExponerObra;
import Interfaz.Paneles.Gestor.Obras.PanelNuevaObraManual;
import Interfaz.Paneles.Gestor.Obras.PanelObra;
import Interfaz.Paneles.Gestor.Obras.PanelPrestar;
import Interfaz.Paneles.Gestor.Obras.PanelRestaurar;
import Interfaz.Paneles.Gestor.Obras.PanelRetirar;
import Interfaz.Paneles.Gestor.Obras.TablaObrasGestor;
import Obra.Obra;
import Sala.Sala;
import Sistema.Sistema;

/**
 * Controlador central de obras de gestor
 * 
 * @author Ignacio Sánchez
 */
public class ControladorObrasCentral {
    /**
     * Mostrar panel detalles de una obra
     * @param vista panel de obra
     * @param panelObras panel detalles de obra
     * @param tabla tabla de obras
     */
    public static void mostrarPanelDetallesObra(PanelObra vista, PanelDetallesObra panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para comprobar sus detalles",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.update(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Detalles obra");
    }

    /**
     * Mostrar panel para añadir una obra
     * @param vista panel a cambiar
     */
    public static void mostrarPanelNuevaObra(PanelObra vista) {

        PanelNuevaObraManual panel = vista.getNuevaObra().getManual();

        panel.update(0);
        
        ((CardLayout) vista.getLayout()).show(vista, "Nueva obra");
    }

    /**
     * Mostrar panel para exponer obra
     * 
     * @param vista panel a cambiar
     * @param panelObras panel para exponer
     * @param tabla tabla de obras
     */
    public static void mostrarPanelExponerObra(PanelObra vista, PanelExponerObra panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para poder exponerla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.updateExponer(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Exponer obra");
    }

    /**
     * Mostrar panel de almacenar obra
     * @param vista vista a cambiar
     * @param panelObras panel de almacenar
     * @param tabla tabla de obras
     */
    public static void mostrarPanelAlmacenarObra(PanelObra vista, PanelAlmacenar panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para poder almacenarla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.update(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Almacenar obra");
    }

    /**
     * Mostrar panel retirar obras
     * @param vista vista a cambiar
     * @param panelObras panel de retirar
     * @param tabla tabla de obras
     */
    public static void mostrarPanelRetirarObra(PanelObra vista, PanelRetirar panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para poder retirarla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.update(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Retirar obra");
    }

    /**
     * Mostrar panel restaurar obras
     * @param vista vista a cambiar
     * @param panelObras panel de restaurar
     * @param tabla tabla de obras
     */
    public static void mostrarPanelRestaurarObra(PanelObra vista, PanelRestaurar panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para poder restaurarla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.update(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Restaurar obra");
    }

    /**
     * Mostrar panel prestar obras
     * @param vista vista a cambiar
     * @param panelObras panel de prestar
     * @param tabla tabla de obras
     */
    public static void mostrarPanelPrestarObra(PanelObra vista, PanelPrestar panelObras,
            JTable tabla) {
        List<Obra> obras = Sistema.getInstance().getObras();
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un obra para poder prestarla",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Obra obra = obras.get(fila);
        panelObras.update(obra);
        ((CardLayout) vista.getLayout()).show(vista, "Prestar obra");
    }

    /**
     * Completar panel exponer obras
     * @param vista vista a cambiar
     * @param panelObras panel de exponer
     * @param tabla tabla de obras
     */
    public static void completarPanelExponerObra(JComboBox<String> exposAElegir, PanelExponerObra panel) {
        int index = exposAElegir.getSelectedIndex();

        if (index != 0)
            panel.elegirSalas(Sistema.getInstance().getExposiciones().get(index - 1));
        else
            panel.updateExponer(null);
    }

    /**
     * Completar boton de exponer
     * @param salasAElegir sala donde exponer
     * @param panel panel de exposicion
     */
    public static void completarBotonExponerObra(JComboBox<String> salasAElegir, PanelExponerObra panel) {
        int index = salasAElegir.getSelectedIndex();

        if (index != 0)
            panel.completarBotonExponer(panel.getExpo().getSalas().get(index-1));
    }

    /**
     * Exponer obra
     * @param obra obra
     * @param expo exposicion
     * @param sala sala
     * @param tabla tabla de obras
     */
    public static void exponer(Obra obra, Exposicion expo, Sala sala, JTable tabla) {
        try {
            obra.exponer(expo, sala);
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getObras());
            JOptionPane.showMessageDialog(null, "Obra expuesta");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Almacenar obra
     * @param obra obra
     * @param tabla tabla de obras
     */
    public static void almacenar(Obra obra, JTable tabla) {
        try {
            obra.almacenar();
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getObras());
            JOptionPane.showMessageDialog(null, "Obra metida en Almacén");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retirar obra
     * @param obra obra
     * @param tabla tabla de obras
     */
    public static void retirar(Obra obra, JTable tabla) {
        try {
            obra.retirar();
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getObras());
            JOptionPane.showMessageDialog(null, "Obra retirada");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Restaurar obra
     * @param obra obra
     * @param tabla tabla de obras
     */
    public static void restaurar(Obra obra, JTable tabla) {
        try {
            obra.restaurar();
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getObras());
            JOptionPane.showMessageDialog(null, "Obra restaurada");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prestar obra
     * @param obra obra
     * @param tabla tabla de obras
     */
    public static void prestar(Obra obra, JTable tabla) {
        try {
            obra.prestar();
            TablaObrasGestor.modificarContenidoFilas((DefaultTableModel)tabla.getModel(), Sistema.getInstance().getObras());
            JOptionPane.showMessageDialog(null, "Obra prestada");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
