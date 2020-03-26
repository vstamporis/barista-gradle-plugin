package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.SvcData;
import gr.aueb.android.barista.core.model.SvcWifi;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.HashMap;
import java.util.Map;

public class RandomConnectivityModel extends ContextModel {

    private Map<Integer, Command> possibleCommands;

    public RandomConnectivityModel(String token) {
        super();
        this.possibleCommands = new HashMap<>();
        this.possibleCommands.put(1, new SvcWifi(token, true));
        this.possibleCommands.put(2, new SvcWifi(token, false));
        this.possibleCommands.put(3, new SvcData(token, true));
        this.possibleCommands.put(4, new SvcData(token, false));
    }

    @Override
    public void execute() {
        new Thread(() -> {
            while (!this.stop) {
                CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
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
