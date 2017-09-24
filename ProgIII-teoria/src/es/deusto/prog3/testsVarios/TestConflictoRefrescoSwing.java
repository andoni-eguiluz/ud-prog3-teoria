package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;


/** Test en el que se ve el problema de que Swing no sea sincronizado
 * con una JTextArea
 * @author andoni
 */
public class TestConflictoRefrescoSwing extends JFrame {
	private static final long serialVersionUID = -1096539967843537835L;
	private static JTextArea ta;

	public TestConflictoRefrescoSwing() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 600, 400 );
		if (tipoAnyadir != 3)
			ta = new JTextArea();
		else
			ta = new TextAreaSegura();
		ta.setEditable( false );
		getContentPane().add( new JScrollPane(ta), BorderLayout.CENTER ); 
	}
	
	/** Añade texto en la primera línea de una textarea y para que no se llene
	 * se ocupa de quitar las 10 últimas líneas si tiene más de 20
	 * Lo hace invocando al propio hilo de Swing
	 * @param ta	Area de texto a la que añadir
	 * @param mens	Mensaje que añadir
	 */
	private static void addMensajeConInvoke( JTextArea ta, String mens ) {
		mensHilo = mens;
		miTA = ta;
		SwingUtilities.invokeLater( rMens );
	}
		private static String mensHilo;
		private static JTextArea miTA;
		private static Runnable rMens = new Runnable() {
			@Override
			public void run() {
				miTA.insert( mensHilo+"\n", 0 );
				if (miTA.getLineCount() > 20) {
					try {
						int offset = miTA.getLineEndOffset( 9 );
						miTA.replaceRange( "", offset, miTA.getText().length() );
					} catch (BadLocationException e) {
					}
				}
				miTA.select(0,2);
			}
		};

		/** Añade texto en la primera línea de una textarea y para que no se llene
		 * se ocupa de quitar las 10 últimas líneas si tiene más de 20
		 * Lo hace sin invocar al hilo - NO SEGURO!!!!
		 * @param miTA	Area de texto a la que añadir
		 * @param mens	Mensaje que añadir
		 */
		private static void addMensaje( JTextArea miTA, String mens ) {
			if (verLlamadas && tipoAnyadir==1)
				System.out.println( "--> insert(" + mens.substring(mens.length()-15,mens.length()) + ")" );
			miTA.insert( mens + "\n", 0 );
			if (verLlamadas && tipoAnyadir==1)
				System.out.println( "<-- insert(" + mens.substring(mens.length()-15,mens.length()) + ")" );
			if (miTA.getLineCount() > 1000) {
				try {
					int offset = miTA.getLineEndOffset( 500 );
					if (verLlamadas)
						System.out.println( "--> replaceRange()" );
					miTA.replaceRange( "", offset, miTA.getText().length() );
					if (verLlamadas)
						System.out.println( "<-- replaceRange()" );
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			miTA.setCaretPosition(0);
			 // Lleva la visual al inicio al seleccionar el texto
			// miTA.select(0,0); // dejando la selección es inseguro
		}
		
		private static void addMensaje( int tipoAnyadir, JTextArea miTA, String mens ) {
			switch (tipoAnyadir) {
				case 1: addMensaje( miTA, mens ); break;
				case 2: addMensajeConInvoke( miTA, mens ); break;
				case 3: addMensaje( miTA, mens ); break;
			}
		}
		
		private static void llenaLaVentanaDeMensajes( JTextArea ta ) {
			recorreDisco(new File(inicioDisco), ta, 1 );
		}

		private static void recorreDisco( File fuente, JTextArea ta, int nivel ) {
        	if (fuente.isDirectory()) {
	        	// System.out.println( fuente.getAbsolutePath() + "\\" + fuente.getName() );
	        	// numDirs++;
	        	String cont[] = fuente.list();
	        	if (cont != null) {
	        		if (cuantosMens==1 && nivel <= 2) // Pocos mensajes por segundo
	        			addMensaje( tipoAnyadir, ta, "Procesando carpeta de nivel superior: " + fuente.getAbsolutePath() + "..." );
		        	for (String fic : cont) {
		        		File f = new File( fuente.getAbsolutePath() + "\\" + fic );
		        		if (cuantosMens==2) // Muchos mensajes por segundo
		        			if (new Random().nextBoolean()) addMensaje( tipoAnyadir, ta, "Procesando carpeta: " + f.getAbsolutePath() + "..." );
		        		recorreDisco( f, ta, nivel+1 );
		        	}
	        	}
	        } else {
	        	// numFics++;
				if (cuantosMens==3) // Muchísimos mensajes por segundo 
					addMensaje( ta, "   " + fuente.getAbsolutePath() );
	        }
		}		

	private static String inicioDisco = "C:\\Program Files (x86)\\";
	
	public static boolean verLlamadas = true;
	public static int tipoAnyadir;
	private static int cuantosMens;
	public static void main( String[] s ) {
		tipoAnyadir = 3;
		// 1 - Añadir normal
		// 2 - Añadir con invoke
		// 3 - Añadir normal con métodos sincronizados
		cuantosMens = 3;
		// 1 - pocos
		// 2 - muchos
		// 3 - miles y miles por segundo
		
		// ==========================================
		// ==========================================
		// NAAA Acaba funcionando más o menos igual. No vale este ejemplo
		// ==========================================
		// ==========================================
		// Pruebas sugeridas:
		// a) 1 + 1 - Todo va bien (o casi)
		// b) 1 + 2 - Empieza a ir mal (se suele colgar)
		// c) 2 + 2 - Suele ir bien pero problemas de refresco
		// d) 2 + 3 - Gran problema de refresco e incluso cuelgue
		//            (Demasiado trabajo para Swing)
		//            (Se generan más rápido mensajes que lo que
		//            se puede procesar la cola de procesos pendientes de Swing)
		TestConflictoRefrescoSwing v = new TestConflictoRefrescoSwing();
		v.setVisible( true );
		llenaLaVentanaDeMensajes( ta );
		v.dispose();
	}
}


	class TextAreaSegura extends JTextArea {
		private static final long serialVersionUID = -5324599821777292397L;
		private static int nivel = 1;  // Sólo para ver las llamadas
		@Override
		synchronized protected void paintComponent(Graphics g) {
			if (TestConflictoRefrescoSwing.verLlamadas) {
				System.out.println( String.format( "%"+nivel+"s", " ") + "--> paintComponent(g)");
				nivel++;
			}
			super.paintComponent(g);
			if (TestConflictoRefrescoSwing.verLlamadas) {
				nivel--;
				System.out.println( String.format( "%"+nivel+"s", " ") + "--> paintComponent(g)");
			}
		}
		@Override
		synchronized public void replaceRange(String str, int start, int end) {
			super.replaceRange(str, start, end);
		}
		@Override
		synchronized public void insert(String str, int pos) {
			if (TestConflictoRefrescoSwing.verLlamadas && TestConflictoRefrescoSwing.tipoAnyadir==3) {
				System.out.println( "--> insert(" + str.substring(str.length()-15,str.length()-1) + ")");
			}
			super.insert(str, pos);
			if (TestConflictoRefrescoSwing.verLlamadas && TestConflictoRefrescoSwing.tipoAnyadir==3) {
				System.out.println( "<-- insert(" + str.substring(str.length()-15,str.length()-1) + ")");
			}
		}
		@Override
		synchronized public void setCaretPosition(int selectionStart) {
			super.setCaretPosition(selectionStart);
		}
		@Override
		synchronized public int getLineEndOffset(int line) throws BadLocationException {
			return super.getLineEndOffset(line);
		}
		@Override
		synchronized public String getText() {
			return super.getText();
		}
	}