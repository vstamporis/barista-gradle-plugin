package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class FuzzMovementModel extends MovementContextModel {

    private GeoFix geoFix;

    public FuzzMovementModel(String token) {
        super(token);
    }

    @Override
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
    }

}
