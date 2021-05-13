
import java.net.*;
import java.io.*;

public class Connection implements Runnable{
    
    Socket client;
    String url;
    String hostAddress;
    BufferedReader fromClient = null;
    PrintWriter toClient = null;


    public Connection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try{
            fromClient = new BufferedReader(
	        new InputStreamReader(client.getInputStream()));
            url = fromClient.readLine();
            if(client != null)
            {
                System.out.println("Client Connected");
            }
            try {
                InetAddress hostInetAddress = InetAddress.getByName(url);//walla
                hostAddress = hostInetAddress.getHostAddress();//walla - IP
                
            } catch (UnknownHostException  e) {
                System.err.println(e);
                hostAddress = "Unknown Host";
            }
        toClient = new PrintWriter(client.getOutputStream(), true);
        // write the Date to the socket

        toClient.println(hostAddress);
        System.out.println("Sent to Client the date");

        // close the socket and resume listening for more connections
        client.close();
        System.out.println("Client Disconnected");

        }
        catch (IOException  ioe) {
            System.err.println(ioe);

        }
        

    
        
    }
}
