package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.executor.ADBCommandClient;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.model.AbstractAdbCommand;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class Monkey extends AbstractAdbCommand {

    private static final int DEFAULT_THROTTLE = 1000;

    private int seed, count, throttle;
    private String apk;

    public Monkey() {

    }

    public Monkey(String sessionToken, int seed, int count, int throttle, String apk) {
        super(sessionToken);
        this.seed = seed;
        this.count = count;
        this.throttle = throttle;
        this.apk = apk;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.MONKEY).append(" ").append("-p ").append(this.apk);
        buffer.append(" ").append("-s ").append(this.seed);
        buffer.append(" ").append("-v ");
        buffer.append(" ").append("--throttle ").append(this.throttle).append(" ").append(this.count);;
        String command = buffer.toString();
        return command;
    }

    @Override
    public boolean isCompleted(CommandClient client) {
        BaristaLogger.print("Completed");
        return true;
    }
}
