package gr.aueb.android.barista.core.fuzzer.context;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.emulator.EmulatorManager;

import java.util.Random;

public abstract class ContextModel {

    public abstract void execute();

    public String getToken() {
        return EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
    }

    public CommandExecutorImpl getCommandExecutor() {
        return (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    public int generateRandomInt(int min, int max) {
        Random random = new Random();

        int number = random.nextInt((max - min) + 1) + min;

        return number;
    }
}
