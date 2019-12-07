import java.net.*;
import java.io.*;

public class ChatClient {
    private String hostName;
    private int port;
    private String username;

    public ChatClient(String ipAddress, int port){
        this.hostName = ipAddress;
        this.port = port;
    }

    public void execute(){
        try {
            Socket socket = new Socket(hostName, port);

            System.out.println("Connected to the chat server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO Error: " + ex.getMessage());

        }
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

    public static void main(String[] args) {

        String hostName = "localhost"; // enter hostName here
        int port = 5000; // enter port number here

        ChatClient client = new ChatClient(hostName, port);
        client.execute();
    }
}
