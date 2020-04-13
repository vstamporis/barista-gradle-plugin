package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.CommandExporter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandImporterTest {

    @Test
    public void getCommandList() {

        String token = EmulatorManager.getManager().getTokenMap().keySet().iterator().next();

        Command wifi = new SvcWifi(token, true);

        List<Command> commandList = new ArrayList<>();

        commandList.add(wifi);

        CommandExporter.export(commandList);

        CommandImporter importer = new CommandImporter("barista-commands.json", token);

        List<Command> commands = importer.getCommandList();

        Assert.assertNotNull(commands);
    }
}