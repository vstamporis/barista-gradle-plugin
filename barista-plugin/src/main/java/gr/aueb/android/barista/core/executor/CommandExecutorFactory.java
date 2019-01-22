package gr.aueb.android.barista.core.executor;

public class CommandExecutorFactory {

    private static CommandExecutor theInstance;

    public static CommandExecutor getInstance(){
        if (theInstance == null){
            theInstance = new CommandExecutorImpl();
        }
        return theInstance;
    }

    /**
     * For testing purposes
     * @param stub
     */
    public static void setStub(CommandExecutor stub){
        theInstance = stub;
    }

}
