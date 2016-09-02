/**
 * Project 1 -- Purdue Pete's Pizza resturant
 * This program recives input on how many guests are in the meal party, how many cheese pizzas,
 * pepporoni pizzas, vegitarian pizzas, 2 liter pepsi, mountain dew and sprite, and tip percentage
 * The program assings a random price for pizza between 5 and 20 dollars and 1 and 5 for soda. 
 * The program computes the total price of all cheese, pepporoni, and vegetarian pizzas, total price for
 * two liter pepsi, sprite and mountain dew, adds a tip and prints a recipt of the purchase.
 * 
 * @author Brian Shrawder
 * 
 * @recitation R01 (Azarmi, Mehdi)
 * 
 * Date September 17, 2012
 * 
 * */

//import statments for the program that allow the math and input needed.

import java.util.*;
import java.text.*;

  public class project1{
    
    /**
     * sets a random price for soda and pizza, also gets input from the user on how many guets pizzas and soda, and tip.
     * computes the price and tip, and displays a recipt of the transaction. 
     */
    
    
    public static void main (String[] args){
      Date today = new Date();
      int guests, cheese, pepperoni, vegetarian, pepsi, mountaindew, sprite; 
      double tip, grandtotal,subtotal, pizzaBill,sodaBill,tipTotal, pizza, soda,tipperperson, 
        cheesePizza, pepperoniPizza, vegetarianPizza, pepsis, mountaindews, sprites;
      
      //sets a random price for the pizza between 5 and 20 dollars and 1 and 5 for the soda
      
      
      pizza = 5.00 + (double)(Math.random()*15.00);
      soda =1.00 +(double) (Math.random()*4.00);
      
      /** inplments the scanner to get input from the user on number of guests, cheese pizza, pepporoni pizza
        * vegetarion pizza, pepsi, mountain dew, sprite, and tip percentage.
        */
   
     Scanner scanner = new Scanner(System.in);

      
    System.out.print("Enter the number of guests:  ");
     guests = scanner.nextInt();
      
    System.out.print("Enter the number of cheese pizzas:  ");
    cheese = scanner.nextInt();
      
    System.out.print("Enthe the number of pepperoni pizzas:  ");
    pepperoni = scanner.nextInt();
      
    System.out.print("Enther the number of vegetarian pizzas:  ");
    vegetarian = scanner.nextInt();
      
    System.out.print("Enter the number of two liters of pepsi:  ");
    pepsi = scanner.nextInt();
      
    System.out.print("Enter the number of two liters of mountain dew:  ");
    mountaindew = scanner.nextInt();
      
    System.out.print("Enter the number of two liters of Sprite");
    sprite = scanner.nextInt();
      
    System.out.print("Enter the percentgae tip");
    tip = scanner.nextDouble();
    
    // gets the current date of the transactio for the recept
    
    
    String dateFormat = "EEEE, MMMM dd, yyyy"; 
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    String formatedDate = simpleDateFormat.format(today);
    
    // uses the preassinged value for pizza to price the diffrent types of pizza and then the total pizza cost.
    
    
    cheesePizza = (cheese*pizza);
    pepperoniPizza = (pepperoni*pizza);
    vegetarianPizza = (vegetarian*pizza);
    pizzaBill = (cheesePizza+pepperoniPizza+vegetarianPizza);
    
    // rounds the price of the pizza to two decimal places
    
    double cp = cheesePizza;
    cp = Math.round(cp*100)/100.0;
    
    double pp = pepperoniPizza;
    pp = Math.round(pp*100)/100.0;
    
    double vp = vegetarianPizza;
    vp = Math.round(vp*100)/100.0;
    
    // uses the preassinged value for soda to price the diffrent types of soda and then the total soda cost.
    
    
    pepsis = (pepsi*soda);
    mountaindews = (mountaindew*soda);
    sprites = (sprite*soda);
    sodaBill = (pepsis+mountaindews+sprites);
    
  // rounds the price of the soda to two decimal places
    
    double ps = pepsis;
    ps = Math.round(ps*100)/100.0;
    
    double ms = mountaindews;
    ms = Math.round(ms*100)/100.0;
    
    double ss = sprites;
    ss = Math.round(ss*100)/100.0;
    
    // computes the subtotal by adding the total cost of pizza and total cost of soda
    
    subtotal = pizzaBill+sodaBill;
    
    //rounds the subtotal to two decimal places
    
    double st = subtotal;
    st = Math.round(st*100)/100.0;
    
    //computes the tip by taking the pizza cost + soda multypled by the (tip/100)
    
    
    tipTotal = (pizzaBill+sodaBill)*(tip/100);
    
    //rounds the tip to two decimal places
    
    double tt = tipTotal;
    tt = Math.round(tt*100)/100.0;
    
    //computs the grand total by adding the tip to the subtotal
    
    grandtotal = tipTotal+subtotal;
    
    //rounds the grand total to two decimal places
    
    double gt = grandtotal;
    gt = Math.round(gt*100)/100.0;
    
    //computes tip per person by divideing the total tip by the number of guests
       
    tipperperson = tipTotal/guests;
    
    //rounds the tip per person to two decimal places
    
    double tpp = tipperperson;
    tpp = Math.round(tpp*100)/100.0;  
    
    // prints a recipt of the purchase for the costomer
      
    
    System.out.println("Purdue Pete's Pizza Piazza" 
                      +"\n" + "==========================" 
                      +"\n"
                      +"\n" + formatedDate + " " 
                      +"\n" 
                      +"\n"+ "Item" +"\t"+"\t"+"\t" + "   Quantity"
                      +"\t"+"\t" + " Price" 
                      +"\n"+"-----------------------------------------------------------------------"
                      +"\n" + "Cheese Pizza" + "\t"+"\t" + "\t" +cheese +"\t"+"\t"+" $"+cp
                      +"\n" + "Pepperoni Pizza" + "\t" +"\t"+"\t"+ pepperoni +"\t"+"\t"+" $"+pp
                      +"\n" + "Vegetarian Pizza" +"\t" +"\t"+ vegetarian + "\t"+"\t"+" $"+vp
                      +"\n" + "2 liter bottle of Pepsi" +"\t"+"\t"+ pepsi+"\t"+"\t"+" $"+ps
                      +"\n" + "2 liter bottle of Mountain Dew" +"\t"+ mountaindew +"\t"+"\t"+" $"+ms
                      +"\n" + "2 liter bottle of Sprite" +"\t"+ sprite +"\t"+"\t"+" $"+ss
                      +"\n"+"-----------------------------------------------------------------------"
                      +"\n" + "SubTotal"+"\t"+"\t"+"\t"+"\t"+"\t"+" $"+st
                      +"\n" + "Tip" +" ("+tip+"%)"+"\t"+"\t"+"\t"+"\t"+"\t"+" $"+tt
                      +"\n" + "Grand Total"+"\t"+"\t"+"\t"+"\t"+"\t"+" $"+gt
                      +"\n"
                      +"\n" +"The tip per person for  "+ guests + " guests is " + "$"+tpp );
      
       
       
      
      
      
      
      
      
     
      
      
    }
  }
      
      
      
      
      
      