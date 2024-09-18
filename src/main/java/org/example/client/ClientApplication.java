package org.example.client;

import org.example.client.commandCarrier.CommandReader;
import org.example.client.commandCarrier.CommandValidator;
import org.example.client.commandCarrier.SendCommand;
import org.example.client.utility.ClientSocketChannelIOManager;
import org.example.client.utility.RequestMaker;
import org.example.client.utility.ScriptManager;
import org.example.common.exceptions.InvalidCommandArgument;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс для установки соединения с сервером.
 */
public class ClientApplication {

    /**
     * Сканнер для чтения команд из скрипта.
     */
    private static Scanner scriptScanner;

    /**
     * Создатель запросов для отправки на сервер.
     */
    private static final RequestMaker requestMaker = new RequestMaker();

    /**
     * Запускает бесконечный цикл селектора.
     * @param selector объект типа Selector для мониторинга состояний канала
     * @param channel объект типа SocketChannel - канал для передачи данных по сети
     * @param scanner объект типа Scanner для чтения ввода пользователя с консоли
     * @param scriptMode флаг режима работы скрипта
     * @throws IOException если произошла ошибка ввода/вывода при работе с каналами или сокетами
     * @throws ClassNotFoundException если класс не найден при десериализации
     * @throws InterruptedException если произошла ошибка в работе потоков
     */
    static void startSelectorLoop(Selector selector, SocketChannel channel, Scanner scanner, boolean scriptMode) throws IOException, ClassNotFoundException, InterruptedException {
        do {
            selector.select();
        } while (startIteratorLoop(selector, channel, scanner, scriptMode));
    }

    /**
     * Запускает итерационный цикл обработки готовых ключей селектора.
     * @param selector объект типа Selector для мониторинга состояний канала
     * @param channel объект типа SocketChannel - канал для передачи данных по сети
     * @param scanner объект типа Scanner для чтения ввода пользователя с консоли
     * @param scriptMode флаг режима работы скрипта
     * @return true, если нужно продолжать работу цикла, false - если необходимо остановиться
     * @throws IOException если произошла ошибка ввода/вывода при работе с каналами или сокетами
     * @throws ClassNotFoundException если класс не найден при десериализации
     * @throws InterruptedException если произошла ошибка в работе потоков
     */
    private static boolean startIteratorLoop(Selector selector, SocketChannel channel, Scanner scanner, boolean scriptMode) throws IOException, ClassNotFoundException, InterruptedException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();
                ClientSocketChannelIOManager socketChannelIOManager = new ClientSocketChannelIOManager(clientChannel);
                Response response = (Response) socketChannelIOManager.receive();
                PrintManager.printInfoMessage(response.getData());
                clientChannel.register(selector, SelectionKey.OP_WRITE);
            } else if (key.isWritable()) {
                try {
                    SendCommand sendCommand = CommandReader.readCommand(scanner, scriptMode);
                    if (sendCommand == null) return false;
                    if (sendCommand.getCommandName().equalsIgnoreCase("execute_script")) {
                        CommandValidator.validateAmountOfArgs(sendCommand.getCommandArgs(), 1);
                        ScriptManager scriptManager = new ScriptManager(sendCommand);
                        scriptScanner = new Scanner(scriptManager.getPath());
                        SocketChannel client = (SocketChannel) key.channel();
                        ClientSocketChannelIOManager socketChannelIOManager = new ClientSocketChannelIOManager(client);
                        Request request = new Request("execute_script");
                        socketChannelIOManager.send(request);
                        client.register(selector, SelectionKey.OP_READ);
                        startSelectorLoop(selector, channel, scriptScanner, true);
                        scriptManager.stopScriptReading();
                        startSelectorLoop(selector, channel, scanner, false);
                    }
                    Request request = requestMaker.createCommandRequest(sendCommand, scanner, scriptMode);
                    SocketChannel client = (SocketChannel) key.channel();
                    ClientSocketChannelIOManager socketChannelIOManager = new ClientSocketChannelIOManager(client);
                    if (sendCommand.getCommandName().equalsIgnoreCase("exit") && sendCommand.getCommandArgs().length == 0) {
                        try {
                            socketChannelIOManager.send(request);
                            PrintManager.printInfoMessage("Прекращение работы.");
                            System.exit(0);
                        } catch (Exception e) {
                            PrintManager.printErr("Сервер недоступен. Команда не будет зарегистрирована на сервере.");
                            PrintManager.printInfoMessage("Прекращение работы.");
                            System.exit(0);
                        }
                    } else {
                        if (request == null) throw new NullPointerException("");
                        socketChannelIOManager.send(request);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                } catch (NullPointerException | IllegalArgumentException | InvalidCommandArgument e) {
                    if (!e.getMessage().isEmpty()) {
                        PrintManager.printErr(e.getMessage());
                    } else {
                        PrintManager.printInfoMessage("Произошла некая ошибка при вводе команды.");
                    }
                }
            }
        }
        return true;
    }
}