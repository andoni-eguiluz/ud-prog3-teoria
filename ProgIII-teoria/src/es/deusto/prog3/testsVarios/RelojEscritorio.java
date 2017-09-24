package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sun.util.calendar.Gregorian;

public class RelojEscritorio extends JFrame {
	private static final long AVISO_NARANJA = 1000*60*5; // 5 minutos
	private static final long AVISO_ROJO = 1000*60*2; // 2 minutos
	private JTextField hora;
	private JTextField crono;
	private Thread miHilo;
	private long horaFin;
	RelojEscritorio( GregorianCalendar horaFin ) {
		this.horaFin = horaFin.getTimeInMillis();
		setTitle( "Reloj presentación" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		hora = new JTextField( "  :  :  " );
		hora.setText( formatoHora.format( new Date() ) );
		hora.setBackground( Color.BLACK );
		hora.setForeground( Color.WHITE );
		hora.setFont( new Font( "Arial", Font.BOLD, 60 ));
		add( hora, BorderLayout.CENTER );
		crono = new JTextField( "  :  :  " );
		crono.setText( dameTiempo(this.horaFin - System.currentTimeMillis()) );
		crono.setBackground( Color.BLACK );
		crono.setForeground( Color.WHITE );
		crono.setFont( new Font( "Arial", Font.BOLD, 40 ));
		JPanel panelInf = new JPanel();
		panelInf.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		panelInf.add( crono );
		add( panelInf, BorderLayout.SOUTH );
		setLocationRelativeTo( null );
		pack();
		miHilo = new Thread(miRunnable);
		miHilo.start();
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// miRunnable.corta(); // Otra manera de cortar
				miHilo.interrupt();
				dispose();
			}
		});
	}
	
	private SimpleDateFormat formatoHora = new SimpleDateFormat( "hh:mm:ss" ); 
	private MiRunnable miRunnable = new MiRunnable();
	private class MiRunnable implements Runnable {
		private boolean sigue = true; 
		public void run() {
			while (sigue) {
//				System.out.println( horaFin );
//				System.out.println( "  " + System.currentTimeMillis() );
//				System.out.println( "  " + (horaFin-System.currentTimeMillis()) );
				if (horaFin-System.currentTimeMillis() < AVISO_ROJO) {
					hora.setForeground( Color.RED );
					crono.setForeground( Color.RED );
				} else if (horaFin-System.currentTimeMillis() < AVISO_NARANJA) {
					hora.setForeground( Color.ORANGE );
					crono.setForeground( Color.ORANGE );
				}
				hora.setText( formatoHora.format( new Date() ) );
				crono.setText( dameTiempo(horaFin - System.currentTimeMillis()) );
				try { Thread.sleep( 1000 ); } catch (InterruptedException e) {
					sigue = false;
				}
				if (Thread.interrupted()) sigue = false;
			}
		};
		public void corta() {
			sigue = false;
		}
	};
	
	private String dameTiempo( long dif ) {
		long difp = Math.abs( dif );
		long mins = difp / 60000;
		long segs = (difp % 60000) / 1000;
		return (dif>0?"+":"-") + 
			String.format( "%1$02d", mins ) + ":" + String.format( "%1$02d", segs );
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar c = new GregorianCalendar( );
		c.set( GregorianCalendar.HOUR, 5 );
		c.set( GregorianCalendar.MINUTE, 15 );
		c.set( GregorianCalendar.SECOND, 00 );
		RelojEscritorio r = new RelojEscritorio( c );
		r.setVisible( true );
	}

}
