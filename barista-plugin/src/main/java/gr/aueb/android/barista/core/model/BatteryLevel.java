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

public class BatteryLevel extends AbstractAdbCommand {
    private int level;
    private static final String DUMPSYS_BATTERY_LEVEL = "shell dumpsys battery set level ";

    public BatteryLevel() {

    }

    public BatteryLevel(String sessionToken, int level) {
        super(sessionToken);
        this.level = level;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(DUMPSYS_BATTERY_LEVEL)
                .append(" ")
                .append(Integer.toString(level));

        String command = buffer.toString();
        return command;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
