package gr.aueb.android.barista.plugin;

import gr.aueb.android.barista.core.BaristaConfigurationExtension;
import gr.aueb.android.barista.server.HttpServerManager;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class BaristaServerStartTask extends DefaultTask {

    private BaristaConfigurationExtension config;
    public static final String NAME = "startBaristaServer";

    @TaskAction
    public void action(){

        config = getProject().getExtensions().findByType(BaristaConfigurationExtension.class);

        HttpServerManager serverManager = HttpServerManager.getInstance();
        serverManager.setConfiguration(config);

        //start the server on localhost
        BaristaLogger.print("Barista server listening at "+ serverManager.getEndpoint());
        serverManager.startServer();
    }


}
