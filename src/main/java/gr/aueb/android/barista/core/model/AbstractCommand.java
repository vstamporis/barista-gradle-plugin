package gr.aueb.android.barista.core.model;


import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.stream.Stream;

/**
 *  AbstractCommand is an abstract class that implemnts the Command interface providing implementation only for functions
 *  that behave the same for all types of commands
 */
public abstract class AbstractCommand implements Command{

    /**
     *  Memebr field that stores the sessionToken of the command.
     *  The sessionToken can be assigned by the BaristaLibrary
     *  or by another Command that uses another instance to verify its execution.
     *
     */
    private String sessionToken;

    /**
     *  Member fied that represents a delay ammount in ms
     */
    private int delay = 0;

    /**
     * Empty Constructor
     */
    public AbstractCommand(){

    }

    /**
     *  Constructor
     *
     * @param sessionToken The sessionToken
     */
    public AbstractCommand(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /**
     *  Getter function that returns the sessionToken encapsulated inside a command. The sessionToken is used
     *  to determine the emulator that the command request is originated.
     *
     * @return The SessionToken
     */
    @Override
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Setter method for the sessionToken member field
     *
     * @param sessionToken The sessionToken field
     */
    @Override
    public  void setSessionToken(String sessionToken){ this.sessionToken = sessionToken;}

    /**
     *  Function that returns the string representation of a command. This string will be used to create
     *  a system process for execution ;
     *
     * @return The command to be executed in the command line
     */
    @Override
    public abstract String getCommandString();

    /**
     *  Function that allows a CommandExecutor to be injected at a Command instance in order for it to decide
     *  which CommandClient to use depending on its type (adb or telnet)
     *
     * @param executor A CommandExecutor instance that knows hoow to execute telnet and adb commands.
     */
    @Override
    public void accept(CommandExecutor executor) {

    }

    /**
     *  Function that returns the ammount of the delay (in ms) that the execution thread should wait after
     *  the execution of a command. Delay is used when one wants to create technical delay between execution
     *  of many commands in a row
     *
     * @return The delay ammount in ms
     */
    @Override
    public int getDelay(){
        return this.delay;
    }

    /**
     * Specify the delay to be applied to the execution thread after the execution of this command.
     *
     * @param delay The delay ammount in ms
     */
    @Override
    public void setDelay(int delay){
        this.delay = delay;
    }

    /**
     * Function that determins if the command is executed successfully or not. Each command should implement this function
     * in order to verify its successful execution. If a subclass of this object doesnt override this method a default message
     * stating that sucseful execution is ignored.
     */
    @Override
    public boolean isCompleted(CommandClient client) {
        BaristaLogger.print("Not checking for successfully execution of command. ");
        return true;
    }

    /**
     * Function that parses the result of the execution. The result may be used for setting variables.
     * It it mostly used for commands that retrieve information from the emulator.
     *
     * @param resultLines The lines returned from the system after execution of the command
     */
    @Override
    public void parseResult(Stream<String> resultLines) {
        BaristaLogger.print("Using Default Result Parser. Output of command is ignored");
    }


}
