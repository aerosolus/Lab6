package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class InsertCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public InsertCommand(CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        collectionManager.addToCollection(request.getArgument(), request.getHumanBeingArgument());
        return new Response(PrintManager.getPlainText("Организация добавлена в коллекцию."));
    }
}
