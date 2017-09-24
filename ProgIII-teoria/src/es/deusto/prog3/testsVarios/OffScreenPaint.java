package es.deusto.prog3.testsVarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {
  private BufferedImage offImg;

  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    // Draw the pie chart
    Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
    arc.setFrame(140, 200, 67, 46);
    arc.setAngleStart(45);
    arc.setAngleExtent(270);
    g2.setColor(Color.gray);
    g2.draw(arc);
    g2.setColor(Color.red);
    g2.fill(arc);
    g2.setColor(Color.black);
    g2.drawString("Arc2D.PIE", 140, 190);
  }

  public Graphics2D createDemoGraphics2D(Graphics g) {
    Graphics2D g2 = null;

    if (offImg == null || offImg.getWidth() != getSize().width
        || offImg.getHeight() != getSize().height) {
      offImg = (BufferedImage) createImage(getSize().width, getSize().height);
    }

    if (offImg != null) {
      g2 = offImg.createGraphics();
      g2.setBackground(getBackground());
    }

    // .. set attributes ..
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
    // .. clear canvas ..
    g2.clearRect(0, 0, getSize().width, getSize().height);

    return g2;
  }

  public void paint(Graphics g) {
    if (getSize().width <= 0 || getSize().height <= 0)
      return;

    Graphics2D g2 = createDemoGraphics2D(g);
    draw(g2);

    g2.dispose();

    System.out.println( offImg + "\n  g = " + offImg.getGraphics().hashCode() );
    
    if (offImg != null && isShowing()) {
      g.drawImage(offImg, 0, 0, this);
    }
  }

}

public class OffScreenPaint {
  public static void main(String[] a) {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setBounds(30, 30, 300, 300);
    window.getContentPane().add(new MyCanvas());
    window.setVisible(true);
  }
}