package Interfaz.Paneles.Gestor;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import Interfaz.Paneles.Common.*;

import Interfaz.InterfazUtils.*;

/**
 * Panel de registrar empleado
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelRegistrarEmpleado extends JPanel {
    protected JTextField valorDNI, valorNSS, valorDireccion, valorNumCuenta;
    protected JCheckBox envNotis, cambiarClim, venderEntradas;
    protected JButton botonRegistrarEmpleado;
    protected JPanel panel;
    protected PanelTitulo titulo;
    JLabel textoDNI, textoNSS, textoDireccion, textoNumCuenta, textoPermisos;
    SpringLayout layout;

    /**
     * Constructor del PanelRegistrarEmpleado
     */
    public PanelRegistrarEmpleado() {
        super();

        this.setLayout(new BorderLayout());

        titulo = new PanelTitulo("Registrar empleado");

        layout = new SpringLayout();
        panel = new JPanel(layout);

        textoDNI = new JLabel("DNI/NIF:");
        FormatUtils.formatBoldComponent(textoDNI, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        textoNSS = new JLabel("NSS:");
        FormatUtils.formatBoldComponent(textoNSS, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        textoDireccion = new JLabel("Dirección:");
        FormatUtils.formatBoldComponent(textoDireccion, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        textoNumCuenta = new JLabel("Nº de cuenta:");
        FormatUtils.formatBoldComponent(textoNumCuenta, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        textoPermisos = new JLabel("Permisos:");
        FormatUtils.formatBoldComponent(textoPermisos, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);

        botonRegistrarEmpleado = new JButton("Registrar empleado");
        FormatUtils.formatPlainComponent(botonRegistrarEmpleado, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        botonRegistrarEmpleado.setPreferredSize(new Dimension(200, 50));
        panel.add(botonRegistrarEmpleado);

        

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panel);
        splitPane.setDividerLocation(50); // Ancho inicial del panel izquierdo
        splitPane.setDividerSize(5); // Tamaño del delimitador
        splitPane.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuario

        this.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Método para actualizar el panel
     */
    public void update(){
        if(valorDNI != null){
            panel.removeAll();
        }

        valorDNI = new JTextField(20);
        FormatUtils.formatPlainComponent(valorDNI, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        InterfazUtils.setupPlaceholder(valorDNI, "Introduzca el DNI/NIF");
        panel.add(valorDNI);

        valorNSS = new JTextField(20);
        FormatUtils.formatPlainComponent(valorNSS, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        InterfazUtils.setupPlaceholder(valorNSS, "Introduzca el NSS");
        panel.add(valorNSS);

        valorDireccion = new JTextField(20);
        FormatUtils.formatPlainComponent(valorDireccion, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        InterfazUtils.setupPlaceholder(valorDireccion, "Introduzca la dirección");
        panel.add(valorDireccion);

        valorNumCuenta = new JTextField(20);
        FormatUtils.formatPlainComponent(valorNumCuenta, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        InterfazUtils.setupPlaceholder(valorNumCuenta, "Introduzca el número de cuenta");
        panel.add(valorNumCuenta);

        envNotis = new JCheckBox("  Enviar notificaciones");
        FormatUtils.formatPlainComponent(envNotis, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        panel.add(envNotis);

        cambiarClim = new JCheckBox("  Cambiar climatización");
        FormatUtils.formatPlainComponent(cambiarClim, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        panel.add(cambiarClim);

        venderEntradas = new JCheckBox("  Vender entradas");
        FormatUtils.formatPlainComponent(venderEntradas, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        panel.add(venderEntradas);


        layout.putConstraint(SpringLayout.NORTH, textoDNI, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, textoDNI, 50, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, valorDNI, 0, SpringLayout.NORTH, textoDNI);
        layout.putConstraint(SpringLayout.EAST, valorDNI, 450, SpringLayout.WEST, textoDNI);

        layout.putConstraint(SpringLayout.NORTH, textoNSS, 75, SpringLayout.NORTH, textoDNI);
        layout.putConstraint(SpringLayout.WEST, textoNSS, 0, SpringLayout.WEST, textoDNI);

        layout.putConstraint(SpringLayout.NORTH, valorNSS, 0, SpringLayout.NORTH, textoNSS);
        layout.putConstraint(SpringLayout.EAST, valorNSS, 450, SpringLayout.WEST, textoNSS);
        layout.putConstraint(SpringLayout.BASELINE, valorNSS, 0, SpringLayout.BASELINE, textoNSS);

        layout.putConstraint(SpringLayout.NORTH, textoDireccion, 75, SpringLayout.NORTH, textoNSS);
        layout.putConstraint(SpringLayout.WEST, textoDireccion, 0, SpringLayout.WEST, textoNSS);

        layout.putConstraint(SpringLayout.NORTH, valorDireccion, 0, SpringLayout.NORTH, textoDireccion);
        layout.putConstraint(SpringLayout.EAST, valorDireccion, 450, SpringLayout.WEST, textoDireccion);
        layout.putConstraint(SpringLayout.BASELINE, valorDireccion, 0, SpringLayout.BASELINE, textoDireccion);

        layout.putConstraint(SpringLayout.NORTH, textoNumCuenta, 75, SpringLayout.NORTH, textoDireccion);
        layout.putConstraint(SpringLayout.WEST, textoNumCuenta, 0, SpringLayout.WEST, textoDireccion);

        layout.putConstraint(SpringLayout.NORTH, valorNumCuenta, 0, SpringLayout.NORTH, textoNumCuenta);
        layout.putConstraint(SpringLayout.EAST, valorNumCuenta, 450, SpringLayout.WEST, textoNumCuenta);
        layout.putConstraint(SpringLayout.BASELINE, valorNumCuenta, 0, SpringLayout.BASELINE, textoNumCuenta);

        layout.putConstraint(SpringLayout.NORTH, textoPermisos, 75, SpringLayout.NORTH, textoNumCuenta);
        layout.putConstraint(SpringLayout.WEST, textoPermisos, 0, SpringLayout.WEST, textoNumCuenta);
        
        layout.putConstraint(SpringLayout.NORTH, envNotis, 0, SpringLayout.NORTH, textoPermisos);
        layout.putConstraint(SpringLayout.WEST, envNotis, -5, SpringLayout.WEST, valorDireccion);
        layout.putConstraint(SpringLayout.BASELINE, envNotis, 0, SpringLayout.BASELINE, textoPermisos);

        layout.putConstraint(SpringLayout.NORTH, cambiarClim, 35, SpringLayout.NORTH, textoPermisos);
        layout.putConstraint(SpringLayout.WEST, cambiarClim, 0, SpringLayout.WEST, envNotis);

        layout.putConstraint(SpringLayout.NORTH, venderEntradas, 76, SpringLayout.NORTH, textoPermisos);
        layout.putConstraint(SpringLayout.WEST, venderEntradas, 0, SpringLayout.WEST, cambiarClim);

        layout.putConstraint(SpringLayout.NORTH, botonRegistrarEmpleado, 175, SpringLayout.NORTH, textoPermisos);
        layout.putConstraint(SpringLayout.WEST, botonRegistrarEmpleado, 0, SpringLayout.WEST, textoPermisos);

        
        panel.add(textoDNI); 
        panel.add(textoNSS); 
        panel.add(textoDireccion); 
        panel.add(textoNumCuenta); 
        panel.add(textoPermisos);

    }

    /**
     * Getter del atributo dni
     * @return dni
     */
    public String getDNI() {
        return valorDNI.getText();
    }

    /**
     * Getter del atributo nss
     * @return nss
     */
    public String getNSS() {
        return valorNSS.getText();
    }

    /**
     * Getter del atributo dirección
     * @return dirección
     */
    public String getDireccion() {
        return valorDireccion.getText();
    }

    /**
     * Getter del atributo numCuenta
     * @return numCuenta
     */
    public String getNumCuenta() {
        return valorNumCuenta.getText();
    }

    /**
     * Getter del atributo envNotis
     * @return envNotis
     */
    public boolean getEnvNotis() {
        return envNotis.isSelected();
    }

    /**
     * Getter del atributo cambiarClim
     * @return cambiarClim
     */
    public boolean getCambiarClim() {
        return cambiarClim.isSelected();
    }

    /**
     * Getter del atributo venderEntradas
     * @return venderEntradas
     */
    public boolean getVenderEntradas() {
        return venderEntradas.isSelected();
    }

    /**
     * Setter del atributo controlador registrar empleado
     * @param c actionListener
     */
    public void setControladorRegistrarEmpleado(ActionListener c) {
        botonRegistrarEmpleado.addActionListener(c);
    }
}