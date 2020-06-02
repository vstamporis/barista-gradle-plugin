package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommandImporter {

    private String fileName, token;

    public CommandImporter(String fileName, String token) {
        this.fileName = fileName;
        this.token = token;
    }

    public List<Command> getCommandList() {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(this.fileName);

        List<Command> commands = null;

        try {
            commands = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Command.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        commands.forEach(i -> {
            try {
                System.out.println(mapper.writeValueAsString(i));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        if (commands != null) {
            commands.forEach(i -> {
                i.setSessionToken(this.token);
            });
        }

        return commands;
    }
}
