package org.example.common;

import java.io.IOException;

/**
 * Интерфейс для контроля ввода/вывода данных
 */
public interface InputOutputManager {
    /**
     * Отправка данных
     * @param data данные для отправки
     * @throws IOException если произошла ошибка ввода/вывода
     */
    void send(DataManager data) throws IOException;

    /**
     * Получение данных
     * @return полученные данные
     * @throws IOException если произошла ошибка ввода/вывода
     * @throws ClassNotFoundException если класс не найден
     */
    DataManager receive() throws IOException, ClassNotFoundException;
}
