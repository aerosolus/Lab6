package org.example.server.utility;

import org.example.common.DataManager;
import org.example.common.InputOutputManager;
import org.example.common.utility.Request;

import java.io.*;
import java.net.Socket;

/**
 * Manages input/output operations for server-side socket communication.
 * Implements the InputOutputManager interface to provide methods for receiving and sending data over sockets.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ServerSocketIOManager implements InputOutputManager {

    /**
     * Socket object used for communication.
     */
    private final Socket socket;

    /**
     * Constructs a ServerSocketIOManager instance with the given socket.
     *
     * @param socket The socket for managing input/output operations.
     */
    public ServerSocketIOManager(Socket socket) {
        this.socket = socket;
    }


    /**
     * Receives and deserializes a Request object from the socket stream.
     *
     * @return The deserialized Request object received from the socket.
     * @throws IOException if an I/O error occurs during reading from the socket.
     * @throws ClassNotFoundException if the class of the serialized object cannot be found.
     */
    @Override
    public DataManager receive() throws IOException, ClassNotFoundException {
        ObjectInput in = new ObjectInputStream(socket.getInputStream());
        return (Request) in.readObject();
    }

    /**
     * Serializes and sends a DataManager object over the socket.
     *
     * @param data The DataManager object to send over the socket.
     * @throws IOException if an I/O error occurs during writing to the socket.
     */
    @Override
    public void send(DataManager data) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.close();
    }
}
