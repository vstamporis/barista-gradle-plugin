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

import gr.aueb.android.barista.emulator.adb.ADBClient;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Logger;

public class HttpServerManager {

    private static HttpServer serverInstance;

    private final static Logger LOGGER = Logger.getLogger(HttpServerManager.class.getName());

    // Base URI the HTTP server will listen on
    //TODO Server URL must be specified from the plugin extension configuration input
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

        // create and start a new instance of grizzly http server exposing the Jersey application at BASE_URI
        serverInstance =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

    }

    /**
     *
     */
    public static void stopServer(){
        System.out.println("[BARISTA-PLUGIN]: Signal to kill Server. Current tests:"+ ADBClient.getCountRunningTests());
        ADBClient.testOnEmulatorFinished();
        if(!ADBClient.hasActiveTestsRunning()) {
            System.out.println("[BARISTA-PLUGIN]:Last Test finished. Stoping Server");
            serverInstance.shutdownNow();
            resetDevice();
        }else{
            System.out.println("[BARISTA-PLUGIN]:Test finished. Remaining: "+ADBClient.getCountRunningTests());
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

    private static void resetDevice(){
        ADBClient adb = new ADBClient();
        adb.resetDimension();
    }
}