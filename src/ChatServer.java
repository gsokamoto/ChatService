import java.net.*;
import java.io.*;
import java.util.Set;

public class ChatServer {
    private int port;
    // usernames object array/set
    // userThreads as object array/set
    //

    public ChatServer(int portNumber)
    {
        this.port = port;
    }

    public void startServer() {
        try {
            System.out.println("Chat server is listening to port: " + port);
            while (true) {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                System.out.println("new user connected");
                // add user to userThread
                // start user thread
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    // @param: String message, UserThread excludeUser
    public void broadcast(){}

    // @param: String userName
    public void addUserName(){}

    //@param STring userName, UserThread
    // we may not need this function?
    public void removeUser(){}

    public Set<String> getuserNames() {
        return null;
        // return this.userNames
    }

    boolean hasUsers()
    {
        return true;
    }

    // test this with main method after if possible
}
