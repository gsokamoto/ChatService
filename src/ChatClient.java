import java.net.*;
import java.io.*;

public class ChatClient {
    private String hostName;
    private int port;
    private String userName;

    public ChatClient(String hostName, int port){
        this.hostName = hostName;
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

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return userName;
    }

    public static void main(String[] args) {

        String hostName = "localhost"; // enter hostName here
        int port = 5000; // enter port number here

        ChatClient client = new ChatClient(hostName, port);
        client.execute();
    }
}
