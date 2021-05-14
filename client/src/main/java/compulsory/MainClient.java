package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {
    private static final String serverAddress = "127.0.0.1";
    private static final int PORT = 8100;

    public static void main(String[] args) {
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ) {
            String request = standardInput.readLine();
            String response;
            while (!request.equals("quit")) {
                out.println(request);
                // Get the response from the server
                response = in.readLine();
                System.out.println(response);

                request = standardInput.readLine();
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

