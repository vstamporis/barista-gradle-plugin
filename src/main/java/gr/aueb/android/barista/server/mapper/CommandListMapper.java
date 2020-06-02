package gr.aueb.android.barista.server.mapper;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.server.dto.CommandDTO;

import java.util.ArrayList;
import java.util.List;

public class CommandListMapper {

    /**
     *  Function that tranforms a list of DTO objects to model obects.
     *
     * @param commandDTOList A list of DTO Command objects
     * @return A list of model Commands
     */
    public static List<Command> fromCommandDTOList(List<CommandDTO> commandDTOList){
        List<Command> commandList = new ArrayList<>();
        for(CommandDTO commandDTO: commandDTOList){
            commandList.add(commandDTO.toDomainObject());
        }
        return commandList;
    }

}
