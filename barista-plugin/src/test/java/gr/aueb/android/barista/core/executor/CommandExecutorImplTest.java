package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.DataHelper;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.core.model.WmSize;
import gr.aueb.android.barista.emulator.adb.ADBClient;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandExecutorImplTest {

    private static CommandExecutorImpl executor;

    @BeforeClass
    public static void setup(){
        executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Test
    public void executeAdbCommand() {

        String  token = ADBClient.getInstance().getTokenMap().keySet().iterator().next();

        Command c = new WmSize(token,500,600,false, DimensionUnit.PIXEL);
        executor.executeAdbCommand(c);

    }

    @Test
    public void executeTelnetCommand() {
        String  token = ADBClient.getInstance().getTokenMap().keySet().iterator().next();

        Command c = new GeoFix(token, 62.5000, 79.000);
        executor.executeTelnetCommand(c);
    }

    @Test
    public void executeCommands() {
    }
}