// A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
	// initialize socket and input output streams
	private Socket socket		 = null;
	private DataInputStream input = null;
	private DataOutputStream out	 = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new DataInputStream( System.in);

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		// string to read message from input
		String line = "";
//  System.out.println("Hello World");
		// keep reading until "Over" is input
		while (!line.equals("Over"))
		{
			try
			{
				line = input.readLine();
        int key = 3;
        String temp = encrypt(line,key).toString();
				out.writeUTF(temp);
			}
			catch(IOException i)
			{
				System.out.println(i);
			}
		}

		// close the connection
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}



	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}


  public static StringBuffer encrypt(String text, int s)
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
            result.append(text.charAt(i)); //It will handle all the Excption Cases
          }

      }
      return result;
  }
}
