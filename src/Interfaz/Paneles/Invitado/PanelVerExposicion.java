package Interfaz.Paneles.Invitado;

import Exposicion.*;
import Interfaz.InterfazUtils.*;
import Obra.Obra;
import Sala.*;
import Sistema.Sistema;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * Panel ver exposición
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class PanelVerExposicion extends JPanel {

  protected JPanel panelDerechoAbajo;
  JButton atras;

  /**
   * Constructor del panel
   */
  public PanelVerExposicion() {
    super();
    atras = new JButton("Atrás");
  }

  /**
   * Función para actualizar el panel
   * @param expo exposición
   */
  public void update(Exposicion expo) {
    this.removeAll();

    panelDerechoAbajo = new JPanel();
    JPanel panelDerechoArriba = new JPanel();
    JPanel panelIzquierdo = new JPanel(new SpringLayout());

    panelDerechoArriba.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
    panelDerechoArriba.setBackground(new Color(210, 245, 255));
    panelIzquierdo.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

    JSplitPane rightSplitPane = new JSplitPane(
        JSplitPane.VERTICAL_SPLIT,
        panelDerechoArriba,
        panelDerechoAbajo);
    rightSplitPane.setResizeWeight(0.07);
    rightSplitPane.setDividerSize(0);
    rightSplitPane.setEnabled(false);

    JSplitPane mainSplitPane = new JSplitPane(
        JSplitPane.HORIZONTAL_SPLIT,
        panelIzquierdo,
        rightSplitPane);
    mainSplitPane.setResizeWeight(0.25);
    mainSplitPane.setDividerSize(0);
    mainSplitPane.setEnabled(false);
    mainSplitPane.setPreferredSize(
        new Dimension(InterfazUtils.DIM_X, InterfazUtils.DIM_Y));

    this.add(mainSplitPane, BorderLayout.CENTER);

    /* Añadimos los labels de la vista */
    JLabel titulo = LabelUtils.addLabel(
        new JLabel(expo.getNombre()),
        panelDerechoArriba);
    FormatUtils.formatBoldComponent(
        titulo,
        FormatUtils.DEFAULT,
        FormatUtils.TITLE_SIZE);

    JLabel textoTipoExpo = LabelUtils.addLabel(
        new JLabel("Tipo de exposición:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoTipoExpo,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel valorTipoExpo = LabelUtils.addLabel(
        new JLabel(expo.isPermanente() ? "Permanente" : "Temporal"),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorTipoExpo,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoObras = LabelUtils.addLabel(
        new JLabel("Obras:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoObras,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel valorObras = LabelUtils.addLabel(
        new JLabel(
            expo.getObras().isEmpty()
                ? "No hay obras en la exposición"
                : expo
                    .getObras()
                    .stream()
                    .map(Obra::getNombre)
                    .collect(Collectors.joining(", "))),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorObras,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoTipoObra = LabelUtils.addLabel(
        new JLabel("Tipos de obras:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoTipoObra,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    String texto = "";
    for (Obra obra : expo.getObras()) {
      texto += obra.getTipoObra();
      texto += (expo.getObras().indexOf(obra) != expo.getObras().size() - 1)
          ? ", "
          : "";
    }

    if (texto.equals("")) texto = "No hay tipos de obras en la exposición";

    JLabel valorTipoObra = LabelUtils.addLabel(
        new JLabel(texto),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorTipoObra,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoSalas = LabelUtils.addLabel(
        new JLabel("Salas:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoSalas,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel valorSalas = LabelUtils.addLabel(
        new JLabel(
            expo.getSalas().isEmpty()
                ? "La exposición no está asociada a ninguna sala"
                : expo
                    .getSalas()
                    .stream()
                    .map(Sala::getNombre)
                    .collect(Collectors.joining(", "))),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorSalas,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoPrecio = LabelUtils.addLabel(
        new JLabel("Precio:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoPrecio,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel valorPrecio = LabelUtils.addLabel(
        new JLabel(String.format("%.2f€", expo.getPrecio())),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorPrecio,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoDescr = LabelUtils.addLabel(
        new JLabel("Descripción:"),
        panelDerechoAbajo);
    FormatUtils.formatBoldComponent(
        textoDescr,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel valorDescr = LabelUtils.addLabel(
        new JLabel(expo.getDescr()),
        panelDerechoAbajo);
    FormatUtils.formatPlainComponent(
        valorDescr,
        FormatUtils.DEFAULT,
        FormatUtils.PLAIN_TEXT_SIZE);

    JLabel textoFecha = null, valorFecha = null;

    if (expo.isTemporal()) {
      textoFecha = LabelUtils.addLabel(new JLabel("Fechas:"), panelDerechoAbajo);
      FormatUtils.formatBoldComponent(
          textoFecha,
          FormatUtils.DEFAULT,
          FormatUtils.PLAIN_TEXT_SIZE);

      valorFecha = LabelUtils.addLabel(
          new JLabel(
              expo
                  .getfInicio()
                  .format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                  " - " +
                  expo.getfFinal().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))),
          panelDerechoAbajo);
      FormatUtils.formatPlainComponent(
          valorFecha,
          FormatUtils.DEFAULT,
          FormatUtils.PLAIN_TEXT_SIZE);
    }

    /* Construimos el layout */
    SpringLayout layoutDerArriba = new SpringLayout();
    panelDerechoArriba.setLayout(layoutDerArriba);

    SpringLayoutUtils.setPosition(
        titulo,
        panelDerechoArriba,
        new Coordinates(25, 0, 0, 25),
        layoutDerArriba);

    SpringLayout layoutDerAbajo = new SpringLayout();
    panelDerechoAbajo.setLayout(layoutDerAbajo);

    /* Organizamos los labels con el layout */
    SpringLayoutUtils.setPosition(
        textoTipoExpo,
        panelDerechoAbajo,
        new Coordinates(25, 0, 0, 25),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorTipoExpo,
        textoTipoExpo,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorTipoExpo,
        0,
        SpringLayout.BASELINE,
        textoTipoExpo);

    SpringLayoutUtils.setPosition(
        textoObras,
        textoTipoExpo,
        new Coordinates(0, 50, 0, 1),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorObras,
        textoObras,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorObras,
        0,
        SpringLayout.BASELINE,
        textoObras);

    SpringLayoutUtils.setPosition(
        textoTipoObra,
        textoObras,
        new Coordinates(0, 50, 0, 1),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorTipoObra,
        textoTipoObra,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorTipoObra,
        0,
        SpringLayout.BASELINE,
        textoTipoObra);

    SpringLayoutUtils.setPosition(
        textoSalas,
        textoTipoObra,
        new Coordinates(0, 50, 0, 1),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorSalas,
        textoSalas,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorSalas,
        0,
        SpringLayout.BASELINE,
        textoSalas);

    SpringLayoutUtils.setPosition(
        textoPrecio,
        textoSalas,
        new Coordinates(0, 50, 0, 1),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorPrecio,
        textoPrecio,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorPrecio,
        0,
        SpringLayout.BASELINE,
        textoPrecio);

    SpringLayoutUtils.setPosition(
        textoDescr,
        textoPrecio,
        new Coordinates(0, 50, 0, 1),
        layoutDerAbajo);
    SpringLayoutUtils.setPositionRev(
        valorDescr,
        textoDescr,
        new Coordinates(0, 0, 0, 10),
        layoutDerAbajo);
    layoutDerAbajo.putConstraint(
        SpringLayout.BASELINE,
        valorDescr,
        0,
        SpringLayout.BASELINE,
        textoDescr);

    if (expo.isTemporal()) {
      SpringLayoutUtils.setPosition(
          textoFecha,
          textoDescr,
          new Coordinates(0, 50, 0, 1),
          layoutDerAbajo);
      SpringLayoutUtils.setPositionRev(
          valorFecha,
          textoFecha,
          new Coordinates(0, 0, 0, 10),
          layoutDerAbajo);
      layoutDerAbajo.putConstraint(
          SpringLayout.BASELINE,
          valorFecha,
          0,
          SpringLayout.BASELINE,
          textoFecha);
    }

    SpringLayout layout = (SpringLayout) panelIzquierdo.getLayout();

    ImageIcon imagen = expo.getImagen();
    JLabel imagenExpoLabel;
    if (imagen != null && imagen.getIconWidth() > 0 && imagen.getIconHeight() > 0) {
      imagenExpoLabel = new JLabel(new ImageIcon(
          imagen.getImage().getScaledInstance(-1, 450, Image.SCALE_SMOOTH)));
    } else {
      imagenExpoLabel = new JLabel("Imagen no disponible");
    }
    LabelUtils.addLabel(imagenExpoLabel, panelIzquierdo);
    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, imagenExpoLabel, 0,
        SpringLayout.HORIZONTAL_CENTER, panelIzquierdo);
    layout.putConstraint(SpringLayout.NORTH, imagenExpoLabel, 77,
        SpringLayout.NORTH, panelIzquierdo);

    layout.putConstraint(
        SpringLayout.WEST,
        atras,
        50,
        SpringLayout.WEST,
        panelIzquierdo);

    layout.putConstraint(
        SpringLayout.NORTH,
        atras,
        20,
        SpringLayout.NORTH,
        panelIzquierdo);

    panelIzquierdo.add(atras);

    this.revalidate();
    this.repaint();
  }

  /**
   * Método para extraer las horas disponibles
   * @return horas disponibles
   */
  public static JComboBox<String> horasDisponibles() {
    List<String> horasEnIntervalo = new ArrayList<>();

    // Generar las horas en punto dentro del intervalo
    LocalTime horaActual = Sistema.getInstance().getHorario().getApertura();
    LocalTime horaFin = Sistema.getInstance().getHorario().getCierre();
    while (horaActual.isBefore(horaFin) || horaActual.equals(horaFin)) {
      // Agregar la hora actual a la lista de horas en punto
      horasEnIntervalo.add(horaActual.toString());

      // Incrementar la hora actual en 1 hora
      horaActual = horaActual.plusHours(1);
    }

    horasEnIntervalo.add(0, "Seleccionar hora");

    JComboBox<String> comboBox = new JComboBox<>(
        horasEnIntervalo.toArray(new String[0]));

    return comboBox;
  }

  /**
   * Getter del panelDerechoAbajo
   * 
   * @return panelDerechoAbajo
   */
  public JPanel getPanelDerechoAbajo() {
    return panelDerechoAbajo;
  }

  /**
   * Setter del controaldor atrás
   * 
   * @param c actionListener
   */
  public void setControladorAtras(ActionListener c) {
    atras.addActionListener(c);
  }
}