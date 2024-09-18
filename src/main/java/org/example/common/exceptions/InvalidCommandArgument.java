package org.example.common.exceptions;

public class InvalidCommandArgument extends Exception {

    public InvalidCommandArgument(String message) {
        super(message);
    }

    public String getMessage() {
        return "Команда с неверным аргументом. Введите \"help\", чтобы получить все команды с их именем и описанием";
    }
}
