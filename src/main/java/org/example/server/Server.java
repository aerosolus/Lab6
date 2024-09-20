package org.example.server;

import org.example.common.utility.PrintManager;
import org.example.server.utility.FileManager;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class responsible for launching the server application.
 * Contains the main method that initializes and starts the server.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class Server {

    /**
     * Name of the file storing the collection.
     */
    static String fileName;

    /**
     * ServerSocket object used for listening to incoming client connections.
     */
    private static ServerSocket serverSocket;

    /**
     * Entry point of the server application.
     * Initializes the server, sets up the port, reads the initial collection
     * and starts the server and console threads.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            fileName = FileManager.getFileName();
            inputPort();
            ServerApplication.fileManager.readCollection();
            ConsoleThread consoleThread = new ConsoleThread();
            consoleThread.start();
            ServerApplication.startServer(args, serverSocket);
        } catch (IOException e) {
            PrintManager.printErr(e.getMessage());
        }
    }

    /**
     * Prompts the user for port input and sets up the server socket accordingly.
     * This method handles both default port selection and custom port entry.
     *
     * @throws IllegalArgumentException if the entered port is invalid.
     */
    private static void inputPort() {
        Scanner scanner = ServerApplication.scanner;
        PrintManager.printInfoMessage("Вы хотите использовать порт по умолчанию? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                PrintManager.printInfoMessage("Введите порт (1-65535):");
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= 65535) {
                        ServerApplication.PORT = portInt;
                        serverSocket = new ServerSocket(portInt);
                    } else {
                        PrintManager.printErr("Число не входит в установленные пределы, повторите ввод.");
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    PrintManager.printErr("Ошибка при обработке номера, повторите ввод.");
                    inputPort();
                }
            } else if ("y".equals(s)) {
                serverSocket = new ServerSocket(ServerApplication.PORT);
            } else {
                PrintManager.printErr("Вы ввели недопустимый символ, попробуйте еще раз.");
                inputPort();
            }
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            PrintManager.printErr("Ошибка при обработке номера, повторите ввод.");
            inputPort();
        } catch (BindException e) {
            PrintManager.printErr("Этот порт недоступен. Введите другой.");
            inputPort();
        } catch (IOException e) {
            PrintManager.printErr("Возникли проблемы с IO.");
            inputPort();
        }
    }
}
