package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

@JsonTypeName("LogcatCrashClear")
public class LogcatCrashClear extends AbstractAdbCommand {

    public LogcatCrashClear(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.LOGCAT_CRASH + " -c";
    }
}
