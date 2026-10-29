package Interfaz.Paneles.Invitado;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.InterfazUtils.InterfazUtils;
import Interfaz.Paneles.Common.PanelRedondeado;

import java.awt.*;
import java.awt.event.*;

/**
 * Panel de inicio de sesión
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class LoginPanel extends JPanel {
    private PanelRedondeado panelLogin;
    private JLabel titulo, text_registrar, registrar;
    private JTextField text1;
    private JPasswordField password;
    private JButton botonLogin;
    private JComboBox<String> tipo_usuario;
    private String username = "";
    private JPanel input;

    /**
     * Constructor del panel
     */
    public LoginPanel() {
        super();

        this.setBackground(new Color(245, 228, 245));
        this.setLayout(new GridBagLayout());
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY));

        panelLogin = new PanelRedondeado(45);
        panelLogin.setBackground(Color.WHITE);
        panelLogin.setPreferredSize(new Dimension(InterfazUtils.DIM_X / 3, InterfazUtils.DIM_Y / 2 + 100));

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;
        gbcMain.fill = GridBagConstraints.NONE;
        gbcMain.anchor = GridBagConstraints.CENTER;

        ImageIcon scaledUserIcon = new ImageIcon(new ImageIcon("src/Interfaz/iconos/usuario.png").getImage()
                .getScaledInstance(-1, 30, Image.SCALE_SMOOTH));
        ImageIcon scaledKeyIcon = new ImageIcon(new ImageIcon("src/Interfaz/iconos/llave.png").getImage()
                .getScaledInstance(-1, 30, Image.SCALE_SMOOTH));

        this.add(panelLogin, gbcMain);

        SpringLayout layout = new SpringLayout();
        panelLogin.setLayout(layout);

        titulo = new JLabel("Iniciar sesión");
        FormatUtils.formatBoldComponent(titulo, FormatUtils.DEFAULT, FormatUtils.TITLE_SIZE);

        String comboBoxItems[] = { InterfazUtils.LOGIN_DEFAULT, InterfazUtils.LOGIN_CLIENTE,
                InterfazUtils.LOGIN_EMPLEADO, InterfazUtils.LOGIN_GESTOR };
        tipo_usuario = new JComboBox<>(comboBoxItems);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, panelLogin);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tipo_usuario, 0, SpringLayout.HORIZONTAL_CENTER,
                panelLogin);
        layout.putConstraint(SpringLayout.NORTH, titulo, 75, SpringLayout.NORTH, panelLogin);
        layout.putConstraint(SpringLayout.NORTH, tipo_usuario, 20, SpringLayout.SOUTH, titulo);

        text1 = new JTextField(username);
        FormatUtils.formatPlainComponent(text1, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        text1.setPreferredSize(new Dimension(200, 30));

        JLabel labelUserIcon = new JLabel(scaledUserIcon);
        JLabel labelPwdIcon = new JLabel(scaledKeyIcon);

        password = new JPasswordField("Contraseña");
        FormatUtils.formatPlainComponent(password, FormatUtils.DEFAULT, FormatUtils.TEXT_SIZE);
        InterfazUtils.setupPlaceholder(password, "Contraseña");
        password.setPreferredSize(new Dimension(200, 30));

        input = new JPanel();
        input.setPreferredSize(new Dimension(300, 300));
        input.setBackground(Color.WHITE);
        SpringLayout layout_input = new SpringLayout();
        input.setLayout(layout_input);

        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setPreferredSize(new Dimension(150, 50));

        text_registrar = new JLabel("¿No tienes cuenta?");

        registrar = new JLabel("Registrarse");
        registrar.setForeground(Color.BLUE);
        registrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        layout_input.putConstraint(SpringLayout.EAST, labelUserIcon, -10, SpringLayout.WEST, text1);
        layout_input.putConstraint(SpringLayout.NORTH, labelUserIcon, 20, SpringLayout.NORTH, input);
        layout_input.putConstraint(SpringLayout.EAST, labelPwdIcon, -10, SpringLayout.WEST, password);
        layout_input.putConstraint(SpringLayout.NORTH, labelPwdIcon, 75, SpringLayout.NORTH, labelUserIcon);
        layout_input.putConstraint(SpringLayout.HORIZONTAL_CENTER, text1, 10, SpringLayout.HORIZONTAL_CENTER, input);
        layout_input.putConstraint(SpringLayout.HORIZONTAL_CENTER, password, 10, SpringLayout.HORIZONTAL_CENTER, input);
        layout_input.putConstraint(SpringLayout.HORIZONTAL_CENTER, botonLogin, 0, SpringLayout.HORIZONTAL_CENTER,
                input);

        layout_input.putConstraint(SpringLayout.NORTH, text1, 20, SpringLayout.NORTH, input);
        layout_input.putConstraint(SpringLayout.NORTH, password, 75, SpringLayout.NORTH, text1);
        layout_input.putConstraint(SpringLayout.NORTH, botonLogin, 75, SpringLayout.NORTH, password);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, input, 0, SpringLayout.HORIZONTAL_CENTER, panelLogin);
        layout.putConstraint(SpringLayout.NORTH, input, 20, SpringLayout.SOUTH, tipo_usuario);

        layout_input.putConstraint(SpringLayout.NORTH, text_registrar, 20, SpringLayout.SOUTH, botonLogin);
        layout_input.putConstraint(SpringLayout.VERTICAL_CENTER, registrar, 0, SpringLayout.VERTICAL_CENTER,
                text_registrar);
        layout_input.putConstraint(SpringLayout.WEST, text_registrar, -38, SpringLayout.WEST, botonLogin);
        layout_input.putConstraint(SpringLayout.WEST, registrar, 5, SpringLayout.EAST, text_registrar);

        input.add(text1);
        input.add(labelUserIcon);
        input.add(password);
        input.add(labelPwdIcon);
        input.add(botonLogin);
        input.add(text_registrar);
        input.add(registrar);
        panelLogin.add(input);
        input.setVisible(false);

        panelLogin.add(titulo);
        panelLogin.add(tipo_usuario);
    }

    /**
     * Función para actualizar el panel
     */
    public void update() {
        input.setVisible(!username.equals(""));
        text1.setText(username);
        password.setText("Contraseña");
        InterfazUtils.setupPlaceholder(text1, username);
    }

    /**
     * Getter del text1
     * 
     * @return text1
     */
    public String getText1String() {
        return this.text1.getText();
    }

    /**
     * Setter del username
     * 
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter del tipo de usuario seleccionado
     * 
     * @return tipo de usuario seleccionado
     */
    public String getTipoUsuarioSelected() {
        return (String) tipo_usuario.getSelectedItem();
    }

    /**
     * Getter de la contraseña
     * 
     * @return contraseña
     */
    public String getPasswordString() {
        return new String(this.password.getPassword());
    }

    /**
     * Setter del controlador de login
     * 
     * @param c actionListener
     */
    public void setControladorLogin(ActionListener c) {
        botonLogin.addActionListener(c);
    }

    /**
     * Setter del controlador de tipo de usuario
     * 
     * @param c actionListener
     */
    public void setControladorTipoUsuario(ActionListener c) {
        tipo_usuario.addActionListener(c);
    }

    /**
     * Setter del controlador de registrarse
     * 
     * @param c actionListener
     */
    public void setControladorRegistrarse(MouseListener c) {
        registrar.addMouseListener(c);
    }

}