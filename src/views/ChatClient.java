package views;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ChatClient extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextField messageField;
    private JTextArea messageArea;
    private String username;
    private String recepient;

    public ChatClient(String username, String recepient) {
        this.username = username;
        this.recepient = recepient;
        // Set up GUI
        messageField = new JTextField(40);
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(messageField.getText());
                messageField.setText("");
            }
        });
        add(messageField, BorderLayout.SOUTH);

        messageArea = new JTextArea(8, 40);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        // When scrollbar shows, always scroll down automatically to get the newest message
        DefaultCaret caret = (DefaultCaret) messageArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        add(scrollPane, BorderLayout.CENTER);

        setTitle("Chat with "+ recepient);
        setSize(500, 300);
        setVisible(true);

        // Connect to server
        try {
            socket = new Socket("localhost", 9999);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(username);
            out.println(recepient);

        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

        // Start a new thread to handle incoming messages
        new Thread(new IncomingReader()).start();
    }

    private class IncomingReader implements Runnable {
        public void run() {
            try {
                while (true) {
                    String message = in.readLine();
                    if (message == null) {
                        break;
                    }
                    messageArea.append(message + "\n");
                    messageArea.repaint();
                    messageArea.revalidate();
                }
            } catch (IOException e) {
                System.err.println("Error: " + e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChatClient client1 = new ChatClient("1", "2");
        Thread.sleep(1000);
        ChatClient client2 = new ChatClient("2", "1");
        Thread.sleep(1000);

        ChatClient client23 = new ChatClient("2d", "1s");
        Thread.sleep(1000);


    }
}
