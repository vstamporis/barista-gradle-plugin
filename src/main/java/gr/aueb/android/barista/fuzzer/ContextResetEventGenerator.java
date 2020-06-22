package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.model.*;

import java.util.ArrayList;
import java.util.List;

public class ContextResetEventGenerator implements EventGenerator {

    private List resetEmulatorCommands = new ArrayList();

    public ContextResetEventGenerator(String token) {
        Command wifiOn = new SvcWifi(token, true);
        Command dataOn = new SvcData(token, true);
        Command gpsOn = new GpsState(token, true);

        resetEmulatorCommands.add(wifiOn);
        resetEmulatorCommands.add(dataOn);
        resetEmulatorCommands.add(gpsOn);
    }

    @Override
    public List<Command> generate() {
        return resetEmulatorCommands;
    }
}
