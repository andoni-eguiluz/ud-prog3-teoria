package es.deusto.prog3.testsVarios;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido extends Thread{
	private String nombre;
	private Clip sonido;
	/**
	 * @param nombrep de la serie para poder reproducir el archivo. 
	 */
	public Sonido(String audio){
		nombre=audio;
	}
	public void run() 
	{ 
		try {
			// Se obtiene un sonido
			sonido = AudioSystem.getClip();
			// Se carga con un fichero wav
			sonido.open(AudioSystem.getAudioInputStream(es.deusto.prog3.testsVarios.recursos.Recurso.class.getResource(nombre)));
			
			// Comienza la reproducción
			sonido.loop(0);
			Thread.sleep(1000);
			// Espera mientras se esté reproduciendo.
			int paso = 0;
			while (sonido.isRunning()){
				paso++; System.out.println( paso );
				Thread.sleep(1000);
				if (interrupted()) {
					sonido.stop();
					break;
				}
			}
			
			// Se cierra
			sonido.stop();
			sonido.close();
		}
		//Cuando interrumpe el sleep
		catch(InterruptedException e){
		}
		//Si no encuentra el archivo a reproducir
		catch (Exception e) {
			// e.printStackTrace();
			System.out.println( "File not found: " + nombre );
		}
	} 
	
	public static void main(String args[]){
		Sonido s = new Sonido("tada.wav");  // ejemplo.wav
		s.start();
	}
	
}
