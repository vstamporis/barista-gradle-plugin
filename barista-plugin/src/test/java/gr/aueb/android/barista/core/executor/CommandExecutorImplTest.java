package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.*;
import gr.aueb.android.barista.emulator.EmulatorManager;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

// FIXME this test case needs a running emulator to work
public class CommandExecutorImplTest {

    private static CommandExecutorImpl executor;

    @BeforeClass
    public static void setup(){
        executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Test
    public void executeAdbCommand() {

        String  token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        Command c = new WmSize(token,500,600,false, DimensionUnit.PIXEL);
        executor.executeAdbCommand(c);

    }

    @Test
    public void executeDensityCommand() {

        String  token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        Command c = new WmDensity(token,600);
        executor.executeAdbCommand(c);

        Command c2 = new WmGetDensity(token);
        executor.executeAdbCommand(c2);
        assertEquals(600,((WmGetDensity) c2).getDensity());

    }

    @Test
    public void executeTelnetCommand() {
        String  token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        Command c = new GeoFix(token, 62.5000, 79.000);
        executor.executeTelnetCommand(c);
    }

    @Test
    public void executeSizeResetCommand(){
        String  token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();
        Command c = new WmSizeReset(token);
        executor.executeAdbCommand(c);
    }

    @Test
    public void executeCommands() {
    }
}