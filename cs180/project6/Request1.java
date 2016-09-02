import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.purdue.cs.cs180.channel.*;

/*
 * cs180 project 6
 * This is the Request part of the Safe Walk application.  Designed to handle the GUI for making a request
 * and sends the information to a server.
 * 
 * @Brandon Jaworsky
 * 
 * @RM4 Fadi Meawad
 * 
 * 11/8/12
 */

public class Request1 extends JFrame implements ActionListener, MessageListener
{
  TCPChannel channel = null;
  JComboBox locationsRequested = null;
  JLabel statusLabel;
  JButton submitButton;
  String[] list;
  
  /*
   * The main method just initializes the window for the GUI.
   */
  
  public static void main(String[] args)
  {
    Request1 myWindow = new Request1(args[0], Integer.parseInt(args[1]));
  }
  
  /*
   * the constructor sets up everything needed for the GUI.
   */
  
  public Request1(String hostname, int port)
  {   
    try
    {
     channel = new TCPChannel(hostname, port); 
    }
    catch(ChannelException ex)
    {
     ex.printStackTrace();
     System.exit(1);
    }
    channel.setMessageListener(this);
    System.out.println(channel.getID());
    
    //the string array for the choices of locations to be requested.
    String[] locations = { "CL50 - Class of 1950 Lecture Hall", "EE - Electrical Engineering Building", 
      "LWSN - Lawson Computer Science Building", "PMU - Purdue Memorial Union", "PUSH - Purdue University Student Health Center" };
    
    //all the properties of the JFrame are here
    JFrame myWindow;
    myWindow = new JFrame();
    this.setTitle("Safe Walk Request");
    this.setSize(200,100);
    myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //all the properties of the content pane are here
    Container cPane = this.getContentPane();
    cPane.setLayout(new BorderLayout());
    
    //all the properties of the combo box are here
    locationsRequested = new JComboBox<String>(locations);
    
    //all the properties of the button are here
    submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    
    //all the properties of the label are here
    statusLabel = new JLabel(" ");
    
    //all the additions to the content pane are here with their respected locations
    cPane.add(locationsRequested, BorderLayout.WEST);
    cPane.add(submitButton, BorderLayout.EAST);
    cPane.add(statusLabel, BorderLayout.SOUTH);
    
    //final adjustments to the JFrame, making it visible and as small as possible
    this.setVisible(true);
    this.pack();
    
  }
  
  /*
   * actionPerformed checks what actions have been provided by the program's user and executes
   * what each should do.
   */
  
 public void actionPerformed(ActionEvent e)
  {    
    String message;
    String message1 = " ";

    String selectedLocation = (String)locationsRequested.getSelectedItem();
    message1 = selectedLocation;
    message = ("Request:" + message1);
    sendRequest(message);

    statusLabel.setText("Searching");
    submitButton.setEnabled(false);
    locationsRequested.setEnabled(false);
    
  }
  
  /*
   * This method opens the channel that will be used for sending the messages.
   */
  
  private void sendRequest(String message)
  {
    try 
    {
      channel.sendMessage(message+channel.getID());
    } 
    catch (ChannelException e)
    {
      e.printStackTrace();
      System.exit(1);
    }  
  }
  
  /*
   * This method receives the message from the server and makes the button and combo box available 
   * again so another request can be made.
   */
  @Override
  public void messageReceived(String arg0, int arg1)
  {
	  list = arg0.split(":",2);
	if(list[0].equals("Assigned")){
   statusLabel.setText(arg0);
   submitButton.setEnabled(true);
   locationsRequested.setEnabled(true);
   setVisible(true);
  }
	else 
		statusLabel.setText(arg0);
  }

}