package org.example.client.commandCarrier;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class serves as a centralized storage for available commands in the command manager.
 * It categorizes commands based on their argument requirements, facilitating efficient command processing.
 *
 * <p>The class uses static sets to store different categories of commands, each representing a unique set of arguments.
 * These sets are initialized in the static initializer block, populating them with predefined command names.</p>
 *
 * <p>Each set corresponds to a specific type of command:
 * <ul>
 *   <li><font color="AQUA">{@code COMMANDS_WITHOUT_ARGS}</font>: Commands that do not require any arguments.</li>
 *   <li><font color="AQUA">{@code COMMANDS_WITH_KEY_ARG}</font>: Commands that accept a single key argument.</li>
 *   <li><font color="AQUA">{@code COMMANDS_WITH_HUMANBEING_ARG}</font>: Commands that accept a HumanBeing object as an argument.</li>
 *   <li><font color="AQUA">{@code COMMANDS_WITH_HUMANBEING_ID_KEY_ARGS}</font>: Commands that accept both ID and HumanBeing as arguments.</li>
 *   <li><font color="AQUA">{@code COMMANDS_WITH_HUMANBEING_KEY_ARGS}</font>: Commands that accept a combination of key and HumanBeing as arguments.</li>
 *   <li><font color="AQUA">{@code SCRIPT_ARGUMENT_COMMAND}</font>: Commands that accept a script as an argument.</li>
 * </ul></p>
 *
 * <p>Using these pre-defined sets allows for quick lookup and categorization of commands during runtime,
 * enabling efficient dispatching and execution of commands based on their argument requirements.</p>
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class CommandStorage {

    /**
     * Set of commands that do not require any arguments.
     */
    public static final Set<String> COMMANDS_WITHOUT_ARGS = new HashSet<>();

    /**
     * Set of commands that accept a single key argument.
     */
    public static final Set<String> COMMANDS_WITH_KEY_ARG = new HashSet<>();

    /**
     * Set of commands that accept a HumanBeing object as an argument.
     */
    public static final Set<String> COMMANDS_WITH_HUMANBEING_ARG = new HashSet<>();

    /**
     * Set of commands that accept both ID and HumanBeing as arguments.
     */
    public static final Set<String> COMMANDS_WITH_HUMANBEING_ID_KEY_ARGS = new HashSet<>();

    /**
     * Set of commands that accept a combination of key and HumanBeing as arguments.
     */
    public static final Set<String> COMMANDS_WITH_HUMANBEING_KEY_ARGS = new HashSet<>();

    /**
     * Set of commands that accept a script as an argument.
     */
    public static final Set<String> SCRIPT_ARGUMENT_COMMAND = new HashSet<>();

    static {
        // Static initializer block to populate the sets with predefined command names
        Collections.addAll(COMMANDS_WITHOUT_ARGS,
                "help",
                "info",
                "show",
                "exit",
                "clear",
                "print_ascending",
                "print_descending",
                "print_field_descending_car"
        );
        Collections.addAll(COMMANDS_WITH_KEY_ARG,
                "remove_key",
                "remove_greater_key",
                "remove_lower_key"
        );
        Collections.addAll(COMMANDS_WITH_HUMANBEING_ARG,
                "remove_lower"
        );
        Collections.addAll(COMMANDS_WITH_HUMANBEING_KEY_ARGS,
                "insert"
        );
        Collections.addAll(COMMANDS_WITH_HUMANBEING_ID_KEY_ARGS,
                "update");
        SCRIPT_ARGUMENT_COMMAND.add("execute_script");
    }
}
