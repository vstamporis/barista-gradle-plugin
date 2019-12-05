/**
 * Author: Tsiskomichelis Stelios
 * Created On: 25/4/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryStatus
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BatteryStatus extends AbstractAdbCommand{

    private static final String AC_POWERED = "AC powered";
    //private boolean USB_POWERED;
    //private boolean WIRELESS_POWERED;
    //private int MAX_CHARGING_CURRENT;
    //private int MAX_CHARGING_VOLTAGE;
    //private int CHARGE_COUNTER;
    //private int STATUS;
    //private int HEALTH;
    //private boolean PRESENT;
    private static final String LEVEL = "level";
    //private int SCALE;
    //private int VOLTAGE;
    //private int TEMPERATURE;
    //private String TECHNOLOGY;

    private Hashtable<String,String> fieldMapping;




    public BatteryStatus(String sessionToken){
        super(sessionToken);
        fieldMapping = new Hashtable<>();

    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.DUMPSYS_BATTERY_STATUS;
    }

    /**
     * Current Battery Service state:
     *   (UPDATES STOPPED -- use 'reset' to restart)
     *   AC powered: true
     *   USB powered: false
     *   Wireless powered: false
     *   Max charging current: 0
     *   Max charging voltage: 0
     *   Charge counter: 10
     *   status: 2
     *   health: 2
     *   present: true
     *   level: 100
     *   scale: 100
     *   voltage: 5000
     *   temperature: 250
     *   technology: Li-ion
     * @param resultLines
     */
    @Override
    public void parseResult(Stream<String> resultLines) {
        BaristaLogger.print("Using custom output parser for fetching battery status.");
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                // consume only the lines for level and ac
                return line.matches("(\\s*level|\\s*AC powered):(.*)");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {

                String[] splitedLine = line.split(":");
                String fieldValue = splitedLine[0].trim();
                String stringValue = splitedLine[1].trim();
                fieldMapping.put(fieldValue,stringValue);

            }
        });
    }

    public Integer getLevel(){
       String stringValue = fieldMapping.get(BatteryStatus.LEVEL);
       return Integer.parseInt(stringValue);

    }

    public Boolean getChargingStatus(){
        String stringValue = fieldMapping.get(BatteryStatus.AC_POWERED);
        return Boolean.parseBoolean(stringValue);
    }

}
