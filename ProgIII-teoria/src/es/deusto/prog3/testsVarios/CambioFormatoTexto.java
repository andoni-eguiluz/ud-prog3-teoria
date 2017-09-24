package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class CambioFormatoTexto {

	/** Cambia el formato de texto de una l�nea de un JTextPane
	 * @param texto	Componente con el texto a modificar
	 * @param linea	N�mero de l�nea (empezando en 0)
	 * @param tipoLetra	Nombre del tipo de letra a usar
	 * @param tamLetra	Tama�o del tipo de letra a usar
	 * @param color	Color del texto a poner en la l�nea
	 */
	public static void cambiarFormatoTextoDeLinea( JTextPane texto, int linea, String tipoLetra, int tamLetra, Color color ) {
		String textoPlano = texto.getText();
		int desde = 0;
		int hasta = textoPlano.length();
		// Buscar la l�nea dentro del texto con los �ndices desde-hasta:
		while (linea >= 0) {
			int finLinea = textoPlano.indexOf( '\n', desde );
			if (finLinea != -1) {  // Si existe el retorno
				if (linea==0)
					hasta = finLinea;
				else
					desde = finLinea+1;
			} else if (linea>0) {  
				// Si se acaba antes del final es que la l�nea es incorrecta
				// (no se hace nada)
				texto.select(0,0);
				return;
			}
			linea--;
		}
		// Seleccionar y cambiar los atributos del texto de la l�nea:
		SimpleAttributeSet attr = new SimpleAttributeSet(); // Atributos a poner
        StyleConstants.setFontFamily( attr, tipoLetra );  // tipo de letra
        StyleConstants.setFontSize( attr, tamLetra );  // tama�o
        StyleConstants.setForeground( attr, color ); // color
		texto.select( desde, hasta ); // Selecciona el texto
		texto.setCharacterAttributes( attr, true ); // Cambia los atributos del texto seleccionado
		texto.select(0,0); // Quita la selecci�n
	}
	
	public static void main( String s[] ) {
		JFrame vent = new JFrame();
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		vent.setSize(500,300);
		JTextPane textoColores = new JTextPane();
		vent.add( new JScrollPane(textoColores), BorderLayout.CENTER );
		textoColores.setText( 
			"Primera l�nea\n" +
			"Segunda l�nea\n" +
			"Tercera l�nea\n" +
			"Cuarta l�nea" );
		cambiarFormatoTextoDeLinea( textoColores, 0, "SansSerif", 14, Color.green );
		cambiarFormatoTextoDeLinea( textoColores, 1, "Arial", 16, Color.red );
		cambiarFormatoTextoDeLinea( textoColores, 3, "Lucida Sans Typewriter", 18, Color.blue );
		cambiarFormatoTextoDeLinea( textoColores, 4, "Times New Roman", 50, Color.gray );
		vent.setVisible( true );
	}
}
