/**
 * Project 5 ---- ShapeFactory class
 * 
 * This class tells the gui the options of circle triangle square and pentagon for use in drawing.
 * @author Brian Shrawder
 *
 *@R01 (Medhi Azaarmi)
 *
 *@Date October 29 2012
 */
public class ShapeFactory {
 // This array stores all shape names as strings. The shapes menu on the window is built using this array
 private static String[] types = new String[]{"Circle","Triangle","Square","Pentagon"};

 /**
  * Creates a shape object of the specified type
  * 
  * @param type the name of the Shape subclass to create. This string will be one of the strings in the types array
  * @param center_x the x coordinate of the center of the shape we wish to draw
  * @param center_y the y coordinate of the center of the shape we wish to draw
  * @param radius the radius of the "guide" circle that defines the size of our shape
  * @return The shape object we've created as an object of type "Shape"
  */
 
 
	 // gives the option for circle triangle square and pentagon and takes parameters of xy center and raidus.
 public static Shape makeShape(String type, int center_x, int center_y, int radius) {
  if(type.equals("Circle")){
	  return new Circle(center_x,center_y,radius);
  }
  else if (type.equals("Triangle")) {
	  return new Triangle(center_x,center_y,radius);
  }
  else if (type.equals("Square")) {
	  return new Square(center_x,center_y,radius);
  }
  else if (type.equals("Pentagon")) {
	  return new Pentagon(center_x,center_y,radius);
  }
  
	 return null;
 }
 
 public static String[] getTypes() {
  return types;
 }
}