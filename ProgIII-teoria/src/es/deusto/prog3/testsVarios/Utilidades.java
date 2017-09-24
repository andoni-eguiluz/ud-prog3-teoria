package es.deusto.prog3.testsVarios;
import java.io.*;

public class Utilidades
{
	
	public static int leerEntero()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer i=new Integer(0);
		boolean b;
		do
		{
				try
				{
					String cad=br.readLine();
					i= new Integer(cad);
					b=false;
				}
				catch (Exception e)
				{
					b=true;
					if (e instanceof NumberFormatException)
						System.out.println("No tecleó un número entero-Repetir.");	
					else
						System.out.println("Error de entrada-Repetir.");
				}

		}	
		while (b);
		return i.intValue();
	}

	public static double leerReal()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Double d=new Double(0);
		boolean b;
		do
		{
				try
				{
					String cad=br.readLine();
					d= new Double(cad);
					b=false;
				}
				catch (Exception e)
				{
					b=true;
					if (e instanceof NumberFormatException)
						System.out.println("No tecleó un número real-Repetir.");	
					else
						System.out.println("Error de entrada-Repetir.");
				}

		}	
		while (b);
		return d.doubleValue();
	}

	public static char leerCaracter()
	{
		char c=0;
		boolean b;
		do
		{
				try
				{
					c=(char) System.in.read();
					System.in.skip(System.in.available());
					b=false;
				}
				catch (Exception e)
				{
					b=true;
					System.out.println("Error de entrada-Repetir.");	
				}
		}	
		while (b);
		return c;
	}

	public static String leerCadena()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String cad="";
		boolean b;
		do
		{
				try
				{
					cad=br.readLine();
					b=false;
				}
				catch (Exception e)
				{
					b=true;
					System.out.println("Error de entrada-Repetir.");
				}

		}	
		while (b);
		return cad;
	}

}
