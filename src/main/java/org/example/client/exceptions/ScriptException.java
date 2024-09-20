package org.example.client.exceptions;

/**
 * This class represents a custom exception that occurs when incorrect input is provided in a script.
 * It is typically thrown when the user enters invalid data, such as a string when a number is expected,
 * or a value that does not meet the requirements for the current operation.
 *
 * <p>This exception is designed to handle various scenarios where the input validation fails,
 * providing a clear indication of the nature of the error.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ScriptException extends Exception {}
