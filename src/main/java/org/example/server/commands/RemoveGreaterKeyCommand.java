package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "remove_greater_key" command for removing all elements from the collection whose key exceeds the given key.
 * This class extends the {@code Command} functionality to perform bulk removal based on a comparison criteria.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class RemoveGreaterKeyCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new RemoveGreaterKeyCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for removing greater key elements.
     */
    public RemoveGreaterKeyCommand(CollectionManager collectionManager) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_greater_key command, removing all elements from the collection whose key exceeds the given key.
     *
     * @param request The user's request containing the key argument for comparison.
     * @return A Response object indicating whether the operation was successful and providing feedback on the removal process.
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
