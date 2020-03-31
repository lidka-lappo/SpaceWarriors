package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JButton;

public class CircleButton extends JButton{

	Color fillColor;
	
	private static final long serialVersionUID = 1L;
	boolean mouseOver = false;
	boolean mousePressed = false;
	public CircleButton(String text, Color clr){
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		fillColor =clr;
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
	
	private int getDiameter(){
		int diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}

	public Dimension getPreferredSize(){
		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
		return new Dimension(minDiameter, minDiameter);
	}
	
	public boolean contains(int x, int y){
		int radius = getDiameter()/2;
		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
	}
	
public void paintComponent(Graphics g){
		
		int diameter = getDiameter();
		int radius = diameter/2;
		
		if(mousePressed){
			g.setColor(Color.LIGHT_GRAY);
		}
		else{
			g.setColor(fillColor);
		}
		g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
		
		if(mouseOver){
			g.setColor(Color.red);
		}
		else{
			g.setColor(Color.BLACK);
		}
		g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
		
		g.setColor(Color.BLACK);
		g.setFont(getFont());
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
	}
}

