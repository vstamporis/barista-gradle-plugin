package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GeoFix;

public class FuzzMovementModel extends MovementContextModel {

    public FuzzMovementModel(String token) {
        super(token);
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        return new GeoFix(this.token, generateRandomLatitude(), generateRandomLongitude());
    }
}
