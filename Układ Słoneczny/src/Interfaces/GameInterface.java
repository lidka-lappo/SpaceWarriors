package Interfaces;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;



public class GameInterface extends JFrame implements ActionListener/*, WindowListener*/{
	JFrame frame;
	
	JButton startButton;
	JButton endButton;

	static DataPanel dPanel;
	
	String title = "SPACE WARRIORS, odlotowa przejazdzka";
	static int width;
	static int height; 
	Planet planets[];
	public MainPanel mainPanel;
	static ExecutorService exec;
	
	private static final long serialVersionUID = 1L;
	public  GameInterface() throws HeadlessException {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.addWindowListener( new WindowAdapter()
		{
			public void windowClosed(WindowEvent e) {
				MainPanel.czynny = false;
			}
		});
		frame.setLayout(null);
		frame.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
		frame.setTitle(title);
		frame.getContentPane().setBackground(Color.black);
 
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();	
       
		//planetki, ewentualne TO DO opracować wczytywaie z pliku
		 int sunX = width/2;
		 int sunY = height/2;
		 
	//	 int dist[] = new int[] {0, 50, 60, 70, 80, 120, 160, 200, 240};
		 int dist[] = new int[] {0, 70, 90, 110, 130, 160, 210, 240, 300};
		 int rr[] = new int[] {40, 8, 12, 12, 8, 40, 40, 24, 24};
	     double a[] = new double[] {0, 90, 90, 90, 90, 90, 90, 90, 90};
	     double aV[] = new double[] {0, 0.09, 0.07, 0.06, 0.05, 0.04, 0.03, 0.02, 0.01};
	     Color col[] = new Color[] {Color.red, Color.gray, Color.orange, Color.blue, Color.red, Color.yellow, Color.orange, Color.blue, Color.lightGray};
	     int m[] = new int[] {1000, 10, 20, 20, 10, 50, 45 ,25 ,30};//trzeba jakos sprytnie przeliczyc, ¿eby nie walnac w slonce od razu 
	     String nam[] = new String[] {"SUN", "MERCURY", "VENUS", "EARTH", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE"};
	 	 
	     Point xxyy[]= new Point[nam.length];
	     planets = new Planet[nam.length];
	     for (int i = 0; i<nam.length; i++)
	     {
	    	xxyy[i]=new Point(sunX+dist[i], sunY);
	        planets[i] = new Planet(rr [i], xxyy[i], dist[i],  aV[i], a[i], col[i], m[i], nam[i]);
	       	planets[i].info();
	     }
		  
	     
		 mainPanel = new MainPanel(650, 650, planets);
		 mainPanel.setBackground(Color.black);
		 mainPanel.setLayout(null);
		 mainPanel.setBounds(0, 0, width, height);		
		
		//start		
		startButton = new JButton("START");
		startButton.setBackground(Color.green);	
		startButton.setFont(new Font("Arial", Font.BOLD, 30));
		startButton.addActionListener(this);
		startButton.setActionCommand("1");      
		startButton.setBounds((width-150)/2, 20, 150, 70);
		mainPanel.add(startButton);
		
		//end
		endButton = new JButton("end");
		endButton.setBackground(Color.red);	
		endButton.addActionListener(this);
		endButton.setActionCommand("2");
		endButton.setBounds(width-100, height-120, 70, 70);
		mainPanel.add(endButton);
		
		dPanel = new DataPanel();
		mainPanel.add(dPanel);
		
		frame.add(mainPanel);
		frame.setVisible(true);
		
		System.out.println(planets[2].getAngleVelocity());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 int choice = Integer.parseInt(arg0.getActionCommand());
	        switch (choice) {
	        	case 1:
	        		//start
	        		startButton.setVisible(false);
	        		SwingUtilities.invokeLater(new Runnable() {
	        			
	        			public void run() {		
	        									
	        				ExecutorService exec = Executors.newFixedThreadPool(1);
	        				exec.execute(mainPanel);
	        				exec.shutdown();				
	        			
	        			}
	        		});
	        		MainPanel.czynny = true;
	       
	            break;
	        	case 2:
	        		//end
	        		MainPanel.czynny = false;
	        		frame.dispose();
	            break; 
	        }

	    }
	
	public static void main(String[] args) throws InterruptedException {
		MainMenu.newGame = new GameInterface();
				/*
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {		
									
				exec = Executors.newFixedThreadPool(1);
				exec.execute(newGame.mainPanel);
				exec.shutdown();				
			
			}
		});*/
	}
	
}