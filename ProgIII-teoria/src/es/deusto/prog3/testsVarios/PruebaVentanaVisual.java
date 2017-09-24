package es.deusto.prog3.testsVarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Ventana creada con WindowBuilder
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class PruebaVentanaVisual extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JLabel mensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaVentanaVisual frame = new PruebaVentanaVisual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PruebaVentanaVisual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println( "Aceptar" );
			}
		});
		panelSur.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelSur.add(btnCancelar);
		
		tabla = new JTable();
		contentPane.add(tabla, BorderLayout.CENTER);
		
		mensaje = new JLabel();
		mensaje.setText("Prueba");
		contentPane.add( mensaje, BorderLayout.NORTH );
	}

}
