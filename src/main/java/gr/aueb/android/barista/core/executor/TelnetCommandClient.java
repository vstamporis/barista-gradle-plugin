/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: TelnetCommandClient
 * Role: Executes telnet commands
 * Description: TelnetCommandClient implements the CommandClient interface and it responsible
 *               for executing all the telnet commands. It utilizes the TelnetConnectionManager for this puprose
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

    /**
     * Performs a telnet connection with the appropriate emulator. The connection port is specified by the sessionTken
     * of the command that is used to get the device id. From the device_id string derives the port number that the emulator
     * is listening for telnet connections. After succesful connection with the emulator the command is executed.
     * For now the there is no mechanism for telnet command verification, however the impact because of this is small
     * since the appliance of telnet commands is almost instant.
     *
     * @param cmd The telnet command to be executed
     */
    @Override
    public void executeCommand(Command cmd) {

        // get the emulator id using the sessionToken
        EmulatorManager client = EmulatorManager.getManager();
        String emulatorID = client.verifyToken(cmd.getSessionToken());


        if (emulatorID != null) {

            // export the port nu,ber from the deviceId String ex. emulator-5554. The number 5554 is the port number
            int emulatorPort = Integer.parseInt(emulatorID.split("-")[1]);

            // create the telnet authentication token located in the home directory of the user
            String homeDirectory = System.getProperty("user.home");
            if (homeDirectory == null) {
                BaristaLogger.print("Please set the home variable");

            }

            ConnectionManager connectionManager = ConnectionManager.createInstance(homeDirectory + File.separatorChar + ".emulator_console_auth_token");

            // authenticated connection to the emulator
            TelnetConnection telnetConnection = null;
            try {
                telnetConnection = connectionManager.connect(emulatorID, "localhost", emulatorPort);
            } catch (EmulatorException e) {
                e.printStackTrace();
            }

            // execute the command
            telnetConnection.command(cmd);
        }

    }
}
