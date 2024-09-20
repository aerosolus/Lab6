package org.example.server.utility;

import org.example.common.utility.Request;
import org.example.common.utility.Response;
import org.example.server.ServerApplication;
import org.example.server.commands.Command;
import org.example.server.exceptions.DisconnectException;

/**
 * Creates and builds responses to client requests using the provided command and request.
 * Handles the generation of new IDs for HumanBeing objects if required by the command.
 *
 * @author Aerosolus
 * @version 1.0
 * @since 1.0
 */
public class RequsetCreator {

    /**
     * Builds a response to a client request based on the given command and request.
     * If the command requires a HumanBeing argument, it generates a new ID for the HumanBeing argument.
     *
     * @param command The command to execute.
     * @param request The client request.
     * @return The response to the client request.
     * @throws DisconnectException if issues occur during disconnection initialization.
     */
    public static Response build(Command command, Request request) throws DisconnectException {
        if (request.getHumanBeingArgument() != null)
            request.getHumanBeingArgument().setId(ServerApplication.collectionManager.generateId());
        return command.execute(request);
    }
}
