package neu.edu.cs5010;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 *
 */
public class PlayYahtzee {

  private String hostName;
  private int portNumber;

  public static void main(String[] args) {
    PlayYahtzee play = new PlayYahtzee();
    play.checkArgument(args);
    play.run();
  }

  /**
   * check the argument
   * @param args the command line input
   */
  private void checkArgument(String[] args) {
    if (args == null || args.length != 2)
      throw new IllegalArgumentException("Please check your argument");
    else if (Integer.parseInt(args[1]) <= 1024)
      throw new IllegalArgumentException("Please use a port that bigger then 1024");
    hostName = args[0];
    portNumber = Integer.parseInt(args[1]);
  }

  /**
   *  use socket to connect to the Yahtzee server. Use BufferedReader to read from server, use PrintWriter to
   *  transfer information to Server.
   */
  private void run() {
    try{
      Socket clientSocket = new Socket(hostName, portNumber);
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      UserInputHandler handler = new UserInputHandler(stdIn);
      String fromServer;
      String fromUser;

      while ((fromServer = in.readLine()) != null) {

        System.out.println("Server : " + fromServer);

        fromUser = handler.handler(fromServer);
//        fromUser = userInput.readLine();

        if (fromUser != null) {
          System.out.println("User : " + fromUser);
          out.println(fromUser);
        }
      }
      clientSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}