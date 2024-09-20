package org.example.common.utility;

import java.io.PrintStream;

/**
 * Utility class for managing console output, providing methods to display various types of messages.
 * This class simplifies the process of printing information, errors, and plain text to the console.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class PrintManager {

    /**
     * Static instance of PrintStream for console output.
     */
    private static final PrintStream printStream = System.out;

    /**
     * Prints an informational message to the console.
     *
     * @param message The message to be printed, typically used for logging.
     */
    public static void printInfoMessage(String message) {
        printStream.println(message);
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to be displayed.
     */
    public static void printErr(String message) {
        printStream.println("Ошибка: " + message);
    }

    /**
     * Returns the given text unchanged, suitable for displaying plain text.
     *
     * @param text The text to be returned as-is.
     * @return The original text, treated as plain text.
     */
    public static String getPlainText(String text) {
        return text;
    }

}