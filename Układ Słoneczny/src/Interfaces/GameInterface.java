package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
	JPanel mainPanel;
	JPanel dataPanel;
	JLabel velocityLabel;
	JLabel fuelLabel;
	String title = "SPACE WARRIORS, odlotowa przejażdżka";
	int width;
	int height;

	
	private static final long serialVersionUID = 1L;
	public GameInterface() throws HeadlessException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     
		frame.setTitle(title);
		frame.getContentPane().setBackground(Color.black);
		
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, width, height);		
		//start		
		startButton = new JButton("START");
		startButton.setBackground(Color.green);	
		startButton.setFont(new Font("Serif", Font.BOLD, 30));
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

	public GameInterface(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public GameInterface(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public GameInterface(String title, GraphicsConfiguration gc) {
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
		new GameInterface();

	}*/
}