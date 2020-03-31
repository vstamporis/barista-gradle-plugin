package gr.aueb.android.barista.core.context.factories;

import gr.aueb.android.barista.core.context.*;
import gr.aueb.android.barista.core.context.model.*;
import gr.aueb.android.barista.emulator.EmulatorManager;

public class ContextModelFactory {

    private String token;

    public ContextModelFactory(String token) {
        this.token = token;
    }

    public ContextModel getConnectivityModel(EnumTypes.ConnectivityType connectivityType) {
        switch (connectivityType) {
            case POOR:
                return new PoorConnectivityModel(this.token);
            case RANDOM:
                return new RandomConnectivityModel(this.token);
        }
        return null;
    }

    public ContextModel getNavigationModel(EnumTypes.NavigationType navigationType) {
        switch (navigationType) {
            case FUZZ:
                return new FuzzMovementModel(this.token);
            case RANDOM_WALK:
                return new RandomWalkModel(this.token);
            case DRIVING:
                return null;
        }
        return null;
    }

    public ContextModel getBatteryDrainModel() {
        return new BatteryDrainModel(this.token);
    }

}
