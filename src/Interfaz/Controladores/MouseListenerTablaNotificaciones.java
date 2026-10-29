package Interfaz.Controladores;

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;


import Usuario.Notificacion;
import Sistema.Sistema;

import java.util.List;

/**
 * Controlador para poner botones en tabla de notificaciones
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class MouseListenerTablaNotificaciones implements MouseListener {
    private JTable table;
    private int nColumn;
	private List<Notificacion> notificaciones;

	/**
	 * Contructor del controlador
	 * @param table tabla de notificaciones
	 * @param nColumn numero de columna a poner el boton
	 */
    public MouseListenerTablaNotificaciones(JTable table, int nColumn) {
        this.table = table;
        this.nColumn = nColumn;
    }

	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
    @Override
	public void mouseClicked(MouseEvent e) {
		this.notificaciones = Sistema.getInstance().getUsuarioActivo().getNotificaciones();
		//capturo fila o columna dependiendo de mi necesidad
		int fila = table.rowAtPoint(e.getPoint());
		int columna = table.columnAtPoint(e.getPoint());
		
		/*uso la columna para valiar si corresponde a la columna de perfil garantizando
		 * que solo se produzca algo si selecciono una fila de esa columna
		 */
		if (columna==nColumn) {
			JOptionPane.showMessageDialog(null, notificaciones.get(fila).getCuerpo(), "Contenido de la notificación", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	//estos metododos pueden ser usados dependiendo de nuestra necesidad, por ejemplo para cambiar el tama�o del icono al ser presionado
	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		int columna = table.columnAtPoint(e.getPoint());
		if(columna== nColumn){
			table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
	}

	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}

	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

    
}
