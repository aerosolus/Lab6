package org.example.server.commands;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Команда для удаления элемента коллекции по его ключу.
 */
public class RemoveKeyCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public RemoveKeyCommand(CollectionManager collectionManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу", 1);
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
        if (!collectionManager.containsKey(key))
            return new Response(PrintManager.getPlainText("Объекта с таким ключом нет в коллекции."));
        else {
            collectionManager.remove(key);
            return new Response(PrintManager.getPlainText("Объект HumanBeing был удален."));
        }
    }
}
