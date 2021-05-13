
import java.net.*;
import java.io.*;

public class ThreadedDateServer
{                                                
	public static final int DEFAULT_PORT = 6013;
	
	private static ServerSocket sock = null;
	
	
	public static void main(String[] args)  {

		try {
			System.out.println("Server is listening...");
			sock = new ServerSocket(DEFAULT_PORT);
			while (true) {
			Socket client = sock.accept();
		
						// create a separate thread
						// to service the request
				Thread worker = (new Thread(new  
										  Connection(client)));
				worker.start();
			   }
			}
			catch (IOException ioe) {
			  System.err.println(ioe);
			}
		}		
}
