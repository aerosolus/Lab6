package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "print_descending" command for displaying elements of the collection in descending order.
 * This class extends the {@code Command} functionality to output collection items sorted by some criteria in reverse order.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class PrintDescendingCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new PrintDescendingCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for printing the collection in descending order.
     */
    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the print_descending command, displaying the collection elements in descending order.
     *
     * @param request The user's request to view the collection in descending order.
     * @return A Response object containing either a message about an empty collection
     * or the sorted collection elements in reverse order.
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else
            return new Response(collectionManager.printCollectionDescending());
    }
}
