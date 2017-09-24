package es.deusto.prog3.testsVarios;
// Necesita Java7 con JavaFX

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


// Nota: si no se heredara de Application, para llamar a JavaFX desde Swing, se podr�a simplemente hacer:
// import javafx.embed.swing.JFXPanel;  // S�lo para inicializar si no se heredara Application
// new JFXPanel(); // Inicializa JavaFX si no se usa application
// Eso s�, en ese caso al final est� el hilo de Java por un lado y el de JavaFX por otro, con lo que
// Platform.exit() no acaba la ejecuci�n del programa

public class ReproducirAudioJava7 extends Application {

	@Override
	public void start( Stage stage ) {
		String bip = "file:///" + System.getProperty("user.dir").replaceAll("\\\\", "/" )
				+ "/res/Luka.mp3";
		Media hit = new Media(bip);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setVolume( 1 );
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia( new Runnable() {
			@Override
			public void run() {
				Platform.exit();
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
