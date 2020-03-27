package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class RandomConnectivityModel extends ConnectivityContextModel {

    public RandomConnectivityModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public void execute() {
        new Thread(() -> {
            CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
            while (!this.stop) {
                executor.executeCommand(possibleCommands.get(this.generateRandomInt(1, 4)));
                BaristaLogger.print("Executing Random Wifi/Data");
                try {
                    Thread.sleep(this.generateRandomInt(10, 60)*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
