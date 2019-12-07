import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class ChatGUI {

    private JFrame jfrm;
    private static JTextField textEntry;
    private static JTextArea window;
    private static JButton enter;
    private static JScrollPane scrollPane;

    ChatGUI(){

        jfrm = new JFrame();
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(500,500);
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setLocationRelativeTo(null);

        jfrm.setTitle("Network Messenger");

        window = new JTextArea();
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
            window.append(text +"\n");

            //enter text to send

        });

        jfrm.getRootPane().setDefaultButton(enter);
        enter.requestFocus();

        jfrm.add(scrollPane);
        jfrm.add(textEntry);
        jfrm.add(enter);
        jfrm.setVisible(true);

    }

    public static void main (String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatGUI();
            }
        });
    }

}
