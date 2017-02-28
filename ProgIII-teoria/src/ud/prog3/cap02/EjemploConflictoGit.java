package ud.prog3.cap02;

public class EjemploConflictoGit {

	public static void main(String[] args) {
		metodoDePrueba();
	}
	
	private static void metodoDePrueba() {
		System.out.println( "Inicio" );
		for (int i=0; i<5; i++) {
			System.out.println( "Valor " + i );
			//
		}
		System.out.println( "Final" );
	}

}
