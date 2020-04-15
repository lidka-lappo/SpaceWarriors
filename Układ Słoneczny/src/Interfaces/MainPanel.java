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
	Rocket rocket;
	Color rocketColor= Color.blue;
	public MainPanel(int width, int height, Planet pl[]) {
		
		wid = width;
		heig = height;
		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, width, height);
		
		planets = pl.clone();
		rocket = new Rocket(new Point(500,300),  rocketColor);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);//t³o xd
		g.fillRect(0, 0, wid, heig);
		rocket.paintRocket(g);
		
		// planetki
		for (int i = 0; i<9; i++) {
			planets[i].paintPlanet(g);	
		}

	
	}
	

	
	public void run() {

		double tmpSunDist=0;
		
		double tmpA = 0;
		double tmpAV = 0;
		
		int tmpX = 0; 
		int tmpY = 0;
		Point tmpPXY;
		
		double Dist =0;
		double DistX =0;
		double DistY =0;
		
		int tmpRX = 0; 
		int tmpRY = 0;
		
		double tmpVX = 0; 
		double tmpVY = 0;
		
		double tmpaX = 0; 
		double tmpaY = 0;

		double sina =0;
		double cosa =0;

		while(czynny) {
			tmpRX=(int)rocket.getApex().getX();
			tmpRY=(int)rocket.getApex().getY();
						
			tmpVX=rocket.getVx();
			tmpVY=rocket.getVy();
			
			DistX= planets[0].getXY().getX() - tmpRX;
			DistY= planets[0].getXY().getY() - tmpRY;
			Dist = (int) Math.pow(DistX*DistX+DistY*DistY, 0.5);
			sina = DistY/Dist;
			cosa = DistX/Dist;
			tmpaX = cosa*planets[0].getMass()/(Dist*Dist); 
		    tmpaY = sina*planets[0].getMass()/(Dist*Dist);

			
			
			for (int i = 1; i<9; i++)
			{
				//ruch planet
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
				
				//ruch rakiety	
				DistX= tmpX - tmpRX;
				DistY= tmpY - tmpRY;
				Dist = (int) Math.pow(DistX*DistX+DistY*DistY, 0.5);
				sina = DistY/Dist;
				cosa = DistX/Dist;
				
				
				
				tmpaX= tmpaX + cosa*planets[i].getMass()/(Dist*Dist);
				tmpaY= tmpaY + sina*planets[i].getMass()/(Dist*Dist);
				
				
			}
			//ruch rakiety
			tmpVX+=tmpaX;
			tmpVY+=tmpaY;
			if(tmpVX>10) //ograniczenie prędkości światła
				tmpVX=10;
			if(tmpVX<-10)
				tmpVX=-10;
			
			if(tmpVY>10)
				tmpVY=10;
			if(tmpVY<-10)
				tmpVY=-10;

			tmpRX+=tmpVX;
			tmpRY+=tmpVY;
			
			tmpPXY = new Point(tmpRX, tmpRY);
			rocket.setApex(tmpPXY);	
			
			rocket.setVx(tmpVX);	
			rocket.setVy(tmpVY);	
			
			this.repaint();
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
}

