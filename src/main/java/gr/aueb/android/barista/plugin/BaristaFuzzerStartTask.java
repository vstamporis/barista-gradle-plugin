package gr.aueb.android.barista.plugin;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.fuzzer.FuzzScheduler;
import gr.aueb.android.barista.core.fuzzer.Monkey;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import javax.xml.ws.Action;

public class BaristaFuzzerStartTask extends DefaultTask {

    private int duration, seed, count, throttle;
    private String apk;

    public static final String NAME = "startBaristaFuzzer";

    @Option(option = "duration", description = "Duration of fuzzing in seconds")
    public void setDuration(String duration) {
        this.duration = Integer.parseInt(duration);
    }

    @Input
    public int getDuration() {
        return duration;
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
        FuzzScheduler fuzzer = new FuzzScheduler(this.duration, this.count, this.throttle, this.apk);

        fuzzer.startFuzz();
    }
}
