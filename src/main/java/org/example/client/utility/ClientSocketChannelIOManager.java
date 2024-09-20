package org.example.client.utility;

import org.example.common.DataManager;
import org.example.common.InputOutputManager;
import org.example.common.utility.SerializationManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * This class manages input/output operations through a network socket channel.
 * This class allows sending and receiving data to/from the server.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class ClientSocketChannelIOManager implements InputOutputManager {

    /**
     * The socket channel used for communication with the server.
     */
    private final SocketChannel channel;

    /**
     * Constructs a ClientSocketChannelIOManager instance.
     *
     * @param channel The SocketChannel through which interaction with the server occurs.
     */
    public ClientSocketChannelIOManager(SocketChannel channel) {
        this.channel = channel;
    }

    /**
     * Sends data to the server.
     *
     * @param data The DataManager object to be sent.
     * @throws IOException if an I/O error occurs while sending data to the server.
     */
    @Override
    public void send(DataManager data) throws IOException {
        ByteBuffer buffer = SerializationManager.serializeRequest(data);
        channel.write(buffer);
    }

    /**
     * Receives data from the server.
     *
     * @return A DataManager object received from the server.
     * @throws IOException if an I/O error occurs while receiving data from the server.
     * @throws ClassNotFoundException if the received data cannot be deserialized.
     */
    @Override
    public DataManager receive() throws IOException, ClassNotFoundException {
        ByteBuffer readBuffer = ByteBuffer.allocate(channel.socket().getReceiveBufferSize());
        channel.read(readBuffer);
        return SerializationManager.deSerializeResponse(readBuffer.array());
    }
}
