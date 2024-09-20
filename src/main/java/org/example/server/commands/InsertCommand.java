package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "insert" command for adding a new element to the collection with a specified key.
 * This class extends the {@code Command} functionality to insert a new HumanBeing object into the collection.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class InsertCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new InsertCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for inserting elements into the collection.
     */
    public InsertCommand(CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the insert command, adding a new element to the collection if the key doesn't exist.
     *
     * @param request The user's request containing the key and HumanBeing object to be inserted.
     * @return A Response object indicating the success or failure of the insertion operation.
     */
    @Override
    public Response execute(Request request) {
        PrintManager.printInfoMessage(request.getCommandName());
        Integer key = request.getKeyArgument();
        if (collectionManager.containsKey(key)) {
            return new Response(PrintManager.getPlainText("В коллекции уже присутствует элемент с таким ключом! Добавление запрещено."));
        } else {
            collectionManager.addToCollection(request.getKeyArgument(), request.getHumanBeingArgument());
            return new Response(PrintManager.getPlainText("Объект HumanBeing был добавлен в коллекцию и получил значение поля id, равное " + request.getHumanBeingArgument().getId() + "."));
        }
    }
}
