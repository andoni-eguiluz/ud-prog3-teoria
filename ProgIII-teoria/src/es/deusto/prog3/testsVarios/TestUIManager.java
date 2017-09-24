package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class TestUIManager {
	private static JTextArea ta;
	private static TreeSet<String> clavesOrd;
	private static UIDefaults valoresPorDefecto;
	private static JComboBox<String> cb;
	public static void main (String[] s) {
		// Combo y vector que guardará las clases diferentes de los UIDefaults
		Vector<String> clases = new Vector<String>();
		cb = new JComboBox<String>(
				new DefaultComboBoxModel<String>( clases ) );
		cb.setEditable( true );
		// Textarea para mostrar los valores de los UIDefaults
		ta = new JTextArea();
		JFrame v = new JFrame();
		v.setSize( 700, 500 );
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		v.add( new JScrollPane( ta ), BorderLayout.CENTER);  
		v.add( cb, BorderLayout.NORTH );
			// Sería color blanco de fondo
		ta.setBackground( UIManager.getColor( "Panel.background" ));
			// Pone el color por defecto del UI usado (gris)
		v.setVisible( true );

		// Saca todas las claves de los valores por defecto y las ordena alfabéticamente (clavesOrd)
		valoresPorDefecto = UIManager.getLookAndFeelDefaults();
		Set<Object> claves = valoresPorDefecto.keySet();
		clavesOrd = new TreeSet<String>();
		for( Object key : claves ) { 
			String clave = key.toString();
			clavesOrd.add( clave );
		}
		// Calcula el vector de clases (para el combo):
		for( String clave : clavesOrd ) {
			clavesOrd.add( clave );
			Object valor = valoresPorDefecto.get(clave);
			String claseValor = "null";
			if (valor != null) {
				claseValor = valor.getClass().getName();
				if (!clases.contains(claseValor)) {
					clases.add( claseValor );
				}
			}
		}
		Collections.sort( clases );
		// Añade todos los valores a la textarea:
		muestraValores( "" );
		// Escuchador para el combo 
		cb.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String clase = (String) cb.getSelectedItem();
				muestraValores( clase );
			}
		});
	}
	
	private static void muestraValores( String filtro ) {
		ta.setText( "" );
		for( String clave : clavesOrd ) {
			Object valor = valoresPorDefecto.get(clave);
			if (filtro.equals("") || (valor != null && valor.getClass().getName().contains( filtro )))
				ta.append( clave + " --> " + valor + "\n" );
		}
		ta.setCaretPosition(0);
	}
}
