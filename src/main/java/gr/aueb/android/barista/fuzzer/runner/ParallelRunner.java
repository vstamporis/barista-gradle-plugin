package gr.aueb.android.barista.fuzzer.runner;

import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.LogcatCrash;
import gr.aueb.android.barista.fuzzer.ContextEventGenerator;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.List;

public class ParallelRunner implements Runner {

    private List<Command> monkeyCommands;
    private ContextEventGenerator eventGenerator;
    private CommandExecutor executor;
    private LogcatCrash crashReporter;
    private boolean stop;

    public ParallelRunner(List<Command> monkeyCommands, ContextEventGenerator eventGenerator, LogcatCrash crashReporter) {
        this.monkeyCommands = monkeyCommands;
        this.eventGenerator = eventGenerator;
        this.executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
        this.crashReporter = crashReporter;
    }

    @Override
    public void start() {
        for (Command cmd : this.monkeyCommands) {
            this.stop = false;
            synchronized (this) {
                this.executor.executeCommand(cmd);
                new Thread(() -> {
                    while (!this.stop) {
                        this.executor.executeCommand(this.eventGenerator.generateSingle());
                    }
                }).start();
                this.stop = true;
            }
        }
        this.executor.executeCommand(this.crashReporter);
        BaristaLogger.printList(this.crashReporter.getCrashLog());
    }

}
