package Interfaz.Controladores;

import javax.swing.*;

import Interfaz.Paneles.Common.TablaExposiciones;
import Interfaz.Paneles.Invitado.PanelVerExposicion;
import Interfaz.InterfazUtils.InterfazUtils;

import Sistema.Sistema;
import Usuario.Empleado;
import Usuario.TipoUsuario;

import java.awt.CardLayout;
import java.awt.event.*;

/**
 * Controlador para poner botones en tabla
 * 
 * @author Ignacio Sánchez - Víctor Sanz - Fabio Desio
 */
public class MouseListenerTabla implements MouseListener {
	private JTable tabla;
    private TablaExposiciones tablaExp;
	private PanelVerExposicion panel_exposicion;
	private JPanel vista;
	

	/**
	 * Contructor del controlador
	 * @param table tabla
	 * @param panel_exposicion paner de visualización de exposición
	 * @param vista vista
	 */
    public MouseListenerTabla(TablaExposiciones table, PanelVerExposicion panel_exposicion, JPanel vista) {
        this.tablaExp = table;
		this.tabla = table.getTabla();
		this.panel_exposicion = panel_exposicion;
		this.vista = vista;
    }

	/**
	 * Action listener
	 * 
	 * @param e evento de ratón
	 */
    @Override
	public void mouseClicked(MouseEvent e) {
		//capturo fila o columna dependiendo de mi necesidad
		int columna = tabla.columnAtPoint(e.getPoint());
		int fila = tabla.rowAtPoint(e.getPoint());
		
		/*uso la columna para valiar si corresponde a la columna de perfil garantizando
		 * que solo se produzca algo si selecciono una fila de esa columna
		 */
		if (columna == 5 && fila >= 0) {
			int filaModelo = tabla.convertRowIndexToModel(fila);
			if (filaModelo >= tablaExp.getExposiciones().size()) return;
			if (Sistema.getInstance().getUsuarioActivo() != null
					&& Sistema.getInstance().getUsuarioActivo().getTipoUsuario() == TipoUsuario.EMPLEADO) {
				Empleado emp = (Empleado) Sistema.getInstance().getUsuarioActivo();
				if(!emp.isVenderEntradas()){
					JOptionPane.showMessageDialog(null, "Error, el empleado no tiene permisos de venta");
					return;
				}
			}
			panel_exposicion.update(tablaExp.getExposiciones().get(filaModelo));
			((CardLayout) vista.getLayout()).show(vista, InterfazUtils.VER_COMPRAR_VENDER);
		}
		
	}
	
	//estos metododos pueden ser usados dependiendo de nuestra necesidad, por ejemplo para cambiar el tama�o del icono al ser presionado
	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Action listener
	 * 
	 * @param arg0 evento de ratón
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
