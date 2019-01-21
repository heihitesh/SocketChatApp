// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "Over" is sent
			while (!line.equals("Over"))
			{
				try
				{
					line = in.readUTF();
          int key = 3;
          String temp = decrypt(line,26-key).toString();
					System.out.println(line +" : "+temp);

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}

  public static StringBuffer decrypt(String text, int s)
  {
      StringBuffer result= new StringBuffer();

      for (int i=0; i<text.length(); i++)
      {
          if (Character.isUpperCase(text.charAt(i)))
          {
              char ch = (char)(((int)text.charAt(i) +
                                s - 65) % 26 + 65);
              result.append(ch);
          }
          else if(Character.isLowerCase(text.charAt(i)))
          {
              char ch = (char)(((int)text.charAt(i) +
                                s - 97) % 26 + 97);
              result.append(ch);
          }
          else{
            result.append(text.charAt(i)); 
          }

      }
      return result;
  }
}
