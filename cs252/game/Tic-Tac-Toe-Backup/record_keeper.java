import java.util.*;
import java.io.*;
import java.awt.*;
import java.net.*;

/**
 * Server Side Tic Tac Toe
 * 
 * Zachary M Soehren
 * 
 * 
 */


public class Record_Keeper extends Thread{
  static int portNumber = 5555;
  static int lock = 0;
  static Socket clientSocket1;
  static PrintWriter out1;
  static BufferedReader in1;
  
  
  public void run(){
    String passed;
    Socket client1 = clientSocket1;
    PrintWriter r_out1 = out1;
    BufferedReader r_in1 = in1;
    lock = 0;
   
  }
  
  public static void main(String args[]){
    try{
      ServerSocket serverSocket = new ServerSocket(portNumber);
      while(true){
        clientSocket1 = serverSocket.accept();
        System.out.println("A player is making a request");
        out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
        in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
        
        lock = 1;
        new Record_Keeper().start();
        while(lock == 1){
          try{
            Thread.sleep(10);
          }catch(InterruptedException e){
          }
        }
        clientSocket1=null;
      } 
    }catch(Exception e){
      System.out.println("Error!");
    }
  }
}