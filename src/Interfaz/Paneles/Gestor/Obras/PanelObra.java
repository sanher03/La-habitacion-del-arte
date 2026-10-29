package Interfaz.Paneles.Gestor.Obras;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Panel derecho con los despeglables en funcion de la seleccion izquierda en obras
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelObra extends JPanel{

    PanelNuevaObra nuevaObra;
    PanelDetallesObra detallesObra;
    PanelExponerObra exponerObra;
    PanelAlmacenar almacenarObra;
    PanelRetirar retirarObra;
    PanelRestaurar restaurarObra;
    PanelPrestar prestarObra;

    /**
     * Contructor del panel derecho de obras
     * 
     * @param tabla JTable con la lista de obras
     */
    public PanelObra(JTable tabla) {
        super(new CardLayout());
        CardLayout layout = (CardLayout) this.getLayout();

        this.nuevaObra = new PanelNuevaObra(tabla, this);
        this.detallesObra = new PanelDetallesObra();
        this.exponerObra = new PanelExponerObra(tabla);
        this.almacenarObra = new PanelAlmacenar(tabla);
        this.retirarObra = new PanelRetirar(tabla);
        this.restaurarObra = new PanelRestaurar(tabla);
        this.prestarObra = new PanelPrestar(tabla);


        JPanel vacio = new JPanel();

        this.add(vacio, "Panel vacío");

        this.add(nuevaObra, "Nueva obra");

        this.add(detallesObra, "Detalles obra");

        this.add(exponerObra, "Exponer obra");

        this.add(almacenarObra, "Almacenar obra");

        this.add(retirarObra, "Retirar obra");

        this.add(restaurarObra, "Restaurar obra");

        this.add(prestarObra, "Prestar obra");

        layout.show(this, "Panel vacío");

    }

    
    /**
     * Getter del panel de nueva obra
     * 
     * @return panel de nueva obra
     */
    public PanelNuevaObra getNuevaObra() {
        return nuevaObra;
    }

    /**
     * Getter del panel de detalles de la obra
     * 
     * @return panel de detalles de la obra
     */
    public PanelDetallesObra getDetallesObra() {
        return detallesObra;
    }

    /**
     * Getter del panel de exponer obra
     * 
     * @return panel de exponer obra
     */
    public PanelExponerObra getExponerObra() {
        return exponerObra;
    }

    /**
     * Getter del panel de almacenar obra
     * 
     * @return panel de almacenar obra
     */
    public PanelAlmacenar getAlmacenarObra() {
        return almacenarObra;
    }

    /**
     * Getter del panel de retirar obra
     * 
     * @return panel de retirar obra
     */
    public PanelRetirar getRetirarObra() {
        return retirarObra;
    }

    /**
     * Getter del panel de restaurar una obra
     * 
     * @return panel de restaurar una obra
     */
    public PanelRestaurar getRestaurarObra() {
        return restaurarObra;
    }

    /**
     * Getter del panel de prestar una obra
     * 
     * @return panel de prestar una obra
     */
    public PanelPrestar getPrestarObra() {
        return prestarObra;
    } 

    

}
