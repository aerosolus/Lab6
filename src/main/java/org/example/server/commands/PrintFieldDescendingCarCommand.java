package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class PrintFieldDescendingCarCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public PrintFieldDescendingCarCommand(CollectionManager collectionManager) {
        super("print_field_descending_car", "вывести значения поля car всех элементов в порядке убывания", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду для вывода всех значений годового оборота организаций в системе в порядке убывания.
     * @param request объект запроса
     * @return ответ на запрос с выводом всех значений годового оборота организаций в системе в порядке убывания
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
