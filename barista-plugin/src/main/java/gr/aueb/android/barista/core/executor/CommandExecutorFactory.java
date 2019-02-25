package gr.aueb.android.barista.core.executor;

public class CommandExecutorFactory {

    private static CommandExecutor theInstance;
    private static CommandClient adbClient = new ADBCommandClient();
    private static CommandClient telnetClient = new TelnetCommandClient();

    public static CommandExecutor getCommandExecutor(){
        if (theInstance == null){
            theInstance = new CommandExecutorImpl();
            theInstance.setAdbCommandClient(adbClient);
            theInstance.setTelnetCommandClient(telnetClient);
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
