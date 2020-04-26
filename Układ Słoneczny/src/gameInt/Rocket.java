//Na razie  bez rysowania i zmian w prędkości i położeniu
import java.awt.Color;
import java.awt.Point;

public class Rocket {
	Point apex, middleOfBase;
	int height, widthOfBase; 
	Color rocketColor;
	int rocketMass;
	float forceX, forceY, accelerationX, accelerationY, velocityX, velocityY;
	
	public Rocket(Point ap, Point mOB, int h, int wOB, Color clr, int m, float Fx, float Fy, float ax, float ay, float Vx, float Vy )
	{
		apex =ap;
		middleOfBase =mOB;
		height =h;
		widthOfBase =wOB;
		rocketColor =clr;
		rocketMass = m;	
		forceX = Fx;
		forceY = Fy;
		accelerationX = ax;
		accelerationY = ay;
		velocityX = Vx;
		velocityY =Vy;
	}
	Point getApex ()
	{
		return apex;
	}
	Point getMiddleOfBase ()
	{
		return middleOfBase;
	}
	Color getColor ()
	{
		return rocketColor;
	}
	int getMass ()
	{
		return rocketMass;
	}
	int getHeight ()
	{
		return height;
	}
	int getWidthOfBase ()
	{
		return widthOfBase;
	}
//	double getVelocity() {
		
//	}

	
	
}
