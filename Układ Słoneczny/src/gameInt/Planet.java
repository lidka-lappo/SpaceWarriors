import java.awt.Color;
import java.awt.Point;

public class Planet { //no i sloneczko:)
	int radious;
	Point center;
	Color planetColor;
	int planetMass;
	String planetName;
	double velocityX, velocityY;
	
	public Planet(int rr, Point xxyy, Color col, int m, String nam, double Vx, double Vy) {
		radious = rr;
		center = xxyy;
		planetColor = col;
		planetMass = m;
		planetName = nam;
		velocityX = Vx;
		velocityY = Vy;
	}
	
	void info ()
	{
		System.out.println(planetName);
		System.out.println("r = "+ radious + ", color" + planetColor + ", mass = " + planetMass +", location: "+center.getX() + ","+center.getY());
	}
	int getRadious ()
	{
		return radious;
	}
	Point getCenter ()
	{
		return center;
	}
	Color getColor ()
	{
		return planetColo;
	}
	int getMass ()
	{
		return planetMass;
	}
	String getName ()
	{
		return planetName;
	}

}
