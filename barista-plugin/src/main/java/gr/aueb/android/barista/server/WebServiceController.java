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
import gr.aueb.android.barista.emulator.adb.SizeDto;
import org.glassfish.grizzly.http.server.Request;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
     * todo DEBUG-ONLY
     * @return
     */
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){

        return GREETING_MSG;
    }

    /**
     * Returns a response that contains the default greeting message as a JSON Object
     * todo DEBUG-ONLY
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
    public Response setDimension(@QueryParam("token") String token,
                             @QueryParam("height") String height,
                             @QueryParam("width") String width){


        ADBClient adbClient = ADBClient.getInstance();
        String emulatorID = adbClient.verifyToken(token);

        if(emulatorID != null){
            int h = Integer.parseInt(height);
            int w = Integer.parseInt(width);
            adbClient.changeDimension(emulatorID,w,h);
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("/reset")
    public Response resetDimension(@QueryParam("token") String token){
        if (token != null) {
            ADBClient adbClient = ADBClient.getInstance();
            String emulatorID = adbClient.verifyToken(token);
            adbClient.resetDimension(emulatorID);
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("/geofix")
    public Response setGeoFix(@QueryParam("token") String token,
                          @QueryParam("lat") double latitude,
                          @QueryParam("longt") double longtitude){

        ADBClient client = ADBClient.getInstance();
        String emulatorID = client.verifyToken(token);

        if(emulatorID != null) {
            int emulatorPort = Integer.parseInt(emulatorID.split("-")[1]);
            HttpServerManager.executeGeoFix(latitude, longtitude, emulatorID, emulatorPort);
            return Response.ok().build();
        }

        return Response.serverError().build();

    }

    @GET
    @Path("/actualSize")
    @Produces({MediaType.APPLICATION_JSON})
    public SizeDto getOverrideSize(@QueryParam("token") String token){

        ADBClient client = ADBClient.getInstance();
        String emulatorID = client.verifyToken(token);

        if(emulatorID != null) {
            SizeDto size = client.getOverrideSize(emulatorID);
            return size;
        }

        return null;
    }

}
