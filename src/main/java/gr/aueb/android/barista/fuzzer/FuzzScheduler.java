package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.context.EnumTypes;
import gr.aueb.android.barista.core.context.factories.ContextModelFactory;
import gr.aueb.android.barista.core.context.model.RandomWalkModel;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaLogger;
import gr.aueb.android.barista.utilities.PropertiesReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class FuzzScheduler {

    private MonkeyEventGenerator monkey;
    private ContextEventGenerator context;
    private CommandExecutor executor;
    private ContextModelFactory ctxFactory;
    private PropertiesReader reader;

    private List<Command> commandsToExecute;
    private List<EventGenerator> eventGenerators;

    private int epochs, throttle, batchSize;
    private String apk, token, conf;

    public FuzzScheduler(int epochs, int throttle, int batchSize, String apk, String conf) {
        this.epochs = epochs;
        this.throttle = throttle;
        this.apk = apk;
        this.batchSize = batchSize;
        this.commandsToExecute = new ArrayList<>();
        this.eventGenerators = new ArrayList<>();
        this.conf = conf;
    }

    public void initialize(boolean context, boolean parallel) {
        this.reader = new PropertiesReader(this.conf);
        this.reader.loadProperties();

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
        this.eventGenerators.add(new MonkeyEventGenerator(this.reader.getMonkeySeed(), this.batchSize, this.throttle, this.apk, this.token));
    }

    private void initializeContext() {
        this.ctxFactory = new ContextModelFactory(this.token);

        this.context = new ContextEventGenerator();

        if (this.reader.getModel("fuzzer.poorConnectivity")) {
            this.context.register(this.ctxFactory.getConnectivityModel(EnumTypes.ConnectivityType.POOR));
        }

        if (this.reader.getModel("fuzzer.randomConnectivity")) {
            this.context.register(this.ctxFactory.getConnectivityModel(EnumTypes.ConnectivityType.RANDOM));
        }

        if (this.reader.getModel("fuzzer.fuzzGPS")) {
            this.context.register(this.ctxFactory.getConnectivityModel(EnumTypes.ConnectivityType.GPS));
        }

        if (this.reader.getModel("fuzzer.walkModel")) {
            RandomWalkModel walkModel = (RandomWalkModel) this.ctxFactory.getNavigationModel(EnumTypes.NavigationType.RANDOM_WALK);
            walkModel.setLat(this.reader.getWalkLatitude());
            walkModel.setLongt(this.reader.getWalkLongitude());

            this.context.register(walkModel);
        }

        if (this.reader.getModel("fuzzer.randomMovement")) {
            this.context.register(this.ctxFactory.getNavigationModel(EnumTypes.NavigationType.FUZZ));
        }

        if (this.reader.getModel("fuzzer.batteryDrain")) {
            this.context.register(this.ctxFactory.getBatteryDrainModel());
        }

        this.eventGenerators.add(this.context);
    }


    public void start() {
        this.executor.executeCommands(this.commandsToExecute);
    }

    public void stop() {

    }

}
