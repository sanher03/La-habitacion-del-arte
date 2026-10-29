package Interfaz.Paneles.Gestor.Obras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import Interfaz.Controladores.ControladorObrasFichero;
import Interfaz.InterfazUtils.FormatUtils;
import Interfaz.Paneles.Common.SuperButton;


/**
 * Panel de coger nuevas obras desde un fichero 
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
*/
public class PanelNuevaObraFichero extends JPanel {

    File selectedFile;
    JLabel textoArchivo;
    JButton importarObras;
    SpringLayout layout;
    JTable tabla;

    /**
     * Constructor del panel de nueva obra desde fichero
     * 
     * @param tabla JTable con la tabla de obras
     */
    public PanelNuevaObraFichero(JTable tabla) {
        super(new SpringLayout());
        layout = (SpringLayout) this.getLayout();

        this.tabla = tabla;

        JButton chooser = new JButton("Seleccionar archivo...");

        PanelNuevaObraFichero panel = this;

        chooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorObrasFichero.setControladorElegirFichero(panel);
            }

        });

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, chooser, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, chooser, -300, SpringLayout.VERTICAL_CENTER, this);

        textoArchivo = new JLabel("");

        FormatUtils.formatBoldComponent(
                textoArchivo,
                FormatUtils.DEFAULT,
                FormatUtils.TEXT_SIZE);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textoArchivo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textoArchivo, 70, SpringLayout.VERTICAL_CENTER, chooser);

        this.add(chooser);
        this.add(textoArchivo);

    }

    /**
     * Actualizador de el panel de archivo de obras
     * 
     * @param selectedFile fichero seleccionado
     */
    public void update(File selectedFile) {
        this.selectedFile = selectedFile;

        textoArchivo.setText("Archivo seleccionado: " + selectedFile.getName());

        if (importarObras != null)
            this.remove(importarObras);

        importarObras = new SuperButton("Importar obras", 250);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, importarObras, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, importarObras, 70, SpringLayout.VERTICAL_CENTER,
                textoArchivo);


        importarObras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ControladorObrasFichero.importarObrasDeFichero(tabla, selectedFile.getName());

            }

        });

        this.add(importarObras);

        this.revalidate();
        this.repaint();

    }

}
