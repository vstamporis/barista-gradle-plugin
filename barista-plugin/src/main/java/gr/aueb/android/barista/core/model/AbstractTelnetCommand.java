package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

public abstract class AbstractTelnetCommand extends AbstractCommand {

    public AbstractTelnetCommand(){}

    public AbstractTelnetCommand(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public void accept(CommandExecutor executor) {
        executor.executeTelnetCommand(this);
    }
}
