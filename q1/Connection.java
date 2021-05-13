
import java.net.*;
import java.io.*;


public class Connection implements Runnable
{
     Socket client;

    public Connection(Socket client) {
        this.client = client;
    }

    
    public void run(){
				try{
                    if(client != null)
                    {
                        System.out.println("Client Connected");
                    }
   
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
				// write the Date to the socket
				pout.println(new java.util.Date().toString());
                System.out.println("Sent to Client the date");

				// close the socket and resume listening for more connections
				client.close();
                System.out.println("Client Disconnected");

                }
                catch (IOException ioe) {
                    System.err.println(ioe);
            }

            
    }
}
