package Interfaz.Paneles.Gestor.Obras;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import Exposicion.Exposicion;
import Interfaz.Controladores.ControladorObrasCentral;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Obra.Obra;
import Sala.Sala;
import Sistema.Sistema;

/**
 * 
 * Panel con la información para exponer obra
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 * 
 */
public class PanelExponerObra extends JPanel {
    JTable tabla;
    SpringLayout layout;
    PanelTitulo titulo;
    JPanel panelInferior;
    JSplitPane completo;
    JComboBox<String> exposAElegir, salasAElegir;
    Exposicion expo;
    Sala sala;
    JButton exponer;
    Obra obra;

    /**
     * Constructor del panel exponer obra
     * 
     * @param tabla JTable con la tabla de obras
     * 
     */
    public PanelExponerObra(JTable tabla) {
        super(new BorderLayout());

        this.tabla = tabla;

    }

    /**
     * Actualizador del panel de exponer obras
     * 
     * @param obra obra a exponer
     * 
     */
    public void updateExponer(Obra obra) {
        this.obra = obra;

        this.removeAll();

        titulo = new PanelTitulo("Exponer obra");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        List<Exposicion> exposiciones = Sistema.getInstance().getExposiciones();

        String[] opciones1 = new String[exposiciones.size() + 1];

        opciones1[0] = "Seleccionar exposición";

        for (int i = 0; i < exposiciones.size(); i++) {
            opciones1[i + 1] = exposiciones.get(i).getNombre();
        }

        exposAElegir = new JComboBox<>(opciones1);

        exposAElegir.setSelectedIndex(0);

        setExpoAElegirControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exposAElegir, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, exposAElegir, 80, SpringLayout.NORTH, panelInferior);

        panelInferior.add(exposAElegir);

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Funcion para elegir las salas donde exponer la obra
     * 
     * @param expo Exposicion a exponer
     */
    public void elegirSalas(Exposicion expo) {
        this.expo = expo;

        List<Sala> salas = expo.getSalas();

        System.out.println(salas.size());

        String[] opciones2 = new String[salas.size() + 1];

        opciones2[0] = "Seleccionar sala";

        for (int i = 0; i < salas.size(); i++) {
            opciones2[i + 1] = salas.get(i).getNombre();
        }

        salasAElegir = new JComboBox<>(opciones2);

        salasAElegir.setSelectedIndex(0);

        setSalaAElegirControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, salasAElegir, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, salasAElegir, 30, SpringLayout.SOUTH, exposAElegir);

        panelInferior.add(salasAElegir);

        this.revalidate();
        this.repaint();

    }


    /**
     * Completar el boton de exponer
     * 
     * @param sala Sala a exponer
     */
    public void completarBotonExponer(Sala sala) {
        this.sala = sala;

        exponer = new SuperButton("Exponer", 150);

        setBotonExponerControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exponer, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, exponer, 30, SpringLayout.SOUTH,
                salasAElegir);

        panelInferior.add(exponer);

        this.revalidate();
        this.repaint();

    }

    /**
     * Setter de la exposicion a elegir con el controlador
     * 
     */
    public void setExpoAElegirControlador() {

        PanelExponerObra panelActual = this;

        exposAElegir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.completarPanelExponerObra(exposAElegir, panelActual);
            }

        });
    }

    /**
     * Asignar el controlador de elegir sala
     * 
     */
    public void setSalaAElegirControlador() {

        PanelExponerObra panelActual = this;

        salasAElegir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.completarBotonExponerObra(salasAElegir, panelActual);
            }

        });
    }

    /**
     * Asignar el controlador para exponer una obra
     * 
     */
    public void setBotonExponerControlador() {

        exponer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasCentral.exponer(obra, expo, sala, tabla);
            }

        });
    }


    /**
     * Getter del atributo exposicion
     * 
     * @return Exposicion a retornar
     */
    public Exposicion getExpo() {
        return expo;
    }

}
