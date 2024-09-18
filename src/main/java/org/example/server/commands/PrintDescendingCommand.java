package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

/**
 * Команда, выводящая элементы коллекции в порядке убывания.
 */
public class PrintDescendingCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполнение команды вывода элементов в порядке убывания.
     * @param request объект запроса
     * @return объект типа Response с результатом выполнения команды
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(PrintManager.getPlainText("Коллекция пуста."));
        else
            return new Response(collectionManager.printCollectionDescending());
    }
}
