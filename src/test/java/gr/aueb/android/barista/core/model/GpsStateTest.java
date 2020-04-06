package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GpsStateTest {

    private static String token;
    private static CommandExecutor executor;

    @BeforeClass
    public static void setup (){
        executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
        token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
    }

    @Test
    public void enableGPS() {
        GpsState state = new GpsState(token, true);
        executor.executeCommand(state);

        GpsStatus status = new GpsStatus(token);
        executor.executeCommand(status);

        Assert.assertEquals("ENABLED", status.getStatus());
    }

    @Test
    public void disableGPS() {
        GpsState state = new GpsState(token, false);
        executor.executeCommand(state);

        GpsStatus status = new GpsStatus(token);
        executor.executeCommand(status);

        Assert.assertEquals("DISABLED", status.getStatus());
    }
}