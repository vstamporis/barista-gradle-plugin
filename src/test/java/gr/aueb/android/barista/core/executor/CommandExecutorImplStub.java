package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutorImplStub implements CommandExecutor {

    public List<Command> commands = new ArrayList<>();

    @Override
    public void executeAdbCommand(Command command) {
        BaristaLogger.print("EXECUTOR-STUB: executing ADB command");
        String commandStr = command.getCommandString();
        String sessionToken = command.getSessionToken();

        String finalADBCommand = "adb -s "+sessionToken+" "+commandStr;
        BaristaLogger.print(finalADBCommand);
    }

    @Override
    public void executeTelnetCommand(Command command) {
        BaristaLogger.print("EXECUTOR-STUB: executing Telnet command");
    }

    @Override
    public void executeCommands(List<Command> commandList) {
        commands.addAll(commandList);
    }

    @Override
    public void executeCommand(Command cmd) {
        BaristaLogger.print("EXECUTOR STUB - exxecute command: "+cmd.getCommandString());
        commands.add(cmd);
        cmd.accept(this);
    }

    @Override
    public void setAdbCommandClient(CommandClient adbCommandClient) {

    }

    @Override
    public void setTelnetCommandClient(CommandClient adbCommandClient) {

    }
}
