/**
 * Project 2 --Point class
 * This class takes input from user in the form of two points and constructs a Point on a line with
 * the two points as x and y values, then uses a tostring method to output the point in a string
 * 
 * @author Brian Shrawder
 * 
 * @recitation 01 (Azarmi,Mehdi)
 * 
 * @date September 26, 2012
 * 
  */

// sets the class name and data members
public class Point{
  public String point;
  private double x,y;
  
  // constructor that assings x and y double values
  
   public Point( double a, double b){
    x = a;
    y = b;
  }
  
  // method when called gets the coordiantes for the point on the line
  void getCoords(){
  }
  
  // gets the x value from the user input and assings it to x coord in point
  public double getX(){
    return x;
  }
  
  
  // gets the y value from the user input and assings it to y coord in point
  public double getY(){
    return y;
  }
  
  // sets the two coordiants to the point in the form (x,y)
  
  public String toString(){
    String line = "X=" +x +" " +"Y=" +y;
    return line;
  }
  }
  

  

  