/**
 * Author: Tsiskomichelis Stelios
 * Created On: 16/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: TelnetTest
 * Role:
 * Description:
 */
package gr.aueb.android.barista.emulator.telnet;

import gr.aueb.android.barista.emulator.EmulatorException;
import gr.aueb.android.barista.emulator.telnet.ConnectionManager;
import gr.aueb.android.barista.emulator.telnet.TelnetConnection;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
@Deprecated
public class TelnetTest {

    /**
     * @precondition An android emultor with id emulator-5554 must be online on host machine
     * @throws EmulatorException
     */

    public void testTelnetConnection() throws EmulatorException {
               // Read emulator console auth token
        String homeDirectory = System.getProperty("user.home");
        if (homeDirectory == null){
            System.out.println("Please set the home variable");
            return;
        }

        ConnectionManager connectionManager = ConnectionManager.createInstance(homeDirectory + File.separatorChar + ".emulator_console_auth_token");
        // FIXME: By Default connects to a single emulator. Must find a way to identify the emulator that issues a request
        TelnetConnection telnetConnection  = connectionManager.connect("emulator-5554", "localhost", 5554);

        // test connection
        //assertTrue(telnetConnection.command(new Help()));
        //assertTrue(telnetConnection.command(new GeoFixCommand(69.52, 44.32)));

    }

}
