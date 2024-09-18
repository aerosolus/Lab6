package org.example.server.commands;

import org.example.common.collectionEntities.HumanBeing;
import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CollectionManager;

public class RemoveLowerCommand extends Command {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный", 1);
        this.collectionManager = collectionManager;
    }


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
