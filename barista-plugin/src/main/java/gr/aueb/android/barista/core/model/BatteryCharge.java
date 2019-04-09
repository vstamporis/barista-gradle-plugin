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

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class BatteryCharge extends AbstractAdbCommand {
    private boolean isPlugged;

    public BatteryCharge() {

    }

    public BatteryCharge(String sessionToken, boolean isPlugged) {
        super(sessionToken);
        this.isPlugged = isPlugged;
    }

    @Override
    public String getCommandString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.DUMPSYS_CHARGE_STATUS)
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
