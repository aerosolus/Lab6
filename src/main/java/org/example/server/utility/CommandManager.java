package org.example.server.utility;

import org.example.common.utility.Request;
import org.example.server.commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for handling commands.
 * Provides functionality to manage and execute various commands related to work with the collection.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 * @see Command
 */
public class CommandManager {

    /**
     * Map of available commands.
     * Key - command name, value - command object.
     */
    public static final Map<String, Command> AVAILABLE_COMMANDS = new HashMap<>();

    /**
     * Constructs a new CommandManager instance.
     * Initializes the map of available commands.
     *
     * @param helpCommand   Command for displaying help
     * @param infoCommand   Command for retrieving collection information
     * @param showCommand   Command for displaying all collection elements
     * @param insertCommand Command for inserting a new element into the collection
     * @param updateCommand Command for updating an existing element in the collection
     * @param removeKeyCommand Command for removing an element by key
     * @param clearCommand   Command for clearing the entire collection
     * @param executeScriptCommand Command for executing a script
     * @param exitCommand     Command for exiting the application
     * @param removeLowerCommand Command for removing lower elements
     * @param removeLowerKeyCommand Command for removing elements with a lower key
     * @param removeGreaterKeyCommand Command for removing elements with a greater key
     * @param printAscendingCommand Command for printing elements in ascending order
     * @param printDescendingCommand Command for printing elements in descending order
     * @param printFieldDescendingCarCommand Command for printing the car field values in descending order
     */
    public CommandManager(Command helpCommand,
                          Command infoCommand,
                          Command showCommand,
                          Command insertCommand,
                          Command updateCommand,
                          Command removeKeyCommand,
                          Command clearCommand,
                          Command executeScriptCommand,
                          Command exitCommand,
                          Command removeLowerCommand,
                          Command removeGreaterKeyCommand,
                          Command removeLowerKeyCommand,
                          Command printAscendingCommand,
                          Command printDescendingCommand,
                          Command printFieldDescendingCarCommand
    ) {
        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(insertCommand.getName(), insertCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeKeyCommand.getName(), removeKeyCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        AVAILABLE_COMMANDS.put(executeScriptCommand.getName(), executeScriptCommand);
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(removeLowerCommand.getName(), removeLowerCommand);
        AVAILABLE_COMMANDS.put(removeLowerKeyCommand.getName(), removeLowerKeyCommand);
        AVAILABLE_COMMANDS.put(removeGreaterKeyCommand.getName(), removeGreaterKeyCommand);
        AVAILABLE_COMMANDS.put(printAscendingCommand.getName(), printAscendingCommand);
        AVAILABLE_COMMANDS.put(printDescendingCommand.getName(), printDescendingCommand);
        AVAILABLE_COMMANDS.put(printFieldDescendingCarCommand.getName(), printFieldDescendingCarCommand);
    }

    /**
     * Initializes a command based on the given request.
     * Retrieves the corresponding command object from the AVAILABLE_COMMANDS map.
     *
     * @param request Client request containing the command name
     * @return Command object corresponding to the requested command name
     * @throws NullPointerException if the request does not contain a valid command name
     */
    public Command initCommand(Request request) {
        String commandName = request.getCommandName();
        return AVAILABLE_COMMANDS.get(commandName);
    }
}
