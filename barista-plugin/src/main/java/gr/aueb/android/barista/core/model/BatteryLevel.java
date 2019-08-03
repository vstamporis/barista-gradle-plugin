/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryLevel
 * Role: Implements the Command that sets the level of the battery of the emulator
 * Description: When BatteryLevel is executed,  the level of the battery will change to the desired level.
 *              This commandd guarantees its execution.
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class BatteryLevel extends AbstractAdbCommand {

    /**
     *  Integer representation of the battry level. (0 - 100)
     */
    private int level;

    /**
     *  Empty constructor
     */
    public BatteryLevel() {

    }

    /**
     *  Constructor that initializes a BatteryLeve instance with the sesionToken and the desired battery level.
     *
     *  @param sessionToken The sessionToken
     *  @param level Integer value representing the desired battery level (0 - 100)
     */
    public BatteryLevel(String sessionToken, int level) {
        super(sessionToken);
        this.level = level;
    }

    /**
     *  Function that creates and returns the string representation of the adb command to be executed.
     *  In this case the command will be something like ' shell dumpsys battery level [level]'
     *
     * @return The String representation
     */
    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.DUMPSYS_BATTERY_LEVEL)
                .append(" ")
                .append(Integer.toString(level));

        String command = buffer.toString();
        return command;
    }

    /**
     *  Function that checks the succseful execution of the command by checking the internal properties of the emulator.
     *  To do so a CommandClient must be provided in order execute the BatteryState command.
     *
     * @param client A CommandClient implementation
     * @return True if the real battery level matches the desired battery level, otherwise false
     */
    @Override
    public boolean isCompleted(CommandClient client){
        BaristaLogger.print("Checking for successfully execution of command. ");
        BatteryStatus batteryStatus = new BatteryStatus(this.getSessionToken());
        client.executeCommand(batteryStatus);
        return (batteryStatus.getLevel() == this.level);

    }

    /**
     *  Get battery level assigned to the coomand
     *
     *  @return The integer representaion of the battery level value (0-100)
     */
    public int getLevel() {
        return level;
    }

    /**
     *  Set battery level value to the command
     *
     *  @param level The integer representation of the battery level (0-100)
     */
    public void setLevel(int level) {
        this.level = level;
    }

}
