package org.example.server.commands;

import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "show" command for displaying all elements of the collection.
 * This class extends the {@code Command} functionality to output all collection elements in a string representation.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class ShowCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new ShowCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for displaying the collection contents.
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the show command, returning a string representation of all elements in the collection.
     *
     * @param request The user's request to display the collection contents.
     * @return A Response object containing the string representation of all elements in the collection.
     */
    @Override
    public Response execute(Request request) {
        return new Response(collectionManager.showSortedCollection());
    }
}
