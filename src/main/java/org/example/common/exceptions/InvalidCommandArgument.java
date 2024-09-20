package org.example.common.exceptions;

/**
 * Custom exception thrown when an invalid argument is encountered in a command.
 * This exception is typically used when a command requires specific arguments,
 * but receives incorrect or missing ones.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class InvalidCommandArgument extends Exception {

    /**
     * Constructs an InvalidCommandArgument with a custom error message.
     *
     * @param message The custom error message describing the invalid argument.
     */
    public InvalidCommandArgument(String message) {
        super(message);
    }

    /**
     * Returns a default error message indicating that an invalid command argument was encountered.
     *
     * @return A string describing the error situation.
     */
    public String getMessage() {
        return "Команда с неверным аргументом. Введите \"help\", чтобы получить все команды с их именем и описанием";
    }
}
