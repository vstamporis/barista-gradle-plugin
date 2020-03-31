package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GeoFix;

public class FuzzMovementModel extends MovementContextModel {

    private GeoFix geoFix;

    public FuzzMovementModel(String token) {
        super(token);
    }

    /*@Override
    public void execute() {
        new Thread(() -> {
            CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
            while (!this.stop) {
                this.geoFix = new GeoFix(this.token, generateRandomLatitude(), generateRandomLongitude());
                executor.executeCommand(this.geoFix);
                BaristaLogger.print("Executing fuzz movement");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

    @Override
    public Command next(long elapsedTimeMillis) {
        return new GeoFix(this.token, generateRandomLatitude(), generateRandomLongitude());
    }
}
