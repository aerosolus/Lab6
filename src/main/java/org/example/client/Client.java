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
import java.util.PrimitiveIterator;
import java.util.Scanner;

/**
 * Главный класс, запускающий приложение клиента.
 * Содержит метод main, отвечающий за запуск приложения.
 */
public class Client {

    /**
     * Порт по умолчанию
     */
    private static int PORT = 52052;

    /**
     * Хост, к которому пытается подключиться клиент.
     */
    private static String HOST;

    /**
     * Максимально возможный номер порта.
     */
    private static final int maxPort = 65535;

    /**
     * Сканнер для считывания ввода с консоли.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Селектор, используемый для отслеживания событий в сокете.
     */
    private static Selector selector;

    /**
     * Режим переподключения, установленный в режим ожидания.
     */
    private static boolean reconnectionMode = false;

    /**
     * Количество неудачных попыток подключения к серверу.
     */
    private static int attempts = 0;

    public static void main(String[] args) {
        try {
            if (!reconnectionMode) {
                inputPort();
            } else {
                Thread.sleep(5 * 1000); // 5 секунд на переподключение
            }
            SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            PrintManager.printInfoMessage("Клиент подключен.");
            attempts = 0;
            clientChannel.configureBlocking(false);
            selector = Selector.open();
            clientChannel.register(selector, SelectionKey.OP_WRITE);
            ClientApplication.startSelectorLoop(selector, clientChannel, SCANNER, false);
        } catch (ClassNotFoundException e) {
            PrintManager.printErr("Попытка сериализовать несериализуемый объект.");
        } catch (InterruptedException e) {
            PrintManager.printErr("Соединение было прервано во время бездействия. Перезапуск клиента.");
        } catch (UnresolvedAddressException e) {
            PrintManager.printErr("Сервер с этим хостом не найден. Попробуйте снова.");
            main(args);
        } catch (IOException e) {
            PrintManager.printErr("Сервер недоступен. Переподключение - попытка номер " + (attempts + 1));
            reconnectionMode = true;
            if (attempts == 5) {
                PrintManager.printErr("Переподключение не удалось. Попробуйте подключиться позднее.");
                System.exit(0);
            }
            attempts++;
            ScriptManager.callStack.clear();
            main(args);
        } catch (NoSuchElementException e) {
            PrintManager.printErr("Принудительное завершение работы.");
            System.exit(1);
        }
    }

    /**
     * Метод inputPort запрашивает у пользователя данные для создания соединения.
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
