package es.deusto.prog3.testsVarios.recursos;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Recurso {
	/** Devuelve la URL de un recurso dado en este paquete
	 * Si no existe, muestra el volcado del error en la salida de error estándar
	 * @param nomRec	Nombre del recurso a buscar
	 * @return	URL del recurso, o null si no existe
	 */
	public static java.net.URL getURLRecurso( String nomRec ) {
		java.net.URL recurso = null;
		try {
			recurso = Recurso.class.getResource( nomRec ).toURI().toURL();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recurso;
	}
}
