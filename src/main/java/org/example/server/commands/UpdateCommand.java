package org.example.server.commands;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class UpdateCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который выполняет команду.
     *
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        Integer id = request.getArgument();
        if (!collectionManager.containsId(id))
            return new Response(PrintManager.getPlainText("Объекта HumanBeing с таким id не существует."));
        else {
            HumanBeing oldHumanBeing = collectionManager.getById(id);
            HumanBeing updatedHumanBeing = request.getHumanBeingArgument();
            Integer key = request.getKeyArgument();
            if (!(collectionManager.containsKey(key) && !collectionManager.getByKey(key).equals(oldHumanBeing))) {
                updatedHumanBeing.setId(id);
                collectionManager.update(key, updatedHumanBeing);
                return new Response(PrintManager.getPlainText("Данные объекта HumanBeing были обновлены."));
            } else {
                return new Response(PrintManager.getPlainText("Использование такого ключа запрещено. Объект HumanBeing не обновлен в коллекции."));
            }
        }
    }
}
