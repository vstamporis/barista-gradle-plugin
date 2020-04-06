package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.ADBCommandClient;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.model.AbstractAdbCommand;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Monkey extends AbstractAdbCommand {

    private String command;

    private boolean completed;

    public Monkey() {

    }

    public Monkey(String sessionToken, String command) {
        super(sessionToken);
        this.command = command;
        this.completed = false;
    }

    @Override
    public String getCommandString() {
        return this.command;
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                return line.contains("Monkey aborted due to error") || line.contains("Monkey finished") || line.contains("CRASHED");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {
                completed = true;
            }
        });
    }

    @Override
    public boolean isCompleted(CommandClient client) {
        BaristaLogger.print("Completed");
        return this.completed;
    }

}
