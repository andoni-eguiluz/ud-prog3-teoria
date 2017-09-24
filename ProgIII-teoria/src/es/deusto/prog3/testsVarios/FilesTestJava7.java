package es.deusto.prog3.testsVarios;
import java.nio.file.Files;  // Java 7
import java.nio.file.LinkOption;  // Java 7
import java.nio.file.Path;  // Java 7
import java.nio.file.Paths;  // Java 7
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.System;

public class FilesTestJava7 implements Serializable {
	private static final long serialVersionUID = 12345L;

	public static void main( String[] s) {

		// Test clase Path (java 7)
		Path p2 = Paths.get("Equipo.txt");
		Path p1 = Paths.get("D:\\Desarrollo\\ProgIII\\TeoriaYEjemplos");
		System.out.println( p1.getFileName() );
		System.out.println( p1.getNameCount() );
		System.out.println( p1.getName(0) );
		System.out.println( p1.getParent() );
		System.out.println( p1.getRoot() );
		System.out.println( p1.subpath( 1, 3 ) );
		System.out.println( p1.toAbsolutePath() );
		System.out.println( p1.toUri() );
		
		// Test lectura fichero binario como texto
		String nomF = "D:\\Clases\\ProgIII\\1213\\Teoria\\PIII_CambiosParaProximoCurso.doc";
		try {
			BufferedReader brFich = new BufferedReader( new java.io.InputStreamReader( new FileInputStream( nomF ) ) );
			String linea = brFich.readLine();
			while (linea != null) {
				if (linea.contains( "al " ))  // Por ej. sacar sólo las líneas que contengan ese substring
					System.out.println( linea );
				linea = brFich.readLine();
			}
			brFich.close();
		} catch (Exception e) {  // FileNotFound, IO
			e.printStackTrace();
		}
		
		// Test escritura fichero binario writeObject
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "temporal.dat" ));
			FilesTestJava7 t = new FilesTestJava7();
			oos.writeObject( t.new TestObject(1) );
			oos.writeObject( t.new TestObject(2) );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class TestObject implements Serializable {
		private static final long serialVersionUID = 1234567L;
		int a;
		TestObject( int a ) { this.a = a; }
		public String toString() { return ""+a; }
	}
}

