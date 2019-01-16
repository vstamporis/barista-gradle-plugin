/**
 * Author: Tsiskomichelis Stelios
 * Created On: 20/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: ServerFactory
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server;


import gr.aueb.android.barista.emulator.adb.ADBClient;
import org.glassfish.grizzly.http.server.Request;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
// todo check jersey multithreading thread
@Path("/")
public class WebServiceController{

    public final static String GREETING_MSG = "Hello World from Jersey Servlet Container";

    @Context
    UriInfo uriInfo;

    @Context
    Request request;
    /**
     * Returns a response that contains the default greeting message as a plain text (text/plain)
     * DEBUG-ONLY
     * @return
     */
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        System.out.println("ADDR: "+request.getRemoteAddr());
        System.out.println("HST: "+request.getRemoteHost());
        System.out.println("PORT: "+request.getRemotePort());
       return GREETING_MSG;
    }

    /**
     * Returns a response that contains the default greeting message as a JSON Object
     * DEBUG-ONLY
     * @return
     */
    @GET
    @Path("status2")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello2(){
        return GREETING_MSG;
    }

    /**
     * Echoes the submited data. Assumes is plain text or JSONed String.
     * DEBUG-ONLY
     * @param originalMsg
     * @return
     */
    @POST
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String echoMessage(String originalMsg){
        return "ECHOING: "+originalMsg;
    }

    /**
     * Self-destruct service. Shuts down the server.
     * DEBUG-ONLY
     */
    @GET
    @Path("kill")
    public void stopServer(){
        HttpServerManager.stopServer();
    }


    @GET
    @Path("/setDimension")
    public void setDimension(@QueryParam("height") String height,@QueryParam("width") String width){
        ADBClient adb = new ADBClient();
        System.out.println("Resizing screen to : "+height+"x"+width);
        int h = Integer.parseInt(height);
        int w = Integer.parseInt(width);
        adb.changeDimension(w,h);
    }

    @GET
    @Path("/reset")
    public void resetDimension(){
        ADBClient adb = new ADBClient();
        adb.resetDimension();
    }

}
