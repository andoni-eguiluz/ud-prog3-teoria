package es.deusto.prog3.testsVarios;

public class PruebaHerencia {
	public static void main( String s[] ) {
		Persona p1 = new Persona( "juan" );
		Alumno a1 = new Alumno( "elena", 9.8 );
		Alumno a2 = new Alumno( "elena", 5.0 );
		Persona p = a1;
		p.calcIRPF();
		if (a1.equals( a2 )) {
			System.out.println( "IGUALES!!" );
		} else {
			System.out.println( "NO IGUALES" );
		}
		System.out.println( a1.toString() );
	}
}

class Persona {
	protected String nombre;
	public Persona( String pNombre ) {
		super();
		nombre = pNombre;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Persona) {
//			Persona temp = (Persona) obj;
//			return (nombre.equals(temp.nombre));
			return (nombre.equals(  ((Persona) obj).nombre));
		} else {
			return false;
		}
	}
	public void calcIRPF() {
		;
	}
}

class Alumno extends Persona {
	// heredo String nombre;
	double media;
	public Alumno() {
		super( "" );
		nombre = "prueba";
		media = 0.0;
	}
	public Alumno( String pNombre, double pMedia ) {
		super( pNombre );
		media = pMedia;
	}
	public void calcMedia() {
		
	}
	@Override
	public String toString() {
		return "Nombre: " + nombre + " - nota media: " + media;
	}
	
}