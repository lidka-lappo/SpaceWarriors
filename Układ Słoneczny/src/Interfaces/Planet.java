package Interfaces;

import java.awt.Color;
import java.awt.Point;

public class Planet { //no i s³oneczko:)
	int radius;
	Point center;
	Color color;
	int mass;
	String name;
	
	public Planet(int rr, Point xxyy, Color col, int m, String nam) {
		radius = rr;
		center = xxyy;
		color = col;
		mass = m;
		name = nam;
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