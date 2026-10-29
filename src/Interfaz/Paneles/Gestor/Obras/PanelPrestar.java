package Interfaz.Paneles.Gestor.Obras;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Interfaz.Controladores.ControladorObrasCentral;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Obra.Obra;

/**
 * Panel lateral derecho de prestar obra
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelPrestar extends JPanel{
    private SpringLayout layout;
    private JTable tabla;
    private PanelTitulo titulo;
    private JPanel panelInferior;
    private JSplitPane completo;
    private Obra obra;
    private JButton confirmar;

    /**
     * Constructor del panel de prestar
     * 
     * @param tabla JTable con la lista de obras 
     */
    public PanelPrestar(JTable tabla) {
        super(new BorderLayout());

        this.tabla = tabla;
    }

    /**
     * Actualizacion del panel de prestar
     * 
     * @param obra Obra para actualizar
     */
    public void update(Obra obra) {

        this.obra = obra;

        this.removeAll();

        JLabel confirmarTexto = new JLabel("¿Quiere prestar \"" + obra.getNombre() + "\"?");

        confirmar = new SuperButton("Confirmar", 150);

        setConfirmarControlador();

        titulo = new PanelTitulo("Prestar obra");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmarTexto, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, confirmar, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, confirmarTexto, 200, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, confirmar, 80, SpringLayout.VERTICAL_CENTER, confirmarTexto);

        panelInferior.add(confirmarTexto);
        panelInferior.add(confirmar);


        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Setter del boton de confirmar
     * 
     */
    public void setConfirmarControlador() {
        confirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.prestar(obra, tabla);
            }

        });
    }
    
}
