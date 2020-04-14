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
		int tmpX = 0;
		int tmpY = 0;
		Point tmpPXY;
		
		int tmpVX = 0;
		int tmpVY = 0;
		int tmpV = 0;
		
		double tmpSunDist=0;
		double sunDistX =0;
		double sunDistY=0;
		
		int znakVx =1;
		int znakVy =1;
		double sina =0; //wartości dla pierwszej ćwiartki;
		double cosa =0;
		
		
		while(czynny) {
			for (int i = 1; i<9; i++)
			{
				//zmiana położenia x i y planety
				tmpX=(int)planets[i].getXY().getX();
				tmpVX=planets[i].getVelX();
				tmpX+=tmpVX;
				
				tmpY=(int)planets[i].getXY().getY();
				tmpVY=planets[i].getVelY();
				tmpY+=tmpVY;
	
				//zmiana prędkości x i y planety
				
				tmpV=planets[i].getVel();
			
				tmpSunDist=planets[i].getSDist();
				sunDistX = tmpX -planets[0].getXY().getX();
				sunDistY = tmpY -planets[0].getXY().getY();
				

				
				
				sina = Math.abs(sunDistY)/tmpSunDist;
				cosa = Math.abs(sunDistX)/tmpSunDist;
				
				
				//jaki znak sinusa
				if(sunDistY>0)
					znakVx=-1;
				else
					znakVx=1;
				//jaki znak cosinusa
				if(sunDistX<0)
					znakVy=-1;
				else
					znakVy=1;
				
				
				if(sina>1) //zabazpieczenie by planety się zbyt daleko nie oddaliły
					sina=1;

				if(cosa>1)
					cosa=1;
				
				
				//nowe prędkości
				//dla xsów
				tmpVX =(int) (tmpV*sina*znakVx);
								
				//dla yków
				tmpVY =(int) (tmpV*cosa*znakVy);
						
				
				
				//nowe położenie
				tmpPXY = new Point(tmpX, tmpY);
		
				//set
				planets[i].setXY(tmpPXY);
				planets[i].setVelX(tmpVX);
				planets[i].setVelY(tmpVY);
				
				
				

			
				
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


