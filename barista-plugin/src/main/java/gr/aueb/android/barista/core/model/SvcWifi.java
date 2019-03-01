/**
 * Author: Tsiskomichelis Stelios
 * Created On: 1/3/2019
 * Project: barista-plugin
 * <p>
 * ClassName: SvcWifi
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

public class SvcWifi extends AbstractAdbCommand {
    private final String SVC_WIFI = "shell svc wifi";
    private boolean enabled;

    public SvcWifi(){

    }

    public SvcWifi(String sessionToken, boolean enabled) {
        super(sessionToken);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(SVC_WIFI).append(" ").append(((enabled)? "enable" : "disable"));
        String command = buffer.toString();
        return command;
    }
}
