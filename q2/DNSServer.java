import java.net.*;
import java.io.*;


public class  DNSServer

{
	public static final int DEFAULT_PORT = 6052;
    public static final int BUFFER_SIZE = 256;
	private static ServerSocket sock = null;


	public static void main(String[] args) throws IOException {
		ServerSocket sock = null;


		try {
			// establish the socket
			sock = new ServerSocket(DEFAULT_PORT);
			System.out.println("Server is Listening...");
			while (true) {
				/**
				 * now listen for connections
				 */

				Socket client = sock.accept();
				
				Thread worker = (new Thread(new  
				Connection(client)));
					worker.start();
			}

				
		
		}
		catch (Exception e) {
			System.err.println(e);
		}
		finally {
				if (sock != null)
				sock.close();
			}
		}
	}