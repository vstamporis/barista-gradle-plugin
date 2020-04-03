package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GpsState;
import gr.aueb.android.barista.core.model.SvcData;
import gr.aueb.android.barista.core.model.SvcWifi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class ConnectivityContextModel implements ContextModel {

    private String token;

    protected Map<Integer, Command> possibleCommands;

    public ConnectivityContextModel(String token) {
        this.token = token;
    }

    protected void initiateConnectivityCommands() {
        this.possibleCommands = new HashMap<>();
        this.possibleCommands.put(1, new SvcWifi(this.token, true));
        this.possibleCommands.put(2, new SvcWifi(this.token, false));
        this.possibleCommands.put(3, new SvcData(this.token, true));
        this.possibleCommands.put(4, new SvcData(this.token, false));
        this.possibleCommands.put(5, new GpsState(this.token, true));
        this.possibleCommands.put(6, new GpsState(this.token, false));
    }

    protected int generateRandomInt(int min, int max) {
        Random random = new Random();

        int number = random.nextInt((max - min) + 1) + min;

        return number;
    }
}
