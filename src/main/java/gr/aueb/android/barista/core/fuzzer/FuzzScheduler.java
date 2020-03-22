package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;

public class FuzzScheduler {

    private Command monkey;

    private int duration, count, throttle;
    private String apk, token;

    public FuzzScheduler(int duration, int count, int throttle, String apk) {
        this.duration = duration;
        this.count = count;
        this.throttle = throttle;
        this.apk = apk;
    }

    public void startFuzz() {
        int seed = (int)(Math.random()*100);
        this.monkey = new Monkey(this.token, seed, this.count, this.throttle, this.apk);

        CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        this.monkey = new Monkey(token, seed, count, throttle, apk);
        executor.executeAdbCommand(this.monkey);
    }
}
