package gr.aueb.android.barista.core.context;

import gr.aueb.android.barista.core.model.Command;

public class RandomConnectivityModel extends ConnectivityContextModel {

    public RandomConnectivityModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        return possibleCommands.get(this.generateRandomInt(1, 4));
    }
}
