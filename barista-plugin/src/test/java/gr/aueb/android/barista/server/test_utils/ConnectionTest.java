/**
 * Author: Tsiskomichelis Stelios
 * Created On: 21/10/2018
 * Project: butler-plugin
 * <p>
 * ClassName: testCOnnection
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.test_utils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import gr.aueb.android.barista.server.WebServiceController;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ConnectionTest extends JerseyTest {


    @Override
    protected Application configure() {

        return new ResourceConfig(WebServiceController.class);

    }

    @Test
    public void testConnection() throws Exception {
      Response response =  target("status").request().get();
      assertNotNull(response);
      assertEquals(200,response.getStatus());

    }

    @Test
    public void testServiceFunctionality(){
        String response =  target("status").request().get(String.class);
        assertNotNull(response);
        assertEquals(WebServiceController.GREETING_MSG,response);
    }
}
