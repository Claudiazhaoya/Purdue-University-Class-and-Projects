/**
 * Project 6 -- SafeWalk
 * This program is a Graphical User interface that communicates with a server 
 * It tells the server that the person or persons who are using it are ready to 
 * be walkers in the safe walk program. When the server gets a request it sends the 
 * walkers the location of the request.
 * 
 * @author Brian Shrawder
 * 
 * @recitation R01 (Medhi Azamri)
 * 
 * @date November 09,2012
 * 
 * */


//all of the import statments needed for the gui and communication
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import edu.purdue.cs.cs180.channel.*;
l

//the class for the gui and communication to the server

public class Response extends JFrame implements ActionListener, MessageListener {

	
	// the label and button for the gui
	JLabel statusLabel;
	JButton readyButton,test;
	String[] list;
	private TCPChannel channel = null;

	
	// method for the response gui and creats the channel connection to the server
	public Response(String name, int port){
		try{
			channel = new TCPChannel(name,port);
		} catch (ChannelException ex){
			ex.printStackTrace();
			System.exit(1);
		}
		channel.setMessageListener(this);
		System.out.println(channel.getID());


//makes the gui and buttons
		this.setTitle("title");
		readyButton = new JButton("Ready");
		statusLabel = new JLabel();
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(readyButton);
		readyButton.addActionListener(this);
		contentPane.add(statusLabel);
		this.pack();
		this.setVisible(true);
	}


//the messageReceived method, when activated displays the location for the request
	@Override
	public void messageReceived(String arg0, int arg1){
		list = arg0.split(":",2);
		if(list[0].equals("Assigned"))
	    {
		statusLabel.setText(arg0);
		readyButton.setEnabled(true);
		setVisible(true);
	}
		else
			statusLabel.setText(arg0);
	}



//sendMessage method when activated sends the message to the server.
	public void sendMessage(){
		try{
			channel.sendMessage("Response:Help Team "+channel.getID());
		}catch (ChannelException ex){
			ex.printStackTrace();
			System.exit(1);
		}
	}



//action preformed listens to the gui and if a button is cliked calles the send message method and disables the ready button
	@Override
	public void actionPerformed(ActionEvent e) {
		statusLabel.setText("");
		JButton clickedButton = (JButton) e.getSource();

		if(clickedButton==readyButton){
			statusLabel.setText("Searching"); 
			readyButton.setEnabled(false);
			sendMessage();
			setVisible(true);
		}

	}


//main method calls the methods to create the gui and connect to the server
//also will exit the gui when closed. 
	public static void main(String[] args) {
		Response response = new Response(args[0], Integer.parseInt(args[1]));
		JFrame myWindow;
		response.setSize(250,150);
		response.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		response.setVisible(true);

	}


}


