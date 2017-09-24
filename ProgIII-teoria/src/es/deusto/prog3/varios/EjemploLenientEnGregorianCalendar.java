package es.deusto.prog3.varios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EjemploLenientEnGregorianCalendar {

	public static void main(String[] args) {
		DateFormat df = DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG  );
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis( System.currentTimeMillis() );
        int dia = 32;  // Ejemplo de día incorrecto
        int mes = 1;
        int anyo = 2017;
        gc.set( anyo, mes, dia );
        gc.setLenient( true );   // Lenient es lo que define que se interprete 32 de febrero como correcto o como erróneo
        System.out.println( df.format( gc.getTime() ) );  // Erróneo pero se reinterpreta (32 de febrero = 4 de marzo)   (lenient = true)
        gc.setLenient( false );
        gc.set( anyo, mes, dia );
        try {
        	System.out.println( gc.getTimeInMillis() );
        	System.out.println( df.format( gc.getTime() ) );
        } catch (IllegalArgumentException e) {
        	System.out.println( "Error en interpretación de fecha " + dia + "/" + mes + "/" + anyo );
        }
        dia = 29;
        mes = 1; // febrero no bisiesto
        gc.set( anyo, mes, dia );
        try {
        	System.out.println( gc.getTimeInMillis() );
        	System.out.println( df.format( gc.getTime() ) );
        } catch (IllegalArgumentException e) {
        	System.out.println( "Error en interpretación de fecha " + dia + "/" + mes + "/" + anyo );
        }
        anyo = 2016; // febrero sí bisiesto
        gc.set( anyo, mes, dia );
        try {
        	System.out.println( gc.getTimeInMillis() );
        	System.out.println( df.format( gc.getTime() ) );
        } catch (IllegalArgumentException e) {
        	System.out.println( "Error en interpretación de fecha " + dia + "/" + mes + "/" + anyo );
        }
	}

}
