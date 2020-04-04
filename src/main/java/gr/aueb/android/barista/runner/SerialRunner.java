package gr.aueb.android.barista.runner;

import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.List;

public class SerialRunner implements Runner {

    private List<Command> commands;
    private CommandExecutor executor;

    public SerialRunner(List<Command> commands) {
        this.commands = commands;
        this.executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Override
    public void start() {
        this.executor.executeCommands(this.commands);
    }

    @Override
    public void stop() {

    }
}
