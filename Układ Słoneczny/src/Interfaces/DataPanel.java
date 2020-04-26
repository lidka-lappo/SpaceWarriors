package Interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {	

	JButton startButton;
	JButton endButton;
	static JLabel velocityLabel;
	static JLabel fuelLabel;
	int panelWidth;
	int panelHeight;
	static int fuelBarWidth;
	static int w;
	private static final long serialVersionUID = 1L;

	public DataPanel() {
		
		this.setBackground(Color.white);		
		this.setBounds(0, GameInterface.height - GameInterface.height/4, GameInterface.width/4, GameInterface.height/4);
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();
		w = (int)(0.8*panelWidth);
		fuelBarWidth = (int)(0.8*panelWidth);
		this.setLayout(new GridLayout(3,1));		
		velocityLabel = new JLabel(" velocity: ["+MainPanel.rocket.getVx()+", "+MainPanel.rocket.getVy()+"]");
		this.add(velocityLabel);
		fuelLabel = new JLabel(" fuel supply: "+MainPanel.fuel+"%");
		this.add(fuelLabel);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		g.setColor(Color.RED);
		g.fillRect(20, (int)(panelHeight*0.6), fuelBarWidth, 20);
		g.setColor(Color.BLACK);
		g.drawRect(20, (int)(panelHeight*0.6), w, 20);
	}


}
