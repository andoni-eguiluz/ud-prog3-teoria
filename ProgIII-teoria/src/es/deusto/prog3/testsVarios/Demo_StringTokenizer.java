package es.deusto.prog3.testsVarios;

import java.util.StringTokenizer;

/** Demostración de uso de la clase StringTokenizer
 * Permite una manera sencilla de dividir un string en "tokens":
 * palabras, frases...
 * El tokenizer parte de caracteres separadores, e identifica todos
 * los substrings que están separados por esos caracteres.
 * @author andoniEguiluz
 */
public class Demo_StringTokenizer {

	public static void main(String s[]) {
		// Partiendo de una frase cualquiera... 
		String miTexto = "Esto es una frase separada por espacios y algunos símbolos de puntuación: todos válidos en castellano. Debe ser separada, en palabras.";
		// se crea un tokenizador indicando qué caracteres son separadores.
		// Por defecto son espacio, retorno de carro, tabulador.
		// Algunos ejemplos:
		// " " - sólo el espacio
		// " \t\n" - espacio, tabulador y retorno de carro
		// " \t,." - espacio, tabulador, punto, coma
		StringTokenizer st = new StringTokenizer( miTexto, " \t\n.,;:" );
		// El objeto creado analiza el string y lo separa en tokens (substrings).
		// Se puede saber el número de tokens:
		System.out.println( st.countTokens() );
		// Y recorrerlos secuencialmente con nextToken():
		while (st.hasMoreTokens()) {
			System.out.println( st.nextToken() );
		}
	}
}
