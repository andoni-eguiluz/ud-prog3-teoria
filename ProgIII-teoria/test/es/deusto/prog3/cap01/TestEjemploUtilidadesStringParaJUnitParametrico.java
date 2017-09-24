package es.deusto.prog3.cap01;

import static es.deusto.prog3.cap01.EjemploUtilidadesStringParaJUnit.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/** Prueba de JUnit param�trico (con listas de valores a testear)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 * (Ver comentarios al final del fichero)
 */
@RunWith(Parameterized.class)  // Esta anotaci�n informa a JUnit de que este test es param�trico
public class TestEjemploUtilidadesStringParaJUnitParametrico {

	@Before
	public void setUp() throws Exception {
		// No hay que hacer inicializaci�n en este caso 
	}

	@After
	public void tearDown() throws Exception {
		// No hay que hacer cierre en este caso
	}

	@Parameters  // Esta anotaci�n prepara un m�todo que devuelve todos los valores a recorrer
	             // Tiene que estar en forma de una lista de arrays de Object
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {     
			{ "Hola\nEsto es un string con tres l�neas\ny\tvarios\ttabuladores.", "Hola#Esto es un string con tres l�neas#y|varios|tabuladores." },
			{ "Esto solo tiene\ttabs", "Esto solo tiene|tabs" }, 
			{ "Y esto no tiene l�neas ni tabuladores", "Y esto no tiene l�neas ni tabuladores" }, 
			{ "", "" }, 
			{ null, null }  
		});
    }
	
	@Parameter(0) // Este es el primer elemento de cada array de Objects
	public String original;
	
	@Parameter(1) // Este es el segundo elemento de cada array de Objects (en este caso solo hay dos, podr�a haber n)
	public String sinTabsYSaltos;
	
	@Test  // En el JUnit param�trico se pueden utilizar los atributos p�blicos asignados a los elementos del array.
	       // Se ejecutar� varias veces, tantas como elementos devuelva el m�todo indicado con @Parameters
	public void testQuitarTabsYSaltosLinea() {
		assertEquals( sinTabsYSaltos, quitarTabsYSaltosLinea( original ));
	}

}

// Obviamente, se puede hacer toda esta repetici�n "manualmente" con una repetitiva
// dentro de un m�todo de test. La ventaja es que si utilizamos el mecanismo de JUnit,
// habr� tantas llamadas al m�todo de test como elementos y si hay varios errores
// se nos informar� de cada uno de ellos 
// (no solo del primero como en cualquier otro test, que se corta en cuanto hay un fallo)
