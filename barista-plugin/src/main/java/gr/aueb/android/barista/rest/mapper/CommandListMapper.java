package gr.aueb.android.barista.rest.mapper;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.rest.dto.CommandDTO;

import java.util.ArrayList;
import java.util.List;

public class CommandListMapper {

    public static List<Command> fromCommandDTOList(List<CommandDTO> commandDTOList){
        List<Command> commandList = new ArrayList<>();
        for(CommandDTO commandDTO: commandDTOList){
            commandList.add(commandDTO.toDomainObject());
        }
        return commandList;
    }

}
