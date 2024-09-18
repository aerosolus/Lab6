package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Класс команды для выполнения скрипта из указанного файла.
 */
public class ExecuteScriptCommand extends Command {

    /**
     * Создает новый объект команды.
     */
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла", 1);
    }

    /**
     * Исполняет скрипт.
     * @param request объект запроса
     * @return объект Response с сообщением о результате выполнения команды
     */
    @Override
    public Response execute(Request request) {
        return new Response(PrintManager.getPlainText("Исполнение скрипта."));
    }
}
