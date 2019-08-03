/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: CommandClient
 * Role: Defines the CommandClient interace
 * Description: CommandClient is the interface that must be implemented by any client responsible for executing commands
 *
 */

package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;

public interface CommandClient {
    /**
     * Function that is responsible for executing a Command. Depending on the type of the command, this function should be
     * able to utlize the appropriate mechanisms for the execution of the provided command.
     * This function should only return when the command is executed.
     * @param cmd The command to be executed
     */
    void executeCommand(Command cmd);

}
