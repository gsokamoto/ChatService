import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WriteThread extends Thread{
    private PrintWriter writer;
    private  Socket socket;
    private ChatClient chatClient;

    public WriteThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("\nEnter your username: ");
        String username = userInput.nextLine();
        chatClient.setUsername(username);
        writer.println(username);

        String text;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        do {
            System.out.print("[" + username + "]: ");
            text = userInput.nextLine();
            writer.println(text);
        }
        while (!text.equals("."));
        try {
            socket.close();
        } catch (IOException ex){
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}
