package es.deusto.prog3.testsVarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PruebaJCombos extends JFrame implements ActionListener {
	private JComboBox curso;
	private JComboBox asignatura;
	
	PruebaJCombos(){
		// Creaci�n de componentes relativos a atributos
			asignatura = new JComboBox();
		// Aspecto general de la ventana
		this.setSize(350,350);
		// Panel principal e integraci�n de componentes en el panel principal
			Container panelContenidos = (JPanel) getContentPane();
			getContentPane().setLayout( new BoxLayout(panelContenidos,BoxLayout.Y_AXIS) );
			String[] cursos = { "1", "2", "3" };
			curso = new JComboBox(cursos);
			getContentPane().add( curso );
			getContentPane().add( asignatura );
			asignatura.addActionListener(this);
			curso.addActionListener(this);
		}
		public void rellenarAsignaturas(String[]Array){
			asignatura.removeAllItems();
			for(int i=0;i<Array.length;i++)
				asignatura.addItem(Array[i]);
		}
		public void actionPerformed (ActionEvent e){
			Object elemento=e.getSource();
			if(elemento instanceof JComboBox)
			{
				if(elemento==curso){
					if(curso.getSelectedIndex()+1==1)
					{
						String[] Asignaturas = { "Programaci�n I", "Matem�tica discreta", "Algebra" };
						rellenarAsignaturas(Asignaturas);
					}
					else if(curso.getSelectedIndex()+1==2)
					{
						String[] Asignaturas = { "Estad�stica", "Calculabilidad", "Programaci�n II" };
						rellenarAsignaturas(Asignaturas);
					}
					else if(curso.getSelectedIndex()+1==3)
					{
						String[] Asignaturas = { "Programaci�n II", "Matem�tica discreta II", "AlgebraIIIII" };
						rellenarAsignaturas(Asignaturas);
					}
				}
			}
		}
			
			
			public static void main (String[]args){
				PruebaJCombos k = new PruebaJCombos();
				k.setVisible(true);
			}
	

}
