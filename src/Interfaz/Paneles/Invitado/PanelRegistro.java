package Interfaz.Paneles.Invitado;

import java.awt.*;
import java.awt.event.ActionListener;
import Interfaz.InterfazUtils.*;
import Interfaz.Paneles.Common.PanelRedondeado;

import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * Panel de registro
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelRegistro extends JPanel {
    JTextField campoDNI;
    JPasswordField campoContraseña, campoRepContraseña;
    JCheckBox recNotis;
    JButton botonRegistro;

    /**
     * Constructor del panel de registro
     */
    public PanelRegistro() {
        super();

        this.setBackground(new Color( 228, 243, 245 ));
        this.setLayout(new GridBagLayout());
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

        JPanel panelRegistro = new PanelRedondeado(45);
        panelRegistro.setBackground(Color.WHITE);
        panelRegistro.setPreferredSize(new Dimension(InterfazUtils.DIM_X / 3, InterfazUtils.DIM_Y / 2 + 100));

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;
        gbcMain.fill = GridBagConstraints.NONE;
        gbcMain.anchor = GridBagConstraints.CENTER;

        this.add(panelRegistro, gbcMain);

        SpringLayout layout = new SpringLayout();
        panelRegistro.setLayout(layout);

        JLabel textoRegistrarse = LabelUtils.addLabel(new JLabel("Registro"), panelRegistro);
        FormatUtils.formatBoldComponent(textoRegistrarse, FormatUtils.DEFAULT, FormatUtils.TITLE_SIZE);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoRegistrarse, 0, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);
        layout.putConstraint(SpringLayout.NORTH, textoRegistrarse, 75, SpringLayout.NORTH, panelRegistro);

        ImageIcon userIcon = new ImageIcon("src/Interfaz/iconos/usuario.png");
        ImageIcon keyIcon = new ImageIcon("src/Interfaz/iconos/llave.png");

        ImageIcon scaledUserIcon = new ImageIcon(userIcon.getImage().getScaledInstance(-1, 30, Image.SCALE_SMOOTH));
        ImageIcon scaledKeyIcon = new ImageIcon(keyIcon.getImage().getScaledInstance(-1, 30, Image.SCALE_SMOOTH));

        /* ------------------------ DNI ------------------------------ */

        campoDNI = new JTextField();
        FormatUtils.formatPlainComponent(campoDNI, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        campoDNI.setPreferredSize(new Dimension(300, 30));
        InterfazUtils.setupPlaceholder(campoDNI, "DNI/NIF");
        panelRegistro.add(campoDNI);

        SpringLayoutUtils.setPosition(campoDNI, panelRegistro, new Coordinates(175, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoDNI, 10, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);

        JLabel labelUserIcon = LabelUtils.addLabel(new JLabel(scaledUserIcon), panelRegistro);

        SpringLayoutUtils.setPosition(labelUserIcon, panelRegistro, new Coordinates(175, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.EAST, labelUserIcon, -10, SpringLayout.WEST, campoDNI);

        /* ------------------------ CONTRASEÑA ------------------------------ */

        campoContraseña = new JPasswordField();
        FormatUtils.formatPlainComponent(campoContraseña, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        campoContraseña.setPreferredSize(new Dimension(300, 30));
        InterfazUtils.setupPlaceholder(campoContraseña, "Contraseña");
        panelRegistro.add(campoContraseña);

        SpringLayoutUtils.setPosition(campoContraseña, campoDNI, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoContraseña, 10, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);

        JLabel labelKeyIcon1 = LabelUtils.addLabel(new JLabel(scaledKeyIcon), panelRegistro);

        SpringLayoutUtils.setPosition(labelKeyIcon1, labelUserIcon, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.EAST, labelKeyIcon1, -10, SpringLayout.WEST, campoContraseña);

        /* ------------------------ REPETIR CONTRASEÑA ------------------------------ */

        campoRepContraseña = new JPasswordField();
        FormatUtils.formatPlainComponent(campoRepContraseña, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        campoRepContraseña.setPreferredSize(new Dimension(300, 30));
        InterfazUtils.setupPlaceholder(campoRepContraseña, "Repetir contraseña");
        panelRegistro.add(campoRepContraseña);

        SpringLayoutUtils.setPosition(campoRepContraseña, campoContraseña, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, campoRepContraseña, 10, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);

        JLabel labelKeyIcon2 = LabelUtils.addLabel(new JLabel(scaledKeyIcon), panelRegistro);

        SpringLayoutUtils.setPosition(labelKeyIcon2, labelKeyIcon1, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.EAST, labelKeyIcon2, -10, SpringLayout.WEST, campoRepContraseña);

        /*
         * ------------------------ PERMISO NOTIFICACIONES
         * ------------------------------
         */

        recNotis = new JCheckBox("  ¿Consiente a recibir notificaciones?  ");
        FormatUtils.formatPlainComponent(recNotis, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        recNotis.setPreferredSize(new Dimension(325, 30));
        panelRegistro.add(recNotis);

        SpringLayoutUtils.setPosition(recNotis, campoRepContraseña, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, recNotis, 0, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);

        /* ------------------------ BOTÓN REGISTRO ------------------------------ */

        botonRegistro = new JButton("Registrarse");
        FormatUtils.formatPlainComponent(botonRegistro, FormatUtils.DEFAULT, FormatUtils.PLAIN_TEXT_SIZE);
        botonRegistro.setPreferredSize(new Dimension(150, 50));
        panelRegistro.add(botonRegistro);

        SpringLayoutUtils.setPosition(botonRegistro, recNotis, new Coordinates(75, 0, 0, 0), layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonRegistro, 0, SpringLayout.HORIZONTAL_CENTER,
                panelRegistro);
    }

    /**
     * Getter del dni
     * @return dni
     */
    public String getDNI() {
        return campoDNI.getText();
    }

    /**
     * Getter de la contraseña
     * @return contraseña
     */
    public String getContraseña() {
        return new String(campoContraseña.getPassword());
    }

    /**
     * Getter de la contraseña rep
     * @return contraseña rep
     */
    public String getRepContraseña() {
        return new String(campoRepContraseña.getPassword());
    }

    /**
     * Getter de recNotis
     * @return recNotis
     */
    public boolean isRecNotis() {
        return recNotis.isSelected();
    }

    /**
     * Getter del botón de registro
     * @return botón de registro
     */
    public JButton getBotonRegistro() {
        return botonRegistro;
    }

    /**
     * Setter del controlador de registro
     * @param c actionListener
     */
    public void setControladorRegistro(ActionListener c){
        botonRegistro.addActionListener(c);
    }
}
