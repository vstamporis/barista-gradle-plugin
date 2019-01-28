package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.adb.ADBClient;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutorImpl implements CommandExecutor {

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
