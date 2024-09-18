package org.example.server.utility;

import org.example.common.DataManager;
import org.example.common.InputOutputManager;
import org.example.common.utility.Request;

import java.io.*;
import java.net.Socket;

/**
 * Класс ServerSocketIO предоставляет методы для управления вводом/выводом данных в сокете на стороне сервера.
 */
public class ServerSocketIOManager implements InputOutputManager {

    /**
     * Сокет
     */
    private final Socket socket;

    /**
     * Конструктор принимает сокет для дальнейшей работы с ним.
     * @param socket - сокет
     */
    public ServerSocketIOManager(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод receive() используется для чтения данных из сокета и десериализации объекта Request.
     * @return объект Request, полученный из сокета.
     * @throws IOException - выбрасывается в случае ошибки ввода/вывода.
     * @throws ClassNotFoundException - выбрасывается, когда не удается найти класс, необходимый для десериализации.
     */
    @Override
    public DataManager receive() throws IOException, ClassNotFoundException {
        ObjectInput in = new ObjectInputStream(socket.getInputStream());
        return (Request) in.readObject();
    }

    /**
     * Метод send() используется для записи данных в сокет в формате объекта.
     * @param data - объект DataManager, который необходимо записать в сокет.
     * @throws IOException - выбрасывается в случае ошибки ввода/вывода.
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
