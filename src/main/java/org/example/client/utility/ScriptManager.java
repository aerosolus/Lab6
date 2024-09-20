package org.example.client.utility;

import org.example.client.commandCarrier.SendCommand;
import org.example.common.utility.PrintManager;

import java.io.File;
import java.util.Stack;

/**
 * Manages the reading and execution of scripts.
 * This class handles the loading and processing of script files, including recursive calls and error handling.
 *
 * @author Aerosolus
 * @version 1.0
 * @version 1.0
 */
public class ScriptManager {

    /**
     * The name of the current script being executed.
     */
    private final String filename;

    /**
     * The file path of the current script.
     */
    private final File path;

    /**
     * A stack to track recursion in scripts.
     */
    public static Stack<String> callStack = new Stack<>();

    /**
     * Constructs a ScriptManager object.
     *
     * @param sendCommand The SendCommand object containing the script filename.
     * @throws IllegalArgumentException if the script file is not found or cannot be read.
     */
    public ScriptManager(SendCommand sendCommand) throws IllegalArgumentException {
        this.filename = sendCommand.getCommandArgs()[0];
        path = new File(new File(System.getProperty("user.dir")), filename);

        // Check if the script exists and is readable
        if (!path.exists() || !path.canRead())
            throw new IllegalArgumentException("Проблема при вызове скрипта. Проверьте данные на верность.");

        // Check for recursive calls
        if (callStack.contains(path.getAbsolutePath())) {
            callStack.clear();
            PrintManager.printErr("Скрипты рекурсивно ссылаются друг на друга. Работа программы прекращена.");
            System.exit(1);
        }
        // Push the current script path onto the call stack
        callStack.push(path.getAbsolutePath());
    }

    /**
     * Stops reading scripts.
     */
    public void stopScriptReading() {
        callStack.clear();
    }

    /**
     * Gets the file path of the current script.
     *
     * @return The File object representing the path to the current script.
     */
    public File getPath() {
        return path;
    }
}
