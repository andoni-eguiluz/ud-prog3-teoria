package es.deusto.prog3.testsVarios;

/** Pruebas de cómo gestionar ficheros en Java, de forma directa
 * o a través de recursos (en cuyo caso se pueden incluir en los .jar)
 * Ejemplos con ficheros de gráficos, de audio y de vídeo 
 * @author eguiluz
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

// Clases para el vídeo
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

// Clases de audio
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



/** Clase que permite generar objetos de visualización de vídeos
 * (JFXPanel), usando JavaFX, para integrarlos en Swing.
 * Soporta reproducción .mp4 (no .flv ni .wmv)
 * @author eguiluz
 */
class ReproductorVideoJavaFX extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFXPanel miPanelJFX;
	private String urlDeVideo; 
	private int VID_WIDTH;
	private int VID_HEIGHT;
	
	/** Constructor del panel de reproducción
	 * @param width	Anchura inicial del panel de reproducción
	 * @param height	Altura inicial del panel de reproducción
	 * @param urlVideo	Path del vídeo a reproducir
	 */
	public ReproductorVideoJavaFX( int width, int height, String urlVideo ) {
		urlDeVideo = urlVideo;
		VID_WIDTH = width;
		VID_HEIGHT = height;
	    miPanelJFX = new JFXPanel();
		// Lanzar al hilo de JFX la creación del panel de vídeo
	    Platform.runLater(new Runnable() {  
	    	@Override public void run() {
	    		setLayout( new BorderLayout() );
	    	    setPreferredSize( new Dimension( VID_WIDTH, VID_HEIGHT ) );
	    		add( miPanelJFX, BorderLayout.CENTER );
	    		initFX();
		    }
	    });
	}
		private Scene playerScene;  // Escena de JavaFX
		private MediaView mediaView;  // mediaView de JavaFX
	    private MediaPlayer player;  // player de JavaFX
		private Media clip;  // Media clip de JavaFX
		
		private void initFX() {  // Inicialización de view JFX
			String s = "";
			try {
				s = es.deusto.prog3.testsVarios.recursos.Recurso.class.getResource( "PitchPerfect.mp4" ).toURI().toString(); // + urlDeVideo
				System.out.println( s );
			} catch (Exception e) {}
			clip = new Media( s );
			// clip = new Media( "file:/D:/desarrollo/ProgIII/TeoriaYEjemplos/src/recursos/alegria.mp4" ) );  // fichero
		    player = new MediaPlayer( clip );
		    mediaView = new MediaView( player );
		    mediaView.setFitWidth( VID_WIDTH );
		    mediaView.setFitHeight( VID_HEIGHT );
			playerScene = new Scene(
				crearPlayerLayout(), 
				VID_WIDTH, VID_HEIGHT );
			// Reproductor JFX
		    player = mediaView.getMediaPlayer();
		    System.out.println("Estado inicial: " + player.getStatus());
		    if (player.getError() != null) {
		    	System.out.println("Error inicial: " + player.getError());
		    }
			// Poner los gestores de error de medios JFX
		    player.statusProperty().addListener(new ChangeListener<MediaPlayer.Status>() {
		    	@Override
		    	public void changed(ObservableValue<? extends MediaPlayer.Status> observable, MediaPlayer.Status oldStatus, MediaPlayer.Status curStatus) {
		    		System.out.println("Cambio de estado: " + curStatus);
		    	}
		    });
		    player.setOnError(new Runnable() {
		    	@Override public void run() {
		    		System.out.println("Error: " + player.getError());
		    	}
		    });
		    miPanelJFX.setScene(playerScene);
		}

		private VBox crearPlayerLayout() {
			final VBox layout = new VBox(0);
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().add( mediaView );
			// layout.setStyle("-fx-background-color: linear-gradient(to bottom, derive(lightseagreen, -20%), lightseagreen);");
			layout.setStyle("-fx-background-color: BLACK;");
			return layout;
		}
		
	/**	Activa la reproducción o la pausa del vídeo
	 * (Si está en pausa, se reproduce, y si está en reproducción, se pausa)
	 */
	public void playPause() {
		if (player.getStatus() == MediaPlayer.Status.PLAYING)
		    Platform.runLater(new Runnable() {  
		    	@Override public void run() {
		    		player.pause();
		    }});
		else
		    Platform.runLater(new Runnable() {  
		    	@Override public void run() {
		    	    player.play();
		    }});
	}
	
	/** Reinicia el punto de reproducción al instante inicial del vídeo
	 */
	public void reset() {
	    Platform.runLater(new Runnable() {  
	    	@Override public void run() {
	    		player.seek( Duration.ZERO );
	    }});
	}
	
	@Override
	public void setSize( int anch, int alt ) {
		mediaView.setFitWidth(anch);
	    mediaView.setFitHeight(alt);
	}

}

/** Clase para reproducir sonidos
 * Funciona como un Thread, hay que construirlo,
 * y luego se lanza con .start()
 * OJO! Si se acaban el resto de threads y se ha creado
 * un objeto de esta clase, el thread pendiente no se acabará
 * hasta llegar al final del sonido, o indefinidamente
 * si se ha dejado pausado. (en ese caso utilizar el método kill()).
 * @author eguiluz
 */
class ReproductorAudio extends Thread {
	private String nombreFic;
	private Clip sonido;
	/**
	 * @param nombre del fichero de audio para poder reproducir el archivo 
	 */
	public ReproductorAudio(String audio){
		nombreFic = audio;
	}
	public void run() 
	{ 
		try {
			// Se obtiene un sonido
			sonido = AudioSystem.getClip();
			// Se carga con un fichero wav
			sonido.open(AudioSystem.getAudioInputStream( es.deusto.prog3.testsVarios.recursos.Recurso.getURLRecurso(nombreFic) ));
			// Comienza la reproducción
			sonido.loop(0);
			sonido.start();
			Thread.sleep(500); // Espera medio segundo al principio
			// Espera mientras se esté reproduciendo.
			while (sonido.isActive() || pausa){
				Thread.sleep(1000); // Cada segundo comprueba que se haya acabado
				if (interrupted()) {
					sonido.stop();
					break; // Finaliza el thread
				}
			}
			// Se cierra el flujo del sonido
			sonido.stop();
			sonido.close();
		}
		// Cuando se interrumpe al hilo en el sleep
		catch(InterruptedException e){ }
		//Si no encuentra el archivo a reproducir
		catch (Exception e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog( null, "Fichero no encontrado o no válido: " + nombreFic );
		}
	}
		private boolean pausa = false;
	/** Pausa o reactiva el sonido
	 */
	public void playPause() {
		if (sonido != null) {
			if (!pausa) {
				sonido.stop();
				pausa = true;
			} else {
				sonido.loop(0);
				sonido.start();
				pausa = false;
			}
		}
	}
	/** Acaba el sonido y el thread que lo tocaba
	 */
	public void kill() {
		pausa = false;
		sonido.stop();
	}
	
}

/** Ventana para probar los ficheros
 * de tres tipos: gráfico, audio y vídeo
 * @author eguiluz
 */
class VentanaFicheros extends JFrame {
	private static final long serialVersionUID = 1L;
	private ReproductorVideoJavaFX miVideo = null;
	private ReproductorAudio miAudio = null;
	private String miFicAudio = null;
	private JLabel miGrafico = null; // label vacío
	private JComponent viendose = null; // Lo que se está viendo ahora
	private JPanel pPrincipal = null; // Panel central
	public VentanaFicheros() {
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setSize( 660, 420 );
	    setLocationRelativeTo(null);
	    pPrincipal = new JPanel();
	    pPrincipal.setLayout( null ); // posicionamiento manual en el panel
	    JPanel botonera = new JPanel();
	    botonera.add( crearBotonVerGrafico() );
	    botonera.add( crearBotonVerVideo() );
	    botonera.add( crearBotonPausaVideo() );
	    botonera.add( crearBotonOirAudio() );
	    botonera.add( crearBotonPausaAudio() );
	    crearGestorRedimension();
	    add( pPrincipal, BorderLayout.CENTER );
	    add( botonera, BorderLayout.SOUTH );
	    setVisible(true);
	}
		private JButton crearBotonVerGrafico() {
			JButton b = new JButton( "Ver img" );
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					if (miGrafico != null && viendose != miGrafico) {
						miGrafico.setVisible(true);
						if (miVideo != null)
							miVideo.setVisible(false);
						viendose = miGrafico;
					}
				}
			});
			return b;
		}
		private JButton crearBotonVerVideo() {
			JButton b = new JButton( "Ver vídeo" );
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					if (miVideo != null && viendose != miVideo) {
						miVideo.setVisible(true);
						if (miGrafico != null)
							miGrafico.setVisible(false);
						viendose = miVideo;
					}
				}
			});
			return b;
		}
		private JButton crearBotonPausaVideo() {
			JButton b = new JButton( "Play/Pausa vídeo" );
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					if (miVideo != null) {
						miVideo.playPause();
					}
				}
			});
			return b;
		}
		private JButton crearBotonOirAudio() {
			JButton b = new JButton( "Iniciar audio" );
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					if (miAudio != null) {
						miAudio.kill();
					}
					miAudio = new ReproductorAudio( miFicAudio );
					miAudio.start();
				}
			});
			return b;
		}
		private JButton crearBotonPausaAudio() {
			JButton b = new JButton( "Pausa/repr. audio" );
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent arg0) {
					if (miAudio != null) {
						miAudio.playPause();
					}
				}
			});
			return b;
		}

		// Cambia el tamaño del vídeo si cambia el tamaño de la ventana
		private void crearGestorRedimension() {
		    addComponentListener( new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					if (miVideo != null) {
						Platform.runLater(new Runnable() {
							@Override public void run() {
								miVideo.setSize( pPrincipal.getWidth(), pPrincipal.getHeight() );
								miVideo.setBounds(0,0,pPrincipal.getWidth(), pPrincipal.getHeight());
								miVideo.updateUI();
							}
						} );
					}
					if (miGrafico != null) {
						miGrafico.setSize( pPrincipal.getWidth(), pPrincipal.getHeight() );
						miGrafico.setBounds(0,0,pPrincipal.getWidth(), pPrincipal.getHeight());
					}
				}
			} );
		    addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (miAudio != null) miAudio.kill();
				}
			});
		}

	/** Inicializa el vídeo a reproducir en la ventana
	 * añadiéndolo a la ventana, sin hacerlo visible 
	 * @param rv	Vídeo a reproducir
	 */
	public void setVideo(ReproductorVideoJavaFX rv) {
		if (miVideo != null) pPrincipal.remove(miVideo);  // si había otro vídeo lo quita
		miVideo = rv;  // Guarda el vídeo a reproducir
		miVideo.reset(); // Inicializa el vídeo
		pPrincipal.add(miVideo); // Lo añade a la ventana
		miVideo.setBounds(0,0,pPrincipal.getWidth(),pPrincipal.getHeight());
		miVideo.setVisible(false);
	}
	
	/** Inicializa el audio a reproducir en la ventana
	 * @param nA	Fichero de audio a reproducir
	 */
	public void setAudio( String nomFicAudio ) {
		miFicAudio = nomFicAudio;
	}
	
	/** Inicializa la imagen a mostrar en la ventana
	 * añadiéndola a la ventana, sin hacerla visible 
	 * @param nF	Fichero de imagen a mostrar
	 */
	public void setImagen( String nomFic ) {
		// ImageIcon ii = new ImageIcon( nomFic ); // fichero
		java.net.URL recurso = es.deusto.prog3.testsVarios.recursos.Recurso.getURLRecurso( nomFic );
		ImageIcon ii = new ImageIcon( recurso );  // recurso
		if (ii.getImageLoadStatus() == MediaTracker.ERRORED)
			throw new NullPointerException();
		if (miGrafico != null) pPrincipal.remove(miGrafico);
		miGrafico = new JLabel();
		miGrafico.setIcon( ii );
		pPrincipal.add(miGrafico);
		miGrafico.setBounds(0,0,pPrincipal.getWidth(),pPrincipal.getHeight());
		miGrafico.setVisible(false);
	}
}

public class GestionMultimediaJavaFX {
	public static void main(String[] args) {
		VentanaFicheros vf = new VentanaFicheros();
		vf.setImagen( "colores.jpg" ); // Recurso: ojo tiene que estar en el bin!!
		vf.setAudio( "musica.wav" );  // o tada.wav
		vf.setVideo( new ReproductorVideoJavaFX( 640, 360, 
				"alegria.mp4" ) );  // recurso
			//	"file:/D:/desarrollo/ProgIII/TeoriaYEjemplos/src/recursos/alegria.mp4" ) );  // fichero
	    	 // "http://static.clipcanvas.com/sample/clipcanvas_14348_H264_320x180.mp4";
	}

}