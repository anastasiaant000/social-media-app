
import chat.ClientHandler;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 9999;
    private static final int NTHREADS = 100;
    private static Executor executor = Executors.newFixedThreadPool(NTHREADS);
    private static ServerSocket server;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Chat server started on port " + PORT);
            while (true) {
                Socket client = server.accept();
                executor.execute(new ClientHandler(client));
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
