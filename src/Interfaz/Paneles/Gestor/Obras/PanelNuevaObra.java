package Interfaz.Paneles.Gestor.Obras;


import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * Panel derecho con la información para añadir nueva obra
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelNuevaObra extends JTabbedPane{
    PanelNuevaObraManual manual;
    PanelNuevaObraFichero fichero;

    /**
     * Constructor del panel de nueva obra
     * 
     */
    public PanelNuevaObra(JTable tabla, PanelObra panel) {
        super();

        manual = new PanelNuevaObraManual(panel, tabla);
        fichero = new PanelNuevaObraFichero(tabla);

        this.add("Manual", manual);
        this.add("Fichero", fichero);
    }

    /**Getter del manual
     * 
     * @return manual de nueva obra
     */
    public PanelNuevaObraManual getManual() {
        return manual;
    }


}
