package es.deusto.prog3.testsVarios;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class PruebaRatonEnPantalla extends JFrame {
	JLabel imagen;
	JTextArea eventos = new JTextArea(5,10);
	Escuchador e = new Escuchador();
	
    public static void main(String[] args) {
        JFrame frame = new PruebaRatonEnPantalla();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * ScrollPaneDemo Constructor
     */
    public PruebaRatonEnPantalla() {
//        eventos.setMaximumSize(new Dimension(2000,100));
//        eventos.setPreferredSize(new Dimension(2000,100));
    	JPanel cont = new JPanel();
        cont.setLayout(new BorderLayout());
        ImageIcon crayons = new ImageIcon("d:/desarrollo/progiii/practicas/crayons.jpg");
        imagen = new JLabel(crayons);
        imagen.addMouseListener(e);
        imagen.addMouseMotionListener(e);
        JScrollPane sp = new JScrollPane();
        sp.getViewport().add( imagen );
        cont.add(sp, BorderLayout.CENTER);
        cont.add(new JScrollPane(eventos), BorderLayout.SOUTH );
        getContentPane().add(cont);
        JPanel glass = new JPanel();
        	glass.setLayout( new BorderLayout() );
			ImageIcon pru = new ImageIcon("d:/fotos/movil/Photo-0010.jpg");
			JLabel imagen2 = new JLabel(pru);
			imagen2.setBounds( 10, 10, 40, 40 );
	        // imagen2.addMouseListener(e);
	        // imagen2.addMouseMotionListener(e);
			// glass.add(imagen2, BorderLayout.CENTER);
        setGlassPane(imagen2);
        getGlassPane().setVisible(true);

//        add(new JScrollPane(imagen2), BorderLayout.SOUTH );
//        JScrollBar vsb = sp.getVerticalScrollBar();
//        JScrollBar hsb = sp.getHorizontalScrollBar();
//        vsb.setValue(crayons.getIconHeight());
//        hsb.setValue(crayons.getIconWidth()/10);
    }

    class Escuchador implements MouseListener, MouseMotionListener {

    	@Override
    	public void mouseClicked(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseClicked: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    		mostrar( "  " + arg0.getClickCount() );
    	}

    	@Override
    	public void mouseEntered(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseEntered: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    	}

    	@Override
    	public void mouseExited(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseExited: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    	}

    	@Override
    	public void mousePressed(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mousePressed: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    	}

    	@Override
    	public void mouseReleased(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseReleased: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    		mostrar( "  " + arg0.getClickCount() );
    	}

    	@Override
    	public void mouseDragged(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseDragged: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    	}

    	@Override
    	public void mouseMoved(MouseEvent arg0) {
    		// TODO Auto-generated method stub
    		mostrar( nombre(arg0.getSource()) + " mouseMoved: " + arg0.getX() + "," + arg0.getY() + " - onScreen " + arg0.getXOnScreen() + "," + arg0.getYOnScreen() );
    	}
    	
    	void mostrar( String st ) {
    		System.out.println( st );
    		eventos.append( st + "\n" );
    			int off = 0;
    			try {
	    			off = eventos.getLineStartOffset(eventos.getLineCount()-1);
    			} catch (BadLocationException e) {}
    			eventos.select( off, off );
    	}
    	
    	String nombre( Object c ) {
    		if (c == imagen) return "[Imagen]";
    		return "Desconocido";
    	}
    }


}

