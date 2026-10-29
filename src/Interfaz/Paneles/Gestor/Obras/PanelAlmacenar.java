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
 * 
 * Panel derecho con la informacion para guradar una obra en Almacen
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 * 
 */
public class PanelAlmacenar extends JPanel{
    private SpringLayout layout;
    private JTable tabla;
    private PanelTitulo titulo;
    private JPanel panelInferior;
    private JSplitPane completo;
    private Obra obra;
    private JButton confirmar;

    /**
     * Constructor para el  panel Almacenar
     * 
     * @param tabla JTable con la tabla de obras
     */
    public PanelAlmacenar(JTable tabla) {
        super(new BorderLayout());

        this.tabla = tabla;
    }

    /**
     * Actualizador del panel de almacenar obras
     * 
     * @param obra obra para almacenar
     */
    public void update(Obra obra) {

        this.obra = obra;

        this.removeAll();

        JLabel confirmarTexto = new JLabel("¿Quiere almacenar \"" + obra.getNombre() + "\"?");

        confirmar = new SuperButton("Confirmar", 150);

        setConfirmarControlador();

        titulo = new PanelTitulo("Almacenar obra");

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
     * Setter del controlador para confirmar
     * 
     * 
     */
    public void setConfirmarControlador() {
        confirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.almacenar(obra, tabla);
            }

        });
    }
    
}
