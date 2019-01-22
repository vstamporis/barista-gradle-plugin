package gr.aueb.android.barista.emulator;

import gr.aueb.android.barista.core.model.Command;

public interface CommandExecutor {

    void executeAdbCommand(Command command);

    void executeTelnetCommand(Command command);

}
