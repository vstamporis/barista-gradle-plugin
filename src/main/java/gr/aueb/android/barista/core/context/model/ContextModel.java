package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;

public interface ContextModel {

    Command next(long elapsedTimeMillis);
}
