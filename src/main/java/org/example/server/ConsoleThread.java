package org.example.server;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, представляющий поток консоли сервера.
 */
public class ConsoleThread extends Thread{

    /**
     * Сканнер, считывающий ввод пользователя.
     */
    private static final Scanner scanner = ServerApplication.scanner;

    /**
     * Состояние запущенного потока.
     */
    public volatile boolean running = true;

    /**
     * Запускает поток консоли сервера.
     */
    @Override
    public void run() {
        try {
            PrintManager.printInfoMessage("Консоль запущена.");
            while (running) {
                String line = scanner.nextLine();
                if ("save".equalsIgnoreCase(line.toLowerCase().trim())) {
                    ServerApplication.fileManager.writeCollection((LinkedHashMap<Integer, HumanBeing>) ServerApplication.collectionManager.getCollection());
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
