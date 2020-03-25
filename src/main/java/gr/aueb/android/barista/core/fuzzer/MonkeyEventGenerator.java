package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Monkey;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class MonkeyEventGenerator {

    private int seed, count, throttle;
    private String apk;
    private Monkey monkey;

    public MonkeyEventGenerator(int seed, int count, int throttle, String apk) {
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

    private String createMonkeyCommand() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.MONKEY).append(" ").append("-p ").append(this.apk);
        buffer.append(" ").append("-s ").append(this.seed);
        buffer.append(" ").append("-v ");
        buffer.append(" ").append("--throttle ").append(this.throttle).append(" ").append(this.count);
        String command = buffer.toString();
        return command;
    }

    public void startMonkeyFuzzing() {
        CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        this.monkey = new Monkey(token, this.createMonkeyCommand());
        executor.executeAdbCommand(this.monkey);
    }
}
