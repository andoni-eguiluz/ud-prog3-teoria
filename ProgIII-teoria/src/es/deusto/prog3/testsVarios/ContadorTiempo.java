package es.deusto.prog3.testsVarios;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ContadorTiempo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame miVentana = new JFrame();
		JLabel miLabel = new JLabel(" XX ");
		miLabel.setForeground( Color.blue );
		miLabel.setBackground( Color.black );
		miLabel.setOpaque( true );
		miLabel.setFont( new Font( "Arial", Font.BOLD, 20));
		miVentana.add( miLabel );
		miVentana.pack();
		miVentana.setVisible( true );
		miVentana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		MiContador cont = new MiContador( 10000, miLabel );
		cont.start();
	}

}

class MiContador extends Thread {
	private long miliSegsQueFaltan;
	private JLabel dondeLoVeo;
	private long momentoInicial;
	public MiContador( long pMiliSegsQueFaltan, JLabel pDondeLoVeo ) {
		miliSegsQueFaltan = pMiliSegsQueFaltan;
		dondeLoVeo = pDondeLoVeo;
		momentoInicial = System.currentTimeMillis();
	}
	public void run() {
		while (momentoInicial + miliSegsQueFaltan >= System.currentTimeMillis()) {
			long tiempoQueFalta = momentoInicial + miliSegsQueFaltan - System.currentTimeMillis();
			dondeLoVeo.setText( ""+ (int) (tiempoQueFalta/1000));
			dondeLoVeo.updateUI();
//			System.out.println( "A");
//			try {
//				Thread.sleep( 1000 );
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			if (interrupted()) break;
		}
	}
}
