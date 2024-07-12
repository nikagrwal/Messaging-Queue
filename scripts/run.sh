#!/bin/bash

# Prompt the user to input either 1 or 2
echo "Please enter 1 for running both the players in same process or 2 for running the players in different process:"
read user_input

# Check the user input and run the corresponding Java files
if [ "$user_input" -eq 2 ]; then
    # Compile and run receiver.java and sender.java
    javac src/main/java/org/example/multiprocessing/Receiver.java src/main/java/org/example/multiprocessing/Sender.java
    if [ $? -eq 0 ]; then
        java -cp src/main/java org.example.multiprocessing.Receiver &
        java -cp src/main/java org.example.multiprocessing.Sender
    else
        echo "Compilation failed for multiprocessing files."
    fi
elif [ "$user_input" -eq 1 ]; then
    # Compile and run multithreading.java and player.java
    javac src/main/java/org/example/multithreading/Multithreading.java src/main/java/org/example/multithreading/Player.java
    if [ $? -eq 0 ]; then
        java -cp src/main/java org.example.multithreading.Multithreading

    else
        echo "Compilation failed for multithreading files."
    fi
else
    echo "Invalid input. Please enter 1 or 2."
fi
