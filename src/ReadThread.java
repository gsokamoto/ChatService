import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ReadThread extends Thread{
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket , ChatClient client){
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        while(true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + dtf.format(LocalDateTime.now()) + " " + response);

                if (client.getUsername() != null) {

                    System.out.print("[" + client.getUsername() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("You have left the chat server");
                break;
            }
        }
    }
}
