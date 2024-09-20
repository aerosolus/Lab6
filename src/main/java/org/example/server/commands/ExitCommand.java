package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Represents the "exit" command for terminating the server program.
 * This class extends the {@code Command} functionality to gracefully shut down the application.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class ExitCommand extends Command {

    /**
     * Creates a new ExitCommand instance.
     * This constructor initializes the command with the appropriate name and description.
     */
    public ExitCommand() {
        super("exit", "завершить программу", 0);
    }

    /**
     * Terminates the server program execution.
     *
     * @param request The user's request to exit the program.
     * @return A Response object containing the client disconnection message.
     */
    @Override
    public Response execute(Request request) {
        return new Response(PrintManager.getPlainText("Отключение клиента."));
    }
}
