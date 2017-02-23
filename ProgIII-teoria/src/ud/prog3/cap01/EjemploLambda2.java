package ud.prog3.cap01;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EjemploLambda2 {

	public static void main(String[] args) {
		// Ejemplo 1
		// Expresiones puramente funcionales (variables y parámetros "función")
		Consumer<String> f = aplica( EjemploLambda2::visuEntero, EjemploLambda2::stringAInt );
		f.accept("5");
		f.accept("Hola");
		// O bien 
		consumeStrings( f, "5", "Hola" );
		
		// Ejemplo 2: Suppliers
		aplica( EjemploLambda2::damePrimo, EjemploLambda2::visuEntero, 20 );
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
	
		private static int ultimoPrimo = -1;  // Para que el siguiente sea +1 (se va solo por impares)
	// Suppliers
	private static int damePrimo() {
		boolean tieneDivisores;
		do {
			tieneDivisores = false;
			ultimoPrimo += 2;  // Siguiente impar
			for (int divisor=3;divisor<=Math.sqrt(ultimoPrimo);divisor+=2) {
				if (ultimoPrimo % divisor == 0) { tieneDivisores = true; break; }
			}
		} while (tieneDivisores);
		return ultimoPrimo;
	}

	public static void aplica( Supplier<Integer> supp, Consumer<Integer> cons, int numVals ) {
		for (int i=0; i<numVals; i++)
			cons.accept( supp.get() );
	}
	
	
}
