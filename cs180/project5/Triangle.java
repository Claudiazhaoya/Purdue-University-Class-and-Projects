/**
 * Project 5 ---- Triangle class
 * This class extends the Shape class, it takes in the xy cords of the center and the radius. It then calculates vertices of a triangle inscribed a guide circle
 * It then prints a triangle of these points for the painter gui to display.
 * 
 * @author Brian Shrawder
 *
 *@R01 (Medhi Azarmi)
 *
 *@ Date October 29,2012
 */

// sets points as doulbe and takes in the xy corrds for center and radius
//also calculates the vertices for the inscribed triangle
public class Triangle extends Shape{
	double p11;
	double p12;
	double p21;
	double p22;
	double p31;
	double p32;
	public Triangle(int center_x, int center_y, int radius) {
		p11 = center_x;
		p12 = center_y-radius;
		p21 = center_x+(radius*Math.pow(3, 1/2));
		p22 = center_y+(radius/2);
		p31 = center_x-(radius*Math.pow(3, 1/2));
		p32=p22;
		
	}
//draws the inscribed triangle for the gui
	public void draw() {
		
		
		drawLine((int)(p11),(int)(p12),(int)(p21),(int)(p22));
		drawLine((int)(p21),(int)(p22),(int)(p31),(int)(p32));
		drawLine((int)(p31),(int)(p32),(int)(p11),(int)(p12));
		
	}

}
