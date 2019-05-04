/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryLevel
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class BatteryLevel extends AbstractAdbCommand {
    private int level;

    public BatteryLevel() {

    }

    public BatteryLevel(String sessionToken, int level) {
        super(sessionToken);
        this.level = level;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.DUMPSYS_BATTERY_LEVEL)
                .append(" ")
                .append(Integer.toString(level));

        String command = buffer.toString();
        return command;
    }

    @Override
    public boolean isCompleted(CommandClient client){
        BaristaLogger.print("Checking for successfully execution of command. ");
        BatteryStatus batteryStatus = new BatteryStatus(this.getSessionToken());
        client.executeCommand(batteryStatus);
        return (batteryStatus.getLevel() == this.level);

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
