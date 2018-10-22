/**
 * Author: Tsiskomichelis Stelios
 * Created On: 20/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: HttpServer
 * Role:
 * Description:
 */
package server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class HttpServerManager {

    private static HttpServer serverInstance;

    // Base URI the Grizzly HTTP server will listen on
    //TODO Server URL must be specified from the plugin extension configuration input
    public static final String BASE_URI = "http://localhost:8040/barista/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     */
    public static void  startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in server package
        final ResourceConfig rc = new ResourceConfig().packages("server");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        if(serverInstance !=null){
            serverInstance.shutdownNow();
        }
        serverInstance =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

    }

    public static void stopServer(){
        serverInstance.shutdownNow();
    }

}