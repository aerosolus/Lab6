package org.example.common.utility;

import org.example.common.DataManager;
import org.example.common.collectionEntities.HumanBeing;

import java.io.Serializable;

/**
 * Represents a request object that encapsulates command information and parameters.
 * Implements Serializable interface for object serialization and DataManager interface for network data transmission.
 * Provides flexible constructors and getter methods for various combinations of command-related data.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class Request implements Serializable, DataManager {

    /**
     * Name of the command being executed.
     */
    private final String commandName;

    /**
     * Integer argument associated with the command.
     */
    private Integer argument;

    /**
     * Key argument associated with the command.
     */
    private Integer keyArgument;

    /**
     * HumanBeing object argument associated with the command.
     */
    private HumanBeing humanBeingArgument;

    /**
     * Constructs a Request object with a given command name.
     *
     * @param commandName Name of the command to be executed.
     */
    public Request(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Constructs a Request object with a given command name and integer argument.
     *
     * @param commandName Name of the command to be executed.
     * @param argument Integer argument associated with the command.
     */
    public Request(String commandName, Integer argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    /**
     * Constructs a Request object with a given command name and HumanBeing object argument.
     *
     * @param commandName Name of the command to be executed.
     * @param humanBeingArgument HumanBeing object associated with the command.
     */
    public Request(String commandName, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.humanBeingArgument = humanBeingArgument;
    }

    /**
     * Constructs a Request object with a given command name, integer argument and HumanBeing object argument.
     *
     * @param commandName Name of the command to be executed.
     * @param argument Integer argument associated with the command.
     * @param humanBeingArgument HumanBeing object associated with the command.
     */
    public Request(String commandName, Integer argument, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.humanBeingArgument = humanBeingArgument;
    }

    /**
     * Constructs a Request object with a given command name, HumanBeing object argument and integer key argument.
     *
     * @param commandName Name of the command to be executed.
     * @param humanBeingArgument HumanBeing object associated with the command.
     * @param keyArgument Integer key argument associated with the command.
     */
    public Request(String commandName, HumanBeing humanBeingArgument, Integer keyArgument) {
        this.commandName = commandName;
        this.humanBeingArgument = humanBeingArgument;
        this.keyArgument = keyArgument;
    }

    /**
     * Constructs a Request object with a given command name, integer argument, integer key argument, and HumanBeing object argument.
     *
     * @param commandName Name of the command to be executed.
     * @param argument Integer argument associated with the command.
     * @param keyArgument Integer key argument associated with the command.
     * @param humanBeingArgument HumanBeing object associated with the command.
     */
    public Request(String commandName, Integer argument, Integer keyArgument, HumanBeing humanBeingArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.keyArgument = keyArgument;
        this.humanBeingArgument = humanBeingArgument;
    }

    /**
     * Retrieves the name of the command.
     *
     * @return Name of the command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Retrieves the integer argument associated with the command.
     *
     * @return Integer argument of the command.
     */
    public Integer getArgument() {
        return argument;
    }

    /**
     * Retrieves the key argument associated with the command.
     *
     * @return Integer key argument of the command.
     */
    public Integer getKeyArgument() {
        return keyArgument;
    }

    /**
     * Retrieves the HumanBeing object associated with the command.
     *
     * @return HumanBeing object associated with the command.
     */
    public HumanBeing getHumanBeingArgument() {
        return humanBeingArgument;
    }

    /**
     * Returns a string representation of the request data, including command name and relevant arguments.
     *
     * @return String representation of the request data.
     */
    @Override
    public String getData(){
        return "Имя команды для отправки: " + commandName
                + (humanBeingArgument == null ? "" : ("\nДанные объекта HumanBeing для отправки:\n " + humanBeingArgument))
                + (argument == null ? "" : ("\nЧисловой аргумент для отправки:\n " + argument))
                + (keyArgument == null ? "" : ("\nАргумент-ключ команды для отправки:\n" + keyArgument));
    }

    /**
     * Returns a string representation of the Request object.
     *
     * @return String representation of the Request object.
     */
    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                '}';
    }
}
