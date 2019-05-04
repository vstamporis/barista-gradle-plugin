package gr.aueb.android.barista.core.model;


import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.stream.Stream;

public abstract class AbstractCommand implements Command{

    public AbstractCommand(){

    }

    public AbstractCommand(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    private String sessionToken;

    @Override
    public String getSessionToken() {
        return sessionToken;
    }

    @Override
    public  void setSessionToken(String sessionToken){ this.sessionToken = sessionToken;}

    @Override
    public abstract String getCommandString();

    @Override
    public void accept(CommandExecutor executor) {

    }

    @Override
    /**
     * Function that determins if the command is executed successfully or not. Each command should implement this function
     * in order to verify its successful execution.
     */
    public boolean isCompleted(CommandClient client) {
        BaristaLogger.print("Not checking for successfully execution of command. ");
        return true;
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        BaristaLogger.print("Using Default Result Parser. Output of command is ignored");
    }


}
