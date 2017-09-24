package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Ejemplo de JPanel con fondo gráfico escalado
 * @author andoni
 */
public class JPanelConFondo extends JPanel {

	private BufferedImage imagenOriginal;
	public JPanelConFondo( String nombreImagenFondo ) {
        URL imgURL = getClass().getResource(nombreImagenFondo);
        try {
        	imagenOriginal = ImageIO.read( imgURL );
        } catch (IOException e) {
        }
	}

	protected void paintComponent(Graphics g) {
		Rectangle espacio = g.getClipBounds();  // espacio de dibujado del panel
		// setBounds( espacio );
		//super.paintComponent(g);  en vez de esto...
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Código para que el dibujado se reescale al área disponible
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Dibujado
		g2.drawImage(imagenOriginal, 0, 0, (int)espacio.getWidth(), (int)espacio.getHeight(), null);
	}
		
	/** Método de prueba del panel
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame prueba = new JFrame();
		prueba.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		prueba.setSize( 600, 500 );
		JPanel panelFondo = new JPanelConFondo( "bicho.png" );
		panelFondo.setLayout( new BorderLayout() );
		JButton botonPrueba = new JButton( "Botón de prueba encima de panel con imagen" );
		panelFondo.add( botonPrueba, "North" );
		prueba.getContentPane().add( panelFondo, "Center" );
		prueba.setVisible( true );
	}

}
