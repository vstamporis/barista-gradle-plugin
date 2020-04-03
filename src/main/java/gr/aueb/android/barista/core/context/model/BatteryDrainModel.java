package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.BatteryCharge;
import gr.aueb.android.barista.core.model.BatteryLevel;
import gr.aueb.android.barista.core.model.BatteryStatus;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.Random;

public class BatteryDrainModel implements ContextModel {

    private Command batteryLevel;
    private String token;

    public BatteryDrainModel(String token) {
        this.token = token;
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        if (this.batteryLevel == null) {
            this.batteryLevel = new BatteryLevel(this.token, 100);
            return this.batteryLevel;
        }

        Command temp = null;

        if (((BatteryLevel) this.batteryLevel).getLevel() < 10) {
            temp = new BatteryLevel(this.token, 100);
        }
        else {
            int currentLevel = ((BatteryLevel) this.batteryLevel).getLevel();
            int finalLevel = currentLevel - this.generateRandomInt(0, 4);

            temp = new BatteryLevel(this.token, finalLevel);
        }

        return temp;
    }

    private int generateRandomInt(int min, int max) {
        Random random = new Random();

        int number = random.nextInt((max - min) + 1) + min;

        return number;
    }
}
