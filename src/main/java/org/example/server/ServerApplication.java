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
 * Класс для установки соединения с клиентами.
 */
public class ServerApplication {
    /**
     * Сканер для чтения ввода.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Файловый менеджер.
     */
    public static final FileManager fileManager = new FileManager(Server.fileName);

    /**
     * Менеджер коллекции.
     */
    public static CollectionManager collectionManager = new CollectionManager();

    /**
     * Состояние сервера.
     */
    public static boolean running = true;

    /**
     * Командный менеджер, содержащий список команд для обработки запросов клиентов.
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
     * Стандартный порт.
     */
    protected static int PORT = 52052;

    /**
     * Метод для запуска сервера.
     * @param args аргументы командной строки.
     * @param serverSocket объект ServerSocket, прослушивающий порт.
     * @throws IOException если возникли проблемы с вводом-выводом.
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
     * Метод, который запускает бесконечный цикл обработки запросов клиента.
     * @param socket сокет для подключения клиента.
     * @param serverSocket серверный сокет для принятия клиентских подключений.
     * @throws IOException если возникает ошибка ввода/вывода при работе с сокетами.
     * @throws ClassNotFoundException если класс не был найден при чтении объекта из потока.
     * @throws InterruptedException если поток был прерван во время ожидания ответа от клиента.
     */
    private static void startSelectorLoop(Socket socket, ServerSocket serverSocket) throws IOException, ClassNotFoundException, InterruptedException {
        while (socket.isConnected()) {
            startIteratorLoop(socket, serverSocket);
        }
    }

    /**
     * Метод, который обрабатывает запросы клиента.
     * @param socket сокет для подключения клиента.
     * @param serverSocket серверный сокет для принятия клиентских подключений.
     * @throws IOException если возникает ошибка ввода/вывода при работе с сокетами.
     * @throws ClassNotFoundException если класс не был найден при чтении объекта из потока.
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
