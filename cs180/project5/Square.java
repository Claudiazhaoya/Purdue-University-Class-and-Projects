
/**
 * Project 5 --- Square class
 * 
 * This class extends the Shape class, it takes in the xy corrds of the center and the radius. It then finds the new vertices of a square inscribed a guide circle
 * then it prints a square for the gui to display.
 * 
 * @author Brian Shrawder
 * 
 * @R01 (Medhi Azarmi)
 * 
 * @ date October 29, 2012
 *
 */

// sets points as doulbes for use in the drawing

public class Square extends Shape {
double x;
double y;
 int r;
 double x1;
 double x2;
 double x3;
 double x4;
 
 double y1;
 double y2;
 double y3;
 double y4;

// calculates vertices for the inscribed square
	public Square(int center_x, int center_y, int radius) {
		x = center_x;
		y = center_y;
		r = radius;
		
		x1 = (int)(radius * Math.cos(Math.toRadians(45)))+x;
		x2 = (int)(radius * Math.cos(Math.toRadians(135)))+x;
		x3 = (int)(radius * Math.cos(Math.toRadians(225)))+x;
		x4 = (int)(radius * Math.cos(Math.toRadians(315)))+x;
		
		y1 = (int)(radius * Math.sin(Math.toRadians(45)))+y;
		y2 = (int)(radius * Math.sin(Math.toRadians(135)))+y;
		y3 = (int)(radius * Math.sin(Math.toRadians(225)))+y;
		y4 = (int)(radius * Math.sin(Math.toRadians(315)))+y;
	}
	
//draws the inscribed square for the gui
	@Override
	public void draw() {

		
		drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
		drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
		drawLine((int)(x3),(int)(y3),(int)(x4),(int)(y4));
		drawLine((int)(x4),(int)(y4),(int)(x1),(int)(y1));
	}
		
	}
