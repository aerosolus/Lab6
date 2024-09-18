package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Команда очистки коллекции.
 */
public class ClearCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию, если она не пуста.
     * @param request объект запроса
     * @return объект Response с сообщением об успешном выполнении или ошибке, если коллекция пуста
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else {
            collectionManager.clearCollection();
            return new Response(PrintManager.getPlainText("Коллекция очищена."));
        }
    }
}
