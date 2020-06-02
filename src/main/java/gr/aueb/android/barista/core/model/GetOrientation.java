package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

import java.util.stream.Stream;

@JsonTypeName("GetOrientation")
public class GetOrientation extends AbstractAdbCommand {

    int orientation;

    public GetOrientation(String sessionToken){
        super(sessionToken);
    }

    public  int getOrientationValue() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.GET_ORIENTATION;
    }

    /**
     *  Parses the result of the  GetOrientation command and sets the result to the
     *  orientation filed of the object. The possible values that can be assigned are the integers 0-3
     *  that represents the four different orientation states.
     *
     * @param resultLines The lines returned after the execution of the GetOrientation Command
     */
    @Override
    public void parseResult(Stream<String> resultLines) {
        // parse a line like 'Row: 0 name=user_rotation, value=1'
        resultLines.forEach(line -> {
            String optionStr = line.split(" " )[3].split("=")[1];
            this.orientation = Integer.parseInt(optionStr);
        });
    }
}
