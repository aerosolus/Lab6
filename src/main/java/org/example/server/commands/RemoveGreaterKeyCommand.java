package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class RemoveGreaterKeyCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public RemoveGreaterKeyCommand(CollectionManager collectionManager) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет удаление элемента коллекции по его id.
     * @param request запрос, содержащий id элемента для удаления
     * @return ответ с информацией об успешности выполнения операции
     */
    @Override
    public Response execute(Request request) {
        Integer key = request.getArgument();
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else {
            collectionManager.removeGreaterKey(key);
        return new Response(PrintManager.getPlainText("Были удалены все элементы, ключ которых превышает заданный."));
        }
    }
}
