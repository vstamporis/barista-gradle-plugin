package gr.aueb.android.barista.plugin;

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension;
import gr.aueb.android.barista.fuzzer.FuzzScheduler;
import gr.aueb.android.barista.fuzzer.MonkeyEventGenerator;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import java.util.ArrayList;
import java.util.List;

public class MonkeyStartTask extends DefaultTask {

    private int seed, epochs, throttle, eventsPerEpoch;

    private String conf;

    private List<String> contextModels = new ArrayList<>();

    public static final String NAME = "startMonkey";

    @Option(option = "events", description = "Events per epoch")
    public void setEventsPerEpoch(String eventsPerEpoch) {
        this.eventsPerEpoch = Integer.parseInt(eventsPerEpoch);
    }

    @Input
    public int getEventsPerEpoch() {
        return eventsPerEpoch;
    }

    @Option(option = "epochs", description = "Number of epochs")
    public void setEpochs(String epochs) {
        this.epochs = Integer.parseInt(epochs);
    }

    @Input
    public int getEpochs() {
        return epochs;
    }

    @Option(option = "throttle", description = "Throttle of testing")
    public void setThrottle(String throttle) {
        this.throttle = Integer.parseInt(throttle);
    }

    @Input
    public int getThrottle() {
        return throttle;
    }

    @Option(option = "config", description = "Config file of context testing")
    public void setConf(String conf) {
        this.conf = conf;
    }

    @Input
    public String getConf() {
        return conf;
    }

    @TaskAction
    public void action() {
        BaseAppModuleExtension android = (BaseAppModuleExtension) this.getProject().getExtensions().findByName("android");
        String apk = android.getDefaultConfig().getApplicationId();

        FuzzScheduler fuzzScheduler = new FuzzScheduler(this.epochs, this.throttle, this.eventsPerEpoch, apk, this.conf);
        fuzzScheduler.initialize(false, false);
        fuzzScheduler.start();
    }

}
