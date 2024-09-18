package org.example.common.utility;

import org.example.common.DataManager;
import org.example.common.collectionEntities.HumanBeing;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Класс Response - класс, содержащий информацию для ответа на запрос.
 */
public class Response implements Serializable, DataManager {

    /**
     * Сообщение, отправляемое в ответ на запрос.
     */
    private String responseMessage;

    /**
     * Данные объекта HumanBeing, отправляемые в ответ на запрос.
     */
    private HumanBeing responseHumanBeing;

    /**
     * Данные коллекции, отправляемые в ответ на запрос.
     */
    private LinkedHashMap<Integer, HumanBeing> responseCollection;

    /**
     * Конструктор класса Response, принимающий сообщение для ответа.
     *
     * @param responseMessage сообщение для ответа
     */
    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и данные объекта HumanBeing для ответа.
     *
     * @param responseMessage сообщение для ответа
     * @param responseHumanBeing данные объекта HumanBeing для ответа
     */
    public Response(String responseMessage, HumanBeing responseHumanBeing) {
        this.responseMessage = responseMessage;
        this.responseHumanBeing = responseHumanBeing;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и коллекцию объектов HumanBeing для ответа.
     *
     * @param responseMessage сообщение для ответа.
     * @param responseCollection коллекция объектов HumanBeing для ответа
     */
    public Response(String responseMessage, LinkedHashMap<Integer, HumanBeing> responseCollection) {
        this.responseMessage = responseMessage;
        this.responseCollection = responseCollection;
    }

    /**
     * Конструктор класса Response, принимающий данные объекта HumanBeing для ответа.
     *
     * @param responseHumanBeing данные объекта HumanBeing для ответа
     */
    public Response(HumanBeing responseHumanBeing) {
        this.responseHumanBeing = responseHumanBeing;
    }

    /**
     * Конструктор класса Response, принимающий коллекцию объектов HumanBeing для ответа.
     *
     * @param responseCollection коллекция объектов HumanBeing для ответа
     */
    public Response(LinkedHashMap<Integer, HumanBeing> responseCollection) {
        this.responseCollection = responseCollection;
    }

    /**
     * Метод, возвращающий сообщение для ответа.
     *
     * @return сообщение для ответа
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Метод, возвращающий данные объекта HumanBeing для ответа.
     *
     * @return данные объекта HumanBeing для ответа
     */
    public HumanBeing getResponseHumanBeing() {
        return responseHumanBeing;
    }

    /**
     * Метод, возвращающий коллекцию объектов HumanBeing для ответа.
     *
     * @return коллекция объектов HumanBeing для ответа
     */
    public LinkedHashMap<Integer, HumanBeing> getResponseCollection() {
        return responseCollection;
    }


    /**
     * Метод, возвращающий информацию для отправки.
     *
     * @return информация для отправки
     */
    @Override
    public String getData() {
        return (responseMessage == null ? "" : (getResponseMessage()))
                + (responseHumanBeing == null ? "" : ("\nДанные объекта HumanBeing:\n" +  getResponseHumanBeing().toString()))
                + (responseCollection == null ? "" : ("\nДанные коллекции:\n" + getResponseCollection()));
    }

    /**
     * Представляет ответ, полученный от сервера, в формате, удобном для чтения.
     */
    @Override
    public String toString() {
        return "Response{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
