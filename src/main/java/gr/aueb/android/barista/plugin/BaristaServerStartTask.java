package gr.aueb.android.barista.plugin;

import gr.aueb.android.barista.core.BaristaConfigurationExtension;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class BaristaServerStartTask extends DefaultTask {

    @TaskAction
    public void action(){

        BaristaConfigurationExtension settings = getProject().getExtensions().findByType(BaristaConfigurationExtension.class);
        BaristaLogger.print("Configuration <host>: " + settings.getHost());
        BaristaLogger.print("Configuration <port>: " + settings.getPort());

    }
}
