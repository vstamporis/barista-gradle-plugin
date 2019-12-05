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

import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.emulator.helpers.TestMonitor;
import gr.aueb.android.barista.utilities.BaristaLogger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class HttpServerManager {

    private static HttpServer serverInstance;

    // Base URI the HTTP server will listen on
    // TODO Server URL must be specified from the plugin extension configuration input
    // todo better coding style
    private static String BASE_URI = "http://localhost:8040/barista/";

    /**
     *  Starts Grizzly HTTP server exposing the REST API defined in the CommandResource class.
     *  When the server start, the EmulatorManager is instantiated for the first time. It also starts
     *  a monitoring procedure in order to keep track of the active testing sessions.
     *
     */
    public static void  startServer() {

        // initialize Barista Configuation
        final ResourceConfig baristaConfiguration = new BaristaApplication();

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

        // create and start a new instance of grizzly http server exposing the Jersey application at BASE_URI
        try {
            serverInstance = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), baristaConfiguration);
        }
        catch (Exception e ){
            BaristaLogger.print("Failed to start server. Try "+BASE_URI+"/kill");

        }

    }

    /**
     *  Create the server configuration
     *
     * @return
     */
    public static ResourceConfig createApp() {
        return new BaristaApplication();
    }

    /**
     *  When this method is called, it assumed that one of the running tests has finished. This results in decreasing
     *  the counter of active tests.
     *  If all running tests are finished and then shuts down the server.
     *
     */
    public static void stopServer(){

        BaristaLogger.print("Signal to kill Server. Current tests:"+ TestMonitor.getRuningTests());
        //todo change test check role

        TestMonitor.testFinished();

        if(! TestMonitor.hasActiveTests()) {
            BaristaLogger.print("Last Test finished. Stoping Server");
            serverInstance.shutdownNow();
        }else{
            BaristaLogger.print("Test finished. Remaining: "+TestMonitor.getRuningTests());
        }
    }


    /**
     * Get the base URI of the server
     * @return the string representation oth the server URI
     */
    public static String getBaseUri() {
        return BASE_URI;
    }

    /**
     * Set the listening port of the server. Default is 8040
     * @param port the listening port number (ex: 9090 )
     */
    public static void setPort(int port) {
        // todo possible control
        BASE_URI = "http://localhost:"+port+"/barista/";
    }

    /**
     *  Function that kills the server without checking if there are active tests running.
     */
    public static void forceKillServer() {
        BaristaLogger.print("Force Stop Server");
        serverInstance.shutdownNow();
    }
}