package org.example.client.commandCarrier;

/**
 * This class represents a command and its arguments that will be sent to the server.
 * It encapsulates the command name and its associated arguments, providing a structured way
 * to transmit user input to the server-side operations.
 *
 * <p>This class is part of the command carrier layer, acting as an intermediary between
 * the client-side command input and the server-side processing.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class SendCommand {

    /**
     * The name of the command.
     */
    private final String commandName;

    /**
     * An array of arguments associated with the command.
     */
    private final String[] commandArgs;

    /**
     * Constructs a new SendCommand object with the specified command name and arguments.
     *
     * @param commandName The name of the command to be sent to the server.
     * @param commandArgs An array of strings representing the arguments associated with the command.
     */
    public SendCommand(String commandName, String[] commandArgs) {
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }

    /**
     * Retrieves the name of the command.
     *
     * @return The name of the command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Retrieves the arguments associated with the command.
     *
     * @return An array of strings representing the command arguments.
     */
    public String[] getCommandArgs() {
        return commandArgs;
    }

}
