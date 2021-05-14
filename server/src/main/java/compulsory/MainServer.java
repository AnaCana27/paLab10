package compulsory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    private static final int PORT = 8100;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("[WAITING FOR CLIENT...]");
                Socket socket = serverSocket.accept();
                ClientRunnable clientRunnable = new ClientRunnable(socket);
                new Thread(clientRunnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
