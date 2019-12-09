import java.io.*;
import java.net.*;

public class UserThread extends Thread {
    private Socket socket;
    private ChatServer chatServer;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer chatServer)
    {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    // allow this thread to broadcast chat chatServer
    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(input)));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            String username = reader.readLine();
            chatServer.addUsername(username);

            String serverMessage = "New user connected: " + username;
            chatServer.broadcast(serverMessage, this);



            String clientMessage;

            while (!(clientMessage = reader.readLine()).equals(".")){
                //clientMessage = reader.readLine();
                serverMessage = "[" + username + "]: " + clientMessage;
                chatServer.broadcast(serverMessage, this);
            }

            chatServer.removeUser(username, this);
            serverMessage = username + " has left the chat room";
            chatServer.broadcast(serverMessage, this);
            socket.close();

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // print users on console, or send as string for GUI later
    public void printUsers(){
        if (chatServer.hasUsers()) {
            writer.println("Connected users: " + chatServer.getUsernames());
        } else {
            writer.println("No other users connected");
        }
    }

    // sends message to client
    void sendMessage(String message) {
        writer.println(message);
    }
}
