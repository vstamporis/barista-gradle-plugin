/**
 * Author: Tsiskomichelis Stelios
 * Created On: 20/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: CommandResource
 * Role:    The REST API of the Barista Server
 * Description: Implements all the REST endpoints that Barista Serve provides.
 */
package gr.aueb.android.barista.server;


import gr.aueb.android.barista.core.executor.CommandException;
import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.mapper.CommandListMapper;
import gr.aueb.android.barista.utilities.BaristaLogger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *  Here is defined the REST API that Barista Server provides
 *
 */
@Path("/")
public class CommandResource {

    public final static String GREETING_MSG = "Hello World from Jersey Servlet Container";

    /**
     *  Request that indicates that test execution from specific emulator is completed.
     *
     */
    @GET
    @Path("kill")
    public void stopServer(){
        HttpServerManager.getInstance().stopServer();
    }

    /**
     *  Request to prepare an emulator for testing. Preparation means to grant the testing package with
     *  neccessary permissions that needs manual confirmation (manifest isnt enough) in order the barista library operates normally. The permissions that are granted
     *  is for reading the extrernal storage, where the session token resides.
     * @return
     */
    @GET
    @Path("/activate")
    public Response givePermissions(){

        EmulatorManager.getManager().setPermissions("android.permission.READ_EXTERNAL_STORAGE");
        EmulatorManager.getManager().setPermissions("android.permission.INTERNET");

        return Response.ok().build();

    }

    /**
     *  Check the connection with server. (DEBUG ONLY)
     *
     * @return
     */
    @GET
    @Path("/test")
    public Response testCnnection(){

        return Response.ok().build();

    }

    /**
     *  Request the execution of multiple commands.
     *
     *  The body of the request should contain a lsit all the commands needed to be executed.
     *  The JSON list is transfoemred into DTO obbjects and the into model objects.
     *  Using the default CommandExecutor all the commands are executed.
     *  After their verified execution an HTTP OK response is returned to the client.
     *
     * @param commands
     * @return HTTP ok (200) if execution is succsefful or HTTP error
     */
    @POST
    @Path("/executeAll")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response executeCommands(List<CommandDTO> commands){
        // Transform arrived DTOs to model objects
        List<Command> commandList = CommandListMapper.fromCommandDTOList(commands);
        // get Executor instance
        CommandExecutor executor = CommandExecutorFactory.getCommandExecutor();

        try {
            executor.executeCommands(commandList);
        } catch (CommandException e){
            // return error response
            return Response.serverError().build();

        }

        return Response.ok().build();

    }

    /**
     *
     *  Request the execution of a single command.
     *
     *  It's not used by Barista library, but it provides a way to test execution of a single
     *  command
     *
     * @param command The Command DTO Object to be executed
     * @return  A Htpp Response describbing the successful or not execution of the command.
     */
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
            return Response.serverError().build();
        }

        return Response.ok().build();

    }

}
