package es.deusto.prog3.testsVarios;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class UDDebug {
	static { init(); }
	private static VentanaUDDebug v;
	private static boolean running = false;
	private static int pulsacionesPlay = 0;
	private static String actualSource = "";
	private static void init() {
		v = new VentanaUDDebug();
		v.setTitle( "UD Debugging" );
		v.pack();
		v.setLocationRelativeTo(null);
		v.setVisible( true );
	}
	public static void close() {
		v.dispose();
	}
	private static void waitForPlay() {
		while (!running) {
			if (pulsacionesPlay > 0) {
				pulsacionesPlay--;
				break;
			} else {
				try {
					Thread.sleep( 100 );
				} catch (InterruptedException e) {
				}
			}
		}
	}
	private static void showTrace() {
		StackTraceElement[] sts = Thread.getAllStackTraces().get( Thread.currentThread() );
		v.taDebug.setText( "" );
		StackTraceElement llamada = null;
		StackTraceElement primeraLlamada = null;
		for (int i=2; i<sts.length; i++) {
			llamada = sts[i];
			if (!llamada.getClassName().equals("tests.UDDebug")) {
				if (primeraLlamada == null) primeraLlamada = llamada;
				// Info de debug
				v.taDebug.append( 
					String.format( "%" + (sts.length-i)*3 + "s", "") +
					llamada.getClassName() + "." +
					llamada.getMethodName() +
					" (" + llamada.getFileName() + 
					" - línea " + llamada.getLineNumber() + ")\n"
				);
			}
		}
	}
	private static void showSourceCode(int offsetCode) {
		// Selección de código
		StackTraceElement[] sts = Thread.getAllStackTraces().get( Thread.currentThread() );
		StackTraceElement primeraLlamada = null;
		for (int i=2; i<sts.length; i++) {
			StackTraceElement llamada = sts[i];
			if (!llamada.getClassName().equals("tests.UDDebug")) {
				primeraLlamada = llamada;
				break;
			}
		}
		if (!actualSource.equals( primeraLlamada.getClassName())) {
			v.taCodigo.setText( SourceCode.loadSource( primeraLlamada.getClassName() ) );
			actualSource = primeraLlamada.getClassName();
		}
		int lin = primeraLlamada.getLineNumber() - 1 + offsetCode;
		try {
			int off1 = v.taCodigo.getLineStartOffset( lin );
			int off2 = v.taCodigo.getLineEndOffset( lin );
			v.taCodigo.setSelectionStart(off1);
			v.taCodigo.setSelectionEnd(off2);
			v.taCodigo.requestFocus();
		} catch (Exception ex) {
			v.taCodigo.setSelectionStart(0);
			v.taCodigo.setSelectionEnd(0);
		}
	}
	public static void showCodeAfter() {
		// TODO: Saber cómo ver cuándo swing acaba para acabar UDDebug
		showTrace();
		showSourceCode( +1 );
		waitForPlay();
	}
	static class VentanaUDDebug extends JFrame {
		private JTextArea taCodigo = new JTextArea(30, 60);
		private JTextArea taDebug = new JTextArea(6, 60);
		private JPanel pBotones = new JPanel();
		private JButton bPlay = new JButton("play");
		private JButton bRunPause = new JButton("run");
		VentanaUDDebug() {
			JScrollPane spCodigo = new JScrollPane( taCodigo );
			taCodigo.setEditable( false );
			pBotones.add( bPlay );
			pBotones.add( bRunPause );
			bPlay.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pulsacionesPlay++;
				}
			});
			bRunPause.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					running = !running;
					if (bRunPause.getText().equals( "run" ))
						 bRunPause.setText( "pause" );
					else bRunPause.setText( "run" );
				}
			});
			getContentPane().add( spCodigo, "Center" );
			getContentPane().add( taDebug, "North" );
			getContentPane().add( pBotones, "South" );
			setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		}
	}
	static private class SourceCode {
		private static String classPath;
		private static HashMap<String,String> sourceCodes =
			new HashMap<String,String>();
		static { init(); }
		static void init() {
			Properties p = System.getProperties();
			classPath = p.getProperty( "java.class.path" );
			if (classPath.endsWith("\\bin"))
				classPath = classPath.substring(0,classPath.length()-4) + "\\src";
		}
		static String loadSource( String classFullName ) {
			if (sourceCodes.containsKey( classFullName )) {
				// Ya estaba cargado el fuente
				return sourceCodes.get( classFullName );
			} else {
				// No estaba cargado, se busca
				String className = classFullName.replace( ".", "\\" );
				String fullFileName = classPath + "/" + className + ".java";
				fullFileName = fullFileName.replace( "\\", "/" );
				String volcadoFic = "";
				try {
					// JEditorPane temp = new JEditorPane( "file:///" + fullFileName );
					// return temp.getText()
					FileInputStream fich = new FileInputStream( fullFileName );
					BufferedReader brFich = new BufferedReader( new java.io.InputStreamReader(fich) );
					String lin;
					while ((lin = brFich.readLine()) != null) {
						// Filtrado de marcas en línea
						if (lin.contains("UDDebug")) {
							if (lin.contains("import")) {
								lin = "";
							} else {
								lin = lin.substring( 0, lin.indexOf( "UDDebug" ));
							}
						}
						volcadoFic = volcadoFic + lin + "\n";
					}
					fich.close();
				} catch (Exception ex) {
				}
				sourceCodes.put( classFullName, volcadoFic );
				return volcadoFic;
			}
		}
	}
}
