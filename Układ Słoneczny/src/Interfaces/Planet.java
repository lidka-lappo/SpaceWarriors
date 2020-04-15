package Interfaces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Planet { //no i słoneczko:)
	Font f = new Font("Arial", 1, 15);
	int radius;
	Point center;
	Color color;
	int mass;
	String name;
	int sunDistance;
	double angleVelocity;
	double angle;
	public Planet(int rr, Point xxyy, int dist, double aV, double a, Color col, int m, String nam) {
		radius = rr;
		center = xxyy;
		color = col;
		mass = m;
		name = nam;
		angleVelocity =aV;
		angle= a;
		sunDistance = dist;
	}
	
	void info ()
	{
		System.out.println(name);
		System.out.println("r = "+ radius + ", color" + color + ", mass = " + mass +", location: "+center.getX() + ","+center.getY());
	}
	int getR ()
	{
		return radius;
	}
	Point getXY ()
	{
		return center;
	}
	
	void setXY (Point xy)
	{
		this.center =xy;
	}
	
	Color getColor ()
	{
		return color;
	}
	int getMass ()
	{
		return mass;
	}
	String getName ()
	{
		return name;
	}
	
	double getAngle ()
	{
		return angle;
	}
	
	void setAngle (double a)
	{
		this.angle =a;
	}
	
	double getAngleVelocity ()
	{
		return angleVelocity;
	}
	
	double getSDist ()
	{
		return sunDistance;
	}
	
	
	void setSDist (int SD)
	{
		this.sunDistance=SD;
	}
	
	
	void paintPlanet(Graphics g) {
		 
		 Graphics2D g2d = (Graphics2D) g;
		 Ellipse2D circle = new Ellipse2D.Double(getXY().getX()-getR(), getXY().getY()-getR(), 2*getR(), 2*getR());
		 g.setColor(getColor());
		 g2d.fill(circle);
		 if (getName()=="SUN") {
				g.setColor(Color.yellow);
				g2d.setStroke(new BasicStroke(20));
				g2d.draw(circle);
			}
			if (getName()=="SATURN") {
				g.setColor(Color.white);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawLine((int)getXY().getX(), (int)getXY().getY()-2*getR(), (int)getXY().getX(), (int)getXY().getY()+2*getR());
			}
			
			g.setColor(Color.green);
			
			g.setFont(f);
			g.drawString(getName(),  (int)getXY().getX(), (int)getXY().getY());
	}

}