package Interfaz.Paneles.Gestor.Exposiciones;

import javax.swing.*;

/**
 * Panel borrar expo incorrecto
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelBorrarExpoIncorrecto extends JPanel {
    SpringLayout layout;
    JLabel text1, text2;

    /**
     * Constructor del panel borrar expo incorrecto
     */
    public PanelBorrarExpoIncorrecto() {
        super();

        layout = new SpringLayout();
        this.setLayout(layout);

        text1 = new JLabel("Esta exposición no se puede borrar");
        text2 = new JLabel("ya que se encuentra disponible");

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, text1, 100, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, text2, 10, SpringLayout.SOUTH, text1);

        this.add(text1);
        this.add(text2);

    }
}
