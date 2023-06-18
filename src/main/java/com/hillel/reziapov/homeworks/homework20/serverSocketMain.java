package com.hillel.reziapov.homeworks.homework20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class serverSocketMain {


    public static void main(String[] args) {

        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening port " + port);

            Socket socketClient = serverSocket.accept();
            System.out.println("Connected to client");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socketClient.getOutputStream(), true);

            String greeting = bufferedReader.readLine();
            System.out.println("Greeting from client: " + greeting);

            if (containsRussianLetters(greeting)) {
                printWriter.println("Що таке паляниця?");
                String answer = bufferedReader.readLine();
                if (answer.equalsIgnoreCase("хліб")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    String dateTime = LocalDateTime.now().format(formatter);
                    printWriter.println(dateTime);
                    System.out.println("Send Data and Time: " + dateTime);
                } else {
                    System.out.println("Wrong response from client");
                    socketClient.close();
                    return;
                }
                System.out.println("Sending farewell to the client.");
                printWriter.println("Goodbye!");
                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error when listen/accept connections or read input ");
            e.printStackTrace();
        }
    }

    private static boolean containsRussianLetters(String str) {
        return str.matches(".*[а-яА-Я].*");
    }
}
