package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Represents the "execute_script" command for running scripts from specified files.
 * This class extends the {@code Command} functionality to execute external scripts.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class ExecuteScriptCommand extends Command {

    /**
     * Creates a new ExecuteScriptCommand instance.
     * This constructor initializes the command with the appropriate name and description.
     */
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла", 1);
    }

    /**
     * Executes the script specified in the request.
     *
     * @param request The user's request containing the script file path.
     * @return A Response object with the result message of the command execution.
     */
    @Override
    public Response execute(Request request) {
        return new Response(PrintManager.getPlainText("Исполнение скрипта."));
    }
}
