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



/** Prueba de menú contextual
 * @author andoni
 */
public class PruebaMenuContextual {
	private static JPopupMenu popup;
	private static void anyadeMenuContextual( Container c ) {
		// 1.- Creamos el menú de contexto (JPopupMenu)
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Opción 1 de menú contextual");
		popup.add(menuItem);
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println( "Opción 1");
			}
		} );
		menuItem = new JMenuItem("Opción 2 de menú contextual");
		popup.add(menuItem);
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println( "Opción 2");
			}
		} );
		// 2.- Añadimos escuchador de ratón al contenedor que va a 
		// sacar ese menú contextual
		MouseListener popupListener = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
		        // if (e.isPopupTrigger())  comprobaría si el evento particular
				// es el de menú de contexto para este SO
				// (botón derecho, etc.)
				// En este caso, simplemente mostramos con cualquier botón:
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
