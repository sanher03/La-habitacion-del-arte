package Interfaz.InterfazUtils;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase de utils para la interfaz
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class InterfazUtils {
    public static String CASA_INICIO = "Carta de casa inicio";
    public static String LOGIN_INICIO = "Carta de login inicio";
    public static String REGISTRO_INICIO = "Carta de registro inicio";
    public static String INICIO = "Carta de inicio";
    public static String GESTOR = "Carta de gestor";
    public static String EMPLEADO = "Carta de empleado";
    public static String CLIENTE = "Carta de cliente";
    public static String PANEL_INICIO = "Carta del panel de inicio";
    public static String PANEL_SESION = "Carta del panel de sesion";
    public static String PANEL_LOGIN = "Carta del panel de login";
    public static String LOGIN_DEFAULT = "Seleccionar usuario";
    public static String LOGIN_CLIENTE = "Cliente";
    public static String LOGIN_EMPLEADO = "Empleado";
    public static String LOGIN_GESTOR = "Gestor";
    public static String ESTADISTICAS = "Carta con las estadisticas globales";
    public static String EXPO_PRINCIPAL = "Carta con el panel de exposiciones principal";
    public static String PROGRAMAR_EXPOSICION = "Carta con el panel de programar expo";
    public static String EDITAR_EXPO = "Carta con el panel de editar expo";
    public static String BORRAR_EXPO_CORRECTO = "Carta con el panel de borrar expo correcto";
    public static String BORRAR_EXPO_INCORRECTO = "Carta con el panel de borrar expo incorrecto";
    public static String DESCUENTOS_EXPO = "Carta con el panel de descuentos expo";
    public static String SORTEO_EXPO = "Carta con el panel de sorteo expo";
    public static String ESTADISTICAS_EXPO = "Carta con el panel de estadisticas expo";
    public static String VER_COMPRAR_VENDER = "Carta con ver, comprar o vender exposicion";
    public static String LISTA_EXPOS = "Carta con la lista de exposiciones";
    public static String NUEVA_SALA = "Carta con el panel de nueva sala";
    public static String SALA_PRINCIPAL = "Carta con el panel principal de sala";
    public static String EDITAR_SALA = "Carta con el panel de editar sala";
    public static String ELIMINAR_SALA = "Carta con el panel de eliminar sala";
    public static String DIVIDIR_SALA = "Carta con el panel de dividir sala";
    public static String DEFAULT_SALA = "Carta con el panel default de sala";
    public static String VACIO = "Carta vacia";
    public static String EDITAR_CREACION = "Carta de editar en creacion";
    public static String EDITAR_DISPONIBLE = "Carta de editar en disponible";
    public static String EDITAR_TEMPORAL = "Carta de editar exposicion temporal";
    public static String EDITAR_PERMANENTE = "Carta de editar exposicion permanente";



    public static int DIM_X = 1920;
    public static int DIM_Y = 1080;
    public static int ACTIVO_CLIENTE = 1;
    public static int ACTIVO_EMPLEADO = 2;
    public static int ACTIVO_GESTOR = 3;

    /**
     * Método para settear un placeholder en los textfields
     * @param field field
     * @param placeholder placeholder
     */
    public static void setupPlaceholder(JTextField field, String placeholder) {
        field.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (field.getText().equals(placeholder)) {
                            field.setText("");
                            if (field instanceof JPasswordField) {
                                ((JPasswordField) field).setEchoChar('●');
                            }
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (field.getText().isEmpty()) {
                            field.setText(placeholder);
                            if (field instanceof JPasswordField) {
                                ((JPasswordField) field).setEchoChar((char) 0);
                            }
                        }
                    }
                });
    }

    /**
     * Método para checkear si un string es numérica
     * @param str string
     * @return true, false según
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }
}
