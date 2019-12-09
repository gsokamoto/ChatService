import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ChatGUI {

    private JFrame jfrm;
    private static JTextField textEntry;
    public JTextArea window;
    private static JButton enter;
    private static JScrollPane scrollPane;
    private ChatClient chatClient;
    private WriteThread writeThrd;
    private boolean hasUsername = false;

    ChatGUI(ChatClient chatClient, WriteThread writeThrd){
        this.chatClient = chatClient;
        this.writeThrd = writeThrd;

        jfrm = new JFrame();
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(500,500);
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setLocationRelativeTo(null);

        jfrm.setTitle("Network Messenger");

        window = new JTextArea();
        window.setText("Enter your username: ");
        window.setEditable(false);
        scrollPane = new JScrollPane(window);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        textEntry = new JTextField("", 25);

        enter = new JButton("Send Message");
        enter.setSize(100,100);
        enter.setMnemonic(KeyEvent.VK_ENTER);
        enter.addActionListener(e->{

            String text = textEntry.getText();
            textEntry.setText("");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

            if(!hasUsername) {
                writeThrd.setUsername(text);
                hasUsername = true;
                jfrm.setTitle(text + "'s Network Messenger");
                window.append("\nYou have successfully joined the group\n");
                // window2.append("\n" + text +"\n");
            }
            else{
                String prefix = dtf.format(LocalDateTime.now()) + " [" + writeThrd.getUsername() + "]: ";
                writeThrd.sendMessage(text);
                if(text.equals("."))
                    window.append(" \nYou have left the chat server\n");
                else
                    window.append("\n" + prefix + text + "\n");
            }

        });

        jfrm.getRootPane().setDefaultButton(enter);
        enter.requestFocus();

        jfrm.add(scrollPane);
        jfrm.add(textEntry);
        jfrm.add(enter);
        jfrm.setVisible(true);

    }

/*
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatGUI();
            }
        });
    }

*/
}
