package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
        List<String> statusLines = resultLines.filter(
                line -> line.contains("EVENT_TOGGLE_WIFI_ON") ||
                        line.contains("EVENT_TOGGLE_WIFI_OFF") ||
                        line.contains("mNetworkInfo"))
            .collect(Collectors.toList());

        if (statusLines.isEmpty()){
            return;
        }

        statusLines.stream().filter(line -> line.contains("mNetworkInfo"))
                .forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {
                String tmp = line.substring(line.indexOf('[')+1, line.lastIndexOf(']'));
                String[] tmpArray = tmp.trim().split(",");
                status = tmpArray[1].trim().split(":")[1].trim();
            }
        });

        if (status == null){
            String lastStatus = statusLines.get(statusLines.size() - 1);
            if (lastStatus.contains("EVENT_TOGGLE_WIFI_ON")){
                status = "CONNECTED/CONNECTED";
            }
            if (lastStatus.contains("EVENT_TOGGLE_WIFI_OFF")){
                status = "DISCONNECTED/DISCONNECTED";
            }
        }
    }

    public String getStatus() {
        return this.status;
    }
}
