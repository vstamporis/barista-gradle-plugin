package gr.aueb.android.barista.core.executor;

import gr.aueb.android.barista.core.model.Command;

public interface CommandClient {

    void executeCommand(Command cmd);

}
