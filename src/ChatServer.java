import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private int port;
    private Set<String> usernames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(int port)
    {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Chat server is listening to port: " + port);
            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("new user connected: ");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(5000);
        server.startServer();
    }

    public void broadcast(String message, UserThread excludeUser){
        for (UserThread aUser : userThreads) {
            if(aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    public void addUsername(String username){
        usernames.add(username);
    }

    public void removeUser(String userName, UserThread aUser){
        boolean removed = usernames.remove(userName);
        if(removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " left");
        }
    }

    public Set<String> getUsernames() {
        return this.usernames;
        // return this.usernames
    }

    boolean hasUsers() {
        return !this.usernames.isEmpty();
    }

}
