/**
 * Project 6 - Server
 * this program handles requests for rides and responses from volunteers. The server
 * stores the requests and responses and matches them on a first come first servebasis
 * 
 * @author: Joshua Gugel
 * 
 * @recitation: R02 Runyan, David Marcus
 *
 * @date: November 9 2012
 */

//import necessary code
import java.util.*;
import edu.purdue.cs.cs180.channel.*;


//Class: Server
class Server implements MessageListener{
  private TCPChannel channel = null;
  
  //declare variables
  String[] list;
  Stack<Integer> requestID; //stores the ID numbers of the requests
  Stack<String> requestMessage; //stores the messages of the requests
  Stack<Integer> responseID; //stores the ID numbers of the responses
  Stack<String> responseMessage; //stores the messages of the responses
  
  
  //Constructor
  //takes the port number as an argument
  public Server(int port){
    //create a new channel and new stacks for handling requests and responses
    channel = new TCPChannel(port);
    channel.setMessageListener(this);
    requestID = new Stack<Integer>();
    requestMessage = new Stack<String>();
    responseID = new Stack<Integer>();
    responseMessage = new Stack<String>();
  }
  
  
  //Method: Message Received
  @Override // what does this part do?
  public void messageReceived(String message, int clientID) {
    
    //split the message to separate the type from the message
    list = message.split(":", 2);
    
    //If Request
    if(list[0].equals("Request"))
    {
      //check if responses stack is empty
      if(responseID.empty())
      {
        //adds the request to the stack and replies searching
        requestID.push(clientID);
        requestMessage.push(list[1]);
        sendResponse("Searching", clientID);
      }
      else
      {
        //send response to available volunteer
        sendResponse("Assigned: " + list[1], responseID.firstElement());
        //send response back to the requester
        sendResponse("Assigned: " + responseMessage.firstElement(), clientID);
        //remove volunteer from stack
        responseMessage.removeElementAt(0);
        responseID.removeElementAt(0);
      }         
    }
    //If Response
    else if(list[0].equals("Response"))
    {   
      //check if requests stack is empty
      if(requestID.empty())
      {
        //adds the response to the stack and replies searching
        responseID.push(clientID);
        responseMessage.push(message);
        sendResponse("Searching", clientID);
      }
      else
      {
        //send response to waiting request
        sendResponse("Assigned: " + list[1], requestID.firstElement());
        //send response back to the volunteer
        sendResponse("Assigned: " + requestMessage.firstElement(), clientID);  
        //remove request from stack
        requestMessage.removeElementAt(0);
        requestID.removeElementAt(0);
      }      
    }
  }
  
  
  //Method: Send response
  private void sendResponse(String message, int clientID){
    //attempts to send a message
    try {
      channel.sendMessage(message, clientID);
    }  catch (ChannelException e){    //catches the exception if the channel is closed
      e.printStackTrace();
    }
  }
  
  
  //Method: Main
  public static void main(String [] args){
    //initializes the server with the arguments
    new Server(Integer.parseInt(args[0]));
  }
}