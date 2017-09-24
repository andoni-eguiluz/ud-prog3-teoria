package es.deusto.prog3.cap01;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class EjemploLambda1 {
	
	public static void test() { System.out.println( "Soy test de clase" ); }

	private void test2() { System.out.println( "Soy test de instancia" ); }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			
			// Java 7
//			new Runnable() {
//				@Override
//				public void run() {
//					System.out.println( "Bla bla bla" );
//				}
//			}
				
			// O bien
//				new Runnable() {
//					@Override
//					public void run() {
//						test();
//					}
//				}
					
				
			// Java 8
			// () -> { System.out.println( "Bla bla bla" );}
				
			// O tb m�todo est�tico
			// EjemploLambda1::test
				
			// O tb m�todo instancia sobre su objeto
			(new EjemploLambda1())::test2
			
			// Tiene que emparejar con Runnable porque es lo que espera invokeLater:
			//    void run () { }
			// O sea
			//    () sin par�metros -> { } c�digo -> sin retorno (void)
			//
			// Cualquier interfaz con UN SOLO m�todo vale para hacerlo as�.  (definido retorno, definidos pars)
			
		);
		
		JButton b = new JButton("");
		b.addActionListener( 
//				new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			}
//				}
			(e) -> {}
		);
	}

}
