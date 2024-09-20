package org.example.common.utility;

import org.example.common.DataManager;
import org.example.common.collectionEntities.HumanBeing;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Represents a response object containing information for replying to a request.
 * Implements Serializable and DataManager interfaces for easy serialization and data management.
 * Provides flexible constructors and getter methods for various combinations of response data.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class Response implements Serializable, DataManager {

    /**
     * Message sent in response to a request.
     */
    private String responseMessage;

    /**
     * HumanBeing object data sent in response to a request.
     */
    private HumanBeing responseHumanBeing;

    /**
     * LinkedHashMap of HumanBeing objects sent in response to a request.
     */
    private LinkedHashMap<Integer, HumanBeing> responseCollection;

    /**
     * Constructs a Response object with a given response message.
     *
     * @param responseMessage Message to be sent in response.
     */
    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * Constructs a Response object with a given response message and HumanBeing object data.
     *
     * @param responseMessage Message to be sent in response.
     * @param responseHumanBeing HumanBeing object data to be sent in response.
     */
    public Response(String responseMessage, HumanBeing responseHumanBeing) {
        this.responseMessage = responseMessage;
        this.responseHumanBeing = responseHumanBeing;
    }

    /**
     * Constructs a Response object with a given response message and collection of HumanBeing objects.
     *
     * @param responseMessage Message to be sent in response.
     * @param responseCollection LinkedHashMap of HumanBeing objects to be sent in response.
     */
    public Response(String responseMessage, LinkedHashMap<Integer, HumanBeing> responseCollection) {
        this.responseMessage = responseMessage;
        this.responseCollection = responseCollection;
    }

    /**
     * Constructs a Response object with a given HumanBeing object data.
     *
     * @param responseHumanBeing HumanBeing object data to be sent in response.
     */
    public Response(HumanBeing responseHumanBeing) {
        this.responseHumanBeing = responseHumanBeing;
    }

    /**
     * Constructs a Response object with a given collection of HumanBeing objects.
     *
     * @param responseCollection LinkedHashMap of HumanBeing objects to be sent in response.
     */
    public Response(LinkedHashMap<Integer, HumanBeing> responseCollection) {
        this.responseCollection = responseCollection;
    }

    /**
     * Retrieves the message sent in response.
     *
     * @return The response message.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Retrieves the HumanBeing object data sent in response.
     *
     * @return The HumanBeing object data.
     */
    public HumanBeing getResponseHumanBeing() {
        return responseHumanBeing;
    }

    /**
     * Retrieves the collection of HumanBeing objects sent in response.
     *
     * @return The LinkedHashMap of HumanBeing objects.
     */
    public LinkedHashMap<Integer, HumanBeing> getResponseCollection() {
        return responseCollection;
    }


    /**
     * Returns a string representation of the response data, including all relevant information.
     *
     * @return A formatted string containing all response data.
     */
    @Override
    public String getData() {
        return (responseMessage == null ? "" : (getResponseMessage()))
                + (responseHumanBeing == null ? "" : ("\nДанные объекта HumanBeing:\n" +  getResponseHumanBeing().toString()))
                + (responseCollection == null ? "" : ("\nДанные коллекции:\n" + getResponseCollection()));
    }

    /**
     * Returns a string representation of the Response object in a readable format.
     *
     * @return A formatted string representation of the Response object.
     */
    @Override
    public String toString() {
        return "Response{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
