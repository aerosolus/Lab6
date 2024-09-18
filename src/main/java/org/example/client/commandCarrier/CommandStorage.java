package org.example.client.commandCarrier;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, хранящий доступные команды для диспетчера команд.
 */
public class CommandStorage {

    /**
     * Множество команд без аргументов.
     */
    public static final Set<String> COMMANDS_WITHOUT_ARGS = new HashSet<>();

    /**
     * Множество команд с аргументом key.
     */
    public static final Set<String> COMMANDS_WITH_KEY_ARG = new HashSet<>();

    /**
     * Множество команд с аргументом - объектом HumanBeing.
     */
    public static final Set<String> COMMANDS_WITH_HUMANBEING_ARG = new HashSet<>();

    /**
     * Множество команд с аргументами ID и HumanBeing.
     */
    public static final Set<String> COMMANDS_WITH_HUMANBEING_ID_ARGS = new HashSet<>();

    /**
     * Множество команд, принимающих аргументом скрипт.
     */
    public static final Set<String> SCRIPT_ARGUMENT_COMMAND = new HashSet<>();

    static {
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
                "insert",
                "remove_key",
                "remove_greater_key",
                "remove_lower_key"
        );
        Collections.addAll(COMMANDS_WITH_HUMANBEING_ARG,
                "remove_lower"
        );
        Collections.addAll(COMMANDS_WITH_HUMANBEING_ID_ARGS,
                "update");
        SCRIPT_ARGUMENT_COMMAND.add("execute_script");
    }
}
