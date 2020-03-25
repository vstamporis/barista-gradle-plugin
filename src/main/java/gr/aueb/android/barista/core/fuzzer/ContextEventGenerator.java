package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.fuzzer.context.ContextModel;
import gr.aueb.android.barista.core.fuzzer.context.PoorConnectivityModel;
import gr.aueb.android.barista.emulator.EmulatorManager;

import java.util.HashMap;
import java.util.Map;

public class ContextEventGenerator {

    private Map<Integer, ContextModel> contextEvents;

    private boolean stop;

    public ContextEventGenerator() {
        this.stop = false;
        this.contextEvents = new HashMap<>();
    }

    public void startContextFuzzing() {
        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        this.contextEvents.put(1, new PoorConnectivityModel(token));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*new Thread(() -> {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
        while (true) {
            //System.out.println("Here");
            this.contextEvents.get(this.generateRandomInt()).execute();
        }
    }

    private int generateRandomInt() {
        return 1;
    }

    public void stopContextFuzzing() {
        this.stop = true;
    }
}
