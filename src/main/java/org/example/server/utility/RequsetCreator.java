package org.example.server.utility;

import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.ServerApplication;
import org.example.server.commands.Command;
import org.example.server.exceptions.DisconnectException;

/**
 * Класс для построения ответа на запрос клиента, используя переданную команду и запрос.
 */
public class RequsetCreator {

    /**
     * Строит ответ на запрос клиента, используя переданную команду и запрос.
     * Если команда требует аргумента организации, устанавливает у организации новый id, сгенерированный менеджером коллекции.
     * @param command команда для выполнения
     * @param request запрос клиента
     * @return ответ на запрос клиента
     * @throws DisconnectException если возникли проблемы с инициализацией отключения
     */
    public static Response build(Command command, Request request) throws DisconnectException {
        if (request.getHumanBeingArgument() != null)
            request.getHumanBeingArgument().setId(ServerApplication.collectionManager.generateId());
        return command.execute(request);
    }
}
