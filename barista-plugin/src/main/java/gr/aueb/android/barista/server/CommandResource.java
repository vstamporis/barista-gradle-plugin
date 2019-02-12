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


import gr.aueb.android.barista.core.executor.CommandException;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.emulator.adb.ADBClient;

import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import gr.aueb.android.barista.rest.mapper.CommandListMapper;

import gr.aueb.android.barista.utilities.BaristaLogger;
import org.glassfish.grizzly.http.server.Request;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

// todo check jersey multithreading thread
@Path("/")
public class CommandResource {

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
//    @GET
//    @Path("status2")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Deprecated
//    public String sayHello2(){
//        return GREETING_MSG;
//    }
//
//    /**
//     * Echoes the submited data. Assumes is plain text or JSONed String.
//     * DEBUG-ONLY
//     * @param originalMsg
//     * @return
//     */
//    @POST
//    @Path("echo")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
//    @Deprecated
//    public String echoMessage(String originalMsg){
//        return "ECHOING: "+originalMsg;
//    }

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
    @Deprecated
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
    @Deprecated
    public Response resetDimension(@QueryParam("token") String token){
        if (token != null) {
            ADBClient adbClient = ADBClient.getInstance();
            String emulatorID = adbClient.verifyToken(token);
            adbClient.resetDimension(emulatorID);
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

//    @GET
//    @Path("/geofix")
//    public Response setGeoFix(@QueryParam("token") String token,
//                          @QueryParam("lat") double latitude,
//                          @QueryParam("longt") double longtitude){
//
//        ADBClient client = ADBClient.getInstance();
//        String emulatorID = client.verifyToken(token);
//
//        if(emulatorID != null) {
//            int emulatorPort = Integer.parseInt(emulatorID.split("-")[1]);
//            HttpServerManager.executeGeoFix(latitude, longtitude, emulatorID, emulatorPort);
//            return Response.ok().build();
//        }
//
//        return Response.serverError().build();
//
//    }

    @GET
    @Path("/actualSize")
    @Produces({MediaType.APPLICATION_JSON})
    public WmSizeDTO getOverrideSize(@QueryParam("token") String token){

        EmulatorManager client = EmulatorManager.getManager();
        String emulatorID = client.verifyToken(token);

        if(emulatorID != null) {
            WmSizeDTO size = client.getOverrideSize(emulatorID);
            return size;
        }

        return null;
    }

    @POST
    @Path("/executeAll")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response executeCommands(List<CommandDTO> commands){

        List<Command> commandList = CommandListMapper.fromCommandDTOList(commands);
        CommandExecutor executor = CommandExecutorFactory.getCommandExecutor();

        try {
            executor.executeCommands(commandList);
        } catch (CommandException e){
            // return error response
        }

        return Response.ok().build();

    }

    @POST
    @Path("/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response executeCommand(CommandDTO command){

        CommandExecutor executor = CommandExecutorFactory.getCommandExecutor();
        Command cmd = command.toDomainObject();
        BaristaLogger.print("Command arrived: "+cmd.getCommandString());

        try {
            executor.executeCommand(cmd);
        } catch (CommandException e){
            e.printStackTrace();
        }

        return Response.ok().build();

    }


}
