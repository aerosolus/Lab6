package org.example.common;

import java.io.IOException;

/**
 * Interface for managing input/output operations, specifically for sending and receiving data.
 * Defines methods for controlling data flow between systems or processes.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public interface InputOutputManager {

    /**
     * Sends data using the managed input/output system.
     *
     * @param data The DataManager object containing the data to be sent.
     * @throws IOException if an I/O error occurs during the send operation.
     */
    void send(DataManager data) throws IOException;

    /**
     * Receives data using the managed input/output system.
     *
     * @return The received DataManager object containing the data.
     * @throws IOException if an I/O error occurs during the receive operation.
     * @throws ClassNotFoundException if the class of the received data is not found.
     */
    DataManager receive() throws IOException, ClassNotFoundException;
}
