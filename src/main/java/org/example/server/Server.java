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
 * Главный класс, запускающий приложение сервера.
 * Содержит метод main, отвечающий за запуск приложения.
 */
public class Server {

    /**
     * Имя файла, в котором хранится коллекция.
     */
    static String fileName;

    /**
     * Объект, который используется для прослушивания входящих клиентских подключений на сервере.
     */
    private static ServerSocket serverSocket;

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
     * Метод inputPort запрашивает данные для создания соединения.
     */
    private static void inputPort() {
        Scanner scanner = ServerApplication.scanner;
        PrintManager.printInfoMessage("Вы хотите использовать порт по умолчанию? [y/n]");
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".toLowerCase().trim().equals(s)) {
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
            } else if ("y".toLowerCase().trim().equals(s)) {
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
