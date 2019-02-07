/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: ADBCommandClient
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.emulator.adb.ADBClient;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ADBCommandClient implements CommandClient {

    private final String ADB_COMMAND = "adb";

    //todo use interface for result - for size now
    @Override
    public void executeCommand(Command cmd) {
        // find emulator by command.getSessionId()

        String token = cmd.getSessionToken();
        String strCommand = cmd.getCommandString();
        String deviceId = EmulatorManager.getManager().verifyToken(token);
        // execute command
        BaristaLogger.print("Must execute ADB command: "+strCommand+" for emulator: "+deviceId);

        List<String> commandList = new ArrayList<>();
        commandList.add(ADB_COMMAND);
        commandList.add("-s");
        commandList.add(deviceId);

        String[] coreCommand = strCommand.split(" ");
        for (String subCommand: coreCommand) {
            commandList.add(subCommand);
        }

        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            //pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.command(commandList);
            Process p = pb.start();
            p.waitFor();
            //read the output
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Stream<String> resultStream = output.lines();
            cmd.parseResult(resultStream);

            output.close();

        } catch (IOException e) {
            BaristaLogger.print("Exception occured: "+e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
