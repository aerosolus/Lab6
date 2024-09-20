package org.example.client.commandCarrier;

import org.example.client.exceptions.InvalidInputException;
import org.example.common.exceptions.InvalidCommandArgument;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class provides methods for validating command arguments.
 * It offers two main functionalities:
 * 1. Verifying the number of arguments passed to a command
 * 2. Checking individual arguments against custom predicates and converting them to specific types
 *
 * <p>The class leverages functional interfaces like Predicate and Function to create flexible and reusable validation logic.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class CommandValidator {

    /**
     * Validates that the number of command arguments matches the expected amount.
     *
     * <p>This method checks if the length of the arguments array equals the specified amount.
     * If they don't match, it throws an InvalidCommandArgument with a descriptive error message.</p>
     *
     * @param args Array of command arguments
     * @param amountOfArgs Expected number of arguments
     * @throws InvalidCommandArgument if the number of arguments doesn't match the expected amount
     */
    public static void validateAmountOfArgs(String[] args, int amountOfArgs) throws InvalidCommandArgument {
        if (args.length != amountOfArgs) {
            throw new InvalidCommandArgument("Было передано неверное количество аргументов. Ожидаемое количество аргументов для этой команды: " + amountOfArgs + ".");
        }
    }

    /**
     * Validates a single command argument using a custom predicate and converts it to the desired type.
     *
     * <p>This method applies a predicate function to validate the argument and a conversion function
     * to transform the string representation of the argument into the desired type.</p>
     *
     * <p>It returns the validated and converted argument if it passes both checks,
     * otherwise it throws an InvalidInputException with the provided error message.</p>
     *
     * @param predicate Predicate function to validate the argument
     * @param errorMessage Error message to be thrown if the argument is invalid
     * @param function Function to convert the string representation of the argument to the desired type
     * @param argument String representation of the argument to be validated and converted
     * @param <T> Type of the argument
     * @return Validated and converted argument value
     * @throws InvalidInputException if the argument doesn't pass the validation check
     * @throws IllegalArgumentException if the conversion function fails
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
