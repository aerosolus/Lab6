package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "clear" command for managing collection.
 * This command clears the collection if it is not empty.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class ClearCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new ClearCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for clearing the collection.
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the clear command on the collection.
     *
     * @param request The user's request to execute the command.
     * @return A Response object indicating whether the operation was successful or failed.
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
