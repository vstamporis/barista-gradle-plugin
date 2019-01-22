package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

public interface Command {

    String getSessionToken();

    String getCommandString();

    void accept(CommandExecutor executor);
}
