package org.example.multiprocessing;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * The Receiver class listens for incoming connections on a specified port,
 * accepts the connection, and then continuously reads messages from the
 * connected client, appends a counter to each message, and sends the modified
 * message back to the client.
 *
 *It is an example of Server-Client socket communication.
 */
 public class Receiver {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            System.out.println("Waiting for connection");
            Socket receiverSocket = serverSocket.accept();
            System.out.println("Connection accepted");

            InputStream inputStream = receiverSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = receiverSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            int messageCounter = 0;

            while (messageCounter < 10) {
                String message = reader.readLine();
                messageCounter++;
                String concatenatedMessage = message + " " + messageCounter;
                System.out.println("Receiver sent: " + concatenatedMessage);
                writer.println(concatenatedMessage);
            }

            receiverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
