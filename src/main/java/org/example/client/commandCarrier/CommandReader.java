package org.example.client.commandCarrier;

import org.example.client.utility.InputManager;
import org.example.common.utility.PrintManager;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class is responsible for reading commands from either console input or script file input,
 * and creating a SendCommand object with the command information.
 *
 * <p>The class provides a static method {@link #readCommand(Scanner, boolean)} that handles the command reading process,
 * including parsing the input, validating the command, and returning a SendCommand object.</p>
 *
 * <p>This class plays a crucial role in the command-line interface of the application,
 * facilitating the interaction between users and the system.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class CommandReader {

    /**
     * Reads a command from either console input or script file input, creates a SendCommand object with the command information.
     *
     * <p>This method handles the entire command reading process, including:</p>
     * <ul>
     *   <li>Printing the command prompt message (if not in script mode)</li>
     *   <li>Parsing the input string into command name and arguments</li>
     *   <li>Validating the command input</li>
     *   <li>Creating and returning a SendCommand object</li>
     * </ul>
     *
     * @param scanner Scanner object for reading from console or script file
     * @param scriptMode boolean indicating whether the application is running in script mode
     * @return SendCommand object containing the command name and its arguments, or null if EOF is encountered
     * @throws NoSuchElementException if the input is invalid and cannot be parsed
     */
    public static SendCommand readCommand(Scanner scanner, boolean scriptMode) {
        try {
            if (!scriptMode) System.out.print(InputManager.INPUT_COMMAND);
            String[] splitInput = scanner.nextLine().trim().split("\\s+");
            if (splitInput.length == 0 || splitInput[0].isEmpty()) {
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
