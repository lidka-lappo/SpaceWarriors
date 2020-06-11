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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import java.awt.event.WindowAdapter;



public class GameInterface extends JFrame implements ActionListener{
	JFrame frame;
	
	JButton startButton;
	JButton endButton;

	public DataPanel dPanel;
	
	String title = "SPACE WARRIORS";
	static int width;
	static int height; 
	Planet planets[];
	public MainPanel mainPanel;
	static ExecutorService exec;
	
	String nam[];
	Color col[];
	int rr[];
	int m[];
	static String velocity;
	static String fuelStr;
	 
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
       
		//planets
		 int sunX = width/2;
		 int sunY = height/2;
		 int dist[] = new int[] {0, 70, 90, 110, 130, 160, 210, 240, 300};
		 double a[] = new double[] {0, 90, 10, 20, 30, 40, 50, 60, 70};
		 double aV[] = new double[] {0, 0.09, 0.07, 0.06, 0.05, 0.04, 0.03, 0.02, 0.01};
	if(MainMenu.star == "Sun") {
		 rr = new int[] {40, 8, 12, 12, 8, 40, 40, 24, 24};
	     col = new Color[] {Color.red, Color.gray, Color.orange, Color.blue, Color.red, Color.yellow, Color.orange, Color.blue, Color.lightGray};
	     m = new int[] {1000, 10, 20, 20, 10, 50, 45 ,25 ,30};
	     if(MainMenu.locale == null) {
	    	 MainMenu.locale = new Locale(MainMenu.language, MainMenu.country);
	    	 MainMenu.r = ResourceBundle.getBundle("Bundle", MainMenu.locale);
	     }
	     nam = new String[] {MainMenu.r.getString("sun"),MainMenu.r.getString("mer"),MainMenu.r.getString("ven"),MainMenu.r.getString("ear"),MainMenu.r.getString("mar"),MainMenu.r.getString("jup"),MainMenu.r.getString("sat"),MainMenu.r.getString("ura"),MainMenu.r.getString("nep")};
		 }
	else {
			 col = new Color[] {Color.blue.brighter(), Color.red.brighter(), Color.blue.darker(), Color.MAGENTA.darker(), Color.green.darker(), Color.CYAN, Color.MAGENTA, Color.black, Color.gray};
              rr = new int[] {50, 8, 12, 20, 8, 40, 40, 24, 24};
			  m = new int[] {1000, 10, 20, 20, 10, 50, 45 ,25 ,30};
			  nam = new String[] {"SYRIUS", "GJ 1214 b", "TRAPPIST-1e", "HAT-P-1 b", "KEPLER-22b ", "HD 189733b", "GJ 504b", "Tres-2b", "J1407 b"};
			 
		 }
	     Point xxyy[]= new Point[nam.length];
	  	 planets = new Planet[nam.length]; 
	  	 
	     for (int i = 0; i<nam.length; i++)
	     {
	    	xxyy[i]=new Point((int)(sunX+(Math.cos((a[i]))*dist[i])), (int)(sunY+(Math.sin((a[i]))*dist[i])));
	        planets[i] = new Planet(rr [i], xxyy[i], dist[i],  aV[i], a[i], col[i], m[i], nam[i]);
	       	planets[i].info();
	     }
		      
		 //mainPanel = new MainPanel(650, 650, planets);
		 mainPanel = new MainPanel(width, height, planets);
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
		endButton.setBounds(width-100, height-120, 100, 70);
		mainPanel.add(endButton);
		
		dPanel = new DataPanel();
	
		DataPanel.velocityLabel.setText("[0,0]");
		DataPanel.fuelLabel.setText("100%");
		
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
	        		MainPanel.czynny = false;
	           		frame.dispose();
	            break; 
	        }
	    }
	
	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run() {
						MainMenu.newGame = new GameInterface();
					}
			});		
		
	}
	
}