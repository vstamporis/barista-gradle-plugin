package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;

public class PoorConnectivityModel extends ConnectivityContextModel {

    public PoorConnectivityModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        return possibleCommands.get(this.generateRandomInt(1, 4));
    }
}
