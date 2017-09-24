package es.deusto.prog3.testsVarios;

import java.awt.*;
import javax.swing.*;

public class EjemploJTabbedPane extends JFrame  
{
   public EjemploJTabbedPane()
   {
      super( "Ejemplo de JTabbedPane" );
      setSize( 692, 335 );
      
      // crear objeto JTabbedPane 
      JTabbedPane panelConFichas = new JTabbedPane();
      panelConFichas.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
      panelConFichas.setFont(new Font("Tahoma", Font.PLAIN, 15));
      
      // establecer pane11 y agregarlo al objeto JTabbedPane 
      JLabel etiqueta1 = new JLabel( "PANEL DATOS DEL CLIENTE", SwingConstants.CENTER );
      JPanel panel1 = new JPanel();
      panel1.setBackground( Color.BLUE);
      panel1.add(etiqueta1 ); 
      panelConFichas.addTab( "Datos del Cliente", null, panel1, "PANEL DATOS CLIENTE" ); 
      
      // establecer panel2 y agregarlo al objeto JTabbedPane
      JLabel etiqueta2 = new JLabel( "PANEL INGRESOS DEL CLIENTE", SwingConstants.CENTER );
      JPanel panel2 = new JPanel();
      panel2.setBackground( Color.YELLOW);
      panel2.add(etiqueta2 );
      panelConFichas.addTab( "Panel ingresos del cliente", null, panel2, "PANEL INGRESOS DEL CLIENTE" ); 

      // establecer panel3 y agregarlo al objeto JTabbedPane
      JLabel etiqueta3 = new JLabel( "PANEL ALBARANES DEL CLIENTE", SwingConstants.CENTER );
      JPanel panel3 = new JPanel();
      panel3.setBackground( Color.RED);
      panel3.add(etiqueta3 );
      panelConFichas.addTab( "Panel albaranes del cliente", null, panel3, "PANEL ALBARANES DEL CLIENTE" );
      
      // establecer panel4 y agregarlo al objeto JTabbedPane
      JLabel etiqueta4 = new JLabel( "PANEL TARIFAS DEL CLIENTE", SwingConstants.CENTER );
      JPanel panel4 = new JPanel();
      panel4.setBackground( Color.ORANGE);
      panel4.add(etiqueta4 );
      panelConFichas.addTab( "Panel tarifas del cliente", null, panel4, "PANEL TARIFAS DEL CLIENTE" );

      // agregar JTabbedPane al contenedor
      getContentPane().add( panelConFichas );

      /* Código para cambiar formato de tab */
      JLabel tab1 = new JLabel( "tab 2" );
      tab1.setBackground( Color.red );
      tab1.setOpaque( true );
      tab1.setMaximumSize( new Dimension( 50, 25 ));
      tab1.setMinimumSize( new Dimension( 50, 25 ));
      tab1.setPreferredSize( new Dimension( 50, 25 ));
      tab1.setHorizontalAlignment( JLabel.CENTER );
      panelConFichas.setTabComponentAt( 1, tab1 );
      
      setVisible( true );

   } // fin del constructor

   public static void main( String args[] )
   {
      EjemploJTabbedPane demoPanelConFichas = new EjemploJTabbedPane();
      demoPanelConFichas.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
   }

} 