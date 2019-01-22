package gr.aueb.android.barista.core.model;


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
    public abstract String getCommandString();
}
