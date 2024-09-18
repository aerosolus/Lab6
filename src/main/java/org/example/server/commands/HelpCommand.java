package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CommandManager;

/**
 * Класс команды для вывода справки.
 */
public class HelpCommand extends Command {

    /**
     * Создает новый объект команды.
     */
    public HelpCommand() {
        super("help", "вывести справку по доступным командам", 0);

    }

    /**
     * Выполняет команду вывода списка доступных команд.
     *
     * @param request объект запроса
     * @return объект ответа с информацией о доступных командах
     */
    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        for (Command command : CommandManager.AVAILABLE_COMMANDS.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return new Response(PrintManager.getPlainText("Доступные команды:\n") + sb);
    }
}
