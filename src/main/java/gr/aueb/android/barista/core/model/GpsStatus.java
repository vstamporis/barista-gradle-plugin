package gr.aueb.android.barista.core.model;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

@JsonTypeName("GpsStatus")
public class GpsStatus extends AbstractAdbCommand {

    private String status;

    public GpsStatus(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.GPS_STATUS_RES;
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                // consume only the lines for level and ac
                return line.contains("gps");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {

                status = line;

            }
        });
    }

    public String getStatus() {
        return this.status != null ? "ENABLED" : "DISABLED";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
