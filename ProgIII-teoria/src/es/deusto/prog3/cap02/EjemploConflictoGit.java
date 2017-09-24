package es.deusto.prog3.cap02;

public class EjemploConflictoGit {
	
	// Comentario aquí
	
	public static void main(String[] args) {
		metodoDePrueba();
	}
	
	private static void metodoDePrueba() {
		System.out.println( "Inicio" );
		for (int i=0; i<5; i++) {
			int j = i*2;
			System.out.println( "Valor " + j );
			//
		}
		System.out.println( "Final" );
	}

}
