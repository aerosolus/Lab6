package org.example.server.commands;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "remove_lower" command for removing all elements from the collection that are less than the given element.
 * This class extends the {@code Command} functionality to perform bulk removal based on a comparison criteria.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class RemoveLowerCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new RemoveLowerCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for removing lower elements.
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the remove_lower command, removing all elements from the collection that are less than the given element.
     *
     * @param request The user's request containing the HumanBeing argument for comparison.
     * @return A Response object indicating whether the operation was successful and providing feedback on the removal process.
     */
    @Override
    public Response execute(Request request) {
        HumanBeing humanBeing = request.getHumanBeingArgument();
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else {
            collectionManager.removeLower(humanBeing);
        return new Response(PrintManager.getPlainText("Были удалены все элементы, которые меньше, чем заданный."));
        }
    }
}
