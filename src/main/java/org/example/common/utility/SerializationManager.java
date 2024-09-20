package org.example.common.utility;

import org.example.common.DataManager;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Manages serialization and deserialization of request and response objects.
 * Provides methods for converting between serialized byte buffers and object representations.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class SerializationManager {

    /**
     * Serializes a request object into a ByteBuffer.
     *
     * @param request The request object to serialize.
     * @return ByteBuffer containing the serialized request data.
     * @throws IOException if an I/O error occurs during serialization.
     */
    public static ByteBuffer serializeRequest(DataManager request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        // Create a byte buffer based on a byte array from a byte array
        ByteBuffer bufToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bufToSend;
    }

    /**
     * Deserializes a byte array representing a response object into a Response object.
     *
     * @param acceptedBuf Byte array containing the serialized response data.
     * @return Deserialized Response object.
     * @throws IOException if an I/O error occurs during deserialization.
     * @throws ClassNotFoundException if the class of the object cannot be found during deserialization.
     */
    public static Response deSerializeResponse(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return response;
    }
}
