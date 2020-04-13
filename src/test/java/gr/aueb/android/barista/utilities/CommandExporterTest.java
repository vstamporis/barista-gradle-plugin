package gr.aueb.android.barista.utilities;

import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.SvcWifi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandExporterTest {

    @Test
    public void export() {
        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        SvcWifi wifi = new SvcWifi(token, true);

        List<Command> commandList = new ArrayList<>();

        commandList.add(wifi);

        CommandExporter.export(commandList);
    }
}