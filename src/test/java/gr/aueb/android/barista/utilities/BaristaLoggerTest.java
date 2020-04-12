package gr.aueb.android.barista.utilities;

import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.LogcatCrash;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaristaLoggerTest {

    @Test
    public void writeCrashLog() {

        CommandExecutor executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        LogcatCrash crash = new LogcatCrash(token, "com.example.stsisko.helloworldgradle");

        executor.executeCommand(crash);

        BaristaLogger.writeCrashLog(crash.getCrashLog());
    }
}