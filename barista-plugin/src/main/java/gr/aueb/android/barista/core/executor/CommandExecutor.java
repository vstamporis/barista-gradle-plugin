package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;

import java.util.List;

public interface CommandExecutor {

    void executeAdbCommand(Command command);

    void executeTelnetCommand(Command command);

    void executeCommands(List<Command> commandList);

    void executeCommand(Command cmd);
}
