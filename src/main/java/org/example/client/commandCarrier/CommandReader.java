package org.example.client.commandCarrier;

import org.example.client.utility.InputManager;
import org.example.common.utility.PrintManager;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для чтения команд из консоли или файла скрипта и создания объекта класса SendCommand с информацией о команде.
 */
public class CommandReader {

    /**
     * Считывает команду из консоли или файла скрипта, создает объект SendCommand с информацией о команде.
     *
     * @param scanner сканнер для чтения из консоли или файла скрипта
     * @param scriptMode режим считывания команд, в котором находится приложение
     * @return объект SendCommand, содержащий имя команды и ее аргументы
     */
    public static SendCommand readCommand(Scanner scanner, boolean scriptMode) {
        try {
            if (!scriptMode) System.out.print(InputManager.INPUT_COMMAND);
            String[] splitInput = scanner.nextLine().trim().split("\\s+");
            if (splitInput.length == 0 || splitInput[0].equals("")) {
                return readCommand(scanner, scriptMode);
            }
            if (splitInput[0].equals("EOF")) return null;
            String commandName = splitInput[0].toLowerCase(Locale.ROOT);
            String[] commandsArgs = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            if (scriptMode) {
                String arguments = String.join(" ", commandsArgs);
                PrintManager.printInfoMessage("$ " + commandName + " " + arguments);
            }
            return new SendCommand(commandName, commandsArgs);

        } catch (NoSuchElementException e) {
            if (!scriptMode) {
                PrintManager.printErr("Принудительное завершение работы.");
                System.exit(1);
            }
            return null;
        }
    }
}
