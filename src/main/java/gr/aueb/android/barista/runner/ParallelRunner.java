package gr.aueb.android.barista.runner;

import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.List;

public class ParallelRunner implements Runner {

    private List<Command> monkeyCommands;
    private List<Command> contextCommands;
    private CommandExecutor executor;

    public ParallelRunner(List<Command> monkeyCommands, List<Command> contextCommands) {
        this.monkeyCommands = monkeyCommands;
        this.contextCommands = contextCommands;
        this.executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Override
    public void start() {
        for (Command cmd : this.monkeyCommands) {
            synchronized (this) {
                this.executor.executeCommand(cmd);
                new Thread(() -> {
                    for (Command i : this.contextCommands) {
                        this.executor.executeCommand(i);
                    }
                }).start();
            }
        }
    }

    @Override
    public void stop() {

    }
}
