package org.example.server;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a console thread for server operations.
 * This thread handles user input and executes commands related to saving and exiting the server application.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ConsoleThread extends Thread{

    /**
     * Scanner instance for reading user input.
     * Shared across all instances of ConsoleThread.
     */
    private static final Scanner scanner = ServerApplication.scanner;

    /**
     * Flag indicating whether the thread is currently running.
     */
    public volatile boolean running = true;

    /**
     * Starts the console thread, initiating continuous monitoring of user input.
     *
     * @throws NoSuchElementException if no element exists in the scanner buffer.
     */
    @Override
    public void run() {
        try {
            PrintManager.printInfoMessage("Консоль запущена.");
            while (running) {
                String line = scanner.nextLine();
                if ("save".equalsIgnoreCase(line.toLowerCase().trim())) {
                    ServerApplication.fileManager.writeCollection(ServerApplication.collectionManager.getCollection());
                } else if ("exit".equalsIgnoreCase(line.toLowerCase().trim())) {
                    PrintManager.printInfoMessage("Работа сервера завершена.");
                    System.exit(0);
                } else {
                    PrintManager.printErr("Такой команды не существует. Напишите 'save' или 'exit'.");
                }
            }
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        }
    }
}
