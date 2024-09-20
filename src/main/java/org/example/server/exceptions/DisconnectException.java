package org.example.server.exceptions;

/**
 * Custom exception representing unexpected disconnections from the server.
 * This exception is thrown when a client unexpectedly disconnects from the server,
 * interrupting ongoing communications or sessions.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class DisconnectException extends Exception {

    /**
     * Returns a default error message indicating an unexpected client disconnection.
     *
     * @return A string describing the disconnection situation.
     */
    public String getMessage() {
        return "Неожиданное прерывание соединения с клиентом.";
    }
}