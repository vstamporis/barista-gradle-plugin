package gr.aueb.android.barista.plugin;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.fuzzer.Monkey;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

public class MonkeyStartTask extends DefaultTask {

    private int seed, count, throttle;
    private String apk;

    public static final String NAME = "startMonkey";

    @Option(option = "seed", description = "Seed of monkey")
    public void setSeed(String seed) {
        this.seed = Integer.parseInt(seed);
    }

    @Input
    public int getSeed() {
        return seed;
    }

    @Option(option = "count", description = "Number of events")
    public void setCount(String count) {
        this.count = Integer.parseInt(count);
    }

    @Input
    public int getCount() {
        return count;
    }

    @Option(option = "throttle", description = "Throttle of testing")
    public void setThrottle(String throttle) {
        this.throttle = Integer.parseInt(throttle);
    }

    @Input
    public int getThrottle() {
        return throttle;
    }

    @Option(option = "apk", description = "Package to test")
    public void setApk(String apk) {
        this.apk = apk;
    }

    @Input
    public String getApk() {
        return apk;
    }

    @TaskAction
    public void action() {
        CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        Command monkey = new Monkey(token, seed, count, throttle, apk);
        executor.executeAdbCommand(monkey);
    }

}
