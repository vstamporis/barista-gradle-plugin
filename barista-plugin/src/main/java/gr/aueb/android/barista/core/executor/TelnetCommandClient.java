/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: TelnetCommandClient
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorException;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.emulator.telnet.ConnectionManager;
import gr.aueb.android.barista.emulator.telnet.TelnetConnection;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.File;

public class TelnetCommandClient implements CommandClient {


    @Override
    public void executeCommand(Command cmd) {

        EmulatorManager client = EmulatorManager.getManager();
        String emulatorID = client.verifyToken(cmd.getSessionToken());

        if (emulatorID != null) {
            int emulatorPort = Integer.parseInt(emulatorID.split("-")[1]);
            String homeDirectory = System.getProperty("user.home");
            if (homeDirectory == null) {
                BaristaLogger.print("Please set the home variable");
                // return false;
            }

            ConnectionManager connectionManager = ConnectionManager.createInstance(homeDirectory + File.separatorChar + ".emulator_console_auth_token");

            TelnetConnection telnetConnection = null;
            try {
                telnetConnection = connectionManager.connect(emulatorID, "localhost", emulatorPort);
            } catch (EmulatorException e) {
                e.printStackTrace();
            }

            telnetConnection.command(cmd);
        }

    }
}
