package org.example.server.commands;

import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Команда, которая выводит все элементы коллекции.
 */
public class ShowCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который выполняет команду.
     * @param request объект запроса
     * @return строку, содержащую все элементы коллекции.
     */
    @Override
    public Response execute(Request request) {
        return new Response(collectionManager.showSortedCollection());
    }
}
