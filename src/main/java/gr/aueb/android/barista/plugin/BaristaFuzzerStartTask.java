package gr.aueb.android.barista.plugin;

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension;
import gr.aueb.android.barista.core.fuzzer.FuzzScheduler;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

public class BaristaFuzzerStartTask extends DefaultTask {

    private int count, throttle, size;
    private String apk;

    public static final String NAME = "startBaristaFuzzer";

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

    @Option(option = "batchSize", description = "Batch of monkey epoch")
    public void setBatchSize(String size) {
        this.size = Integer.parseInt(size);
    }

    @Input
    public int getBatchSize() {
        return size;
    }

    @TaskAction
    public void action() {
        BaseAppModuleExtension android = (BaseAppModuleExtension) this.getProject().getExtensions().findByName("android");
        this.apk = android.getDefaultConfig().getApplicationId();


        FuzzScheduler fuzzer = new FuzzScheduler(this.count, this.throttle, this.size, this.apk);
        fuzzer.startFuzz();
    }
}
