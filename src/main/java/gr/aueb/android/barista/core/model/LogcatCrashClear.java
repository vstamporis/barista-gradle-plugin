package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class LogcatCrashClear extends AbstractAdbCommand {

    public LogcatCrashClear(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.LOGCAT_CRASH + " -c";
    }
}
