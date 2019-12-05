/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryCharge
 * Role: Implements the Command that sets the charging state of the emulator
 * Description: When BatteryCharge is executed,  the state of the charging status of the emulator will change
 *              acoording to the specified status. This commandd guarantees its execution.
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class BatteryCharge extends AbstractAdbCommand {

    /**
     *  Boolean representation of the charging status
     */
    private boolean isPlugged;

    /**
     * Empty constructor
     */
    public BatteryCharge() {

    }

    /**
     *  Constructor that initializes a BatteryCharge instance with the sesionToken and the charging state.
     *
     *  @param sessionToken The sessionToken
     *  @param isPlugged Boolean value representing he charging status. True for charging, false for not charging
     */
    public BatteryCharge(String sessionToken, boolean isPlugged) {
        super(sessionToken);
        this.isPlugged = isPlugged;
    }

    /**
     *  This function returns the actual adb command string to be executed using the adb console.
     *  This is command is 'shell dumpsys battery set ac [1|0]'
     *
     *  @return The string representation of the addb command
     */
    @Override
    public String getCommandString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.DUMPSYS_CHARGE_STATUS)
                .append(" ")
                .append(((isPlugged)? "1" : "0"));

        String command = buffer.toString();
        return command;
    }

    /**
     *  Function that checks if the execution of the command is succesfull by checking internally the emulator's
     *  charging state. To do so it uses the command BatteryStatus and asserts the actual charging status with
     *  the expected chargig status.
     *
     *  @param client A Command client to use in order to execute the command for verification
     *  @return True if the the actual charging status equals with the expected one.
     */
    @Override
    public boolean isCompleted(CommandClient client){
        BaristaLogger.print("Checking for successfully execution of command. ");
        BatteryStatus batteryStatus = new BatteryStatus(this.getSessionToken());
        client.executeCommand(batteryStatus);
        return (batteryStatus.getChargingStatus() == this.isPlugged);

    }

    /**
     *  Get the charging state assigned to the command
     *
     *  @return True if is set to chrging, otherwise false
     */
    public boolean isPlugged() {
        return isPlugged;
    }

    /**
     *  Set the charging state to the command
     *
     * @param plugged The charging state (true:charging, false: not charging)
     */
    public void setPlugged(boolean plugged) {
        isPlugged = plugged;
    }
}
