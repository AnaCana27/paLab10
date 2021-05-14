package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {
    private Socket socket = null;

    public ClientRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String request, response;
            while ((request = in.readLine()) != null) {
                if (request.equals("stop"))
                    System.exit(0);
                out.println("Server received the request ..." + request);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
