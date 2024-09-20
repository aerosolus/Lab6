package org.example.server.commands;

import org.example.common.utility.Request;
import org.example.common.utility.Response;

/**
 * Abstract base class for commands.
 * Defines common structure and behavior for all commands in the system.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public abstract class Command {

    /**
     * The name of the command.
     */
    private final String name;

    /**
     * Description of the command.
     */
    private final String description;

    /**
     * Number of arguments required by the command.
     */
    private final int amountOfArgs;

    /**
     * Constructs a new Command instance.
     *
     * @param name         The name of the command.
     * @param description  Description of the command.
     * @param amountOfArgs Number of arguments required by the command.
     */
    public Command(String name, String description, int amountOfArgs) {
        this.name = name;
        this.description = description;
        this.amountOfArgs = amountOfArgs;
    }

    /**
     * Executes the command based on the given request.
     *
     * @param request User's request to execute the command.
     * @return Server's response to the executed command.
     * @throws UnsupportedOperationException if the command is not implemented.
     */
    public abstract Response execute(Request request);

    /**
     * Retrieves the name of the command.
     *
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the number of arguments required by the command.
     *
     * @return The number of arguments required by the command.
     */
    public int getAmountOfArgs() {
        return amountOfArgs;
    }

    /**
     * Returns a string representation of the Command object.
     *
     * @return String representation of the Command object.
     */
    @Override
    public String toString() {
        return name + " : " + description;
    }
}