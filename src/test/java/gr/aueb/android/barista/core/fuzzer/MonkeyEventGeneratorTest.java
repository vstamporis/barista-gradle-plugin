package gr.aueb.android.barista.core.fuzzer;

import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.rest.dto.MonkeyDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonkeyEventGeneratorTest {

    private static CommandExecutorImpl executor;

    @BeforeClass
    public static void setup(){
        executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
    }

    @Test
    public void testMonkey() {

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        Command monkey = new MonkeyEventGenerator(token, 1234, 100, 1000, "net.osmtracker");
        executor.executeAdbCommand(monkey);

    }

}
