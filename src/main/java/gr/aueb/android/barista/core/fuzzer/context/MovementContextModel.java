package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.utilities.BaristaLogger;

public abstract class MovementContextModel extends ContextModel {

    public MovementContextModel(String token) {
        super(token);
    }

    protected double generateRandomLatitude() {
        double randomValue = -90 + (90 - (-90)) * this.random.nextDouble();
        randomValue = Double.parseDouble(String.format("%.6f", randomValue));
        BaristaLogger.print("Random double: " + randomValue);
        return randomValue;
    }

    protected double generateRandomLongitude() {
        double randomValue = -180 + (180 - (-180)) * this.random.nextDouble();
        randomValue = Double.parseDouble(String.format("%.6f", randomValue));
        BaristaLogger.print("Random double: " + randomValue);
        return randomValue;
    }
}
