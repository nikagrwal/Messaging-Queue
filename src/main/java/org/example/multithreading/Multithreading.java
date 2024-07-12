package org.example.multithreading;
/*
 * This class initiates two players and set there
 * and initializes message queues for both these players.
 * We then set the threads for this player and start the thread
 * We then start the communication by sending an initial message to the other player.
 * This code demonstrates a producer-consumer problem in JAVA

 */
public class Multithreading {

    public static void main(String[] args) {
        // Create two Players
        Player player1 = new Player("Initiator");
        Player player2 = new Player("Receiver");

        //Setting up messagequeue for each player
        player1.setOtherQueue(player2.getMyQueue());
        player2.setOtherQueue(player1.getMyQueue());

        //Creating threads for both players
        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);

        //Starting the thread
        thread1.start();
        thread2.start();

        //Player1 sends the first message
        player1.sendMessage("Hi");
    }
}
