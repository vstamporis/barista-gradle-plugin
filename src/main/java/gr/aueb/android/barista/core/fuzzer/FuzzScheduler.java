package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.Monkey;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.gradle.api.publish.ivy.internal.artifact.SingleOutputTaskIvyArtifact;

public class FuzzScheduler {

    private MonkeyEventGenerator monkey;
    private ContextEventGenerator context;

    private int count, throttle, batchSize;
    private String apk, token;

    public FuzzScheduler(int count, int throttle, int batchSize, String apk) {
        this.count = count;
        this.throttle = throttle;
        this.apk = apk;
        this.batchSize = batchSize;
    }

    public void startFuzz() {
        int seed = (int)(Math.random()*100);

        this.monkey = new MonkeyEventGenerator(seed, this.batchSize, this.throttle, this.apk);
        this.context = new ContextEventGenerator();

        this.context.startContextFuzzing();
        int epochs = this.count/this.batchSize;
        /*for (int i = 0; i < epochs; i++) {
            this.monkey.startMonkeyFuzzing();
        }
        this.context.stopContextFuzzing();*/
    }

}
