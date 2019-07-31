import java.io.*;

public class StartingPointServer {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Server server = new Server(5555);
		server.waitingForClients();
	}
}
