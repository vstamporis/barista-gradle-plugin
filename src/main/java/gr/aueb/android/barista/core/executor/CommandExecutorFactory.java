/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: CommandExecutorFactory
 * Role: Singleton class that constructs and provides a CommandExecutor instance.
 * Description: CommandExecutorFactory builds a CommandExecutor that contains an ADBCommandClient and a TelnetCommand Client.
 *
 *
 */
package gr.aueb.android.barista.core.executor;

public class CommandExecutorFactory {

    private static CommandExecutor theInstance;
    private static CommandClient adbClient = new ADBCommandClient();
    private static CommandClient telnetClient = new TelnetCommandClient();

    /**
     *  If the instance of CommandExecutorFactory is initilized then it is returned, else
     *  the CommandExecutorFactory is constructed and its newly created instance is returned.
     * @return
     */
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
