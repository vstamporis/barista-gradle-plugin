package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.context.ContextModel;
import gr.aueb.android.barista.core.model.Command;
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
            toExecute.add(cmd);
        }
        return toExecute;
    }

    public Command generateSingle() {
        ContextModel model = this.models.get(randomInt(this.models.size()));

        return model.next(1);
    }

    public void register(ContextModel contextModel) {
        this.models.add(contextModel);
    }

    private int randomInt(int max) {
        Random rand = new Random();

        return rand.nextInt(max);
    }
}
