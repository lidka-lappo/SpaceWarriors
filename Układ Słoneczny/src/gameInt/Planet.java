import java.awt.Color;
import java.awt.Point;

public class Planet { //no i s³oneczko:)
	int r;
	Point xy;
	Color color;
	int mass;
	String name;
	
	public Planet(int rr, Point xxyy, Color col, int m, String nam) {
		r = rr;
		xy = xxyy;
		color = col;
		mass = m;
		name = nam;
	}
	
	void info ()
	{
		System.out.println(name);
		System.out.println("r = "+ r + ", color" + color + ", mass = " + mass +", location: "+xy.getX() + ","+xy.getY());
	}
	int getR ()
	{
		return r;
	}
	Point getXY ()
	{
		return xy;
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

}
