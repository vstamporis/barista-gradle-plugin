/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryCharge
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

public class BatteryCharge extends AbstractAdbCommand {
    private boolean isPlugged;
    private static final String DUMPSYS_BATTERY_LEVEL = "shell dumpsys battery set ac ";

    public BatteryCharge() {

    }

    public BatteryCharge(String sessionToken, boolean isPlugged) {
        super(sessionToken);
        this.isPlugged = isPlugged;
    }

    @Override
    public String getCommandString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(DUMPSYS_BATTERY_LEVEL)
                .append(" ")
                .append(((isPlugged)? "1" : "0"));

        String command = buffer.toString();
        return command;
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean plugged) {
        isPlugged = plugged;
    }
}
