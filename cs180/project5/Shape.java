import java.awt.Graphics;

public abstract class Shape {
 private Graphics g; //Used for drawing

 // You will need to override this method for all subclasses of Shape
 public abstract void draw();
 
 /**
  * Draws a line on the window. This line is defined by two points,
  * (x1,y1) and (x2,y2)
  * 
  * @param x1 the x coordinate of the first point
  * @param y1 the y coordinate of the first point
  * @param x2 the x coordinate of the second point
  * @param x2 the y coordinate of the second point
  */
 protected void drawLine(int x1, int y1, int x2, int y2) {
  g.drawLine(x1,y1,x2,y2);
 }
 
 /**
  * Draws an ellipse on the window.
  * 
  * @param center_x the x coordinate of the center of the ellipse
  * @param center_y the y coordinate of the center of the ellipse
  * @param width the width of the ellipse
  * @param height the height of the ellipse
  */
 protected void drawEllipse(int center_x, int center_y, int width, int height) {
  g.drawOval(center_x-(width/2),center_y-(height/2),width,height);
 }
 

 
 /**
  * This is used by the GUI, you do not need to worry about it
  */
 public void setGraphics(Graphics g) {
  this.g = g;
 }
}
