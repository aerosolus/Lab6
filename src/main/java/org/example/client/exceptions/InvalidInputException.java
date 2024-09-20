package org.example.client.exceptions;

/**
 * This class represents a custom exception that is thrown when invalid input is detected.
 * It is typically used to indicate that the input provided does not meet the expected criteria
 * or format for a particular operation or method.
 *
 * <p>This exception is useful in scenarios where input validation needs to be handled
 * separately from other types of exceptions, allowing for more granular error handling.</p>
 *
 * <p>The exception can be customized with a specific error message, providing
 * detailed information about the nature of the invalid input.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified error message.
     *
     * @param errorMessage The detailed error message describing the invalid input.
     * This message will be stored and returned by the {@link #getMessage()} method.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
