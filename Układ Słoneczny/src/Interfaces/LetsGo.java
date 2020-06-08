package Interfaces;

import static java.util.concurrent.TimeUnit.*;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LetsGo { 
	static animationPanel ap;
	static JFrame fr;
	static MainMenu mainMenu;
	
	public class animationPanel extends JPanel implements Runnable{
		private static final long serialVersionUID = 1L;
		BufferedImage images[] = new BufferedImage[6];
		int i = 0;

		animationPanel() {
			this.setSize(500, 500);
			for (int i = 0; i < 6; ++i)
				try {
					images[i] = ImageIO.read(new File("src/imagess/"+ (i+1) + ".jpg"));
				} catch (IOException e) {
				
					e.printStackTrace();
				}
		}
		
		public void paint(Graphics g){
			super.paintComponent(g);
	        g.drawImage(images[i], 0, 0, null);
	    }
		
		@Override
		public void run() {
			repaint();
			if (i<5) 
				  i++; 
			else 
				i=0;
		}	
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		ap = new LetsGo().new animationPanel();
		fr = new JFrame("SPACE WARRIORS, odlotowa przejażdżka");
	    fr.add(ap, BorderLayout.CENTER);
	    fr.setSize(500,500);	    
	    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    fr.setVisible(true);
	    final ScheduledExecutorService scheduler = 
			       Executors.newScheduledThreadPool(2);  
	    scheduler.schedule(new Runnable() {
            @Override
			public void run() { 
            	scheduler.shutdownNow();
            	mainMenu = new MainMenu();
            	//fr.dispose();	
            	fr.setVisible(false);//nwm kurcze jak zamknąć to okno, dispose() nie działa            	
            	}
        }, 3, SECONDS);
	    
	    scheduler.scheduleWithFixedDelay(ap, 0, 500, MILLISECONDS);
	    
	}
}
