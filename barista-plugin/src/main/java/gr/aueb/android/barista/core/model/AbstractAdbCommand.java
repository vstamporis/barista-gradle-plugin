package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutor;

public abstract class AbstractAdbCommand extends AbstractCommand {


    public AbstractAdbCommand(){

    }

    public AbstractAdbCommand(String sessionToken){
        super(sessionToken);
        
    }

    @Override
    public void accept(CommandExecutor executor) {
        executor.executeAdbCommand(this);
    }
}
