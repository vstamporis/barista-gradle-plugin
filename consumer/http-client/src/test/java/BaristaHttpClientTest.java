import gr.aueb.consumer.http_client.BaristaHttpClient;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Author: Tsiskomichelis Stelios
 * Created On: 25/10/2018
 * Project: http-client
 * <p>
 * ClassName: BaristaServerClientTest
 * Role:
 * Description:
 */


public class BaristaHttpClientTest {

    @Test
    public void testRestClient(){

        BaristaHttpClient client = new BaristaHttpClient(null);
        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus();
        assertNotNull(message);
        System.out.println("REST RESPONSE: "+ message);
    }

    @Test
    public void testRestClient2(){

        BaristaHttpClient client = new BaristaHttpClient(null);
        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus2();
        assertNotNull(message);
        System.out.println("REST RESPONSE: "+ message);
    }

}
