package Interfaces;
import java.awt.*;

import javax.swing.JPanel;


public class MainPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel;	
	Planet planets[];
	int wid;
	int heig;
	public boolean czynny =true;
	
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
		// planetki
		for (int i = 0; i<9; i++) {
			planets[i].paintPlanet(g);	
		}
		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.fillRect(560, 460, 10, 10);*/
	
	}
	

	
	public void run() {
		
		double tmpSunDist=0;
		
		double tmpA = 0;
		double tmpAV = 0;
		
		int tmpX = 0;
		int tmpY = 0;
		Point tmpPXY;

		while(czynny) {
			for (int i = 1; i<9; i++)
			{
				tmpSunDist=planets[i].getSDist();
				
				tmpA=planets[i].getAngle();
				tmpAV=planets[i].getAngleVelocity();
				tmpA+=tmpAV;
				if(tmpA>360)
					tmpA=0;
				planets[i].setAngle(tmpA);
				
				tmpX=(int)planets[i].getXY().getX();
				tmpY=(int)planets[i].getXY().getY();
				tmpX=(int)planets[0].getXY().getX()+(int) (tmpSunDist*Math.sin(tmpA));
				tmpY=(int)planets[0].getXY().getY()+(int) (tmpSunDist*Math.cos(tmpA));
	
				tmpPXY = new Point(tmpX, tmpY);
				planets[i].setXY(tmpPXY);
				
			}

			this.repaint();
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
}

