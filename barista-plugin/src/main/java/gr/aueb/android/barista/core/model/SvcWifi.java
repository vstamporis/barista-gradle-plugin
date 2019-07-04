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

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.Hashtable;
import java.util.Map;

public class SvcWifi extends AbstractAdbCommand {

    private boolean enabled;
    private  static final Map<String, Boolean> possibleStatusDescription;

    static{
        possibleStatusDescription = new Hashtable<>();
        possibleStatusDescription.put("CONNECTED/CONNECTED", true);
        possibleStatusDescription.put("DISCONNECTED/DISCONNECTED", false);
    }


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
        buffer.append(BaristaCommandPrefixes.SVC_WIFI).append(" ").append(((enabled)? "enable" : "disable"));
        String command = buffer.toString();
        return command;
    }

    @Override
    public boolean isCompleted(CommandClient client){
        SvcWifiStatus wifiStatus = new SvcWifiStatus(this.getSessionToken());
        client.executeCommand(wifiStatus);
        Boolean result = possibleStatusDescription.get(wifiStatus.getStatus());
        if(result != null ){
            return result;
        }
        return false;
    }
}
