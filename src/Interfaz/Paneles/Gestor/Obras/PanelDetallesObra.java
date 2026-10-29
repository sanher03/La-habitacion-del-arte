package Interfaz.Paneles.Gestor.Obras;

import java.awt.BorderLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import Exposicion.TipoDeObra;
import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.Paneles.Common.PanelTitulo;
import Obra.*;

/**
 * Panel que muestra a la derecha los detalles de la obra
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 * 
 */
public class PanelDetallesObra extends JPanel {
    PanelTitulo titulo;
    SpringLayout layout;
    JPanel panelInferior;
    JSplitPane completo;
    Obra obra;
    JLabel autor, autorValor, textoTipoObra, textDimensiones, textoTemp, textoHum;

    /**
     * Constructor del panel con los detalles
     * 
     * 
     */
    public PanelDetallesObra() {
        super(new BorderLayout());
    }

    /**
     * Actualizacion del panel con los detalles de la obra
     * 
     * @param obra Obra de la que poner los detalles
     */
    public void update(Obra obra) {
        this.obra = obra;

        this.removeAll();

        titulo = new PanelTitulo(obra.getNombre());

        panelInferior = new JPanel(new SpringLayout());
        layout = (SpringLayout) panelInferior.getLayout();

        completo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titulo, panelInferior);
        completo.setDividerLocation(50); // Ancho inicial del panel izquierdo
        completo.setDividerSize(5); // Tamaño del delimitador
        completo.setEnabled(false); // Deshabilitar redimensionamiento manual por el usuari

        FormatUtils.formatBoldComponent(
                titulo.getTitulo(),
                FormatUtils.DEFAULT,
                FormatUtils.TITLE_SIZE);

        autor = new JLabel("Autor: ");

        FormatUtils.formatBoldComponent(
                autor,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        autorValor = new JLabel((obra.getAutor()));

        FormatUtils.formatPlainComponent(
                autorValor,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoFecha = new JLabel("Fecha de realización: ");
        FormatUtils.formatBoldComponent(
                textoFecha,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorFecha = new JLabel(Integer.toString(obra.getAnio()));

        FormatUtils.formatPlainComponent(
                valorFecha,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoDescr = new JLabel("Descripción: ");
        FormatUtils.formatBoldComponent(
                textoDescr,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);
        
        JLabel valorDescr = new JLabel("<html>" + obra.getDescr() + "<html>");

        FormatUtils.formatPlainComponent(
                valorDescr,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoPropia = new JLabel("Propia: ");
        FormatUtils.formatBoldComponent(
                textoPropia,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);
        
        JLabel valorPropia = new JLabel(Boolean.toString(obra.isPropia()));

        FormatUtils.formatPlainComponent(
                valorPropia,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoPoliza = new JLabel("Póliza: ");
        FormatUtils.formatBoldComponent(
                textoPoliza,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);
        
        JLabel valorPoliza = new JLabel(obra.getPoliza());

        FormatUtils.formatPlainComponent(
                valorPoliza,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoCuantia = new JLabel("Cuantía seguro: ");
        FormatUtils.formatBoldComponent(
                textoCuantia,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);
        
        JLabel valorCuantia = new JLabel(Double.toString(obra.getCuantiaSeguro()));

        FormatUtils.formatPlainComponent(
                valorCuantia,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);
        

        textoTipoObra = new JLabel("Tipo de obra:");

        FormatUtils.formatBoldComponent(
                textoTipoObra,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTipoObra = new JLabel(obra.getTipoObra().toString());
        FormatUtils.formatPlainComponent(
                valorTipoObra,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel estadoObra = new JLabel("Estado del obra:");

        FormatUtils.formatBoldComponent(
                estadoObra,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorEstado = new JLabel(obra.getEstado().toString());

        FormatUtils.formatPlainComponent(
                valorEstado,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, autor, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, autorValor, 30, SpringLayout.EAST, autor);
        layout.putConstraint(SpringLayout.WEST, textoFecha, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorFecha, 30, SpringLayout.EAST, textoFecha);
        layout.putConstraint(SpringLayout.WEST, textoDescr, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorDescr, 30, SpringLayout.EAST, textoDescr);
        layout.putConstraint(SpringLayout.WEST, textoPropia, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorPropia, 30, SpringLayout.EAST, textoPropia);
        layout.putConstraint(SpringLayout.WEST, textoPoliza, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorPoliza, 30, SpringLayout.EAST, textoPoliza);
        layout.putConstraint(SpringLayout.WEST, textoCuantia, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorCuantia, 30, SpringLayout.EAST, textoCuantia);
        layout.putConstraint(SpringLayout.WEST, textoTipoObra, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTipoObra, 30, SpringLayout.EAST, textoTipoObra);
        layout.putConstraint(SpringLayout.WEST, estadoObra, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorEstado, 30, SpringLayout.EAST, estadoObra);

        layout.putConstraint(SpringLayout.NORTH, autor, 50, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.NORTH, autorValor, 50, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.NORTH, textoFecha, 50, SpringLayout.NORTH, autor);
        layout.putConstraint(SpringLayout.NORTH, valorFecha, 50, SpringLayout.NORTH, autor);
        layout.putConstraint(SpringLayout.NORTH, textoDescr, 50, SpringLayout.NORTH, textoFecha);
        layout.putConstraint(SpringLayout.NORTH, valorDescr, 50, SpringLayout.NORTH, textoFecha);
        layout.putConstraint(SpringLayout.NORTH, textoPropia, 50, SpringLayout.NORTH, textoDescr);
        layout.putConstraint(SpringLayout.NORTH, valorPropia, 50, SpringLayout.NORTH, textoDescr);
        layout.putConstraint(SpringLayout.NORTH, textoPoliza, 50, SpringLayout.NORTH, textoPropia);
        layout.putConstraint(SpringLayout.NORTH, valorPoliza, 50, SpringLayout.NORTH, textoPropia);
        layout.putConstraint(SpringLayout.NORTH, textoCuantia, 50, SpringLayout.NORTH, textoPoliza);
        layout.putConstraint(SpringLayout.NORTH, valorCuantia, 50, SpringLayout.NORTH, textoPoliza);
        layout.putConstraint(SpringLayout.NORTH, estadoObra, 50, SpringLayout.NORTH, textoCuantia);
        layout.putConstraint(SpringLayout.NORTH, valorEstado, 50, SpringLayout.NORTH, textoCuantia);
        layout.putConstraint(SpringLayout.NORTH, textoTipoObra, 50, SpringLayout.NORTH, estadoObra);
        layout.putConstraint(SpringLayout.NORTH, valorTipoObra, 50, SpringLayout.NORTH, estadoObra);

        panelInferior.add(autor);
        panelInferior.add(autorValor);
        panelInferior.add(textoFecha);
        panelInferior.add(valorFecha);
        panelInferior.add(textoDescr);
        panelInferior.add(valorDescr);
        panelInferior.add(textoPropia);
        panelInferior.add(valorPropia);
        panelInferior.add(textoPoliza);
        panelInferior.add(valorPoliza);
        panelInferior.add(textoCuantia);
        panelInferior.add(valorCuantia);
        panelInferior.add(estadoObra);
        panelInferior.add(valorEstado);
        panelInferior.add(textoTipoObra);
        panelInferior.add(valorTipoObra);

        
        if (obra.getTipoObra() == TipoDeObra.AUDIOVISUAL) {
            completarAudiovisual((Audiovisual) obra);
        } else {
            completarDimensiones(obra);
            if (obra.getTemp() != null) {
                completarTemperatura(obra);
            }
            if (obra.getHumedad() != null) {
                completarHumedad(obra);
            }

            if (obra.getTipoObra() == TipoDeObra.PINTURA) {
                completarPintura((Pintura) obra);
            } else if (obra.getTipoObra() == TipoDeObra.ESCULTURA){
                completarEscultura((Escultura) obra);
            } else {
                completarFotografía((Fotografia) obra);
            }
        }

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Completar las dimensiones de las obras
     * 
     * @param obra Obra de la que completar las dimensiones
     * 
     */
    public void completarDimensiones(Obra obra) {
        textDimensiones = new JLabel("Dimensiones (alto x ancho x largo): ");

        FormatUtils.formatBoldComponent(
                textDimensiones,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorDimensiones = new JLabel(obra.getDimensiones().getAlto() + " cm. x " + obra.getDimensiones().getAncho() + " cm. x " + obra.getDimensiones().getLargo() + " cm.");

        FormatUtils.formatPlainComponent(
                valorDimensiones,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textDimensiones, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorDimensiones, 30, SpringLayout.EAST, textDimensiones);

        layout.putConstraint(SpringLayout.NORTH, textDimensiones, 50, SpringLayout.NORTH, textoTipoObra);
        layout.putConstraint(SpringLayout.NORTH, valorDimensiones, 50, SpringLayout.NORTH, textoTipoObra);

        panelInferior.add(textDimensiones);
        panelInferior.add(valorDimensiones);

    }
    /**
     * Completar la temperatura de las obras
     * 
     * @param obra Obra de la que cambiar la temperatura
     */
    public void completarTemperatura(Obra obra) {
        textoTemp = new JLabel("Rango de temperatura: ");

        FormatUtils.formatBoldComponent(
                textoTemp,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTemp = new JLabel(obra.getTemp().getMin() + " ºC - " + obra.getTemp().getMax() + " ºC ");

        FormatUtils.formatPlainComponent(
                valorTemp,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoTemp, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTemp, 30, SpringLayout.EAST, textoTemp);

        layout.putConstraint(SpringLayout.NORTH, textoTemp, 50, SpringLayout.NORTH, textDimensiones);
        layout.putConstraint(SpringLayout.NORTH, valorTemp, 50, SpringLayout.NORTH, textDimensiones);

        panelInferior.add(textoTemp);
        panelInferior.add(valorTemp);
    }

    /**
     * Completar la humedad de las obras
     * 
     * @param obra Obra a completar la humedad
     */
    public void completarHumedad(Obra obra) {
        textoHum = new JLabel("Rango de humedad: ");

        FormatUtils.formatBoldComponent(
                textoHum,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorHum = new JLabel(obra.getHumedad().getMin() + " % - " + obra.getHumedad().getMax() + " % ");

        FormatUtils.formatPlainComponent(
                valorHum,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoHum, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorHum, 30, SpringLayout.EAST, textoHum);

        layout.putConstraint(SpringLayout.NORTH, textoHum, 100, SpringLayout.NORTH, textDimensiones);
        layout.putConstraint(SpringLayout.NORTH, valorHum, 100, SpringLayout.NORTH, textDimensiones);

        panelInferior.add(textoHum);
        panelInferior.add(valorHum);
    } 

    /**
     * Completar los detalles de una pintura
     * 
     * @param obra pintura a completar
     */
    public void completarPintura(Pintura obra) {
        JLabel textoTecnica = new JLabel("Técnica: ");

        FormatUtils.formatBoldComponent(
                textoTecnica,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTecnica = new JLabel(obra.getTecnica());

        FormatUtils.formatPlainComponent(
                valorTecnica,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoTecnica, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTecnica, 30, SpringLayout.EAST, textoTecnica);

        layout.putConstraint(SpringLayout.NORTH, textoTecnica, 200, SpringLayout.NORTH, textDimensiones);
        layout.putConstraint(SpringLayout.NORTH, valorTecnica, 200, SpringLayout.NORTH, textDimensiones);

        panelInferior.add(textoTecnica);
        panelInferior.add(valorTecnica);

    }

    /**
     * Completar los detalles de una escultura
     * 
     * @param obra Escultura a completar
     */
    public void completarEscultura(Escultura obra) {
        JLabel textoMaterial = new JLabel("Material: ");

        FormatUtils.formatBoldComponent(
                textoMaterial,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorMaterial = new JLabel(obra.getMaterial());

        FormatUtils.formatPlainComponent(
                valorMaterial,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoMaterial, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorMaterial, 30, SpringLayout.EAST, textoMaterial);

        layout.putConstraint(SpringLayout.NORTH, textoMaterial, 200, SpringLayout.NORTH, textDimensiones);
        layout.putConstraint(SpringLayout.NORTH, valorMaterial, 200, SpringLayout.NORTH, textDimensiones);

        panelInferior.add(textoMaterial);
        panelInferior.add(valorMaterial);

    }

    /**
     * Completar los detalles de una fotografia
     * 
     * @param obra Fotografía a completar
     */
    public void completarFotografía(Fotografia obra) {
        JLabel textoTipo = new JLabel("Tipo: ");

        FormatUtils.formatBoldComponent(
                textoTipo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTipo = new JLabel(obra.getTipo().toString());

        FormatUtils.formatPlainComponent(
                valorTipo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoTipo, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTipo, 30, SpringLayout.EAST, textoTipo);

        layout.putConstraint(SpringLayout.NORTH, textoTipo, 200, SpringLayout.NORTH, textDimensiones);
        layout.putConstraint(SpringLayout.NORTH, valorTipo, 200, SpringLayout.NORTH, textDimensiones);

        panelInferior.add(textoTipo);
        panelInferior.add(valorTipo);

    }

    /**
     * Completar los detalles de una obra audiovisual
     * 
     * @param obra Obra audiovisual a completar
     */
    public void completarAudiovisual(Audiovisual obra) {
        JLabel textoIdioma = new JLabel("Idioma: ");

        FormatUtils.formatBoldComponent(
                textoIdioma,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorIdioma = new JLabel(obra.getIdioma());

        FormatUtils.formatPlainComponent(
                valorIdioma,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);        

        layout.putConstraint(SpringLayout.WEST, textoIdioma, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorIdioma, 30, SpringLayout.EAST, textoIdioma);

        layout.putConstraint(SpringLayout.NORTH, textoIdioma, 50, SpringLayout.NORTH, textoTipoObra);
        layout.putConstraint(SpringLayout.NORTH, valorIdioma, 50, SpringLayout.NORTH, textoTipoObra);

        panelInferior.add(textoIdioma);
        panelInferior.add(valorIdioma);

        JLabel textoDuracion = new JLabel("Duración: ");

        FormatUtils.formatBoldComponent(
                textoDuracion,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorDuracion = new JLabel(obra.getDuracion().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        FormatUtils.formatPlainComponent(
                valorDuracion,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);        

        layout.putConstraint(SpringLayout.WEST, textoDuracion, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorDuracion, 30, SpringLayout.EAST, textoDuracion);

        layout.putConstraint(SpringLayout.NORTH, textoDuracion, 50, SpringLayout.NORTH, textoIdioma);
        layout.putConstraint(SpringLayout.NORTH, valorDuracion, 50, SpringLayout.NORTH, textoIdioma);

        panelInferior.add(textoDuracion);
        panelInferior.add(valorDuracion);

    }

    
}
