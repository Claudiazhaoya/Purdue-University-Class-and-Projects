/**
 * Project 5--- Pentagon class
 * 
 * This class extends the Shape class. It takes in the xy corrds of the center and the radius of a guide circle. It then calculates vertices of a 
 * pentagon inscribed in a guide circle. It then prints the pentagon for the painter gui to display.
 * @author Brian Shrawder
 *
 *@R01 (Medhi Azaarmi)
 *
 *@Date October 29, 2012
 */

//sets points for use in the drawing
public class Pentagon extends Shape{
	double x;
	double y;
	int r;
	double x1;
	double x2;
	double x3;
	double x4;
	double x5;
	
	double y1;
	double y2;
	double y3;
	double y4;
	double y5;

	//calculates vertices for an inscribed pentagon
	public Pentagon(int center_x, int center_y, int radius) {
		x = center_x;
		y = center_y;
		r = radius;
		
		x1 = (int)(radius * Math.cos(Math.toRadians(72)))+x;
		x2 = (int)(radius * Math.cos(Math.toRadians(144)))+x;
		x3 = (int)(radius * Math.cos(Math.toRadians(216)))+x;
		x4 = (int)(radius * Math.cos(Math.toRadians(288)))+x;
		x5 = (int)(radius * Math.cos(Math.toRadians(360)))+x;
		
		
		y1 = (int)(radius*Math.sin(Math.toRadians(72)))+y;
		y2 = (int)(radius*Math.sin(Math.toRadians(144)))+y;
		y3 = (int)(radius*Math.sin(Math.toRadians(216)))+y;
		y4 = (int)(radius*Math.sin(Math.toRadians(288)))+y;
		y5 = (int)(radius*Math.sin(Math.toRadians(360)))+y;
	}

	//draws the insribed pentagon for the gui
	@Override
	public void draw() {
		drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
		drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
		drawLine((int)(x3),(int)(y3),(int)(x4),(int)(y4));
		drawLine((int)(x4),(int)(y4),(int)(x5),(int)(y5));
		drawLine((int)(x5),(int)(y5),(int)(x1),(int)(y1));

		
	}

}
