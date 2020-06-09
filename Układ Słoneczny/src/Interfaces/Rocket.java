package Interfaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;



public class Rocket {

	Point apex;
	
	Color color;

	double Vx;
	double Vy;

	
	public Rocket(Point a,  Color col) {
		color = col;
		apex =a;
		Vx=0; 
		Vy=0;
	}
	
	Point getApex ()
	{
		return apex;
	}
	
	void setApex (Point a)
	{
		this.apex = a;
	}
	
	
	Color getColor ()
	{
		return color;
	}
	
	void setColor(Color col)
	{
		this.color =col;
	}

	double getVx ()
	{
		return Vx;
	}
	
	void setVx(double Vx)
	{
		this.Vx =Vx;
	}
	
	double getVy ()
	{
		return Vy;
	}
	
	void setVy(double Vy)
	{
		this.Vy =Vy;
	}
	
	
	
	
	void paintRocket(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		 int x[]= new int[] {(int) getApex().getX(), (int) getApex().getX() -5 , (int) getApex().getX()+5};
		 int y[]= new int[] {(int) getApex().getY(), (int) getApex().getY()-10, (int) getApex().getY()-10};
		 Polygon triangle = new Polygon(x, y, 3);
		 g.setColor(getColor());
		 g2d.fill(triangle);
		 g.drawPolygon(triangle);
	}
}
