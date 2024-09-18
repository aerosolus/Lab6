package org.example.client.utility;

import org.example.client.commandCarrier.SendCommand;
import org.example.common.utility.PrintManager;

import java.io.File;
import java.util.Stack;

/**
 * Класс для чтения скриптов.
 */
public class ScriptManager {

    /**
     * Имя скрипта.
     */
    private final String filename;

    /**
     * Путь до скрипта скрипта.
     */
    private final File path;

    /**
     * Стек для отслеживания рекурсии в скриптах.
     */
    public static Stack<String> callStack = new Stack<>();

    /**
     * Конструктор для создания объекта ScriptReader.
     *
     * @param sendCommand объект типа CommandToSend, содержащий имя скрипта.
     * @throws IllegalArgumentException исключение, которое выбрасывается, если файл скрипта не найден или не может быть прочитан.
     */
    public ScriptManager(SendCommand sendCommand) throws IllegalArgumentException {
        this.filename = sendCommand.getCommandArgs()[0];
        path = new File(new File(System.getProperty("user.dir")), filename);
        if (!path.exists() || !path.canRead())
            throw new IllegalArgumentException("Проблема при вызове скрипта. Проверьте данные на верность.");
        if (callStack.contains(path.getAbsolutePath())) {
            callStack.clear();
            PrintManager.printErr("Скрипты рекурсивно ссылаются друг на друга. Работа программы прекращена.");
            System.exit(1);
        }
        callStack.push(path.getAbsolutePath());
    }

    /**
     * Метод для остановки чтения скрипта.
     */
    public void stopScriptReading() {
        callStack.clear();
    }

    /**
     * Метод для получения пути к скрипту.
     * @return объект типа File, представляющий путь к скрипту.
     */
    public File getPath() {
        return path;
    }
}
