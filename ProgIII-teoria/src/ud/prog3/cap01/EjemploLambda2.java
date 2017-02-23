package ud.prog3.cap01;

import java.util.function.Consumer;
import java.util.function.Function;

public class EjemploLambda2 {

	public static void main(String[] args) {
		// Expresiones puramente funcionales
		Consumer<String> f = aplica( EjemploLambda2::visuEntero, EjemploLambda2::stringAInt );
		f.accept("5");
		f.accept("Hola");
		// O bien 
		consumeStrings( f, "5", "Hola" );
	}
	
	public static Consumer<String> aplica( Consumer<Integer> procesadora, Function<String,Integer> transformadora ) {
		return (String s) -> { procesadora.accept(transformadora.apply(s)); };
	}
	
	public static void consumeStrings( Consumer<String> f, String...strings ) {
		for (String s : strings) f.accept(s);
	}

	private static void visuEntero( int i ) {
		System.out.println( i );
	}
	
	private static int stringAInt( String s ) {
		try {
			return Integer.parseInt( s );
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
}
