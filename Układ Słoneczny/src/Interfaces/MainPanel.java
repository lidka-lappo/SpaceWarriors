package Interfaces;
import java.awt.*;
import java.awt.geom.*;

//import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel;	
	Planet planets[];
	int wid;
	int heig;
	
	public MainPanel(int width, int height, Planet pl[]) {
		
		wid = width;
		heig = height;
		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, width, height);
		
		planets = pl.clone();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);//t³o xd
		g.fillRect(0, 0, wid, heig);
		Graphics2D g2d = (Graphics2D) g;
		Font f = new Font("Arial", 1, 15);
		// planetki
		for (int i = 0; i<planets.length; i++) {
			Ellipse2D circle = new Ellipse2D.Double(planets[i].getXY().getX(), planets[i].getXY().getY(), planets[i].getR(), planets[i].getR());//(xy[i].getX(), xy[i].getY(), r[i], r[i]);
			g.setColor(planets[i].getColor());
			g2d.fill(circle);
			
			if (planets[i].getName()=="SUN") {
				g.setColor(Color.yellow);
				g2d.setStroke(new BasicStroke(20));
				g2d.draw(circle);
			}
			if (planets[i].getName()=="SATURN") {
				g.setColor(Color.white);
				g2d.setStroke(new BasicStroke(10));
				g2d.drawLine((int)planets[i].getXY().getX(), (int)planets[i].getXY().getY()+planets[i].getR(), (int)planets[i].getXY().getX()+planets[i].getR(), (int)planets[i].getXY().getY());
			}
			g.setColor(Color.green);
			
			g.setFont(f);
			g.drawString(planets[i].getName(),  (int)planets[i].getXY().getX(), (int)planets[i].getXY().getY());
			
		}

	
	}
	
/*	 public static void main(String[] args) {
	        JFrame frame = new JFrame();
	        frame.setSize(640, 480);
	        Point p = new Point (200, 200);
	        Point pp = new Point (100, 100);
	        Point xxyy[]= new Point[]{p, pp};
	        int rr[] = new int[] {50, 10};
	        Color col[] = new Color[] {Color.orange, Color.red};
	        int m[] = new int[] {1000, 10};
	        String nam[] = new String[] {"SUN", "MARS"};
	        Planet pl[] = new Planet[nam.length];
	        for (int i = 0; i<nam.length; i++) {
	        	pl[i] = new Planet(rr[i], xxyy[i], col[i], m[i], nam[i]);
	        	pl[i].info();
	        }
	        MainPanel panel = new MainPanel(640, 480, pl);
	        frame.add(panel);
	        frame.setVisible(true);
	    }*/
	

}

