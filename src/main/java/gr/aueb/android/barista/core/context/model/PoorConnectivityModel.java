package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;

public class PoorConnectivityModel extends ConnectivityContextModel {

    public PoorConnectivityModel(String token) {
        super(token);
        this.initiateConnectivityCommands();
    }

    /*@Override
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
    }*/

    @Override
    public Command next(long elapsedTimeMillis) {
        return possibleCommands.get(this.generateRandomInt(1, 4));
    }
}
