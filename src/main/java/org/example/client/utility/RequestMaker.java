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
 * Класс для создания запроса по команде, полученной от пользователя.
 */
public class RequestMaker {

    /**
     * Создает запрос по переданной команде.
     * @param command команда
     * @param scanner сканер
     * @param scriptMode режим скрипта
     * @return запрос
     * @throws NullPointerException если команда не найдена
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
     * Создает запрос без аргументов.
     * @param command команда
     * @return запрос
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
     * Создает запрос с аргументом key.
     * @param command команда
     * @return запрос
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
     * Класс для создания запроса с аргументом типа HumanBeing.
     *
     * @param command команда
     * @param scanner сканер
     * @param scriptMode режим скрипта
     * @return запрос
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
     * Класс для создания запроса с аргументом типа HumanBeing и ID.
     * @param command команда
     * @param scanner сканер
     * @param scriptMode режим скрипта
     * @return запрос
     */
//    private Request createRequestWithHumanBeingID(SendCommand command, Scanner scanner, boolean scriptMode) {
//        try {
//            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 1);
//            InputManager inputManager = new InputManager(scanner, scriptMode);
//            int id = CommandValidator.validateArg(arg -> ((int) arg) > 0,
//                    "Значение ID должно быть натуральным числом!",
//                    Integer::parseInt,
//                    command.getCommandArgs()[0]);
//            return new Request(command.getCommandName(), id, inputManager.askHumanBeing());
//        } catch (InvalidCommandArgument | InvalidInputException | IllegalArgumentException | ScriptException e) {
//            PrintManager.printErr("Проверьте корректность данных скрипта. Работа приложения завершается.");
//            System.exit(1);
//            return null;
//        }
//    }

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
            //System.exit(1);
            return null;
        } catch (InvalidInputException e) {
            PrintManager.printErr("Некорректный ввод. Введете корректную команду.");
            //System.exit(1);
            return null;
        } catch (ScriptException e) {
            PrintManager.printErr("Проверьте корректность данных скрипта. Работа приложения завершается.");
            //System.exit(1);
            return null;
        }
    }

    private Request createRequestWithHumanBeingKey(SendCommand command, Scanner scanner, boolean scriptMode) {
        try {
            CommandValidator.validateAmountOfArgs(command.getCommandArgs(), 0);
            InputManager inputManager = new InputManager(scanner, scriptMode);
            Integer key = inputManager.askKey();
            return new Request(command.getCommandName(), inputManager.askHumanBeing(), key);
        } catch (InvalidCommandArgument | IllegalArgumentException e) {
            PrintManager.printErr("Аргумент команды не удалось обработать. Введете корректную команду.");
            //System.exit(1);
            return null;
        } catch (ScriptException e) {
            PrintManager.printErr("Проверьте корректность данных скрипта. Работа приложения завершается.");
            //System.exit(1);
            return null;
        }
    }
}
