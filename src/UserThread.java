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
    public void run(){}

    // print users on console, or send as string for GUI later
    public void printUsers(){}

    // sends message to client
    void sendMessage(String message) {}
}
