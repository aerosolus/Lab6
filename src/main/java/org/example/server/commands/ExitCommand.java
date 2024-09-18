package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Класс команды, завершающей программу.
 */
public class ExitCommand extends Command {

    /**
     * Создает новый объект команды.
     */
    public ExitCommand() {
        super("exit", "завершить программу", 0);
    }

    /**
     * Завершает выполнение программы на сервере.
     * @param request объект запроса
     * @return сообщение об отключении клиента
     */
    @Override
    public Response execute(Request request) {
        return new Response(PrintManager.getPlainText("Отключение клиента."));
    }
}
