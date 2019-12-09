import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ReadThread extends Thread{
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
    private ChatGUI clientGUI;

    public ReadThread(Socket socket , ChatClient client, ChatGUI clientGUI){
        this.socket = socket;
        this.client = client;
        this.clientGUI = clientGUI;

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
        String response = "";

        while(true) {
            try {
                response = reader.readLine();
                String formatedResponse = "\n" + dtf.format(LocalDateTime.now()) + " " + response;
                //System.out.println(formatedResponse);
                clientGUI.window.append(formatedResponse + "\n");

                /*
                if (client.getUsername() != null) {
                    String formatedUsername = "[" + client.getUsername() + "]: ";
                    System.out.print(formatedUsername);
                    clientGUI.window.append(formatedUsername);
                }
                */
            } catch (IOException ex) {
                //System.out.println("You have left the chat server");
                //clientGUI.window.append("You have left the chat sever");
                break;
            }
        }
    }
}
