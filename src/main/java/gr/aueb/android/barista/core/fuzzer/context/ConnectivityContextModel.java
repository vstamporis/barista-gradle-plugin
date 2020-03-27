package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.SvcData;
import gr.aueb.android.barista.core.model.SvcWifi;

import java.util.HashMap;
import java.util.Map;

public abstract class ConnectivityContextModel extends ContextModel {

    protected Map<Integer, Command> possibleCommands;

    public ConnectivityContextModel(String token) {
        super(token);
    }

    protected void initiateConnectivityCommands() {
        this.possibleCommands = new HashMap<>();
        this.possibleCommands.put(1, new SvcWifi(token, true));
        this.possibleCommands.put(2, new SvcWifi(token, false));
        this.possibleCommands.put(3, new SvcData(token, true));
        this.possibleCommands.put(4, new SvcData(token, false));
    }
}
