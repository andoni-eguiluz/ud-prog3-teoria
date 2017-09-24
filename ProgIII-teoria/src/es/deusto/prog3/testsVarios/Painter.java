package es.deusto.prog3.testsVarios;

// http://www.sourcecodester.com/tutorials/java/5700/creating-simple-paint-program-java.html

import java.awt.*;
import java.awt.event.*;
// Java extension packages
import javax.swing.*;
public class Painter extends JFrame {
	private int x = -10, y = -10;
	// set up GUI and register mouse event handler

	public Painter()
	{


		super( "Simple Program" );
		// create a label and place it in SOUTH of BorderLayout
		getContentPane().add(
				new Label( "Drag the mouse to draw" ),
				BorderLayout.SOUTH );
		addMouseMotionListener(
				// anonymous inner class
				new MouseMotionAdapter() {
					// store drag coordinates and repaint
					public void mouseDragged( MouseEvent event )
					{
						x = event.getX();
						y = event.getY();
						repaint();
					}
				}
				// end anonymous inner class
				); // end call to addMouseMotionListener
		setSize( 400, 400 );
		setVisible( true );
	}
	// draw oval in a 4-by-4 bounding box at the specified
	// location on the window
	public void paint( Graphics g )
	{
		g.fillOval( x, y, 4, 4 );
	}
	// execute application
	public static void main( String args[] )
	{
		Painter application = new Painter();
		application.addWindowListener(
				// adapter to handle only windowClosing event
				new WindowAdapter() {
					public void windowClosing( WindowEvent event )
					{
						System.exit( 0 );
					}
				}
				// end anonymous inner class
				); // end call to addWindowListener
	}
}
// end class Painter

