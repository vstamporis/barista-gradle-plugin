package gr.aueb.android.barista.core.context;

import gr.aueb.android.barista.core.model.Command;

public class FuzzGPSModel extends ConnectivityContextModel {

    public FuzzGPSModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        return possibleCommands.get(this.generateRandomInt(5, 6));
    }
}
