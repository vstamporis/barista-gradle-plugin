package gr.aueb.android.barista.core.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BatteryStatusTest {

    @Test
    public void parseResult() {

        BatteryStatus batteryStatus = new BatteryStatus(null);
        String testString = "Current Battery Service state:\n" +
                "            (UPDATES STOPPED -- use 'reset' to restart)\n" +
                "               AC powered: true\n" +
                "               USB powered: false\n" +
                "               Wireless powered: false\n" +
                "               Max charging current: 0\n" +
                "               Max charging voltage: 0\n" +
                "               Charge counter: 10\n" +
                "               status: 2\n" +
                "               health: 2\n" +
                "               present: true\n" +
                "               level: 100\n" +
                "               scale: 100\n" +
                "               voltage: 5000\n" +
                "               temperature: 250\n" +
                "               technology: Li-ion" ;

        new BufferedReader(new StringReader(testString))
                .lines().forEach(line->batteryStatus.parseResult(Stream.of(line)));

        assertEquals(new Integer(100), batteryStatus.getLevel());
        assertEquals(true, batteryStatus.getChargingStatus());
    }

}