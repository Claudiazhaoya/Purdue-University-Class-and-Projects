
/**
 * Project 5 --- Circle class
 * 
 * This class extend the Shape class. It takes in the x y coordiantes as center and the radius. It then draws a circle for the gui to display.
 * @author Brian Shrawder
 * 
 * @R01 (Medhi Azarmi)
 * 
 * @ Date October 29, 2012
 *
 */

// sets values for use drawing
public class Circle extends Shape {
	int x;
	int y;
	int r;

	// calcuates values for vertices for drawing
	public Circle(int center_x, int center_y, int radius) {	
		x = center_x;
		y = center_y;
		r = radius;
	System.out.println("test"+center_x+center_y+radius);
	//drawEllipse(center_x,center_y,radius,radius);
	}
	//draws a circle for the gui
@Override
	public void draw(){

		drawEllipse(x,y,r+r,r+r);
	
		
	}
}

