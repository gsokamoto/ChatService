import java.io.*;
import java.net.*;

public class WriteThread extends Thread{
    private PrintWriter writer;
    private  Socket socket;
    private ChatClient chatClient;
    private String username;

    public WriteThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;
        username = "";

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while(true){}
    }

    public void sendMessage(String message) {
        String text = message;

        try {
            if (text.equals("."))
                socket.close();
            else
                writer.println(text);
        }
        catch(IOException ex){
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }

    public void setUsername(String username)
    {
        this.username = username;
        chatClient.setUsername(username);
        writer.println(username);
    }

    public String getUsername()
    {
        return username;
    }
}
