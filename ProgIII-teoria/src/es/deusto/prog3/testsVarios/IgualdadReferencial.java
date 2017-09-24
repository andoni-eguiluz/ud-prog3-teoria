package es.deusto.prog3.testsVarios;

public class IgualdadReferencial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "Prueba";
		String s2 = "Prueba";
		if(s1 == s2) {
			// Referencias al mismo string immutable string - 
			// Una técnica de optimización de código Java 
			System.out.println("'Prueba' sí es == a 'Prueba'");
		} else {
			System.out.println("'Prueba' no es == a 'Prueba'");
		}
		String s3 = new String( "Prueba" );
		String s4 = new String( "Prueba" );
		if(s3 == s4) {
			System.out.println("Con new String() 'Prueba' sí es == a 'Prueba'");
		} else {
			System.out.println("Con new String() 'Prueba' no es == a 'Prueba'");
		}
		String s5 = "Prueba";
		String s6b = "ba";
		String s6 = "Prue" + s6b;
		if(s5 == s6) {
			System.out.println("Con concatenación 'Prueba' sí es == a 'Prueba'");
		} else {
			System.out.println("Con concatenación 'Prueba' no es == a 'Prueba'");
		}
	}

}
