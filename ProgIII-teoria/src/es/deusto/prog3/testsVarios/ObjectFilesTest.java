package es.deusto.prog3.testsVarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectFilesTest {
	public static void main (String[] s) {
		// Test escritura fichero binario writeObject
		try {
			ObjectInputStream oos = new ObjectInputStream( new FileInputStream( "temporal.dat" ));
			Object o1 = oos.readObject();
			System.out.println( o1 );
			Object o2 = oos.readObject();
			System.out.println( o2 );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
