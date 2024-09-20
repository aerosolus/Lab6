package org.example.server.commands;

import org.example.common.utility.PrintManager;
import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.utility.CommandManager;

/**
 * Represents the "help" command for displaying available commands and their descriptions.
 * This class extends the {@code Command} functionality to provide user assistance.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class HelpCommand extends Command {

    /**
     * Creates a new HelpCommand instance.
     * This constructor initializes the command with the appropriate name and description.
     */
    public HelpCommand() {
        super("help", "вывести справку по доступным командам", 0);

    }

    /**
     * Executes the help command, listing all available commands.
     *
     * @param request The user's request to view help information.
     * @return A Response object containing a formatted string with all available commands.
     */
    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        for (Command command : CommandManager.AVAILABLE_COMMANDS.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return new Response(PrintManager.getPlainText("Доступные команды:\n") + sb);
    }
}
