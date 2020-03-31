package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.context.EnumTypes;
import gr.aueb.android.barista.core.context.factories.ContextModelFactory;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuzzScheduler {

    private MonkeyEventGenerator monkey;
    private ContextEventGenerator context;
    private CommandExecutor executor;
    private ContextModelFactory ctxFactory;

    private List<Command> commandsToExecute;
    private List<EventGenerator> eventGenerators;

    private int epochs, throttle, batchSize;
    private String apk, token;

    public FuzzScheduler(int epochs, int throttle, int batchSize, String apk) {
        this.epochs = epochs;
        this.throttle = throttle;
        this.apk = apk;
        this.batchSize = batchSize;
        this.commandsToExecute = new ArrayList<>();
        this.eventGenerators = new ArrayList<>();
    }

    public void initialize(boolean context, boolean parallel) {
        this.executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
        this.token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        this.initializeMonkey();
        if (context) this.initializeContext();

        for (int i = 0; i < this.epochs; i++) {
            for (EventGenerator eg: this.eventGenerators) {
                List<Command> commands = eg.generate();
                this.commandsToExecute.addAll(commands);
            }
        }
    }

    private void initializeMonkey() {
        int seed = new Random().nextInt();
        this.eventGenerators.add(new MonkeyEventGenerator(seed, this.batchSize, this.throttle, this.apk, this.token));
    }

    private void initializeContext() {
        this.ctxFactory = new ContextModelFactory(this.token);

        this.context = new ContextEventGenerator();
        this.context.register(this.ctxFactory.getConnectivityModel(EnumTypes.ConnectivityType.POOR));
        this.context.register(this.ctxFactory.getNavigationModel(EnumTypes.NavigationType.RANDOM_WALK));
        this.context.register(this.ctxFactory.getBatteryDrainModel());

        this.eventGenerators.add(this.context);
    }


    public void start() {
        this.executor.executeCommands(this.commandsToExecute);
    }

    public void stop() {

    }
}
