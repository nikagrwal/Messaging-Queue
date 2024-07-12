package org.example.multiprocessing;

import java.io.*;
import java.net.Socket;

/*
 * The Sender class connects to a server running on localhost at port 5050,
 * sends messages to the server with an appended counter, and prints the
 * server's responses.
 */
public class Sender {


    public static void main(String[] args) {

        try {
            Socket senderSocket = new Socket("localhost", 5050);
            OutputStream outputStream = senderSocket.getOutputStream();
            InputStream inputStream = senderSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            String message = "Hi";
            int messageCounter = 0;

            while (messageCounter < 10) {
                messageCounter++;
                message = message + " " + messageCounter;
                writer.println(message );
                System.out.println("Initiator sent: " + message);

                // Receive response from receiver
                message = reader.readLine();

                // Sleep for a short time to allow receiver to process
                Thread.sleep(100);
            }

            senderSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
