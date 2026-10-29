package Interfaz.InterfazUtils;

import java.awt.*;
import javax.swing.*;

/**
 * Clase de formateo
 */
public class FormatUtils {

    /* -------------------- TAMAÑOS DE FUENTE ------------------ */
    public static final int TITLE_SIZE = 24;
    public static final int SUBTITLE_SIZE = 20;
    public static final int TEXT_SIZE = 16;
    public static final int PLAIN_TEXT_SIZE = 14;

    /* --------------------- TIPOS DE FUENTE ------------------- */
    public static final String DEFAULT = "Dialog";
    public static final String TIMES_NEW_ROMAN = "Liberation Serif";
    public static final String COURIER = "Courier";

    /* ------------- MÉTODOS ESTÁTICOS DE FORMATEO ------------ */
    /**
     * Formateador de texto plano
     * 
     * @param component componente
     * @param fontType  tipo de fuente
     * @param fontSize  tamaño de fuente
     */
    public static void formatPlainComponent(
            JComponent component,
            String fontType,
            int fontSize) {
        component.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }

    /**
     * Formateador de texto negrita
     * 
     * @param component componente
     * @param fontType  tipo de fuente
     * @param fontSize  tamaño de fuente
     */
    public static void formatBoldComponent(
            JComponent component,
            String fontType,
            int fontSize) {
        component.setFont(new Font(fontType, Font.BOLD, fontSize));
    }

    /**
     * Formateador de texto cursiva
     * 
     * @param component componente
     * @param fontType  tipo de fuente
     * @param fontSize  tamaño de fuente
     */
    public static void formatItalicComponent(
            JComponent component,
            String fontType,
            int fontSize) {
        component.setFont(new Font(fontType, Font.ITALIC, fontSize));
    }

    /**
     * Formateador de texto negrita y cursiva
     * 
     * @param component componente
     * @param fontType  tipo de fuente
     * @param fontSize  tamaño de fuente
     */
    public static void formatBoldAndItalicComponent(
            JComponent component,
            String fontType,
            int fontSize) {
        component.setFont(new Font(fontType, Font.BOLD | Font.ITALIC, fontSize));
    }
}
