package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Represents the "print_field_descending_car" command for displaying values
 * of the "car" field for all elements in descending order.
 * This class extends the {@code Command} functionality to output the "car" field values, sorted in reverse order.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class PrintFieldDescendingCarCommand extends Command {

    /**
     * The manager responsible for handling the collection operations.
     */
    private final CollectionManager collectionManager;

    /**
     * Creates a new PrintFieldDescendingCarCommand instance.
     *
     * @param collectionManager The CollectionManager instance to use for printing the "car" field values in descending order.
     */
    public PrintFieldDescendingCarCommand(CollectionManager collectionManager) {
        super("print_field_descending_car", "вывести значения поля car всех элементов в порядке убывания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the print_field_descending_car command, displaying all values of the "car" field for elements in the collection,
     * sorted in descending order.
     *
     * @param request The user's request to view the "car" field values in descending order.
     * @return A Response object containing the sorted list of "car" field values from all elements in the collection.
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else {
            return new Response(PrintManager.getPlainText("Все значения поля car в порядке убывания: " + collectionManager.printFieldDescendingCar()));
        }
    }
}
