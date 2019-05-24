package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;

import java.util.List;

public class CommandExecutorImpl implements CommandExecutor {

    //todo try COMMAND_TYPE approach 
    CommandClient adbCommandClient;
    CommandClient telnetCommandClient;


    @Override
    public void executeAdbCommand(Command command) {
        adbCommandClient.executeCommand(command);
    }

    @Override
    public void executeTelnetCommand(Command command) {
        telnetCommandClient.executeCommand(command);
    }

    @Override
    public void executeCommands(List<Command> commandList) {
        for(Command cmd: commandList){
            cmd.accept(this);
            if(cmd.getDelay() != 0){
                try {
                    Thread.sleep(cmd.getDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void executeCommand(Command cmd) {

        cmd.accept(this);


    }

    @Override
    public void setAdbCommandClient(CommandClient adbCommandClient) {
        this.adbCommandClient = adbCommandClient;
    }

    @Override
    public void setTelnetCommandClient(CommandClient telnetCommandClient) {
        this.telnetCommandClient = telnetCommandClient;
    }
}
