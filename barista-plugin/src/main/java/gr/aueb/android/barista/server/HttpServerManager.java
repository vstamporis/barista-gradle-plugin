/**
 * Author: Tsiskomichelis Stelios
 * Created On: 20/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: HttpServer
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server;

import gr.aueb.android.barista.emulator.EmulatorException;
import gr.aueb.android.barista.emulator.TestMonitor;
import gr.aueb.android.barista.emulator.adb.ADBClient;
import gr.aueb.android.barista.emulator.telnet.ConnectionManager;
import gr.aueb.android.barista.emulator.telnet.TelnetConnection;
import gr.aueb.android.barista.emulator.telnet.command.GeoFixCommand;
import gr.aueb.android.barista.utilities.BaristaLoger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.File;
import java.net.URI;
import java.util.logging.Logger;

public class HttpServerManager {

    private static HttpServer serverInstance;

    // Base URI the HTTP server will listen on
    // TODO Server URL must be specified from the plugin extension configuration input
    // todo better coding style
    private static String BASE_URI = "http://localhost:8040/barista/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     */
    public static void  startServer() {

        // create a resource config that scans for JAX-RS resources and providers in server package
        final ResourceConfig rc = new ResourceConfig().packages("gr/aueb/android/barista/server");

        //todo propably unreached code
        if(serverInstance !=null){
            // if for any reason server instance is running (not null) then shut down
            serverInstance.shutdownNow();
        }
        ADBClient client = ADBClient.getInstance();
        // initialize TestMonitor
        TestMonitor.setRunningTests(client.listDevices().size());
        // create and start a new instance of grizzly http server exposing the Jersey application at BASE_URI
        serverInstance =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);


    }

    /**
     *
     */
    public static void stopServer(){

        ADBClient adbClient = ADBClient.getInstance();

        BaristaLoger.print("Signal to kill Server. Current tests:"+ TestMonitor.getRuningTests());
        //todo change test check role


        TestMonitor.testFinished();

        if(! TestMonitor.hasActiveTests()) {
            BaristaLoger.print("Last Test finished. Stoping Server");
            serverInstance.shutdownNow();
            //resetDevice();
        }else{
            BaristaLoger.print("Test finished. Remaining: "+TestMonitor.getRuningTests());
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

    public static boolean executeGeoFix(double lat, double longt, String emulatorID, int emulatorPort){
        BaristaLoger.print("Executing geofix on "+emulatorID+" port: "+emulatorPort);
        String homeDirectory = System.getProperty("user.home");
        if (homeDirectory == null){
            BaristaLoger.print("Please set the home variable");
            return false;
        }

        ConnectionManager connectionManager = ConnectionManager.createInstance(homeDirectory + File.separatorChar + ".emulator_console_auth_token");
        // FIXME: By Default connects to a single emulator. Must find a way to identify the emulator that issues a request
        TelnetConnection telnetConnection  = null;
        try {
            telnetConnection = connectionManager.connect(emulatorID, "localhost", emulatorPort);
        } catch (EmulatorException e) {
            e.printStackTrace();
        }


       return  telnetConnection.command(new GeoFixCommand(lat, longt));
    }
}