package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.fuzzer.context.*;
import gr.aueb.android.barista.emulator.EmulatorManager;

import java.util.*;

public class ContextEventGenerator {

    private Map<Integer, ContextModel> contextEvents;

    private List<ContextModel> models;

    private boolean stop;

    public ContextEventGenerator() {
        this.stop = false;
        this.contextEvents = new HashMap<>();
        this.models = new ArrayList<>();
    }

    public void startContextFuzzing() {
        /*new Thread(() -> {
            while (!this.stop) {
                this.contextEvents.get(this.generateRandomInt(1, 3)).execute();
            }
        }).start();*/
        for (ContextModel model : this.models) {
            model.execute();
        }
    }

    public void initializeContextEvents() {
        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        this.contextEvents.put(1, new PoorConnectivityModel(token));
        this.contextEvents.put(2, new RandomConnectivityModel(token));
        this.contextEvents.put(3, new BatteryDrainModel(token));
//        this.models.add(new BatteryDrainModel(token));
//        this.models.add(new PoorConnectivityModel(token));
//        this.models.add(new RandomConnectivityModel(token));
        this.models.add(new RandomWalkModel(token));
//        this.models.add(new FuzzMovementModel(token));
    }

    public void stopContextFuzzing() {
        this.stop = true;
        for (ContextModel model : this.models) {
            model.stop();
        }
    }
}
