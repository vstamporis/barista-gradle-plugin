package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.context.model.*;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.*;

public class ContextEventGenerator implements EventGenerator {

    private Map<Integer, ContextModel> contextEvents;

    private List<ContextModel> models;

    private boolean stop;
    private String token;

    public ContextEventGenerator() {
        this.stop = false;
        this.contextEvents = new HashMap<>();
        this.models = new ArrayList<>();
    }

    @Override
    public List<Command> generate() {
        List<Command> toExecute = new ArrayList<>();
        for (ContextModel model: this.models) {
            Command cmd = model.next(1);
            BaristaLogger.print("COMMAND TOKEN: " + cmd.getSessionToken());
            toExecute.add(cmd);
        }
        return toExecute;
    }

    public void register(ContextModel contextModel) {
        this.models.add(contextModel);
    }

    /*public void startContextFuzzing() {
        new Thread(() -> {
            while (!this.stop) {
                this.contextEvents.get(this.generateRandomInt(1, 3)).execute();
            }
        }).start();
        for (ContextModel model : this.models) {
            model.execute();
        }
    }*/

    /*public void initializeContextEvents() {
        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        this.contextEvents.put(1, new PoorConnectivityModel());
        this.contextEvents.put(2, new RandomConnectivityModel());
        this.contextEvents.put(3, new BatteryDrainModel());
        this.models.add(new BatteryDrainModel());
        this.models.add(new PoorConnectivityModel());
        this.models.add(new RandomConnectivityModel());
        this.models.add(new RandomWalkModel());
        this.models.add(new FuzzMovementModel());
    }

    public void stopContextFuzzing() {
        this.stop = true;
        for (ContextModel model : this.models) {
            model.stop();
        }
    }*/
}
