package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PruebaCombo {
	private static String[] opciones = { "Opción 1", "Opción 2" };
	private static JFrame v;
	public static void main(String s[]) {
		v = new JFrame();
		JComboBox<String> miCombo = new JComboBox<String>( opciones );
		v.getContentPane().add( miCombo, BorderLayout.NORTH );
		v.setVisible(true);
		miCombo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame v2 = new JFrame();
				v2.setVisible(true);
				v.setVisible( false );
			}
		} );
	}
}
