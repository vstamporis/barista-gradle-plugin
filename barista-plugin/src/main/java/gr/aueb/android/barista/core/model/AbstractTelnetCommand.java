package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

/**
 *  AbstractTelnetCommand is an abstract class that extends the AbstractCommand class and implements
 *  the functionality of any telnet command. This functionality is the ussage of TelenetCommandClient as the
 *  executor.
 */
public abstract class AbstractTelnetCommand extends AbstractCommand {

    /**
     *  Empty constructor
     */
    public AbstractTelnetCommand(){}

    /**
     * Parametered Cunstructor
     *
     * @param sessionToken The sessionToken
     */
    public AbstractTelnetCommand(String sessionToken) {
        super(sessionToken);
    }

    /**
     * Accept command is used when the command is about to be executed by the CommandExecutor.
     * As a telnet command, this object uses the TelnetCommandCLient provided by the executor.
     *
     * @param executor
     */
    @Override
    public void accept(CommandExecutor executor) {
        executor.executeTelnetCommand(this);
    }
}
