import java.net.*;
import java.io.*;

public class  DNSClient
{
	public static final int DEFAULT_PORT = 6052;
	public static Socket echoSocket;
	public static PrintWriter bout;
	public static BufferedReader bin;
	
	private static void test1(String url)
	{
		try {
			String toSend = url ;
			String toRecieve;
			
			//Connect to Server
			Socket echoSocket = new Socket("127.0.0.1", DEFAULT_PORT);
			System.out.println("Connected to Server"); 
			InputStream in = echoSocket.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));
			bout = new PrintWriter(echoSocket.getOutputStream(), true);
			
			
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			
			
			bout.println(toSend); // connect to server

			toRecieve = bin.readLine();
			System.out.println(toSend +" = "+ toRecieve); 

			//print toRecieve
			
			bin.close();
			bout.close();
			echoSocket.close();
			
			System.out.println("Disconnected from Server"); 
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
	
 
    public static void main(String[] args)  {
		if(args.length == 1){
			test1(args[0]);
		}
		else{
			System.out.println("Please enter only 1 parameter");
			System.exit(0);
		}
    	
	}



}