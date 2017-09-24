package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AbreWebsDesdeEmail {

	/**
	 * @param args
	 */
	private static JTextArea miTA = new JTextArea();
	public static void main(String[] args) {
		if (args.length==1) {
			final JFrame miV = new JFrame();
			miV.setSize( 550, 400 );
			miV.setTitle( "Muestra fichero" );
			miV.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			miV.add( new JScrollPane( miTA ), BorderLayout.CENTER );
			JPanel inf = new JPanel();
			JButton miB = new JButton( "Cerrar" );
			inf.add( miB );
			miTA.setWrapStyleWord( true );
			miTA.setLineWrap( true );
			miV.add( new JScrollPane( miTA ), BorderLayout.CENTER );
			miV.add( inf, BorderLayout.SOUTH );
			miTA.append( "Número de parámetros: " + args.length + "\n" );
			leerFichero( args[0] );
			miB.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					miV.dispose();
				}
			});
			miV.setVisible( true );
		}
	}

	private static void leerFichero( String path ) {
		try {
			FileInputStream fich;
			BufferedReader brFich;
			fich = new FileInputStream( path );
			brFich = new BufferedReader( new java.io.InputStreamReader(fich) );
			String lin = brFich.readLine();
			while (lin!=null) {
				miTA.append( lin + "\n" );
				lin = brFich.readLine();
			}
			fich.close();
		} catch (Exception e) {
		}
	}
	
}
