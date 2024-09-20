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
 * Represents the main application for establishing and managing connections with a server.
 * This class handles the core functionality of the client, including reading commands, sending requests,
 * and processing server responses.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ClientApplication {

    /**
     * Scanner for reading commands from a script.
     */
    private static Scanner scriptScanner;

    /**
     * Creator for generating requests to be sent to the server.
     */
    private static final RequestMaker requestMaker = new RequestMaker();

    /**
     * Starts an infinite loop for the selector.
     *
     * @param selector The Selector object for monitoring channel states.
     * @param channel The SocketChannel object for network data transmission.
     * @param scanner The Scanner object for reading user console input.
     * @param scriptMode Flag indicating whether the application is running in script mode.
     * @throws IOException if an I/O error occurs when working with channels or sockets.
     * @throws ClassNotFoundException if a class is not found during deserialization.
     * @throws InterruptedException if a thread interruption occurs.
     */
    static void startSelectorLoop(Selector selector, SocketChannel channel, Scanner scanner, boolean scriptMode) throws IOException, ClassNotFoundException, InterruptedException {
        do {
            selector.select();
        } while (startIteratorLoop(selector, channel, scanner, scriptMode));
    }

    /**
     * Starts an iterative loop for processing ready keys from the selector.
     *
     * @param selector The Selector object for monitoring channel states.
     * @param channel The SocketChannel object for network data transmission.
     * @param scanner The Scanner object for reading user console input.
     * @param scriptMode Flag indicating whether the application is running in script mode.
     * @return true if the loop should continue, false if it should stop.
     * @throws IOException if an I/O error occurs when working with channels or sockets.
     * @throws ClassNotFoundException if a class is not found during deserialization.
     * @throws InterruptedException if a thread interruption occurs.
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
                    }
                }
            }
        }
        return true;
    }
}