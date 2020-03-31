package gr.aueb.android.barista.fuzzer;

import gr.aueb.android.barista.core.model.Command;

import java.util.List;

public interface EventGenerator {

    List<Command> generate();
}
