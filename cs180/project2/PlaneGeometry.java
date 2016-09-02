/**
 * Project 2 -- PlaneGeometry class
 * This class is the controller class for the program. It takes input form the user and gets a line
 * segment from the linesegment after making the start and end points in the point class
 * then prints the slope y intercept length and equation of a line, after that it takes input about 
 * how much to scale the line by then finds a new end point new line segment and new lenght and prints it
 * 
 * 
 * @author Brian Shrawder
 * 
 * @recitation 01 (Azarmi,Mehdi)
 * 
 * @date September 26, 2012
 * 
  */

//used to import the information needed for the scanner

import java.util.*;

//sets class name and intilizies main varables

public class PlaneGeometry{
  public static void main(String[] args){
    double x,y,c,d,scalar;
    
    
    //initilize the scanner method
    
     Scanner scanner = new Scanner(System.in);
     
    //gets first x coordiante
     
    System.out.print(" Enter the firtst x coordinate:  ");
    x = scanner.nextDouble();

    //gets first y coordiante
    
    System.out.print( " Enter the first y coordinate:  ");
    y = scanner.nextDouble();
    
    //gets second x coordiante
    
    System.out.print(" Enter the second x coordinate:  ");
    c = scanner.nextDouble();

    //gets second y coordiante
    
    System.out.print( " Enter the second y coordinate:  ");
    d = scanner.nextDouble();
    
    //creats point one using x and y variables and the point class
    
    Point p1;
    p1 = new Point(x, y);
    
    //creates point two using x and y variables and the point class
    Point p2;
    p2 = new Point(c,d);
      
    // creats a line segment using points one and two and the line segment class
    LineSegment slope;
    slope = new LineSegment(p1,p2);
    
    
    

    
   //computes slope length and yintercept using the line segment and line segment class
    
   slope.computeSlope();
   slope.length();
   slope.computeYIntercept();
  
    
 
    //prints the slope length y intercept equation of a line
   
   System.out.print("Slope: "); slope.printSlope();    
   System.out.print("Length: "); slope.printLength();
   System.out.print("Y-Intercept: "); slope.printYIntercept();
   System.out.print("Equation: "); slope.printLine();  
 
   //get the number that the uer wants the line to be scaled by
   
   System.out.print("Enter the scale by factor:");
   scalar = scanner.nextDouble();
   
   // creats a new line segment that is scaled by the requested number
   
   LineSegment scaled;
   scaled = slope.scaleByFactor(scalar);
   

   
   // prints the new length after scaling
   
  System.out.print("New Length: "); scaled.printLength();
 
    

  }
  
  
}

     