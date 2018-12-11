/**
 * Author: Tsiskomichelis Stelios
 * Created On: 9/12/2018
 * Project: barista-plugin
 * <p>
 * ClassName: ADBClientTest
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.test_utils;

import gr.aueb.android.barista.server.ADBClient;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class ADBClientTest {


    @Test
    public void testDeviceList(){
        ADBClient adbClient = new ADBClient();

        ArrayList<String> l = adbClient.listDevices();
        assertNotNull(l);
    }

    @Test
    public void testResizeScreen(){
        ADBClient adbClient = new ADBClient();
        assertTrue(adbClient.changeDimension(500,600));
        assertTrue(adbClient.resetDimension());
    }


}
