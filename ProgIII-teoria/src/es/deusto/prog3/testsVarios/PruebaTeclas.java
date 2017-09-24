package es.deusto.prog3.testsVarios;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PruebaTeclas implements KeyListener {

	static private ArrayList<Integer> listaTeclasPulsadas =
		new ArrayList<Integer>();
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Si la tecla no estaba pulsada, añadirla
		if (!listaTeclasPulsadas.contains( e.getKeyCode() ))
			listaTeclasPulsadas.add( 
					new Integer(e.getKeyCode()) );
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Quitar la tecla de la lista
		listaTeclasPulsadas.remove( 
			new Integer( e.getKeyCode() ));
	}

	public static void main(String[] args) {
		JFrame miVentana = new JFrame();
		JTextArea texto = new JTextArea();
		texto.setEnabled( false );
		miVentana.getContentPane().add( texto, "Center" ); 
		miVentana.setSize( 640, 480 );
		miVentana.addKeyListener( new PruebaTeclas() );
		miVentana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		miVentana.setVisible( true );
		while (true) {
			try {
				Thread.sleep( 250 );
			} catch (InterruptedException e) {
			}
			texto.insert( listaTeclasPulsadas + "\n", 0 );
			if (!miVentana.isDisplayable()) break; // Acaba
		}
	}

}
