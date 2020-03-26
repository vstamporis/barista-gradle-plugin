package gr.aueb.android.barista.core.fuzzer.context;

import autovalue.shaded.com.google$.common.collect.$PeekingIterator;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.BatteryCharge;
import gr.aueb.android.barista.core.model.BatteryLevel;
import gr.aueb.android.barista.core.model.BatteryStatus;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class BatteryDrainModel extends ContextModel {

    private Command batteryCharge;
    private Command batteryLevel;
    private Command batteryStatus;
    private String token;

    public BatteryDrainModel(String token) {
        super();
        this.token = token;
    }

    @Override
    public void execute() {
        new Thread(() -> {
            while (!this.stop) {
                this.batteryStatus = new BatteryStatus(this.token);
                executeCommand(this.batteryStatus);

                if (((BatteryStatus) this.batteryStatus).getLevel() < 10) {
                    this.batteryCharge = new BatteryCharge(this.token, true);
                    executeCommand(this.batteryCharge);
                }
                if (((BatteryStatus) this.batteryStatus).getLevel() == 100) {
                    this.batteryCharge = new BatteryCharge(this.token, false);
                    executeCommand(this.batteryCharge);
                }

                if (!((BatteryStatus) this.batteryStatus).getChargingStatus()) {
                    this.batteryStatus = new BatteryStatus(token);
                    executeCommand(this.batteryStatus);
                    int currentLevel = ((BatteryStatus) this.batteryStatus).getLevel();
                    int finalLevel = currentLevel - this.generateRandomInt(0, 4);

                    this.batteryLevel = new BatteryLevel(this.token,finalLevel);
                    executeCommand(this.batteryLevel);
                }
                try {
                    Thread.sleep(this.generateRandomInt(15, 45)*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void executeCommand(Command cmd) {
        CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
        executor.executeCommand(cmd);
        BaristaLogger.print("Executing battery command");
    }

}
