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


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class WebServiceController{

    public final static String GREETING_MSG = "Hello World from Jersey Servlet Container";
    @Context
    UriInfo uriInfo;

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        return GREETING_MSG;
    }

    @GET
    @Path("status2")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello2(){
        return GREETING_MSG;
    }

    @POST
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String echoMessage(String originalMsg){
        return "ECHOING: "+originalMsg;
    }
    /**
     * Self-destruct service
     */
    @GET
    @Path("kill")
    public void stopServer(){
        HttpServerManager.stopServer();
    }


}
