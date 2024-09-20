package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "print_ascending" command for displaying elements of the collection in ascending order.
 * This class extends the {@code Command} functionality to output collection items sorted by some criteria.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class PrintAscendingCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new PrintAscendingCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for printing the collection.
     */
    public PrintAscendingCommand(CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the print_ascending command, displaying the collection elements in ascending order.
     *
     * @param request The user's request to view the collection in ascending order.
     * @return A Response object containing either a message about an empty collection or the sorted collection elements.
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else
            return new Response(collectionManager.printCollectionAscending());
    }
}
