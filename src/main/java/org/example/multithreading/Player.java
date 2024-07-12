package org.example.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*

Player class that creates a player
and implements runnable interface which allows player to run on separate threads
We also implement send and receive message methods that
sends and receives message until message counter max outs.
 */
public class Player implements Runnable {
    private final String name;
    private final BlockingQueue<String> myQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<String> otherQueue;
    private int messageCounter = 1;
    private static final int MAX_MESSAGES = 11;

    public Player(String name) {
        this.name = name;
    }

    public void setOtherQueue(BlockingQueue<String> otherQueue) {
        this.otherQueue = otherQueue;
    }

    // send message to other player queue until max limit is reached
    public void sendMessage(String content) {
        if (messageCounter <= MAX_MESSAGES) {
            System.out.println(name + " sent: " + content);
            otherQueue.add(content);
            messageCounter++;
        }
    }

    /* The run method continuously takes messages from its own queue and sends
     * replies to the other player's queue until the maximum message count is
     * reached.
     */
    @Override
    public void run() {
        try {
            while (messageCounter < MAX_MESSAGES) {
                String message = myQueue.take();

                if (messageCounter <= MAX_MESSAGES) {
                    sendMessage(message + " " + messageCounter);
                } else {
                    System.out.println(name + " stops after receiving " + MAX_MESSAGES + " messages.");
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public BlockingQueue<String> getMyQueue() {
        return myQueue;
    }
}
