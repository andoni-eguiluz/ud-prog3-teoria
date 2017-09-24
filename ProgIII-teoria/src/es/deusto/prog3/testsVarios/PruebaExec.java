package es.deusto.prog3.testsVarios;

import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;


public class PruebaExec implements ActionListener {
	private static Process procesoInicial = null;
	public static void main( String[] s ) {
		JFrame v = new JFrame();
		v.getContentPane().add( new JLabel( "Prueba"), "Center" );
		JPanel botonera = new JPanel();
		JButton b1 = new JButton( "Video 1" );
		JButton b2 = new JButton( "Video 2" );
		JButton b3 = new JButton( "Video 3" );
		b1.setActionCommand( "D:/WorkSpace/Worten-2010/Trailers Worten/Juegos/RDR2_LITW2_MULTI_SPA_720_.mp4" );
		b2.setActionCommand( "D:/Videos/AOrdenar/Malu-Duele.flv" );
		b3.setActionCommand( "D:/WorkSpace/Worten-2010/Trailers Worten/Documentales/1 agua.mov" );
		botonera.add( b1 ); botonera.add( b2 ); botonera.add( b3 );
		PruebaExec escuchador = new PruebaExec();
		b1.addActionListener( escuchador ); b2.addActionListener( escuchador ); b3.addActionListener( escuchador );
		v.getContentPane().add( botonera, "South" );
		v.pack();
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		v.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println( "Destroying..." );
				if (procesoInicial!=null) procesoInicial.destroy();
//				if (procesoAnt!=null) procesoAnt.destroy();
//				if (proceso!=null) proceso.destroy();
			}
		});
		v.setVisible(true);
		/* 
		try {
			String comando = "C:/Program Files (x86)/Media Player Classic - Home Cinema/mpc-hc.exe " +
				'"' + "f:/Trailers Worten/Juegos/RDR2_LITW2_MULTI_SPA_720_.mp4" + '"' + " /play /fullscreen /monitor 2";
			Process ls_proc = Runtime.getRuntime().exec(comando);
			Thread.sleep( 10000 );
			comando = "C:/Program Files (x86)/Media Player Classic - Home Cinema/mpc-hc.exe " +
				"D:/Videos/AOrdenar/Malu-Duele.flv /play /close /fullscreen /monitor 2";
			// ls_proc.destroy();
			Process ls_proc2 = Runtime.getRuntime().exec(comando);
			Thread.sleep( 10000 );
			comando = "C:/Program Files (x86)/Media Player Classic - Home Cinema/mpc-hc.exe " +
				'"' + "f:/Trailers Worten/Documentales/1 agua.mov" + '"' + " /play /fullscreen /monitor 2";
			// ls_proc2.destroy();
			Process ls_proc3 = Runtime.getRuntime().exec(comando);
			Thread.sleep( 10000 );
			ls_proc3.destroy();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		*/
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println( "Lanzando video: " + arg0.getActionCommand() );
		String comando = "C:/Program Files (x86)/Media Player Classic - Home Cinema/mpc-hc.exe " +
			'"' + arg0.getActionCommand() + '"' + " /play /fullscreen /monitor 2";
		//   /new si se quiere lanzar un nuevo player cada vez
		try {
			Process proceso = Runtime.getRuntime().exec(comando);
			if (procesoInicial == null) procesoInicial = proceso;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
