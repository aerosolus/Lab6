package org.example.client.exceptions;

/**
 * This class represents a custom exception that is thrown when attempting to pass a null value
 * where a non-null value is required.
 *
 * <p>It is commonly used in situations where a method or function expects a non-null argument,
 * but receives a null reference instead. This exception helps to catch and handle such cases
 * explicitly, improving the robustness and reliability of the code.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class NotNullException extends Exception{
}
