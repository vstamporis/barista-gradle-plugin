package gr.aueb.android.barista.plugin;

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.fuzzer.MonkeyEventGenerator;
import gr.aueb.android.barista.core.model.Monkey;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
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

    @TaskAction
    public void action() {
        BaseAppModuleExtension android = (BaseAppModuleExtension) this.getProject().getExtensions().findByName("android");
        this.apk = android.getDefaultConfig().getApplicationId();

        MonkeyEventGenerator monkey = new MonkeyEventGenerator(this.seed, this.count, this.throttle, this.apk);
        monkey.startMonkeyFuzzing();
    }

}
