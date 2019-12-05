package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import java.util.List;

/**
 *  CommandExecutor interface is used to specify the functions
 */
public interface CommandExecutor {

    /**
     *  Function that executes adb commands
     *
     * @param command An adb command to be executed
     */
    void executeAdbCommand(Command command);

    /**
     *  Function that executes telnet commands
     *
     * @param command A telenet command to be executed
     */
    void executeTelnetCommand(Command command);

    /**
     *  Function that iterates a list of commands and executes them one by one
     *
     * @param commandList A list of commands to be executed
     */
    void executeCommands(List<Command> commandList);

    /**
     *  Function that executes a Command
     *
     * @param cmd The command to be executed
     */
    void executeCommand(Command cmd);

    /**
     *  Function that assigns the ADBCommandClient with a CommandClient. This is command client is used
     *  the executeAdbCommand function.
     *
     * @param adbCommandClient A comandClient capable for executing ADB commands
     */
    void setAdbCommandClient(CommandClient adbCommandClient);

    /**
     *  Function that assigns the TelnetCommandClient with a CommandClient. This is command client is used
     *  the executeTelnetCommand function.
     * @param telnetCommandClient  A comandClient capable for executing telnet commands
     */
    void setTelnetCommandClient(CommandClient telnetCommandClient);
}
