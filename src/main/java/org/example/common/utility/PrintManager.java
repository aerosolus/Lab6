package org.example.common.utility;

import java.io.PrintStream;

/**
 * Класс PrintManager предназначен для вывода текстовых сообщений в консоль.
 */
public class PrintManager {

    /**
     * Объект вывода текста в консоль.
     */
    private static final PrintStream printStream = System.out;

    /**
     * Выводит в консоль информационное сообщение.
     *
     * @param message строка, содержащая текст информационного сообщения.
     */
    public static void printInfoMessage(String message) {
        printStream.println(message);
    }

    /**
     * Выводит в консоль сообщение об ошибке.
     *
     * @param message строка, содержащая текст сообщения об ошибке.
     */
    public static void printErr(String message) {
        printStream.println("Ошибка: " + message);
    }

    /**
     * Возвращает строку простого текста.
     *
     * @param text строка, которую нужно отобразить белым цветом
     * @return строка с белым текстом
     */
    public static String getPlainText(String text) {
        return text;
    }

}