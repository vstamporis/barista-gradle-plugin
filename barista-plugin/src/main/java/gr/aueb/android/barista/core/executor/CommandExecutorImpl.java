package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;

import java.util.List;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void executeAdbCommand(Command command) {
        // find emulator by command.getSessionId()

        String strCommand = command.getCommandString();
        // execute command
    }

    @Override
    public void executeTelnetCommand(Command command) {

        // find emulator by command.getSessionId()
        String strCommand = command.getCommandString();

        // execute command through the ADBClient or the TelnetClient
    }

    @Override
    public void executeCommands(List<Command> commandList) {
        for(Command cmd: commandList){
            cmd.accept(this);
        }
    }
}
