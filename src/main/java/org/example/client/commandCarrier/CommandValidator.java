package org.example.client.commandCarrier;

import org.example.client.exceptions.InvalidInputException;
import org.example.common.exceptions.InvalidCommandArgument;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Класс CommandValidators предоставляет методы для валидации аргументов команд.
 */
public class CommandValidator {

    /**
     * Проверяет, что количество аргументов команды соответствует заданному количеству.
     *
     * @param args аргументы команды
     * @param amountOfArgs ожидаемое количество аргументов
     * @throws InvalidCommandArgument если количество аргументов не совпадает с ожидаемым
     */
    public static void validateAmountOfArgs(String[] args, int amountOfArgs) throws InvalidCommandArgument {
        if (args.length != amountOfArgs) {
            throw new InvalidCommandArgument("Было передано неверное количество аргументов. Ожидаемое количество аргументов для этой команды: " + amountOfArgs + ".");
        }
    }

    /**
     * Проверяет аргумент команды на соответствие предикату.
     *
     * @param predicate функция, принимающая значение аргумента и возвращающая true, если оно валидно
     * @param errorMessage сообщение об ошибке, которое будет выброшено, если аргумент не валиден
     * @param function функция, которая преобразует строковое представление аргумента в нужный тип
     * @param argument строковое представление аргумента
     * @param <T> тип аргумента
     * @return значение аргумента, если оно валидно
     * @throws InvalidInputException если аргумент не валиден
     * @throws IllegalArgumentException если не удалось преобразовать строку аргумента в нужный тип
     */
    public static <T> T validateArg(Predicate<Object> predicate,
                                    String errorMessage,
                                    Function<String, T> function,
                                    String argument) throws InvalidInputException, IllegalArgumentException {
        T value = function.apply(argument);
        if (predicate.test(value)) {
            return value;
        } else {
            throw new InvalidInputException(errorMessage);
        }
    }
}
