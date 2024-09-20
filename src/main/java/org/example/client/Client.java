package org.example.client;

import org.example.client.utility.ScriptManager;
import org.example.common.utility.PrintManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class responsible for launching the client application.
 * Contains the main method that initializes and runs the client connection.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class Client {

    /**
     * Default port number for client communication.
     */
    private static int PORT = 52052;

    /**
     * Host address to which the client attempts to connect.
     */
    private static String HOST;

    /**
     * Maximum possible port number.
     */
    private static final int maxPort = 65535;

    /**
     * Scanner for reading console input.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Selector used for tracking events in the socket.
     */
    private static Selector selector;

    /**
     * Flag indicating reconnection mode.
     */
    private static boolean reconnectionMode = false;

    /**
     * Number of failed connection attempts to the server.
     */
    private static int attempts = 0;

    /**
     * Main entry point for the client application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            if (!reconnectionMode) {
                inputPort(); // Prompt user for host and port details
            } else {
                Thread.sleep(5000); // Wait for 5 seconds before attempting reconnection
            }

            // Establish connection with the server
            SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            PrintManager.printInfoMessage("Клиент подключен.");
            attempts = 0;
            clientChannel.configureBlocking(false);
            selector = Selector.open();
            clientChannel.register(selector, SelectionKey.OP_WRITE);

            // Start the selector loop
            ClientApplication.startSelectorLoop(selector, clientChannel, SCANNER, false);
        } catch (ClassNotFoundException e) {
            PrintManager.printErr("Попытка сериализовать несериализуемый объект.");
        } catch (InterruptedException e) {
            PrintManager.printErr("Соединение было прервано во время бездействия. Перезапуск клиента.");
        } catch (UnresolvedAddressException e) {
            PrintManager.printErr("Сервер с этим хостом не найден. Попробуйте снова.");
            main(args); // Restart the connection attempt
        } catch (IOException e) {
            PrintManager.printErr("Сервер недоступен. Переподключение - попытка номер " + (attempts + 1));
            reconnectionMode = true;
            if (attempts == 5) {
                PrintManager.printErr("Переподключение не удалось. Попробуйте подключиться позднее.");
                System.exit(0);
            }
            attempts++;
            ScriptManager.callStack.clear();
            main(args); // Attempt reconnection
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        }
    }

    /**
     * Prompts the user for connection details.
     */
    private static void inputPort() {
        PrintManager.printInfoMessage("Введите имя хоста:");
        try {
            HOST = SCANNER.nextLine();
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        }
        PrintManager.printInfoMessage("Вы хотите использовать порт по умолчанию? [y/n]");
        try {
            String s = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                PrintManager.printInfoMessage("Введите порт удаленного хоста (1-65535):");
                String port = SCANNER.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        PORT = portInt;
                    } else {
                        PrintManager.printErr("Число не входит в установленные пределы, повторите ввод.");
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    PrintManager.printErr("Ошибка при обработке номера, повторите ввод.");
                    inputPort();
                }
            } else if (!"y".equals(s)) {
                PrintManager.printErr("Вы ввели недопустимый символ, попробуйте еще раз.");
                inputPort();
            }
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        }
    }
}
