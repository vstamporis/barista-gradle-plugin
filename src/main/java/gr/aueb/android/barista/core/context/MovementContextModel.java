package gr.aueb.android.barista.core.context;

import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.Random;

public abstract class MovementContextModel implements ContextModel {

    private Random random;

    protected String token;

    public MovementContextModel(String token) {
        this.token = token;
        this.random = new Random();
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

    protected int generateRandomInt(int min, int max) {

        int number = this.random.nextInt((max - min) + 1) + min;

        return number;
    }
}
