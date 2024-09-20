package org.example.client.utility;

import org.example.client.commandCarrier.CommandStorage;
import org.example.client.commandCarrier.CommandValidator;
import org.example.client.commandCarrier.SendCommand;
import org.example.client.exceptions.InvalidInputException;
import org.example.client.exceptions.ScriptException;
import org.example.common.exceptions.InvalidCommandArgument;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;

import java.util.Scanner;

/**
 * Creates requests based on user commands.
 * This class handles the creation of various types of requests depending on the command and its arguments.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class RequestMaker {

    /**
     * Creates a request based on the given command.
     *
     * @param command The SendCommand object representing the user's command.
     * @param scanner The Scanner object used for user input.
     * @param scriptMode Indicates whether the application is running in script mode.
     * @return The created Request object.
     * @throws NullPointerException if the command is not found.
     */
    public Request createCommandRequest(SendCommand command, Scanner scanner, boolean scriptMode) throws NullPointerException {
        String name = command.getCommandName();
        Request request;
        if (CommandStorage.COMMANDS_WITHOUT_ARGS.contains(name)) {
            request = createRequestWithoutArgs(command);
        } else if (CommandStorage.COMMANDS_WITH_KEY_ARG.contains(name)) {
            request = createRequestWithKey(command);
        } else if (CommandStorage.COMMANDS_WITH_HUMANBEING_ARG.contains(name)) {
            request = createRequestWithHumanBeing(command, scanner, scriptMode);
        } else if (CommandStorage.COMMANDS_WITH_HUMANBEING_KEY_ARGS.contains(name)) {
                request = createRequestWithHumanBeingKey(command, scanner, scriptMode);
        } else if (CommandStorage.COMMANDS_WITH_HUMANBEING_ID_KEY_ARGS.contains(name)) {
            request = createRequestWithHumanBeingKeyID(command, scanner, scriptMode);
        } else if (CommandStorage.SCRIPT_ARGUMENT_COMMAND.contains(name)) {
            request = createRequestWithHumanBeingKeyID(command, scanner, scriptMode);
        } else {
            throw new NullPointerException("Команда не найдена. Напишите 'help' для просмотра всех доступных команд.");
        }
        return request;
    }

    /**
     * Creates a request without arguments.
     *
     * @param command The SendCommand object representing the command.
     * @return The created Request object.
     */
    private Request createRequestWithoutArgs(SendCommand command) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 0);
            return new Request(command.getCommandName());
        } catch (InvalidCommandArgument e) {
            PrintManager.printErr(e.getMessage());
            return null;
        }
    }

    /**
     * Creates a request with a 'key' argument.
     *
     * @param command The SendCommand object representing the command.
     * @return The created Request object.
     * @throws IllegalArgumentException if the input value cannot be parsed.
     */
    private Request createRequestWithKey(SendCommand command) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 1);
            Integer key = CommandValidator.validateArg(arg -> ((int) arg) > 0,
                    "Значение ключа должно быть целым числом!",
                    Integer::parseInt,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), key);
        } catch (InvalidCommandArgument | InvalidInputException | NullPointerException e) {
            PrintManager.printErr(e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            PrintManager.printErr("Введено некорректное значение аргумента.");
            return null;
        }
    }

    /**
     * Creates a request with a HumanBeing argument.
     *
     * @param command The SendCommand object representing the command.
     * @param scanner The Scanner object used for user input.
     * @param scriptMode Indicates whether the application is running in script mode.
     * @return The created Request object.
     */
    private Request createRequestWithHumanBeing(SendCommand command, Scanner scanner, boolean scriptMode) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 0);
            InputManager inputManager = new InputManager(scanner, scriptMode);
            return new Request(command.getCommandName(), inputManager.askHumanBeing());
        } catch (InvalidCommandArgument | ScriptException e) {
            PrintManager.printErr("Проверьте правильность данных в скрипте. Остановка приложения.");
            System.exit(1);
            return null;
        }
    }

    /**
     * Creates a request with a HumanBeing, ID and 'key' arguments.
     *
     * @param command The SendCommand object representing the command.
     * @param scanner The Scanner object used for user input.
     * @param scriptMode Indicates whether the application is running in script mode.
     * @return The created Request object.
     * @throws IllegalArgumentException if the input value cannot be parsed.
     */
    private Request createRequestWithHumanBeingKeyID(SendCommand command, Scanner scanner, boolean scriptMode) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 1);
            InputManager inputManager = new InputManager(scanner, scriptMode);
            int id = CommandValidator.validateArg(arg -> ((int) arg) > 0,
                    "Значение ID должно быть натуральным числом!",
                    Integer::parseInt,
                    command.getCommandArgs()[0]);
            Integer key = inputManager.askKey();
            return new Request(command.getCommandName(), id, key, inputManager.askHumanBeing());
        } catch (InvalidCommandArgument | IllegalArgumentException e) {
            PrintManager.printErr("Аргумент команды не удалось обработать. Введете корректную команду.");
            return null;
        } catch (InvalidInputException e) {
            PrintManager.printErr("Некорректный ввод. Введете корректную команду.");
            return null;
        } catch (ScriptException e) {
            PrintManager.printErr("Проверьте корректность данных скрипта. Работа приложения завершается.");
            return null;
        }
    }

    /**
     * Creates a request with a HumanBeing and 'key' arguments.
     *
     * @param command The SendCommand object representing the command.
     * @param scanner The Scanner object used for user input.
     * @param scriptMode Indicates whether the application is running in script mode.
     * @return The created Request object.
     * @throws IllegalArgumentException if the input value cannot be parsed.
     */
    private Request createRequestWithHumanBeingKey(SendCommand command, Scanner scanner, boolean scriptMode) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 0);
            InputManager inputManager = new InputManager(scanner, scriptMode);
            Integer key = inputManager.askKey();
            return new Request(command.getCommandName(), inputManager.askHumanBeing(), key);
        } catch (InvalidCommandArgument | IllegalArgumentException e) {
            PrintManager.printErr("Аргумент команды не удалось обработать. Введете корректную команду.");
            return null;
        } catch (ScriptException e) {
            PrintManager.printErr("Проверьте корректность данных скрипта. Работа приложения завершается.");
            return null;
        }
    }
}
