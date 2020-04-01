package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInterface extends JFrame implements ActionListener{
	JFrame frame;
	JButton startButton;
	JButton endButton;
	MainPanel mainPanel;
	JPanel dataPanel;
	JLabel velocityLabel;
	JLabel fuelLabel;
	String title = "SPACE WARRIORS, odlotowa przeja¿d¿ka";
	int width;
	int height;
	Planet planets[];
	
	private static final long serialVersionUID = 1L;
	public  GameInterface() throws HeadlessException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
		frame.setTitle(title);
		frame.getContentPane().setBackground(Color.black);
		
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
		
		////tworzymy cia³a niebieskie
        Point xxyy[]= new Point[]{new Point (width/2, height/2), new Point(200, 200), new Point(250, 250), new Point(300, 300), new Point(350, 350), new Point(400, 400), new Point(450, 450), new Point(500, 500), new Point(550, 550)};
        int rr[] = new int[] {100, 10, 15, 15, 10, 50, 50, 30, 30};
        Color col[] = new Color[] {Color.red, Color.gray, Color.orange, Color.blue, Color.red, Color.yellow, Color.orange, Color.blue, Color.lightGray};
        int m[] = new int[] {1000, 10, 10, 10, 10, 10, 10 ,10 ,10};//trzeba jakos sprytnie przeliczyc, ¿eby nie walnac w slonce od razu 
        String nam[] = new String[] {"SUN", "MERCURY", "VENUS", "EARTH", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE"};
        planets = new Planet[nam.length];
        for (int i = 0; i<nam.length; i++) {
        	planets[i] = new Planet(rr [i], xxyy[i], col[i], m[i], nam[i]);
        	planets[i].info();
        }
	    
	    MainPanel mainPanel = new MainPanel(width, height, planets);
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
		//data
		dataPanel = new JPanel();
		dataPanel.setBackground(Color.white);		
		dataPanel.setBounds(0, height - height/3, width/4, height/3);
		dataPanel.setLayout(new GridLayout(3,1));		
		velocityLabel = new JLabel(" velocity: ");//+v
		dataPanel.add(velocityLabel);
		fuelLabel = new JLabel(" fuel supply: ");//+fuel
		dataPanel.add(fuelLabel);
		dataPanel.add(new JLabel("wykres paliwka"));
		
		mainPanel.add(dataPanel);
		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public  GameInterface(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public  GameInterface(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public  GameInterface(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 int choice = Integer.parseInt(arg0.getActionCommand());
	        switch (choice) {
	        	case 1:
	        		//start
	        		startButton.setVisible(false);
	            break;
	        	case 2:
	        		//end
	        		frame.setVisible(false);
	        		new MainMenu();
	            break; 
	        }

	    }
	
	
	/*public static void main(String[] args) {
		new  GameInterface();

	}*/
}