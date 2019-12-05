package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import java.util.List;

/**
 *  CommandExecutorImpl implements the CommandExecutor interface.
 *  It injects itself to a Command object in order for it to decide which command client (adb or telnet) to use for the execution.
 */
public class CommandExecutorImpl implements CommandExecutor {

    CommandClient adbCommandClient;
    CommandClient telnetCommandClient;

    /**
     *  Wraper function for adb command execution.
     *  It utilizes the adbCommandClient for the execution of those commands
     * @param command The command to be executed with the adb client
     */
    @Override
    public void executeAdbCommand(Command command) {
        adbCommandClient.executeCommand(command);
    }

    /**
     *  Wraper function for adb command execution
     *  It utilizes the telnetCommandClient for the execution of those commands
     * @param command The command to be executed with the adb client
     */
    @Override
    public void executeTelnetCommand(Command command) {
        telnetCommandClient.executeCommand(command);
    }

    /**
     *  Function that loops through all the given commands and injects the CommandExecutor to them.
     *  After the injection, the command calls the executeTelnetCommand() or the executeAdbCommand() command
     *  depending on the type of the command.
     *
     *  It takes into considaration the delay option of each command.
     *
     * @param commandList A list of commands to be executed
     * */
    @Override
    public void executeCommands(List<Command> commandList) {
        for(Command cmd: commandList){
            cmd.accept(this);
            if(cmd.getDelay() != 0){
                try {
                    Thread.sleep(cmd.getDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  Function that injects the CommandExecutor inside the provided command.
     *  This way the command knows how to use the CommandExecutor depending on its type.
     *  If the command is tye od adb commands then it uses the  executeAdbCommand() otherwise
     *  if it is telnet type command, it uses the executeTelenetCommand().
     *
     * @param cmd The Command to be executed
     */
    @Override
    public void executeCommand(Command cmd) {
        cmd.accept(this);
    }

    /**
     *  Setter method for the adbCommandClient
     *
     * @param adbCommandClient An adb CommandClient implementation
     */
    @Override
    public void setAdbCommandClient(CommandClient adbCommandClient) {
        this.adbCommandClient = adbCommandClient;
    }

    /**
     *  Setter method for the telnetCommandClient
     *
     * @param telnetCommandClient A telnet CommandClient implementation
     */
    @Override
    public void setTelnetCommandClient(CommandClient telnetCommandClient) {
        this.telnetCommandClient = telnetCommandClient;
    }
}
