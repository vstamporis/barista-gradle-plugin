package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

import java.util.stream.Stream;

public class GetOrientation extends AbstractAdbCommand {

    int orientation;

    public GetOrientation(String sessionToken){
        super(sessionToken);
    }

    public  int getOrientationValue() {
        return orientation;
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.GET_ORIENTATION;
    }

    @Override
    // parse a line like 'Row: 0 name=user_rotation, value=1'
    public void parseResult(Stream<String> resultLines) {
        resultLines.forEach(line -> {
            String optionStr = line.split(" " )[3].split("=")[1];
            this.orientation = Integer.parseInt(optionStr);
        });
    }
}
