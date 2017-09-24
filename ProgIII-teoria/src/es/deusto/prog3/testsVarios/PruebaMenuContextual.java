package es.deusto.prog3.testsVarios;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;



/** Prueba de men� contextual
 * @author andoni
 */
public class PruebaMenuContextual {
	private static JPopupMenu popup;
	private static void anyadeMenuContextual( Container c ) {
		// 1.- Creamos el men� de contexto (JPopupMenu)
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Opci�n 1 de men� contextual");
		popup.add(menuItem);
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println( "Opci�n 1");
			}
		} );
		menuItem = new JMenuItem("Opci�n 2 de men� contextual");
		popup.add(menuItem);
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println( "Opci�n 2");
			}
		} );
		// 2.- A�adimos escuchador de rat�n al contenedor que va a 
		// sacar ese men� contextual
		MouseListener popupListener = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
		        // if (e.isPopupTrigger())  comprobar�a si el evento particular
				// es el de men� de contexto para este SO
				// (bot�n derecho, etc.)
				// En este caso, simplemente mostramos con cualquier bot�n:
	            popup.show(e.getComponent(),
	                       e.getX(), e.getY());
			}
			@Override
			public void mousePressed(MouseEvent e) {
		    }
		};
		c.addMouseListener( popupListener );
	}
	
	
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		vent.setSize( 600, 400 );
		anyadeMenuContextual( vent.getContentPane() );
		vent.setVisible( true );
	}

}
