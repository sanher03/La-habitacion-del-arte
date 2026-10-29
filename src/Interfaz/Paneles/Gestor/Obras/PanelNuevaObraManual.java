package Interfaz.Paneles.Gestor.Obras;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Interfaz.Controladores.ControladorAñadirObraManual;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Obra.Obra;

/**
 * Panel para añadir nueva obra manualmente
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelNuevaObraManual extends JPanel {
    PanelTitulo titulo;
    JPanel panelInferior;
    JSplitPane completo;
    JButton añadir;
    Obra obra;
    JComboBox<String> tipoObra;
    SpringLayout layout;
    JLabel textoDim, humTexto;
    PanelObra panel;
    JTextField nombreValor, autorValor, valorFecha, valorPoliza, valorCuantia, alto, ancho, largo, minTemp, maxTemp,
            minHum, maxHum, valorTecnica, valorMaterial, valorIdioma, horas, minutos, segundos;
    JTextArea valorDescr;
    JTable tabla;
    JRadioButton opcion1, opcion2;
    JComboBox<String> valorTipoFoto;

    /**
     * Constructor del panel de nueva obra manualmente
     * 
     * @param panel Panel de obras
     * @param tabla JTable con la lista de obras
     */
    public PanelNuevaObraManual(PanelObra panel, JTable tabla) {
        super(new BorderLayout());

        this.panel = panel;
        this.tabla = tabla;

    }

    /**
     * Actualizador del panel de nueva obra manual
     * 
     * @param index Index para marcar el seleccionado
     */
    public void update(int index) {

        this.removeAll();

        titulo = new PanelTitulo("Añadir nueva obra");

        layout = new SpringLayout();
        panelInferior = new JPanel(layout);

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        String[] opciones2 = { "Seleccione tipo de obra", "Pintura", "Escultura",
                "Fotografía", "Audiovisual" };

        tipoObra = new JComboBox<>(opciones2);

        tipoObra.setSelectedIndex(index);

        setTipoObraControlador();

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tipoObra, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.NORTH, tipoObra, 80, SpringLayout.NORTH, panelInferior);

        panelInferior.add(tipoObra);

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Panel comun a mostrar
     * 
     */
    public void panelComun() {
        JLabel nombre = new JLabel("Nombre");

        nombreValor = new JTextField();
        nombreValor.setPreferredSize(new Dimension(200, 20));

        JLabel autor = new JLabel("Autor: ");

        autorValor = new JTextField();
        autorValor.setPreferredSize(new Dimension(200, 20));

        JLabel textoFecha = new JLabel("Año de realización: ");

        valorFecha = new JTextField();
        valorFecha.setPreferredSize(new Dimension(200, 20));

        JLabel textoDescr = new JLabel("Descripción: ");

        valorDescr = new JTextArea(5, 30);

        ButtonGroup buttonGroup = new ButtonGroup();
        opcion1 = new JRadioButton("Propia");
        opcion2 = new JRadioButton("Ajena");
        buttonGroup.add(opcion1);
        buttonGroup.add(opcion2);

        JLabel textoPoliza = new JLabel("Póliza: ");

        valorPoliza = new JTextField();
        valorPoliza.setPreferredSize(new Dimension(200, 20));

        JLabel textoCuantia = new JLabel("Cuantía seguro: ");

        valorCuantia = new JTextField();
        valorCuantia.setPreferredSize(new Dimension(200, 20));

        añadir = new SuperButton("Añadir obra", 250);

        layout.putConstraint(SpringLayout.WEST, nombre, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, nombreValor, 200, SpringLayout.WEST,
                nombre);
        layout.putConstraint(SpringLayout.WEST, autor, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, autorValor, 200, SpringLayout.WEST,
                autor);
        layout.putConstraint(SpringLayout.WEST, textoFecha, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorFecha, 200, SpringLayout.WEST,
                textoFecha);
        layout.putConstraint(SpringLayout.WEST, textoDescr, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorDescr, 200, SpringLayout.WEST,
                textoDescr);
        layout.putConstraint(SpringLayout.WEST, textoPoliza, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorPoliza, 200, SpringLayout.WEST,
                textoPoliza);
        layout.putConstraint(SpringLayout.WEST, opcion1, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, opcion2, 200, SpringLayout.WEST,
                textoPoliza);
        layout.putConstraint(SpringLayout.WEST, textoCuantia, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorCuantia, 200, SpringLayout.WEST,
                textoCuantia);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 40, SpringLayout.VERTICAL_CENTER,
                tipoObra);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreValor, 40, SpringLayout.VERTICAL_CENTER,
                tipoObra);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, autor, 40, SpringLayout.VERTICAL_CENTER,
                nombre);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, autorValor, 40, SpringLayout.VERTICAL_CENTER,
                nombre);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoFecha, 30, SpringLayout.VERTICAL_CENTER,
                autor);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorFecha, 30, SpringLayout.VERTICAL_CENTER,
                autor);
        layout.putConstraint(SpringLayout.NORTH, textoDescr, 20, SpringLayout.SOUTH,
                textoFecha);
        layout.putConstraint(SpringLayout.NORTH, valorDescr, 20, SpringLayout.SOUTH,
                textoFecha);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, opcion1, 30, SpringLayout.SOUTH,
                valorDescr);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, opcion2, 30, SpringLayout.SOUTH,
                valorDescr);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoPoliza, 30, SpringLayout.VERTICAL_CENTER,
                opcion1);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorPoliza, 30, SpringLayout.VERTICAL_CENTER,
                opcion1);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoCuantia, 30, SpringLayout.VERTICAL_CENTER,
                textoPoliza);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorCuantia, 30, SpringLayout.VERTICAL_CENTER,
                valorPoliza);

        panelInferior.add(nombre);
        panelInferior.add(nombreValor);
        panelInferior.add(autor);
        panelInferior.add(autorValor);
        panelInferior.add(textoFecha);
        panelInferior.add(valorFecha);
        panelInferior.add(textoDescr);
        panelInferior.add(valorDescr);
        panelInferior.add(opcion1);
        panelInferior.add(opcion2);
        panelInferior.add(textoPoliza);
        panelInferior.add(valorPoliza);
        panelInferior.add(textoCuantia);
        panelInferior.add(valorCuantia);
    }

    /**
     * Completar las dimensiones de la nueva obra
     * 
     * 
     */
    public void completarDimensiones() {
        textoDim = new JLabel("Dimensiones:");

        alto = new JTextField();
        alto.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion1 = new JLabel("x");

        ancho = new JTextField();
        ancho.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion2 = new JLabel("x");

        largo = new JTextField();
        largo.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion3 = new JLabel("cm. (alto x ancho x largo)");

        layout.putConstraint(SpringLayout.WEST, textoDim, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, alto, 200, SpringLayout.WEST,
                textoDim);
        layout.putConstraint(SpringLayout.WEST, conjuncion1, 10, SpringLayout.EAST,
                alto);
        layout.putConstraint(SpringLayout.WEST, ancho, 10, SpringLayout.EAST,
                conjuncion1);
        layout.putConstraint(SpringLayout.WEST, conjuncion2, 10, SpringLayout.EAST,
                ancho);
        layout.putConstraint(SpringLayout.WEST, largo, 10, SpringLayout.EAST,
                conjuncion2);
        layout.putConstraint(SpringLayout.WEST, conjuncion3, 10, SpringLayout.EAST,
                largo);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoDim, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, alto, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion1, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, ancho, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion2, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, largo, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion3, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);

        panelInferior.add(textoDim);
        panelInferior.add(alto);
        panelInferior.add(conjuncion1);
        panelInferior.add(ancho);
        panelInferior.add(conjuncion2);
        panelInferior.add(largo);
        panelInferior.add(conjuncion3);

    }

    /**
     * Completar la temperatura y humedad de la nueva obra
     * 
     */
    public void completarTemperaturaYHumedad() {
        JLabel tempTexto = new JLabel("Temperatura:");

        minTemp = new JTextField();
        minTemp.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion1 = new JLabel("º C min.");

        maxTemp = new JTextField();
        maxTemp.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion2 = new JLabel("º C max.");

        layout.putConstraint(SpringLayout.WEST, tempTexto, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, minTemp, 200, SpringLayout.WEST,
                tempTexto);
        layout.putConstraint(SpringLayout.WEST, conjuncion1, 10, SpringLayout.EAST,
                minTemp);
        layout.putConstraint(SpringLayout.WEST, maxTemp, 10, SpringLayout.EAST,
                conjuncion1);
        layout.putConstraint(SpringLayout.WEST, conjuncion2, 10, SpringLayout.EAST,
                maxTemp);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, tempTexto, 30, SpringLayout.VERTICAL_CENTER,
                textoDim);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, minTemp, 30, SpringLayout.VERTICAL_CENTER,
                textoDim);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion1, 30, SpringLayout.VERTICAL_CENTER,
                textoDim);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, maxTemp, 30, SpringLayout.VERTICAL_CENTER,
                textoDim);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion2, 30, SpringLayout.VERTICAL_CENTER,
                textoDim);

        panelInferior.add(tempTexto);
        panelInferior.add(minTemp);
        panelInferior.add(conjuncion1);
        panelInferior.add(maxTemp);
        panelInferior.add(conjuncion2);

        humTexto = new JLabel("Humededad:");

        minHum = new JTextField();
        minHum.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion3 = new JLabel("% min.");

        maxHum = new JTextField();
        maxHum.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion4 = new JLabel("% max.");

        layout.putConstraint(SpringLayout.WEST, humTexto, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, minHum, 200, SpringLayout.WEST,
                humTexto);
        layout.putConstraint(SpringLayout.WEST, conjuncion3, 10, SpringLayout.EAST,
                minHum);
        layout.putConstraint(SpringLayout.WEST, maxHum, 10, SpringLayout.EAST,
                conjuncion3);
        layout.putConstraint(SpringLayout.WEST, conjuncion4, 10, SpringLayout.EAST,
                maxHum);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, humTexto, 30, SpringLayout.VERTICAL_CENTER,
                tempTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, minHum, 30, SpringLayout.VERTICAL_CENTER,
                tempTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion3, 30, SpringLayout.VERTICAL_CENTER,
                tempTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, maxHum, 30, SpringLayout.VERTICAL_CENTER,
                tempTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion4, 30, SpringLayout.VERTICAL_CENTER,
                tempTexto);

        panelInferior.add(humTexto);
        panelInferior.add(minHum);
        panelInferior.add(conjuncion3);
        panelInferior.add(maxHum);
        panelInferior.add(conjuncion4);
    }

    /**
     * Completar el boton de añadir
     * 
     */
    public void completarBotonAñadir() {

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, añadir, 0, SpringLayout.HORIZONTAL_CENTER,
                panelInferior);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, añadir, 200, SpringLayout.VERTICAL_CENTER,
                panelInferior);

        panelInferior.add(añadir);

    }

    /**
     * Completar la opcion caracteristica de pintura
     * 
     */
    public void completarPintura() {
        panelComun();
        completarDimensiones();
        completarTemperaturaYHumedad();

        JLabel textoTecnica = new JLabel("Técnica:");

        valorTecnica = new JTextField();
        valorTecnica.setPreferredSize(new Dimension(200, 20));

        layout.putConstraint(SpringLayout.WEST, textoTecnica, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTecnica, 200, SpringLayout.WEST,
                textoTecnica);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoTecnica, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorTecnica, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);

        panelInferior.add(textoTecnica);
        panelInferior.add(valorTecnica);

        completarBotonAñadir();

        setCrearPintura();
    }

    /**
     * Completar la opcion caracteristica de escultura
     * 
     */
    public void completarEscultura() {

        panelComun();
        completarDimensiones();
        completarTemperaturaYHumedad();

        JLabel textoMaterial = new JLabel("Material:");

        valorMaterial = new JTextField();
        valorMaterial.setPreferredSize(new Dimension(200, 20));

        layout.putConstraint(SpringLayout.WEST, textoMaterial, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorMaterial, 200, SpringLayout.WEST,
                textoMaterial);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoMaterial, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorMaterial, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);

        panelInferior.add(textoMaterial);
        panelInferior.add(valorMaterial);

        completarBotonAñadir();

        setCrearEscultura();

    }

    /**
     * Completar la opcion caracteristica de fotografia
     * 
     */
    public void completarFotografía() {
        panelComun();
        completarDimensiones();
        completarTemperaturaYHumedad();

        JLabel textoTipoFoto = new JLabel("Técnica:");

        String[] opciones = { "Seleccionar tipo", "Blanco y negro", "A color" };

        valorTipoFoto = new JComboBox<String>(opciones);

        layout.putConstraint(SpringLayout.WEST, textoTipoFoto, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTipoFoto, 200, SpringLayout.WEST,
                textoTipoFoto);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoTipoFoto, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorTipoFoto, 30, SpringLayout.VERTICAL_CENTER,
                humTexto);

        panelInferior.add(textoTipoFoto);
        panelInferior.add(valorTipoFoto);

        completarBotonAñadir();

        setCrearFotografía();

    }

    /**
     * Completar la opcion caracteristica de obra audiovisual
     * 
     */
    public void completarAudiovisual() {
        panelComun();

        JLabel textoIdioma = new JLabel("Idioma:");

        valorIdioma = new JTextField();
        valorIdioma.setPreferredSize(new Dimension(200, 20));

        JLabel textoDuracion = new JLabel("Duración:");

        horas = new JTextField();
        horas.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion5 = new JLabel("h.");

        minutos = new JTextField();
        minutos.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion6 = new JLabel("min.");

        segundos = new JTextField();
        segundos.setPreferredSize(new Dimension(40, 20));

        JLabel conjuncion7 = new JLabel("s.");

        layout.putConstraint(SpringLayout.WEST, textoIdioma, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorIdioma, 200, SpringLayout.WEST,
                textoIdioma);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoIdioma, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, valorIdioma, 30, SpringLayout.VERTICAL_CENTER,
                valorCuantia);

        panelInferior.add(textoIdioma);
        panelInferior.add(valorIdioma);

        layout.putConstraint(SpringLayout.WEST, textoDuracion, 20, SpringLayout.WEST,
                panelInferior);
        layout.putConstraint(SpringLayout.WEST, horas, 200, SpringLayout.WEST,
                textoDuracion);
        layout.putConstraint(SpringLayout.WEST, conjuncion5, 10, SpringLayout.EAST,
                horas);
        layout.putConstraint(SpringLayout.WEST, minutos, 10, SpringLayout.EAST,
                conjuncion5);
        layout.putConstraint(SpringLayout.WEST, conjuncion6, 10, SpringLayout.EAST,
                minutos);
        layout.putConstraint(SpringLayout.WEST, segundos, 10, SpringLayout.EAST,
                conjuncion6);
        layout.putConstraint(SpringLayout.WEST, conjuncion7, 10, SpringLayout.EAST,
                segundos);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoDuracion, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, horas, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion5, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, minutos, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion6, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, segundos, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, conjuncion7, 30, SpringLayout.VERTICAL_CENTER,
                textoIdioma);

        panelInferior.add(textoDuracion);
        panelInferior.add(horas);
        panelInferior.add(conjuncion5);
        panelInferior.add(minutos);
        panelInferior.add(conjuncion6);
        panelInferior.add(segundos);
        panelInferior.add(conjuncion7);

        completarBotonAñadir();

        setCrearAudiovisual();

    }

    /**
     * Setter del boton añadir para crear pintura
     * 
     */

    public void setCrearPintura() {

        añadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorAñadirObraManual.createPintura(panel, tabla, nombreValor, autorValor,
                        valorFecha, valorDescr,
                        opcion1, opcion2, valorPoliza, valorCuantia, alto, ancho, largo,
                        minTemp, maxTemp,
                        minHum, maxHum, valorTecnica);
            }

        });
    }

    /**
     * Setter del boton añadir para crear escultura
     * 
     */
    public void setCrearEscultura() {

        añadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorAñadirObraManual.createEscultura(panel, tabla, nombreValor, autorValor,
                        valorFecha,
                        valorDescr,
                        opcion1, opcion2, valorPoliza, valorCuantia, alto, ancho, largo,
                        minTemp, maxTemp,
                        minHum, maxHum, valorMaterial);
            }

        });
    }
    
    /**
     * Setter del boton añadir para crear fotografia
     * 
     */
    public void setCrearFotografía() {

        añadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorAñadirObraManual.createFotografia(panel, tabla, nombreValor, autorValor,
                        valorFecha,
                        valorDescr,
                        opcion1, opcion2, valorPoliza, valorCuantia, alto, ancho, largo,
                        minTemp, maxTemp,
                        minHum, maxHum, tipoObra);
            }

        });
    }

    /**
     * Setter del boton añadir para crear obra audiovisual
     * 
     */
    public void setCrearAudiovisual() {

        añadir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorAñadirObraManual.createAudiovisual(panel, tabla, nombreValor, autorValor,
                        valorFecha, valorDescr,
                        opcion1, opcion2, valorPoliza, valorCuantia, valorIdioma, horas,
                        minutos, segundos);
            }

        });
    }

    /**
     * Setter del boton para seleccionar el tipo de obra
     * 
     */
    public void setTipoObraControlador() {

        PanelNuevaObraManual panelActual = this;

        tipoObra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorAñadirObraManual.completarPanelCreacion(panelActual, tipoObra);
            }

        });
    }
}
