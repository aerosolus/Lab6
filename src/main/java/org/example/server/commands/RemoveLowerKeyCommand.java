package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "remove_lower_key" command for removing all elements from the collection,
 * whose key is less than the given value.
 * This class extends the {@code Command} functionality to perform bulk removal based on a comparison criteria applied to keys.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class RemoveLowerKeyCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new RemoveLowerKeyCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for removing elements with lower keys.
     */
    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        super("remove_lower_key", "удалить из коллекции все элементы, ключ которых меньше, чем заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_lower_key command, removing all elements from the collection,
     * whose key is less than the given value.
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
            collectionManager.removeLowerKey(key);
        return new Response(PrintManager.getPlainText("Были удалены все элементы, ключ которых меньше, чем заданный."));
        }
    }
}
