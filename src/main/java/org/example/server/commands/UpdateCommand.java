package org.example.server.commands;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "update" command for updating the value of an element in the collection based on its ID.
 * This class extends the {@code Command} functionality to modify an existing element in the collection.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class UpdateCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new UpdateCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for updating collection elements.
     */
    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the update command, modifying an existing element in the collection based on the provided arguments.
     *
     * @param request The user's request containing the ID, HumanBeing object, and key for updating.
     * @return A Response object indicating the result of the update operation.
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
                return new Response(PrintManager.getPlainText("Использование такого ключа запрещено. Объект HumanBeing не обновлен."));
            }
        }
    }
}
