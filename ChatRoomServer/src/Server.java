import java.net.*;
import java.awt.BorderLayout;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Server {
	
	JFrame serverGui;
	JTextArea displayWindow;
	private ServerSocket serverSocket;
	private Socket socket;
	public Hashtable<Socket, ObjectOutputStream> outputStreams;
	public Hashtable<String, ObjectOutputStream> clients;

	public Server(int port) throws IOException{
		serverGui = new JFrame("Server");
		serverGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverGui.setSize(500, 500);
		displayWindow = new JTextArea();
		serverGui.add(new JScrollPane(displayWindow), BorderLayout.CENTER);
		serverGui.setVisible(true);

		
		outputStreams = new Hashtable<Socket, ObjectOutputStream>();
		clients = new Hashtable<String, ObjectOutputStream>();
		
		serverSocket = new ServerSocket(port);
		showMessage("Waiting for clients at " + serverSocket);
	}

	public void waitingForClients() throws IOException, ClassNotFoundException{
		
		while (true){
			socket = serverSocket.accept();
			
			new ServerThread(this, socket);
		}
	}

	public void showMessage(final String message) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(
				new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						displayWindow.append(message);
					}
					
				}
				);
	}

	public void sendToAll(Object data) throws IOException{
		
		for (Enumeration<ObjectOutputStream> e = getOutputStreams(); e.hasMoreElements(); ){
			synchronized (outputStreams){
				ObjectOutputStream tempOutput = e.nextElement();
				tempOutput.writeObject(data);
				tempOutput.flush();
			}
		}
	}


	private Enumeration<ObjectOutputStream> getOutputStreams() {
		// TODO Auto-generated method stub
		return outputStreams.elements();
	}

	public void sendPrivately(String username, String message) throws IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream tempOutput = clients.get(username);
		tempOutput.writeObject(message);
		tempOutput.flush();
	}

		public void removeClient(String username) throws IOException{
			
			synchronized (clients){
				clients.remove(username);
				sendToAll("!" + clients.keySet());
			}
		}

	public void removeConnection(Socket socket, String username) throws IOException{
		
		synchronized (outputStreams){
			outputStreams.remove(socket);
		}

		showMessage("\n" + username + "(" + socket.getInetAddress().getHostAddress() + ") is offline");

	}
}












