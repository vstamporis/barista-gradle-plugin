package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

/**
 *  AbstractAdbCommand is an abstract class that extends the AbstractCommand class and implements
 *  the functionality of any adb command. This functionality is the ussage of ADBCommandClient as the
 *  executor.
 */
public abstract class AbstractAdbCommand extends AbstractCommand {

    /**
     *  Empty Constructor
     */
    public AbstractAdbCommand(){

    }

    /**
     *  Parametered Constructor
     * @param sessionToken The sessionToken
     */
    public AbstractAdbCommand(String sessionToken){
        super(sessionToken);
        
    }

    /**
     *  Implementation of the acccept() method that uses the adbCommandClient as the default executor for
     *  adb commands.
     * @param executor
     */
    @Override
    public void accept(CommandExecutor executor) {
        executor.executeAdbCommand(this);
    }
}
