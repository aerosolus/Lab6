package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class RemoveLowerKeyCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        super("remove_lower_key", "удалить из коллекции все элементы, ключ которых меньше, чем заданный", 1);
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
            collectionManager.removeLowerKey(key);
        return new Response(PrintManager.getPlainText("Были удалены все элементы, ключ которых меньше, чем заданный."));
        }
    }
}
