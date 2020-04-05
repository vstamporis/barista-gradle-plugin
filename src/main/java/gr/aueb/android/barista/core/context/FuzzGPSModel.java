package gr.aueb.android.barista.core.context;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class FuzzGPSModel extends ConnectivityContextModel {

    public FuzzGPSModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        BaristaLogger.print("--------------" + this.generateRandomInt(5, 6));
        return possibleCommands.get(this.generateRandomInt(5, 6));
    }
}
