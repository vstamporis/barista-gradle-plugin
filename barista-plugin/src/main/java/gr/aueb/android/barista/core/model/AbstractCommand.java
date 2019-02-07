package gr.aueb.android.barista.core.model;


import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.executor.CommandExecutor;

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
    public boolean isCompleted(CommandClient client) {
        return true;
    }

    @Override
    public void parseResult(Stream<String> resultLines) {

    }


}
