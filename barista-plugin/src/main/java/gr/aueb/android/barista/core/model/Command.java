package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

public interface Command {

    /**
     * the emulatorId injected in the android test client
     * @return
     */
    String getSessionToken();

    String getCommandString();

    void accept(CommandExecutor executor);
}
