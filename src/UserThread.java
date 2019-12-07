import java.io.*;
import java.net.*;

public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer server)
    {
        this.socket = socket;
        this.server = server;
    }

    // allow this thread to broadcast chat server
    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(input)));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            String userName = reader.readLine();
            server.addUserName(userName);

            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
            } while (!clientMessage.equals("."));

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // print users on console, or send as string for GUI later
    public void printUsers(){
        if(server.hasUsers()){
            if (server.hasUsers()) {
                writer.println("Connected users: " + server.getUserNames());
            } else {
                writer.println("No other users connected");
            }
        }
    }

    // sends message to client
    void sendMessage(String message) {
        writer.println(message);
    }
}
