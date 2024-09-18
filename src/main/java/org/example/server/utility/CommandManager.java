package org.example.server.utility;

import org.example.common.utility.Request;
import org.example.server.commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд.
 */
public class CommandManager {

    /**
     * Список доступных команд.
     * Ключ - название команды, значение - объект команды.
     */
    public static final Map<String, Command> AVAILABLE_COMMANDS = new HashMap<>();

//    /**
//     * Создает новый объект менеджера команд.
//     * @param insertCommand - команда добавления элемента в коллекцию
//     * @param clearCommand - команда очистки коллекции
//     * @param executeScriptCommand - команда исполнения скрипта
//     * @param exitCommand - команда выхода из приложения
//     * @param helpCommand - команда вывода справки по доступным командам
//     * @param infoCommand - команда вывода информации о коллекции
//     * @param printDescendingCommand - команда вывода элементов коллекции в порядке убывания
//     * @param printFieldDescendingAnnualTurnoverCommand - команда вывода значения поля annualTurnover элементов коллекции в порядке убывания
//     * @param printUniqueEmployeesCountCommand - команда вывода количества уникальных значений поля employeesCount элементов коллекции
//     * @param removeByIdCommand - команда удаления элемента коллекции по заданному id
//     * @param removeFirstCommand - команда удаления первого элемента коллекции
//     * @param showCommand - команда вывода всех элементов коллекции
//     * @param shuffleCommand - команда перемешивания элементов коллекции
//     * @param sortCommand - команда сортировки элементов коллекции в естественном порядке
//     * @param updateByIdCommand - команда обновления значения элемента коллекции по заданному id
//     */
    public CommandManager(Command helpCommand,
                          Command infoCommand,
                          Command showCommand,
                          Command insertCommand,
                          Command updateCommand,
                          Command removeKeyCommand,
                          Command clearCommand,
                          Command executeScriptCommand,
                          Command exitCommand,
                          Command removeLowerCommand,
                          Command removeGreaterKeyCommand,
                          Command removeLowerKeyCommand,
                          Command printAscendingCommand,
                          Command printDescendingCommand,
                          Command printFieldDescendingCarCommand
    ) {
        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(insertCommand.getName(), insertCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeKeyCommand.getName(), removeKeyCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        AVAILABLE_COMMANDS.put(executeScriptCommand.getName(), executeScriptCommand);
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(removeLowerCommand.getName(), removeLowerCommand);
        AVAILABLE_COMMANDS.put(removeLowerKeyCommand.getName(), removeLowerKeyCommand);
        AVAILABLE_COMMANDS.put(removeGreaterKeyCommand.getName(), removeGreaterKeyCommand);
        AVAILABLE_COMMANDS.put(printAscendingCommand.getName(), printAscendingCommand);
        AVAILABLE_COMMANDS.put(printDescendingCommand.getName(), printDescendingCommand);
        AVAILABLE_COMMANDS.put(printFieldDescendingCarCommand.getName(), printFieldDescendingCarCommand);
    }

    /**
     * Метод для получения объекта команды по имени команды из запроса.
     * @param request запрос от клиента
     * @return объект команды, соответствующий имени команды из запроса
     */
    public Command initCommand(Request request) {
        String commandName = request.getCommandName();
        return AVAILABLE_COMMANDS.get(commandName);
    }
}
