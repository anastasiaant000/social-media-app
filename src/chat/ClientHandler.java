package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private static ConcurrentMap<List, ClientHandler> clients = new ConcurrentHashMap<>();
    private List<String> pair;
    private String sender;
    private String recipient;

    public ClientHandler(Socket client) {
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            // read sender and recipient
            this.sender = in.readLine();
            this.recipient = in.readLine();
            pair = new ArrayList<>(2);
            pair.add(sender);
            pair.add(recipient);
            clients.put(pair, this);

        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                System.out.println(sender + " for " + recipient + " : " + message);
                broadcastMessage(message, sender, recipient);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        } finally {
            if (client != null) {
                try {
                    clients.remove(sender);
                    client.close();
                } catch (IOException e) {
                    
                }
            }
        }
    }

    private void broadcastMessage(String message, String sender, String recipient) {
        for (ClientHandler c : clients.values()) {

            if (c.pair.get(0).equals(this.sender)) {
                c.out.println(this.sender + ": " + message);
            }
            if (c.pair.get(0).equals(this.recipient)) {
                c.out.println(sender + ": " + message);
            }

        }
    }
}
