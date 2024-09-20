package org.example.server;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.commands.*;
import org.example.server.exceptions.DisconnectException;
import org.example.server.utility.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages server operations, including connection setup, command execution, and client communication.
 * This class serves as the main entry point for the server application,
 * handling incoming connections and delegating tasks to appropriate components.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ServerApplication {

    /**
     * Scanner instance for reading console input.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * File manager for handling file operations.
     */
    public static final FileManager fileManager = new FileManager(Server.fileName);

    /**
     * Collection manager for managing the collection of HumanBeing objects.
     */
    public static CollectionManager collectionManager = new CollectionManager();

    /**
     * Flag indicating whether the server is running.
     */
    public static boolean running = true;

    /**
     * Command manager containing a list of commands for processing client requests.
     */
    public static CommandManager commandManager = new CommandManager(
            new HelpCommand(),
            new InfoCommand(collectionManager),
            new ShowCommand(collectionManager),
            new InsertCommand(collectionManager),
            new RemoveKeyCommand(collectionManager),
            new ClearCommand(collectionManager),
            new ExecuteScriptCommand(),
            new ExitCommand(),
            new RemoveLowerCommand(collectionManager),
            new RemoveGreaterKeyCommand(collectionManager),
            new RemoveLowerKeyCommand(collectionManager),
            new PrintAscendingCommand(collectionManager),
            new PrintDescendingCommand(collectionManager),
            new UpdateCommand(collectionManager),
            new PrintFieldDescendingCarCommand(collectionManager)
    );

    /**
     * Standard port number for server communication.
     */
    protected static int PORT = 52052;

    /**
     * Starts the server, initiating continuous listening for client connections.
     *
     * @param args Command-line arguments.
     * @param serverSocket ServerSocket object listening on the specified port.
     * @throws IOException if input/output problems occur.
     */
    static void startServer(String[] args, ServerSocket serverSocket) throws IOException {
        try {
            PrintManager.printInfoMessage("Сервер запущен.");
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (running) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> {
                    try {
                        startSelectorLoop(clientSocket, serverSocket);
                    } catch (SocketException e) {
                        PrintManager.printErr("Connection reset");
                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        PrintManager.printErr(e.getMessage());
                    }
                });
            }
        } catch (IOException e) {
            PrintManager.printErr("Не удается принять клиентское соединение: " + e.getMessage());
        }
    }

    /**
     * Initiates a loop for processing client requests continuously.
     *
     * @param socket Client socket for connection.
     * @param serverSocket Server socket for accepting client connections.
     * @throws IOException if input/output problems occur with sockets.
     * @throws ClassNotFoundException if the class of an object read from the stream is not found.
     * @throws InterruptedException if the thread is interrupted while waiting for client response.
     */
    private static void startSelectorLoop(Socket socket, ServerSocket serverSocket) throws IOException, ClassNotFoundException, InterruptedException {
        while (socket.isConnected()) {
            startIteratorLoop(socket, serverSocket);
        }
    }

    /**
     * Processes incoming client requests.
     *
     * @param socket Client socket for connection.
     * @param serverSocket Server socket for accepting client connections.
     * @throws IOException if input/output problems occur with sockets.
     * @throws ClassNotFoundException if the class of an object read from the stream is not found.
     */
    private static void startIteratorLoop(Socket socket, ServerSocket serverSocket) throws IOException, ClassNotFoundException {
        boolean isClientDisconnected = false;
        try {
            ServerSocketIOManager socketIOManager = new ServerSocketIOManager(socket);
            Request request = (Request) socketIOManager.receive();
            Command command = ServerApplication.commandManager.initCommand(request);
            if (request.getCommandName().equals("exit")) {
                PrintManager.printInfoMessage("Отключение клиента.");
                try {
                    socket.close();
                } catch (IOException e) {
                    PrintManager.printErr("Ошибка при закрытии сокета: " + e.getMessage());
                }
            } else {
                Response response = RequsetCreator.build(command, request);
                socketIOManager.send(response);
                PrintManager.printInfoMessage("Сервер отправил ответ клиенту.");
            }
        } catch (DisconnectException e) {
            socket.close();
            serverSocket.close();
            PrintManager.printErr(e.getMessage());
            PrintManager.printInfoMessage("Работа сервера завершена.");
            System.exit(1);
        } catch (IOException e) {
            if (e.getMessage().equals("Connection reset")) {
                isClientDisconnected = true;
                PrintManager.printInfoMessage("Клиент внезапно отключился."); }
        } finally {
            if (isClientDisconnected) {
                try {
                    socket.close();
                } catch (IOException e) {
                    PrintManager.printInfoMessage("Ошибка при закрытии сокета: " + e.getMessage());
                }
            }
        }
    }
}
