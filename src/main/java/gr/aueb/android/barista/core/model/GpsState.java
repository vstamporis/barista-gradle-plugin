package gr.aueb.android.barista.core.model;

import java.util.Hashtable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

@JsonTypeName("GpsState")
public class GpsState extends AbstractAdbCommand {

    private boolean enabled;
    private static final Map<String, Boolean> possibleStatusDescription;

    static{
        possibleStatusDescription = new Hashtable<>();
        possibleStatusDescription.put("ENABLED", true);
        possibleStatusDescription.put("DISABLED", false);
    }

    public GpsState() {

    }

    public GpsState(String sessionToken, boolean enabled) {
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
        buffer.append(BaristaCommandPrefixes.GPS_STATUS).append(" ").append(((enabled)? "+gps" : "-gps"));
        String command = buffer.toString();
        return command;
    }

    @Override
    public boolean isCompleted(CommandClient client){
        GpsStatus gpsStatus = new GpsStatus(this.getSessionToken());
        client.executeCommand(gpsStatus);
        Boolean result = possibleStatusDescription.get(gpsStatus.getStatus());
        if(result == enabled ){
            BaristaLogger.print("Completed");
            return true;
        }
        return true;
    }
}
