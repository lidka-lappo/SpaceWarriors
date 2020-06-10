package Interfaces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JButton;

public class CircleButton extends JButton{

	Color fillColor;
	Color circuitColor;
	int diametr;
	private static final long serialVersionUID = 1L;
	boolean mouseOver = false;
	boolean mousePressed = false;
	public CircleButton(String text, Color clrf, int d){
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		diametr =d;
		fillColor =clrf;
		if(ifOpen)
			circuitColor = Color.green;
		else
			circuitColor = Color.red;
		
		MouseAdapter mouseListener = new MouseAdapter(){

			public void mousePressed(MouseEvent me){
				if(contains(me.getX(), me.getY())){
					mousePressed = true;
					repaint();
				}
			}
			
			public void mouseReleased(MouseEvent me){
				mousePressed = false;
				repaint();
			}

			public void mouseExited(MouseEvent me){
				mouseOver = false;
				mousePressed = false;
				repaint();
			}

			public void mouseMoved(MouseEvent me){
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}	
		};
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);		
	}
	
	void setOpen(){
		circuitColor = Color.green;
		ifOpen = true;
		repaint();
	}
	
	void setClose(){
		circuitColor = Color.red;
		ifOpen = false;
		repaint();
	}
	
	private int getDiametr(){
		return diametr;
	}

	public Dimension getPreferredSize(){
		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), diametr);
		return new Dimension(minDiameter, minDiameter);
	}
	
	public boolean contains(int x, int y){
		int radius = getDiametr()/2;
		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
	}
	
public void paintComponent(Graphics g){
		
		int diameter = getDiametr();
		int radius = diameter/2;
		BasicStroke bs1 = new BasicStroke(2);
		
		
		if(mousePressed){
			g.setColor(fillColor.darker());
			bs1 = new BasicStroke(5);
			}
		else
			g.setColor(fillColor);

		
		
		
		
		
		
	
		g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
		g.setColor(circuitColor);
		
		((Graphics2D) g).setStroke(bs1);
		
		g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
		g.setColor(Color.white);
		g.setFont(getFont());
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4 - diametr/2-5);
	}
}



