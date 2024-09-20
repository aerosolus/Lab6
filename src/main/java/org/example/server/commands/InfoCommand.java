package org.example.server.commands;

import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "info" command for retrieving and displaying information about the collection.
 * This class extends the {@code Command} functionality to provide detailed information about the current state of the collection.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class InfoCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new InfoCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for retrieving collection information.
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the info command, retrieving and returning detailed information about the collection.
     *
     * @param request The user's request to view collection information.
     * @return A Response object containing the collected information about the collection.
     */
    @Override
    public Response execute(Request request) {
        return new Response(collectionManager.collectionInfo());
    }
}
