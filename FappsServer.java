import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FappsServer {
	public FappsServer(){
		// create socket
        int port = 8080;
	ServerSocket serverSocket = null;
	try{
        	serverSocket = new ServerSocket(port);
        } catch (Exception e){
		System.out.println("Failed to create server socket");
	}
	System.err.println("Started server on port " + port);

        // repeatedly wait for connections, and process
        while (true) {
            try{
 		System.out.println("Awaiting connection from client ...");
		Socket clientSocket = serverSocket.accept();
                System.err.println("Accepted connection from client");
	        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            	// waits for data and reads it in until connection dies
            	// readLine() blocks until the server receives a new line from client
            	String s;
            	s = in.readLine();{//while ((s = in.readLine()) != null) {
                	System.out.println(s);
			out.write("Echo: " + s + "\n"); //Newline needed to terminate readLine()'s wait condition
            	}

            	System.err.println("Closing connection with client");
            	out.close();
            	in.close();
            	clientSocket.close();
	    } catch (Exception e){
		System.out.println("Failed to process incoming connection");
		System.exit(1);
	    }
        }
    }	
	
    public static void main(String[] args){
	FappsServer fs = new FappsServer();
    }
}

