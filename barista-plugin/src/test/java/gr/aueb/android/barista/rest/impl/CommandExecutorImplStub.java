package gr.aueb.android.barista.rest.impl;

import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.model.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutorImplStub implements CommandExecutor {

    List<Command> commands = new ArrayList<>();

    @Override
    public void executeAdbCommand(Command command) {

    }

    @Override
    public void executeTelnetCommand(Command command) {

    }

    @Override
    public void executeCommands(List<Command> commandList) {
        commands.addAll(commandList);
    }

    @Override
    public void executeCommand(Command cmd) {
        commands.add(cmd);
    }
}
