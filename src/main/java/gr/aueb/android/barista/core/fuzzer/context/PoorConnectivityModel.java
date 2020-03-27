package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class PoorConnectivityModel extends ConnectivityContextModel {

    public PoorConnectivityModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    @Override
    public void execute() {
        new Thread(() -> {
            CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
            while (!this.stop) {
                executor.executeCommand(possibleCommands.get(this.generateRandomInt(1, 4)));
                BaristaLogger.print("Executing Wifi/Data");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
