package gr.aueb.android.barista.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import gr.aueb.android.barista.core.model.Command;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommandExporter {

    public static void export(List<Command> list) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date());
        String name = time + "-barista-commands.json";

        try {
            FileWriter writer = new FileWriter(name);

            writer.write("[ " + System.lineSeparator());
            writer.flush();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            for (int i = 0; i < list.size(); i++) {
                writer.write(mapper.writeValueAsString(list.get(i)));
                if (i != list.size() -1) writer.write(", ");
            }

            writer.write(System.lineSeparator() + "]");
            writer.flush();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
