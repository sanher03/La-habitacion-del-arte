package Interfaz.Paneles.Cliente.Sorteos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.Controladores.ControladorSorteosCliente;
import Interfaz.Paneles.Common.PanelTitulo;
import Interfaz.Paneles.Common.SuperButton;
import Sorteo.EstadosSorteo;
import Sorteo.Sorteo;
import Sorteo.SorteoFechaHora;
import Sorteo.SorteoPlazoDado;
import Sorteo.TipoSorteo;

/**
 * Panel para ver los detalles del sorteo
 * 
 * @author Ignacio Sáncehz - Víctor Sanz - Fabio Desio
 */
public class PanelDetallesSorteo extends JPanel {
    PanelTitulo titulo;
    SpringLayout layout;
    JPanel panelInferior;
    JSplitPane completo;
    Sorteo sorteo;
    JLabel nEntradas, nEntradasValor, textoTipoSorteo;

    /**
     * Constructor PanelDetallesSorteo
     */
    public PanelDetallesSorteo() {
        super(new BorderLayout());
    }

    /**
     * Función para modificar el panel de detalles
     * 
     * @param sorteo sorteo
     */
    public void update(Sorteo sorteo) {
        this.sorteo = sorteo;

        this.removeAll();

        titulo = new PanelTitulo("Sorteo de \"" + sorteo.getExposicion().getNombre() + "\"");

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

        nEntradas = new JLabel("Número de entradas: ");

        FormatUtils.formatBoldComponent(
                nEntradas,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        nEntradasValor = new JLabel(Integer.toString(sorteo.getnEntradas()));

        FormatUtils.formatPlainComponent(
                nEntradasValor,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoFecha = new JLabel("Fechas de inscripción: ");
        FormatUtils.formatBoldComponent(
                textoFecha,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorFecha = new JLabel(
                sorteo.getfInicioInscripcion()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                        " - " +
                        sorteo.getfFinInscripcion().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        FormatUtils.formatPlainComponent(
                valorFecha,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        textoTipoSorteo = new JLabel("Tipo de sorteo:");

        FormatUtils.formatBoldComponent(
                textoTipoSorteo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTipoSorteo = new JLabel(sorteo.getTipoSorteo().toString());
        FormatUtils.formatPlainComponent(
                valorTipoSorteo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel estadoSorteo = new JLabel("Estado del sorteo:");

        FormatUtils.formatBoldComponent(
                estadoSorteo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorEstado = new JLabel(sorteo.getEstado().toString());

        FormatUtils.formatPlainComponent(
                valorEstado,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, nEntradas, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, nEntradasValor, 30, SpringLayout.EAST, nEntradas);
        layout.putConstraint(SpringLayout.WEST, textoFecha, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorFecha, 30, SpringLayout.EAST, textoFecha);
        layout.putConstraint(SpringLayout.WEST, textoTipoSorteo, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTipoSorteo, 30, SpringLayout.EAST, textoTipoSorteo);
        layout.putConstraint(SpringLayout.WEST, estadoSorteo, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorEstado, 30, SpringLayout.EAST, estadoSorteo);

        layout.putConstraint(SpringLayout.NORTH, nEntradas, 50, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.NORTH, nEntradasValor, 50, SpringLayout.NORTH, panelInferior);
        layout.putConstraint(SpringLayout.NORTH, textoFecha, 50, SpringLayout.NORTH, nEntradas);
        layout.putConstraint(SpringLayout.NORTH, valorFecha, 50, SpringLayout.NORTH, nEntradas);
        layout.putConstraint(SpringLayout.NORTH, estadoSorteo, 50, SpringLayout.NORTH, textoFecha);
        layout.putConstraint(SpringLayout.NORTH, valorEstado, 50, SpringLayout.NORTH, textoFecha);
        layout.putConstraint(SpringLayout.NORTH, textoTipoSorteo, 50, SpringLayout.NORTH, estadoSorteo);
        layout.putConstraint(SpringLayout.NORTH, valorTipoSorteo, 50, SpringLayout.NORTH, estadoSorteo);

        panelInferior.add(nEntradas);
        panelInferior.add(nEntradasValor);
        panelInferior.add(textoFecha);
        panelInferior.add(valorFecha);
        panelInferior.add(estadoSorteo);
        panelInferior.add(valorEstado);
        panelInferior.add(textoTipoSorteo);
        panelInferior.add(valorTipoSorteo);

        if (sorteo.getTipoSorteo() == TipoSorteo.PARA_FECHA_HORA) {
            completarFechaHora((SorteoFechaHora) sorteo);
        } else if (sorteo.getTipoSorteo() == TipoSorteo.PARA_PLAZO_DADO) {
            completarPlazoDado((SorteoPlazoDado) sorteo);
        }

        if (sorteo.getEstado() == EstadosSorteo.ACTIVO) {
            completarBotonInscribirse();
        }

        this.add(completo, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * Método para completar los sorteos por fecha y hora
     * 
     * @param sorteo sorteo
     */
    public void completarFechaHora(SorteoFechaHora sorteo) {
        JLabel textoFechaVisita = new JLabel("Fecha de visita: ");

        FormatUtils.formatBoldComponent(
                textoFechaVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTextoVisita = new JLabel(
                sorteo.getFechaVisita().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        FormatUtils.formatPlainComponent(
                valorTextoVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoHoraVisita = new JLabel("Hora de visita: ");

        FormatUtils.formatBoldComponent(
                textoHoraVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorHoraVisita = new JLabel(
                sorteo.getHoraVisita().format(DateTimeFormatter.ofPattern("HH:mm")));

        FormatUtils.formatPlainComponent(
                valorHoraVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoFechaVisita, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTextoVisita, 30, SpringLayout.EAST, textoFechaVisita);
        layout.putConstraint(SpringLayout.WEST, textoHoraVisita, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorHoraVisita, 30, SpringLayout.EAST, textoHoraVisita);

        layout.putConstraint(SpringLayout.NORTH, textoFechaVisita, 50, SpringLayout.NORTH, textoTipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, valorTextoVisita, 50, SpringLayout.NORTH, textoTipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, textoHoraVisita, 50, SpringLayout.NORTH, textoFechaVisita);
        layout.putConstraint(SpringLayout.NORTH, valorHoraVisita, 50, SpringLayout.NORTH, textoFechaVisita);

        panelInferior.add(textoFechaVisita);
        panelInferior.add(valorTextoVisita);
        panelInferior.add(textoHoraVisita);
        panelInferior.add(valorHoraVisita);

    }

    /**
     * Método para completar los sorteos por plazo dado
     * @param sorteo
     */
    public void completarPlazoDado(SorteoPlazoDado sorteo) {
        JLabel textoFechaVisita = new JLabel("Inicio intervalo de visita: ");

        FormatUtils.formatBoldComponent(
                textoFechaVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorTextoVisita = new JLabel(
                sorteo.getFechaInicioVisita().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        FormatUtils.formatPlainComponent(
                valorTextoVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel textoHoraVisita = new JLabel("Fin intervalo de visita: ");

        FormatUtils.formatBoldComponent(
                textoHoraVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        JLabel valorHoraVisita = new JLabel(
                sorteo.getFechaFinalVisita().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

        FormatUtils.formatPlainComponent(
                valorHoraVisita,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.WEST, textoFechaVisita, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorTextoVisita, 30, SpringLayout.EAST, textoFechaVisita);
        layout.putConstraint(SpringLayout.WEST, textoHoraVisita, 30, SpringLayout.WEST, panelInferior);
        layout.putConstraint(SpringLayout.WEST, valorHoraVisita, 30, SpringLayout.EAST, textoHoraVisita);

        layout.putConstraint(SpringLayout.NORTH, textoFechaVisita, 50, SpringLayout.NORTH, textoTipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, valorTextoVisita, 50, SpringLayout.NORTH, textoTipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, textoHoraVisita, 50, SpringLayout.NORTH, textoFechaVisita);
        layout.putConstraint(SpringLayout.NORTH, valorHoraVisita, 50, SpringLayout.NORTH, textoFechaVisita);

        panelInferior.add(textoFechaVisita);
        panelInferior.add(valorTextoVisita);
        panelInferior.add(textoHoraVisita);
        panelInferior.add(valorHoraVisita);

    }

    /**
     * Método para completar el botón para inscribirse
     */
    public void completarBotonInscribirse() {
        JButton inscribirse = new SuperButton("Inscribirse", 250);

        String[] opciones = {"Seleccione número de entradas", "1", "2"};

        JComboBox<String> entradas = new JComboBox<>(opciones);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, entradas, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inscribirse, 0, SpringLayout.HORIZONTAL_CENTER, panelInferior);

        layout.putConstraint(SpringLayout.NORTH, entradas, 200, SpringLayout.NORTH, textoTipoSorteo);
        layout.putConstraint(SpringLayout.NORTH, inscribirse, 50, SpringLayout.NORTH, entradas);

        panelInferior.add(entradas);
        panelInferior.add(inscribirse);

        PanelDetallesSorteo panel = this;

        inscribirse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorSorteosCliente.inscribirse(panel, sorteo, entradas);
            }

        });
    }
}
