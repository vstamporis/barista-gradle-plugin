package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SvcWifiStatus extends AbstractAdbCommand {

    private String status;

    public SvcWifiStatus(String sessionToken){
        super(sessionToken);

    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.SVC_WIFI_STATUS;
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                // consume only the lines for level and ac
                return line.contains("mNetworkInfo");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {

                String tmp = line.substring(line.indexOf('[')+1, line.lastIndexOf(']'));
                String[] tmpArray = tmp.trim().split(",");
                status = tmpArray[1].trim().split(":")[1].trim();
            }
        });
    }

    public String getStatus() {
        return this.status;
    }
}
