/**
 * Author: Tsiskomichelis Stelios
 * Created On: 20/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: HttpServerManager
 * Role: Manges the operation of the Barista HTTP Server
 * Description: It can start and stop the HTTP Server.
 */
package gr.aueb.android.barista.server;

import gr.aueb.android.barista.core.BaristaConfigurationExtension;
import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.core.emulator.helpers.TestMonitor;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class HttpServerManager {


    private HttpServer serverInstance;
    private int port = 8070;
    private String host = "192.168.0.1";

    private static HttpServerManager theInstance;

    public static HttpServerManager getInstance(){
        if (theInstance == null){
            theInstance = new HttpServerManager();
        }
        return theInstance;
    }

    public void setConfiguration(BaristaConfigurationExtension configuration){
        this.host = configuration.getHost();
        this.port = configuration.getPort();
        endpoint = String.format(endpoint, host, Integer.toString(port));
    }

    // Base URI the HTTP server will listen on
    private String endpoint = "http://%s:%s/barista/";

    /**
     *  Starts Grizzly HTTP server exposing the REST API defined in the CommandResource class.
     *  When the server start, the EmulatorManager is instantiated for the first time. It also starts
     *  a monitoring procedure in order to keep track of the active testing sessions.
     *
     */
    public void  startServer() {

        // initialize Barista Configuation
        final ResourceConfig application = new BaristaApplication();

         // check if a previous instance is still active and shut it down
        //  propably unreached code
        if(serverInstance !=null){
            // if for any reason server instance is running (not null) then shut down
            serverInstance.shutdownNow();

        }

        // first initialization of ADBClient where emulators are recognized
        //todo must migrate the role of device manager to another class
        EmulatorManager.getManager();
        EmulatorManager.revalidate();

        // initialize TestMonitor
        TestMonitor.setRunningTests(EmulatorManager.getManager().getConnectedDevices().size());

        // create and start a new instance of grizzly http server exposing the Jersey application at endpoint
        try {
            serverInstance = GrizzlyHttpServerFactory.createHttpServer(URI.create(endpoint), application);
        }
        catch (Exception e ){
            BaristaLogger.print("Failed to start server. Try "+ endpoint +"/kill");

        }

    }

    /**
     *  When this method is called, it assumed that one of the running tests has finished. This results in decreasing
     *  the counter of active tests.
     *  If all running tests are finished and then shuts down the server.
     *
     */
    public void stopServer(){

        BaristaLogger.print("Signal to kill Server. Current tests:"+ TestMonitor.getRuningTests());
        //todo change test check role

        TestMonitor.testFinished();

        if(! TestMonitor.hasActiveTests()) {
            BaristaLogger.print("Last Test finished. Gracefully stopping server in 5 seconds");
            serverInstance.shutdown(5, TimeUnit.SECONDS);
        }else{
            BaristaLogger.print("Test finished. Remaining: "+TestMonitor.getRuningTests());
        }
    }


    /**
     * Get the base URI of the server
     * @return the string representation oth the server URI
     */
    public String getEndpoint() {
        return endpoint;
    }


    /**
     *  Function that kills the server without checking if there are active tests running.
     */
    public void forceKillServer() {
        BaristaLogger.print("Force Stop Server");
        serverInstance.shutdownNow();
    }
}