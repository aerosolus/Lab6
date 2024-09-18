package org.example.common.utility;

import org.example.common.DataManager;
import org.example.common.collectionEntities.HumanBeing;

import java.io.Serializable;

/**
 * Класс, представляющий объект-запрос.
 * Реализует интерфейс Serializable для возможности сериализации объектов.
 * Реализует интерфейс Data для отправки данных по сети.
 * Содержит информацию о команде и параметрах команды.
 */
public class Request implements Serializable, DataManager {

    /**
     * Имя команды.
     */
    private final String commandName;

    /**
     * Числовой аргумент команды.
     */
    private Integer argument;

    /**
     * Ключ-аргумент команды.
     */
    private Integer keyArgument;

    /**
     * Аргумент команды - объект HumanBeing.
     */
    private HumanBeing humanBeingArgument;

    /**
     * Конструктор класса, принимающий имя команды.
     * @param commandName имя команды
     */
    public Request(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Конструктор класса, принимающий имя команды и числовой аргумент.
     * @param commandName имя команды
     * @param argument числовой аргумент команды
     */
    public Request(String commandName, Integer argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    /**
     * Конструктор класса, принимающий имя команды и объект HumanBeing.
     * @param commandName имя команды
     * @param humanBeingArgument объект HumanBeing
     */
    public Request(String commandName, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.humanBeingArgument = humanBeingArgument;
    }

    /**
     * Конструктор класса, принимающий имя команды, числовой аргумент и объект HumanBeing.
     * @param commandName имя команды
     * @param argument числовой аргумент команды
     * @param humanBeingArgument объект HumanBeing
     */
    public Request(String commandName, Integer argument, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.humanBeingArgument = humanBeingArgument;
    }

    public Request(String commandName, HumanBeing humanBeingArgument, Integer keyArgument) {
        this.commandName = commandName;
        this.keyArgument = keyArgument;
        this.humanBeingArgument = humanBeingArgument;
    }

    public Request(String commandName, Integer argument, Integer keyArgument, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.keyArgument = keyArgument;
        this.humanBeingArgument = humanBeingArgument;
    }

    /**
     * Получает имя команды.
     * @return имя команды
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Получает числовой аргумент.
     * @return числовой аргумент команды
     */
    public Integer getArgument() {
        return argument;
    }

    public Integer getKeyArgument() {
        return keyArgument;
    }

    /**
     * Получает объект HumanBeing.
     * @return объект HumanBeing
     */
    public HumanBeing getHumanBeingArgument() {
        return humanBeingArgument;
    }

    /**
     * Метод getData() возвращает строковое представление данных объекта в виде имени команды и соответствующих аргументов.
     */
    @Override
    public String getData(){
        return "Имя команды для отправки: " + commandName
                + (humanBeingArgument == null ? "" : ("\nДанные объекта HumanBeing для отправки:\n " + humanBeingArgument))
                + (argument == null ? "" : ("\nЧисловой аргумент для отправки:\n " + argument));
    }

    /**
     * Возвращает строковое представление объекта в формате "Ответ[имя команды]".
     */
    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                '}';
    }
}
