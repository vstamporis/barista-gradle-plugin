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

import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.mapper.CommandListMapper;

import gr.aueb.android.barista.utilities.BaristaLogger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// todo check jersey multithreading thread
@Path("/")
public class CommandResource {

    public final static String GREETING_MSG = "Hello World from Jersey Servlet Container";

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
    @Path("/activate")
    public Response givePermissions(){

        EmulatorManager.getManager().setPermissions("android.permission.READ_EXTERNAL_STORAGE");
        EmulatorManager.getManager().setPermissions("android.permission.INTERNET");

        return Response.ok().build();

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
