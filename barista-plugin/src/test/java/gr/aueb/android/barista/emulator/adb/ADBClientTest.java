/**
 * Author: Tsiskomichelis Stelios
 * Created On: 9/12/2018
 * Project: barista-plugin
 * <p>
 * ClassName: ADBClientTest
 * Role:
 * Description:
 */
package gr.aueb.android.barista.emulator.adb;

import gr.aueb.android.barista.emulator.adb.ADBClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class ADBClientTest {

    private ADBClient adbClient = ADBClient.getInstance();

    @Test
    public void testDeviceList(){
        ArrayList<String> l = adbClient.getConnectedDevices();
        assertNotNull(l);
    }

    @Test
    public void testResizeScreen(){
        ADBClient adbClient = ADBClient.getInstance();
        String token = adbClient.getTokenMap().keySet().iterator().next();
        String device = adbClient.verifyToken(token);
        assertTrue(adbClient.changeDimension(device,500,600));
        assertTrue(adbClient.resetDimension(device));
    }


    @Test
    public void testPushFile(){

        ArrayList<String> l = adbClient.listDevices();
        System.out.println("Pushing at emulator: "+l.get(0));
        boolean result = adbClient.pushFile(l.get(0),"key.txt","sdcard");
        assertTrue(result);
    }


}
