package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.emulator.EmulatorManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogcatCrashTest {

    private static CommandExecutorImpl executor;

    private LogcatCrash crash;

    @BeforeClass
    public static void setup(){
        executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Before
    public void setLogcat() {
        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        crash = new LogcatCrash(token, "com.example.stsisko.helloworldgradle");
        executor.executeCommand(crash);
    }

    @Test
    public void hasCrashed() {
        Assert.assertTrue(crash.hasCrashed());
    }

    @Test
    public void getCrashLog() {
        Assert.assertNotNull(crash.getCrashLog());
        crash.getCrashLog().stream().forEach(System.out::println);
    }
}