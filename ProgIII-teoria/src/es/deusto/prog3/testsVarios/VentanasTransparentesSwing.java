package es.deusto.prog3.testsVarios;

import java.awt.*;
import javax.swing.*;

public class VentanasTransparentesSwing {
    public static void main(String[] args) {
        // 1. Código para ver si el SO soporta transparencia:
        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            System.err.println( "Transparencia no soportada" );
            System.exit(0);
        }
        // Código para comprobar transparencia de forma o de pixel
        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT)) {
            System.err.println("Ventanas con forma no soportadas");
            System.exit(0);
        }
        
        // 2. Activar el L&F por defecto decorado para permitir transparencia
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // 3. Activar ventanas
    	// Activar ventana con forma
    	VentanaConForma vCF = new VentanaConForma();
    	vCF.setVisible( true );
    	// Activar ventana con transparencias alfa
    	VentanaConTransparenciasAlfa vCTA = new VentanaConTransparenciasAlfa();
    	vCTA.setVisible( true );
        // Activar ventana transparente
        VentanaTransparente v = new VentanaTransparente();
        v.setVisible(true);
        
        // 4. Ver cómo cambia la transparencia de la última ventana
        // - irla ocultando y mostrando alternativamente
        float cambioTransp = -0.02f; // primero resta y luego sumará
        while (true) {
        	if (v.getOpacity()<-cambioTransp) {
        		cambioTransp = +0.02f;
        	} else if (v.getOpacity()>1.0f-cambioTransp) {
        		cambioTransp = -0.02f;
        	}
        	v.setOpacity( v.getOpacity() + cambioTransp );
        	try {
				Thread.sleep(50);
			} catch (InterruptedException e) { }
        }
    }
}

class VentanaTransparente extends JFrame {
	private static final long serialVersionUID = 1L;
	public VentanaTransparente() {
        super("Ventana transparente");
        setLayout(new GridBagLayout());
        setSize(500,200);
        setLocationRelativeTo(null);
        setLocation( 50, 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Ventana transparentándose más y menos..."));
    }
 
}

class VentanaConForma extends JFrame {
	private static final long serialVersionUID = 1L;
	public VentanaConForma() {
        super("Ventana con forma");
        setLayout(new GridBagLayout());
        setSize(500,200);
        setLocationRelativeTo(null);
        setLocation( 50, 250 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Ventana con forma..."));
        // Poner la forma a la ventana (ejemplo: elipse)
        setShape(new java.awt.geom.Ellipse2D.Double(-25,-25,getWidth()+50,getHeight()+50));
        // Ojo: la forma es la misma aunque la ventana se redimensione
        // Si quisiéramos que cambiara, habría que escuchar en la ventana el evento
        //   ComponentListener - método componentResized
    }
}

class VentanaConTransparenciasAlfa extends JFrame {
    public VentanaConTransparenciasAlfa() {
        super("Ventana con transparencia alfa (gradiente)");
        setBackground(new Color(0,0,0,0));
        setSize(new Dimension(500,200));
        setLocationRelativeTo(null);
        setLocation( 50, 450 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 0;
                    final int G = 200;  // fondo verde
                    final int B = 0;
                    Paint p =
                        new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                            0.0f, getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        setContentPane(panel);
        setLayout(new GridBagLayout());
        add(new JLabel("Ventana con transparencia gradiente..."));
    }
}
 