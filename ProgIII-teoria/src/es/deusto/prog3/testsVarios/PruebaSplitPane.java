package es.deusto.prog3.testsVarios;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.xml.internal.ws.api.server.Container;

public class PruebaSplitPane extends JFrame implements ListSelectionListener {
	public PruebaSplitPane() {
		//crea un splitpane con los nombres de las categorías a la izquierda y las imágenes a la derecha.
		JSplitPane s=new JSplitPane(); 
		//array de nombres de la categorias 
		String[]nombres={
			"andalucia.jpg", 
			"aragon.jpg", 
			"asturias.jpg", 
			"canarias.jpg", 
			"cantabria.jpg", 
			"castilla y leon.jpg", 
			"castilla la mancha.jpg", 
			"cataluña.jpg", 
			"ceuta.jpg", 
			"extremadura.jpg", 
			"galicia.jpg", 
			"islas baleares.jpg", 
			"la rioja.jpg", 
			"madrid.jpg", 
			"melilla.jpg", 
			"murcia.jpg", 
			"pais vasco.jpg", 
			"valencia.jpg"}; 
		//mete en el splitpane la lista de nombres a la izquierda 
		//NO SALE EL SCROLL 
		JList categorias= new JList(nombres); 
		categorias.addListSelectionListener(this); 
		JScrollPane scroll= new JScrollPane(categorias); 
		JPanel izquierda = new JPanel();
		izquierda.setLayout( new BorderLayout() );
		izquierda.add( scroll, "Center" );
		s.setLeftComponent(izquierda); 
		s.setPreferredSize(new Dimension(400, 200)); 
		//crea un JLabel con las imágenes con scroll 
		JLabel imag= new JLabel(); 
		ImageIcon andalucia= new ImageIcon ("Imagenes/andalucia.jpg"); 
		imag.setIcon(andalucia); 
		s.setRightComponent(imag); 
		s.setOneTouchExpandable(true); 
		//se añade al panel izquierdo el JSplitpane 
		getContentPane().add(s); 
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

		Calendar calendar = GregorianCalendar.getInstance();
	       int currentYear = calendar.get(Calendar.YEAR);
	        SpinnerModel yearModel = new SpinnerNumberModel(currentYear, //initial value
	                                       currentYear - 10, //min
	                                       currentYear + 10, //max
	                                       1);                //step
			JSpinner spinner = new JSpinner(yearModel);
			getContentPane().add(spinner,"South");		

			pack();
	
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
	}	
	public static void main( String[] s ) {
		JFrame v = new PruebaSplitPane();
		v.setVisible( true );
	}
}
