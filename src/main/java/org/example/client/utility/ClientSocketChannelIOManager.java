package org.example.client.utility;

import org.example.common.DataManager;
import org.example.common.InputOutputManager;
import org.example.common.utility.SerializationManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Класс ClientSocketChannelIOManager для обработки ввода/вывода через сетевой канал.
 * Позволяет отправлять и получать данные с сервера.
 */
public class ClientSocketChannelIOManager implements InputOutputManager {

    /**
     * Канал сокета, используемый для связи с сервером.
     */
    private final SocketChannel channel;

    /**
     * Конструктор ClientSocketChannelIOManager.
     * @param channel канал SocketChannel, через который происходит взаимодействие с сервером.
     */
    public ClientSocketChannelIOManager(SocketChannel channel) {
        this.channel = channel;
    }

    /**
     * Отправляет данные на сервер.
     * @param data объект типа DataManager, который нужно отправить.
     * @throws IOException если произошла ошибка при передаче данных на сервер.
     */
    @Override
    public void send(DataManager data) throws IOException {
        ByteBuffer buffer = SerializationManager.serializeRequest(data);
        channel.write(buffer);
    }

    /**
     * Получает данные с сервера.
     * @return объект типа DataManager, полученный с сервера.
     * @throws IOException если произошла ошибка при передаче данных от сервера.
     * @throws ClassNotFoundException если не удалось распаковать полученные данные.
     */
    @Override
    public DataManager receive() throws IOException, ClassNotFoundException {
        ByteBuffer readBuffer = ByteBuffer.allocate(channel.socket().getReceiveBufferSize());
        channel.read(readBuffer);
        return SerializationManager.deSerializeResponse(readBuffer.array());
    }
}
