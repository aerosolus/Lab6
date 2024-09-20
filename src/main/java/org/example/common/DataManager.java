package org.example.common;

/**
 * Interface defining objects that can provide a string representation of their data.
 * Objects implementing this interface should encapsulate data and
 * provide a standardized way to retrieve that data as a string.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public interface DataManager {

    /**
     * Retrieves a string representation of the data contained within the object.
     *
     * @return A string representation of the data.
     */
    String getData();
}
