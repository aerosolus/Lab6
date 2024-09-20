package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "remove_key" command for deleting an element from the collection based on its key.
 * This class extends the {@code Command} functionality to remove a specific item from the collection,
 * using its unique identifier.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class RemoveKeyCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new RemoveKeyCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for removing elements based on their keys.
     */
    public RemoveKeyCommand(CollectionManager collectionManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_key command, deleting an element from the collection based on its key.
     *
     * @param request The user's request containing the key of the element to be removed.
     * @return A Response object indicating whether the operation was successful and providing feedback on the deletion process.
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
