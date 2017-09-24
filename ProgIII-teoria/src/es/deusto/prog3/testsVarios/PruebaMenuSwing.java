package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class PruebaMenuSwing extends JFrame implements ActionListener {
	private JTextArea texto = new JTextArea();
	public PruebaMenuSwing() {
		setTitle( "Ventana de prueba de menú" );
		setSize( 600, 400 );
		add( texto, BorderLayout.CENTER );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Mi menú");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
		  	menu.addMouseListener( new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					actionPerformed( new ActionEvent( this, 0, "SELECCIONADO PRINCIPAL" ));
				}
			});
		boolean soloMenu = false;
		if (!soloMenu) {
			JMenuItem menuItem = new JMenuItem(
			    "Opción 1", KeyEvent.VK_T);
			  	menuItem.setAccelerator( KeyStroke.getKeyStroke(
			    KeyEvent.VK_1, ActionEvent.ALT_MASK ));
			  	menuItem.setActionCommand( "OPC1" );
			  	menuItem.addActionListener(this);
			  	menu.add(menuItem);
			menuItem = new JMenuItem("Opción 2" );
			  	menuItem.setMnemonic(KeyEvent.VK_B);
			  	menuItem.setActionCommand( "OPC2" );
			  	menuItem.addActionListener(this);
			  	menu.add(menuItem);
			menu.addSeparator();
			ButtonGroup group = new ButtonGroup();
			  	JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(
			  		"Opción 3" );
			  		rbMenuItem.setSelected(true);
				  	rbMenuItem.setActionCommand( "OPC3" );
				  	rbMenuItem.addActionListener(this);
				    group.add(rbMenuItem);
				    menu.add(rbMenuItem);
			    rbMenuItem = new JRadioButtonMenuItem("Opción 4");
				  	rbMenuItem.setActionCommand( "OPC4" );
				  	rbMenuItem.addActionListener(this);
				    group.add(rbMenuItem);
				    menu.add(rbMenuItem);
		}
		setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
	    if (!e.getActionCommand().equals( "" )) {
	    	texto.append( "Pulsado " + e.getActionCommand() + "\n" );
	    }
	}
	  
	public static void main(String[] args) {
		PruebaMenuSwing v = new PruebaMenuSwing();
		v.setVisible( true );
	}
}
