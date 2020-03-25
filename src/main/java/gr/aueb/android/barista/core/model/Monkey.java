package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.ADBCommandClient;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.model.AbstractAdbCommand;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class Monkey extends AbstractAdbCommand {

    private static final int DEFAULT_THROTTLE = 1000;

    private String command;

    public Monkey() {

    }

    public Monkey(String sessionToken, String command) {
        super(sessionToken);
        this.command = command;
    }

    @Override
    public String getCommandString() {
        return this.command;
    }

    @Override
    public boolean isCompleted(CommandClient client) {
        BaristaLogger.print("Completed");
        return true;
    }
}
