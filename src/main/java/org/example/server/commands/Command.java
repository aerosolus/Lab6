package org.example.server.commands;

import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Абстрактный класс для команд
 */
public abstract class Command {

    /**
     * Название команды
     */
    private final String name;

    /**
     * Описание команды
     */
    private final String description;

    /**
     * Количество аргументов у команды
     */
    private final int amountOfArgs;

    /**
     * Конструктор абстрактного класса команды
     *
     * @param name         название команды
     * @param description  описание команды
     * @param amountOfArgs количество аргументов у команды
     */
    public Command(String name, String description, int amountOfArgs) {
        this.name = name;
        this.description = description;
        this.amountOfArgs = amountOfArgs;
    }

    /**
     * Метод для выполнения команды
     *
     * @param request запрос пользователя
     * @return ответ сервера
     */
    public abstract Response execute(Request request);


    /**
     * Получает название команды
     *
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /**
     * Получает описание команды
     *
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получает количество аргументов команды
     *
     * @return количество аргументов команды
     */
    public int getAmountOfArgs() {
        return amountOfArgs;
    }

    /**
     * Переопределенный метод toString() для класса Command
     *
     * @return строковое представление объекта Command
     */
    @Override
    public String toString() {
        return name + " : " + description;
    }
}