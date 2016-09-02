

/**
 * Project 2 -- LineSegment class
 * This class uses a constructor to assign values to a start and end point based on user input and the point class
 * It then takes the points that were found and uses them inside methods to compute slope, length and y intercept
 * it then uses a tostring method to return the equation of a line in the form y=mx+b where m is the slope and b
 * is the y intercept, it then asks the user for a number to scale the lenght of the line by. it uses this number
 * to find a new end point, creates a new line segment and finds a new lenght and prints it. 
 * 
 * @author Brian Shrawder
 * 
 * @recitation 01 (Azarmi,Mehdi)
 * 
 * @date September 26, 2012
 * 
  */
public class LineSegment{
  double slope, length, yintercept,a,y, newlength;
  String line;
  
  // constructor that gives values to start and end point from the point class
 public LineSegment(Point a , Point b){
    start = a;
    end = b;
  }
    
      
  Point start, end;
  
  // gets the start point
  
  public Point getStart(){
    return start;
  }
    
  // gets the end point 
  
  public Point getEnd(){
    return end;
  }

  
  //  when called calculates slope of the line segment
 double computeSlope(){
    slope=((end.getY()-start.getY())/(end.getX()-start.getX()));
  return slope;
  }
 
 // when called prints the slope of the line
  void printSlope(){
    System.out.printf("" +"%.2f \n" ,  slope );
  }
  
  //when called computes the length of the line segment
 
  double length(){
    length=Math.sqrt((Math.pow(start.getX()-end.getX(),2))+(Math.pow(start.getY()-end.getY(),2)));
    return length;
  }
  
  //when called pirnts the length of the line segment
  void printLength(){
    System.out.printf("" +"%.2f \n",  length());
  }
  
  // when called computes the y intercept of the line
  
  double computeYIntercept(){
    yintercept=(start.getY()-(slope*start.getX()));
  return yintercept;
  }
  
  //when called prints the y intercept of the line
  void printYIntercept(){
    System.out.printf("" + "%.2f \n",  yintercept);
  }
  

  public String toString(){
    double s = slope;
    s = Math.round(slope*100)/100.00;
    
    double yi = yintercept;
    yi = Math.round(yintercept*100)/100.00;
 
    String line = " "  + "Y=" +" " + s +"x"+"+"+ yi;
    return line;
   }
   
   // when called prints the equation of a line
   void printLine(){
     System.out.printf("" + toString()+"\n");
   }
   
   // when called finds the new end point and length based on the scalar inputed by user
  
  public LineSegment scaleByFactor(double scalar){
    double b,c;
   
    b= (((end.getX()-start.getX())*scalar) + start.getX());
    c= (((end.getY()-start.getY())*scalar) + start.getY());
    
  Point p3;
  p3 = new Point (b,c);
  
  LineSegment newline;
    newline = new LineSegment(start,p3);
    
  
  return newline;
  
    
  }      
}
      
      